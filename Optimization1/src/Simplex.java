public class Simplex {
    public void maximize(double[] C, double[][] A, double[] b, int eps){
        //TODO
        //Artyom
    }

    public void minimize(double[] C, double[][] A, double[] b, int eps){
        //TODO
        //Artyom
    }

    private boolean isFinished(double[] C){
        for (double d: C){
            if (d < 0){
                return false;
            }
        }
        return true;
    }

    private boolean isCorrectInput(double[] b){
        for (double d: b){
            if (d < 0){
                System.out.println("The method is not applicable!");
                return false;
            }
        }
        return true;
    }

    // return:  index of leaving variable in C or -1 if problem is unbounded
    // input:   index - index of entering variable in C
    private int getLeavingVariable(int index, double[][] A, double[] b){
        int leavingIndex = -1;
        double minRation = 0;
        for (int i = 0; i < b.length; i++){
            if (b[i] > 0){
                if (minRation == 0 || minRation > b[i] / A[i][index]){
                    minRation = b[i] / A[i][index];
                }
            }
        }
        return leavingIndex;
    }

    private void addSlack(double[] C, double[][] A){
        //TODO
        //Artyom
    }

    private void output(double[] x, double value){
        for (int i = 1; i < x.length + 1; i++){
            System.out.println("x" + i + " = " + x[i - 1]);
        }
        System.out.println("Optimized value of objective function: " + value);
    }
}
