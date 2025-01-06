package org.example.service;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.List;

public class FertilizerCalculator {

    public static void main(String[] args) {

        // Целевые значения (сколько нужно азота, фосфора и калия)
        double nTarget = 13; // Количество азота (N)
        double pTarget = 40;  // Количество фосфора (P)
        double kTarget = 13;  // Количество калия (K)

        // Состав удобрений (в процентах содержания, делим на 100)
        double[][] fertilizerComposition = {
                {0.0 / 100, 52.0 / 100, 34.0 / 100}, // Удобрение 1
                {15.5 / 100, 0.0 / 100, 0.0 / 100}, // Удобрение 2
                {0.0 / 100, 0.0 / 100, 50.0 / 100},  // Удобрение 3
                {0.0 / 100, 64.0 / 100, 0.0 / 100}  // Удобрение 4
        };

        // Создание целевой функции: минимизация x1 + x2 + x3
        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(new double[]{1, 1, 1, 1}, 0);

        // Создание ограничений
        List<LinearConstraint> constraints = new ArrayList<>();
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][0], fertilizerComposition[1][0], fertilizerComposition[2][0], fertilizerComposition[3][0]}, // N-состав
                Relationship.EQ,
                nTarget
        ));
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][1], fertilizerComposition[1][1], fertilizerComposition[2][1], fertilizerComposition[3][1]}, // P-состав
                Relationship.EQ,
                pTarget
        ));
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][2], fertilizerComposition[1][2], fertilizerComposition[2][2], fertilizerComposition[3][2]}, // K-состав
                Relationship.EQ,
                kTarget
        ));

        // Добавляем ограничения на не отрицательность x1, x2, x3
        constraints.add(new LinearConstraint(new double[]{1, 0, 0, 0}, Relationship.GEQ, 0)); // x1 >= 0
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
            double[] calculatedTargets = new double[3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < quantities.length; j++) {
                    calculatedTargets[i] += quantities[j] * fertilizerComposition[j][i];
                }
            }

            String[] elements = {"N", "P", "K"};
            double[] targets = {nTarget, pTarget, kTarget};
            for (int i = 0; i < 3; i++) {
                double error = Math.abs(calculatedTargets[i] - targets[i]);
                System.out.printf("%s: %.2f (погрешность: %.2f)%n", elements[i], calculatedTargets[i], error);
            }
        } else {
            System.out.println("Решение не найдено.");
        }

    }
}

