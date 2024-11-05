public class Optimization3 {
    public static void main(String[] args) {
        TransportationProblem t = new TransportationProblem();

        double[] S1 = {140, 180, 160};
        double[] D1 = {60, 70, 120, 130, 100};
        double[][] C1 = {
                {2, 3, 4, 2, 4},
                {8, 4, 1, 4, 1},
                {9, 7, 3, 7, 2}};
        // Northwest: 1380
        // Vogel: 1260
        // Russel: 1420
        t.initialBasicVariable(S1, D1, C1);

        double[] S2 = {160, 140, 170};
        double[] D2 = {120, 50, 190, 110};
        double[][] C2 = {{7, 8, 1, 2}, {4, 5, 9, 8}, {9, 2, 3, 6}};
        // Northwest: 3220
        // Vogel: 1330
        // Russel: 1530
        t.initialBasicVariable(S2, D2, C2);

        double[] S3 = {7, 9, 18};
        double[] D3 = {5, 8, 7, 14};
        double[][] C3 = {{19, 30, 50, 10}, {70, 30, 40, 60}, {40, 8, 70, 20}};
        // Northwest: 1015
        // Vogel: 779
        // Russel: 807
        t.initialBasicVariable(S3, D3, C3);
    }
}
