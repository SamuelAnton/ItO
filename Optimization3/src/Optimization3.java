public class Optimization3 {
    public static void main(String[] args) {
        TransportationProblem t = new TransportationProblem();
        double[] S = {7, 9, 18};
        double[] D = {5, 8, 7, 14};
        double[][] C = {{19, 30, 50, 10}, {70, 30, 40, 60}, {40, 8, 70, 20}};
        t.initialBasicVariable(S, D, C);
    }
}
