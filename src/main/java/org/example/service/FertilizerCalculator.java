package org.example.service;

//import org.apache.commons.math3.linear.*;

public class FertilizerCalculator {

//    public static void main(String[] args) {
//        // 1. Целевые значения (сколько нужно азота, фосфора и калия)
//        double nTarget = 100; // Количество азота (N)
//        double pTarget = 50;  // Количество фосфора (P)
//        double kTarget = 30;  // Количество калия (K)
//
//        // 2. Матрица состава удобрений (в процентах содержания)
//        // Каждый элемент - это содержание N, P и K в 1 кг удобрения
//        double[][] fertilizerComposition = {
//                {20, 10, 5}, // Удобрение 1: 20% N, 10% P, 5% K
//                {15, 5, 10}, // Удобрение 2: 15% N, 5% P, 10% K
//                {10, 20, 5}  // Удобрение 3: 10% N, 20% P, 5% K
//        };
//
//        // 3. Целевой вектор (необходимые количества N, P и K)
//        double[] targets = {nTarget, pTarget, kTarget};
//
//        try {
//            // 4. Создание матрицы состава удобрений и вектора целей
//            RealMatrix coefficients = new Array2DRowRealMatrix(fertilizerComposition);
//            RealVector constants = new ArrayRealVector(targets);
//
//            // 5. Решение системы уравнений
//            DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
//            RealVector solution = solver.solve(constants);
//
//            // 6. Вывод результата
//            System.out.println("Необходимые количества удобрений:");
//            for (int i = 0; i < solution.getDimension(); i++) {
//                System.out.printf("Удобрение %d: %.2f кг%n", i + 1, solution.getEntry(i));
//            }
//        } catch (SingularMatrixException e) {
//            // Исключение в случае невозможности решения
//            System.out.println("Решение невозможно: система уравнений не имеет единственного решения.");
//        }
//    }


}
