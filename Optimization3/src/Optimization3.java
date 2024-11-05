public class Optimization3 {
    public static void main(String[] args) {
        TransportationProblem t = new TransportationProblem();
        double[] S = {160, 140, 170};
        double[] D = {120, 50, 190, 110};
        double[][] C = {{7, 8, 1, 2}, {4, 5, 9, 8}, {9, 2, 3, 6}};
        t.initialBasicVariable(S, D, C);
    }
}
