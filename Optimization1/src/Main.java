public class Main {
    public static void main(String[] args) {
        Simplex simplex = new Simplex();
        double eps = 0.001;
        // Test 1
        // Answer: F*(0, 8, 20) = 400
        System.out.println("Test 1:");
        double[] C1 = {9, 10, 16};
        double[][] A1 = {
                {18, 15, 12},
                {6, 4, 8},
                {5, 3, 3}
        };
        double[] b1 = {360, 192, 180};
        simplex.maximize(C1, A1, b1, eps);

        // Test 2
        // Answer F*(3, 12) = 33
        System.out.println("\nTest 2:");
        double[] C2 = {3, 2};
        double[][] A2 = {
                {2, 1},
                {2, 3},
                {3, 1}
        };
        double[] b2 = {18, 42, 24};
        simplex.maximize(C2, A2, b2, eps);


        // Test 3
        // Answer: F*(2.17, 1.22, 1.51) = 18.66
        System.out.println("\nTest 3:");
        double[] C3 = {3, 5, 4};
        double[][] A3 = {
                {2, 3, 0},
                {0, 2, 5},
                {3, 2, 4}
        };
        double[] b3 = {8, 10, 15};
            simplex.maximize(C3, A3, b3, eps);

        // Test 4
        // Answer: F*(138.2, 23.6) = 8326
        System.out.println("\nTest 4:");
        double[] C4 = {50, 60};
        double[][] A4 = {
                {2, 1},
                {3, 4},
                {4, 7}
        };
        double[] b4 = {300, 509, 812};
        simplex.maximize(C4, A4, b4, eps);


        // Test 5
        // Answer: F* (9.13, 4.38, 0) = 122.63
        System.out.println("\nTest 5:");
        double[] C5 = {12, 3, 1};
        double[][] A5 = {
                {10, 2, 1},
                {7, 3, 2},
                {2, 4, 1}
        };
        double[] b5 = {100, 77, 80};
        simplex.maximize(C5, A5, b5, eps);
    }
}
