package org.example.service;

import jakarta.transaction.Transactional;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.example.model.dto.CalculatedOfFertilizerDTO;
import org.example.model.entity.CalculatedOfFertilizer;
import org.example.model.entity.Fertilizer;
import org.example.model.entity.PeriodVegetation;
import org.example.repository.CalculatedOfFertilizerRepository;
import org.example.service.interfeces.CalculatedOfFertilizerService;
import org.example.service.mapping.CalculatedOfFertilizerMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class CalculatedOfFertilizerServiceImpl implements CalculatedOfFertilizerService {

    private final CalculatedOfFertilizerRepository repository;
    private final CalculatedOfFertilizerMapper mapper;

    public CalculatedOfFertilizerServiceImpl(CalculatedOfFertilizerRepository repository, CalculatedOfFertilizerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CalculatedOfFertilizerDTO saveCalculatedOfFertilizer(CalculatedOfFertilizerDTO calculatedOfFertilizerDTO) {
        CalculatedOfFertilizer calculatedOfFertilizer = mapper.mapDtoToEntity(fertilizerCalculator(calculatedOfFertilizerDTO));

        // Todo написать

        return mapper.mapEntityToDto(repository.save(calculatedOfFertilizer));
    }

    @Override
    public CalculatedOfFertilizerDTO getCalculatedOfFertilizerByName(String calculatedOfFertilizerName) {
        return mapper.mapEntityToDto(repository.findCalculatedOfFertilizerByCalculatedOfFertilizerName(calculatedOfFertilizerName));
    }





    private CalculatedOfFertilizerDTO fertilizerCalculator(CalculatedOfFertilizerDTO calculatedOfFertilizerDTO) {

        return null;
    }

    private CalculatedOfFertilizer calculator (CalculatedOfFertilizer calculatedOfFertilizer){
        PeriodVegetation periodVegetation = calculatedOfFertilizer.getPeriodVegetation();
        Set<Fertilizer> fertilizerSet = calculatedOfFertilizer.getFertilizer();

        // Целевые значения (сколько нужно кальция, азота, фосфора и калия)
        double caTarget = periodVegetation.getCa(); // Количество азота 22 (Ca)
        double nTarget = periodVegetation.getN(); // Количество азота 13 (N)
        double pTarget = periodVegetation.getP();  // Количество фосфора 40 (P)
        double kTarget = periodVegetation.getK();  // Количество калия 13 (K)


        // Состав удобрений (в процентах содержания, делим на 100)
//        double[][] fertilizerComposition = {
//                {0.0 / 100, 0.0 / 100, 52.0 / 100, 34.0 / 100}, // Удобрение 1
//                {26.0 / 100, 15.5 / 100, 0.0 / 100, 0.0 / 100}, // Удобрение 2
//                {0.0 / 100, 0.0 / 100, 0.0 / 100, 50.0 / 100},  // Удобрение 3
//                {0.0 / 100, 0.0 / 100, 64.0 / 100, 0.0 / 100}  // Удобрение 4
//        };

        // Создаём двумерный массив с фиксированным размером 4 и инициализируем его пустым
        double[][] fertilizerComposition = new double[4][4];

        // Преобразуем объекты из fertilizerSet в строки массива
        int index = 0;
        for (Fertilizer fertilizer : fertilizerSet) {
            if (index >= 4) break;
            fertilizerComposition[index][0] = fertilizer.getCa() / 100;
            fertilizerComposition[index][1] = fertilizer.getN() / 100;
            fertilizerComposition[index][2] = fertilizer.getP() / 100;
            fertilizerComposition[index][3] = fertilizer.getK() / 100;
            index++;
        }



        // Создание целевой функции: минимизация x1 + x2 + x3 + 4x
        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(new double[]{1, 1, 1, 1}, 0);

        // Создание ограничений
        List<LinearConstraint> constraints = new ArrayList<>();
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][0], fertilizerComposition[1][0], fertilizerComposition[2][0], fertilizerComposition[3][0]}, // CaO-состав
                Relationship.LEQ, // меньше или равно (0.2xi + 0.15xzy + 0.1xz <= 100)
                caTarget
        ));
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][1], fertilizerComposition[1][1], fertilizerComposition[2][1], fertilizerComposition[3][1]}, // N-состав
                Relationship.GEQ, // Relationship.EQ равенство (0.2xi + 0.15xzy + 0.1xz = 100)
                nTarget
        ));
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][2], fertilizerComposition[1][2], fertilizerComposition[2][2], fertilizerComposition[3][2]}, // P-состав
                Relationship.GEQ,
                pTarget
        ));
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][3], fertilizerComposition[1][3], fertilizerComposition[2][3], fertilizerComposition[3][3]}, // K-состав
                Relationship.GEQ,
                kTarget
        ));

        // Добавляем ограничения на не отрицательность x1, x2, x3, x4
        constraints.add(new LinearConstraint(new double[]{1, 0, 0, 0}, Relationship.GEQ, 0)); // x1 >= 0 (больше или равно - Relationship.GEQ)
        constraints.add(new LinearConstraint(new double[]{0, 1, 0, 0}, Relationship.GEQ, 0)); // x2 >= 0
        constraints.add(new LinearConstraint(new double[]{0, 0, 1, 0}, Relationship.GEQ, 0)); // x3 >= 0
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 1}, Relationship.GEQ, 0)); // x4 >= 0

        // Преобразуем список ограничений в LinearConstraintSet
        LinearConstraintSet constraintSet = new LinearConstraintSet(constraints);

        // Решение задачи
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(
                objectiveFunction,
                constraintSet,
                GoalType.MINIMIZE,
                new NonNegativeConstraint(true) // Гарантируем, что x >= 0
        );

        if (solution == null) {
            System.out.println("Решение не найдено.");
            return null;
        }

        // Заполняем результат
        double[] quantities = solution.getPoint();
        double[] calculatedTargets = new double[4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < quantities.length; j++) {
                calculatedTargets[i] += quantities[j] * fertilizerComposition[j][i];
            }
        }

        // Рассчитываем погрешность
        double[] targets = {caTarget, nTarget, pTarget, kTarget};
        List<Double> errors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            double error = (targets[i] != 0) ? Math.abs(calculatedTargets[i] - targets[i]) / targets[i] * 100 : 0;
            errors.add(error);
        }

        // Создаём объект результата
        CalculatedOfFertilizer calculatedOfFertilizer = new CalculatedOfFertilizer();
        calculatedOfFertilizer.setCalculatedName("Рассчёт удобрений");
        calculatedOfFertilizer.setCalculatedDescription("Автоматический расчёт удобрений");
        calculatedOfFertilizer.setFertilizer(fertilizerSet);
        calculatedOfFertilizer.setPeriodVegetation(periodVegetation);
        calculatedOfFertilizer.setWeight(Arrays.asList(quantities[0], quantities[1], quantities[2], quantities[3]));
        calculatedOfFertilizer.setCalculationError(errors);

        // Сохраняем в базу
        return calculatedOfFertilizer;
    }
}

        /*
        // Вывод результата
        if (solution != null) {
            double[] quantities = solution.getPoint();

            System.out.println("Необходимые количества удобрений:");
            for (int i = 0; i < quantities.length; i++) {
                System.out.printf("Удобрение %d: %.2f кг%n", i + 1, quantities[i]);
            }

            System.out.println("\nРассчитанное содержание:");
            double[] calculatedTargets = new double[4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < quantities.length; j++) {
                    calculatedTargets[i] += quantities[j] * fertilizerComposition[j][i];
                }
            }

            String[] elements = {"Ca","N", "P", "K"};
            double[] targets = {caTarget, nTarget, pTarget, kTarget};
            for (int i = 0; i < 4; i++) {
                double error = Math.abs(calculatedTargets[i] - targets[i]);
                double errorPercentage = (error != 0) ? (error / targets[i]) * 100 : 0; // Если цель равна 0, погрешность 0%
                System.out.printf("%s: %.2f (погрешность: %.2f %%)%n", elements[i], calculatedTargets[i], errorPercentage);
            }
        } else {
            System.out.println("Решение не найдено.");
        }

        return null;
        */


        /*
        Необходимые количества удобрений:
        Удобрение 1: 38,24 кг
        Удобрение 2: 83,87 кг
        Удобрение 3: 0,00 кг
        Удобрение 4: 31,43 кг

        Рассчитанное содержание:
        Ca: 21,81 (погрешность: 0,88 %)
        N: 13,00 (погрешность: 0,00 %)
        P: 40,00 (погрешность: 0,00 %)
        K: 13,00 (погрешность: 0,00 %)
         */
