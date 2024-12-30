package org.example.service;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.List;

public class FertilizerCalculator {

    public static void main(String[] args) {

        // Целевые значения (сколько нужно азота, фосфора и калия)
        double nTarget = 100; // Количество азота (N)
        double pTarget = 50;  // Количество фосфора (P)
        double kTarget = 30;  // Количество калия (K)

        // Состав удобрений (в процентах содержания, делим на 100)
        double[][] fertilizerComposition = {
                {20.0 / 100, 10.0 / 100, 5.0 / 100}, // Удобрение 1
                {15.0 / 100, 5.0 / 100, 10.0 / 100}, // Удобрение 2
                {10.0 / 100, 20.0 / 100, 5.0 / 100}  // Удобрение 3
        };

        // Создание целевой функции: минимизация x1 + x2 + x3
        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(new double[]{1, 1, 1}, 0);

        // Создание ограничений
        List<LinearConstraint> constraints = new ArrayList<>();
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][0], fertilizerComposition[1][0], fertilizerComposition[2][0]}, // N-состав
                Relationship.EQ,
                nTarget
        ));
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][1], fertilizerComposition[1][1], fertilizerComposition[2][1]}, // P-состав
                Relationship.EQ,
                pTarget
        ));
        constraints.add(new LinearConstraint(
                new double[]{fertilizerComposition[0][2], fertilizerComposition[1][2], fertilizerComposition[2][2]}, // K-состав
                Relationship.EQ,
                kTarget
        ));

        // Добавляем ограничения на неотрицательность x1, x2, x3
        constraints.add(new LinearConstraint(new double[]{1, 0, 0}, Relationship.GEQ, 0)); // x1 >= 0
        constraints.add(new LinearConstraint(new double[]{0, 1, 0}, Relationship.GEQ, 0)); // x2 >= 0
        constraints.add(new LinearConstraint(new double[]{0, 0, 1}, Relationship.GEQ, 0)); // x3 >= 0

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

            // Рассчитать итоговое содержание N, P и K
            double calculatedN = 0, calculatedP = 0, calculatedK = 0;
            for (int i = 0; i < quantities.length; i++) {
                calculatedN += fertilizerComposition[i][0] * quantities[i];
                calculatedP += fertilizerComposition[i][1] * quantities[i];
                calculatedK += fertilizerComposition[i][2] * quantities[i];
            }

            // Вывод итогового содержания и погрешности
            double nError = Math.abs(calculatedN - nTarget);
            double pError = Math.abs(calculatedP - pTarget);
            double kError = Math.abs(calculatedK - kTarget);

            System.out.println("\nРассчитанное содержание:");
            System.out.printf("Азот (N): %.2f (погрешность: %.2f)%n", calculatedN, nError);
            System.out.printf("Фосфор (P): %.2f (погрешность: %.2f)%n", calculatedP, pError);
            System.out.printf("Калий (K): %.2f (погрешность: %.2f)%n", calculatedK, kError);
        } else {
            System.out.println("Решение не найдено.");
        }


//        // Целевые значения (сколько нужно азота, фосфора и калия)
//        double nTarget = 100; // Количество азота (N)
//        double pTarget = 50;  // Количество фосфора (P)
//        double kTarget = 30;  // Количество калия (K)
//
//        // Погрешность 3%
//        double tolerance = 0.03;
//
//        // Состав удобрений (в процентах содержания, делим на 100)
//        double[][] fertilizerComposition = {
//                {20.0 / 100, 10.0 / 100, 5.0 / 100}, // Удобрение 1
//                {15.0 / 100, 5.0 / 100, 10.0 / 100}, // Удобрение 2
//                {10.0 / 100, 20.0 / 100, 5.0 / 100}  // Удобрение 3
//        };
//
//        // Создание целевой функции: минимизация x1 + x2 + x3
//        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(new double[]{1, 1, 1}, 0);
//
//        // Создание ограничений с учетом погрешности
//        List<LinearConstraint> constraints = new ArrayList<>();
//
//        // Ограничения для азота (N)
//        constraints.add(new LinearConstraint(
//                new double[]{fertilizerComposition[0][0], fertilizerComposition[1][0], fertilizerComposition[2][0]}, // N-состав
//                Relationship.GEQ,
//                (1 - tolerance) * nTarget
//        ));
//        constraints.add(new LinearConstraint(
//                new double[]{fertilizerComposition[0][0], fertilizerComposition[1][0], fertilizerComposition[2][0]}, // N-состав
//                Relationship.LEQ,
//                (1 + tolerance) * nTarget
//        ));
//
//        // Ограничения для фосфора (P)
//        constraints.add(new LinearConstraint(
//                new double[]{fertilizerComposition[0][1], fertilizerComposition[1][1], fertilizerComposition[2][1]}, // P-состав
//                Relationship.GEQ,
//                (1 - tolerance) * pTarget
//        ));
//        constraints.add(new LinearConstraint(
//                new double[]{fertilizerComposition[0][1], fertilizerComposition[1][1], fertilizerComposition[2][1]}, // P-состав
//                Relationship.LEQ,
//                (1 + tolerance) * pTarget
//        ));
//
//        // Ограничения для калия (K)
//        constraints.add(new LinearConstraint(
//                new double[]{fertilizerComposition[0][2], fertilizerComposition[1][2], fertilizerComposition[2][2]}, // K-состав
//                Relationship.GEQ,
//                (1 - tolerance) * kTarget
//        ));
//        constraints.add(new LinearConstraint(
//                new double[]{fertilizerComposition[0][2], fertilizerComposition[1][2], fertilizerComposition[2][2]}, // K-состав
//                Relationship.LEQ,
//                (1 + tolerance) * kTarget
//        ));
//
//        // Добавляем ограничения на не отрицательность x1, x2, x3
//        constraints.add(new LinearConstraint(new double[]{1, 0, 0}, Relationship.GEQ, 0)); // x1 >= 0
//        constraints.add(new LinearConstraint(new double[]{0, 1, 0}, Relationship.GEQ, 0)); // x2 >= 0
//        constraints.add(new LinearConstraint(new double[]{0, 0, 1}, Relationship.GEQ, 0)); // x3 >= 0
//
//        // Преобразуем список ограничений в LinearConstraintSet
//        LinearConstraintSet constraintSet = new LinearConstraintSet(constraints);
//
//        // Решение задачи
//        SimplexSolver solver = new SimplexSolver();
//        PointValuePair solution = solver.optimize(
//                objectiveFunction,
//                constraintSet,
//                GoalType.MINIMIZE,
//                new NonNegativeConstraint(true) // Гарантируем, что x >= 0
//        );
//
//        // Вывод результата
//        if (solution != null) {
//            double[] quantities = solution.getPoint();
//            System.out.println("Необходимые количества удобрений:");
//            for (int i = 0; i < quantities.length; i++) {
//                System.out.printf("Удобрение %d: %.2f кг%n", i + 1, quantities[i]);
//            }
//        } else {
//            System.out.println("Решение не найдено.");
//        }
    }
}

