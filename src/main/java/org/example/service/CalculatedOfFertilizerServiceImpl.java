package org.example.service;

import jakarta.transaction.Transactional;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.example.model.dto.CalculatedOfFertilizerDTO;
import org.example.model.entity.CalculatedOfFertilizer;
import org.example.repository.CalculatedOfFertilizerRepository;
import org.example.service.interfeces.CalculatedOfFertilizerService;
import org.example.service.mapping.CalculatedOfFertilizerMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return mapper.mapEntityToDto(repository.save(calculatedOfFertilizer));
    }

    @Override
    public CalculatedOfFertilizerDTO getCalculatedOfFertilizerByName(String calculatedOfFertilizerName) {
        return mapper.mapEntityToDto(repository.findCalculatedOfFertilizerByCalculatedOfFertilizerName(calculatedOfFertilizerName));
    }

    private CalculatedOfFertilizerDTO fertilizerCalculator(CalculatedOfFertilizerDTO calculatedOfFertilizerDTO) {

        // Целевые значения (сколько нужно кальция, азота, фосфора и калия)
        double caoTarget = 22; // Количество азота (CaO)
        double nTarget = 13; // Количество азота (N)
        double pTarget = 40;  // Количество фосфора (P)
        double kTarget = 13;  // Количество калия (K)

        // Состав удобрений (в процентах содержания, делим на 100)
        double[][] fertilizerComposition = {
                {0.0 / 100, 0.0 / 100, 52.0 / 100, 34.0 / 100}, // Удобрение 1
                {26.0 / 100, 15.5 / 100, 0.0 / 100, 0.0 / 100}, // Удобрение 2
                {0.0 / 100, 0.0 / 100, 0.0 / 100, 50.0 / 100},  // Удобрение 3
                {0.0 / 100, 0.0 / 100, 64.0 / 100, 0.0 / 100}  // Удобрение 4
        };

        // Создание целевой функции: минимизация x1 + x2 + x3 + 4x
        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(new double[]{1, 1, 1, 1}, 0);

        // Создание ограничений
        List<LinearConstraint> constraints = new ArrayList<>();
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][0], fertilizerComposition[1][0], fertilizerComposition[2][0], fertilizerComposition[3][0]}, // CaO-состав
                Relationship.LEQ, // меньше или равно (0.2xi + 0.15xzy + 0.1xz <= 100)
                caoTarget
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

            String[] elements = {"CaO","N", "P", "K"};
            double[] targets = {caoTarget, nTarget, pTarget, kTarget};
            for (int i = 0; i < 4; i++) {
                double error = Math.abs(calculatedTargets[i] - targets[i]);
                double errorPercentage = (error != 0) ? (error / targets[i]) * 100 : 0; // Если цель равна 0, погрешность 0%
                System.out.printf("%s: %.2f (погрешность: %.2f %%)%n", elements[i], calculatedTargets[i], errorPercentage);
            }
        } else {
            System.out.println("Решение не найдено.");
        }

        return null;

    }
}
