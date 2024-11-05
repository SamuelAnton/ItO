public class Optimization3 {
    public static void main(String[] args) {
        TransportationProblem t = new TransportationProblem();
        double[] S = {140, 180, 160};
        double[] D = {60, 70, 120, 130, 100};
        double[][] C = {{1, 3, 4, 2, 4}, {8, 4, 1, 4, 1}, {3, 7, 3, 7, 2}};
        t.initialBasicVariable(S, D, C);
    }
}
