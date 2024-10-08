public class Simplex {
    public void maximize(double[] C, double[][] A, double[] b, double eps, boolean flag){
        if (!isCorrectInput(b)) return;

        for (int i = 0; i < C.length; i++){
            C[i]  *= -1;
        }

        int numConstraints = A.length;
        int numVariables = A[0].length;

        double[] newC = new double[numVariables + numConstraints];
        System.arraycopy(C, 0, newC, 0, numVariables);
        C = newC;

        double[][] newA = new double[numConstraints][numVariables + numConstraints];
        for (int i = 0; i < numConstraints; i++) {
            System.arraycopy(A[i], 0, newA[i], 0, numVariables);
            newA[i][numVariables + i] = 1; // Add slack variable
        }
        A = newA;

        int[] zColumn = new int[numConstraints]; // Answer indexes without slack variables
        for (int i = 0; i < numConstraints; i++){
            zColumn[i] = numVariables + i;
        }

        double value = 0; // the value of Z line.
        while (true) {
            int enteringIndex = getEnteringVariable(C);
            if(enteringIndex==-1){
                break; // problem is finished
            }
            int leavingIndex = getLeavingVariable(enteringIndex, A, b);
            if (leavingIndex == -1) {
                System.out.println("The problem is unbounded!");
                return;
            }
            zColumn[leavingIndex] = enteringIndex;
            value = pivot(A, b, C, enteringIndex, leavingIndex,value);
            for (int i = 0; i < C.length; i++){
                if (C[i] < 0 && C[i] > eps * -1){
                    C[i] = 0;
                }
            }
        }

        double[] z = new double[numVariables];
        for (int i = 0; i < numConstraints; i++) {
            if (zColumn[i] >= numVariables){
                continue;
            }
            z[zColumn[i]] = b[i];
        }
        if(flag){
            output(z,-1 * value); // for minimisation
        }else{
            output(z, value);
        }
    }

    public void maximize(double[] C, double[][] A, double[] b, double eps) {
        maximize(C, A, b, eps, false);
    }

    public void minimize(double[] C, double[][] A, double[] b, double eps){
        double[] negC = new double[C.length];
        for (int i = 0; i < C.length; i++) {
            negC[i] = -C[i]; // Convert minimization to maximization
        }

        maximize(negC, A, b, eps,true);
    }

    private boolean isCorrectInput(double[] b){
        for (double d: b){
            if (d < 0){
                return false;
            }
        }
        return true;
    }

    // return:  index of leaving variable in C or -1 if problem is unbounded
    // input:   index - index of entering variable in C
    private int getLeavingVariable(int index, double[][] A, double[] b){
        int leavingIndex = -1;
        double minRation = -1;
        for (int i = 0; i < b.length; i++){
            if (A[i][index] > 0){
                if (minRation == -1 || minRation > b[i] / A[i][index]){
                    minRation = b[i] / A[i][index];
                    leavingIndex = i;
                }
            }
        }
        return leavingIndex;
    }

    // return:  index of entering variable in C or -1 if problem is finished
    private int getEnteringVariable(double[] C){
        int enteringIndex = -1;
        double minVar =0;
        for (int i = 0; i < C.length; i++){
            if (C[i] < 0){
                if (minVar>C[i]){
                    minVar = C[i];
                    enteringIndex = i;
                }
            }
        }
        return enteringIndex;
    }

    private double pivot(double[][] A, double[] b, double[] C, int enteringIndex, int leavingIndex, double value) {

        double pivotVar = A[leavingIndex][enteringIndex];

        // normalisation
        for(int i=0;i<A[0].length;i++){
            A[leavingIndex][i] /= pivotVar;
        }
        b[leavingIndex]/=pivotVar;

        // pivoting
        for(int i=0;i<A.length;i++){
            if (leavingIndex==i){
                continue;
            }
            double multiplier = A[i][enteringIndex];

            for(int j=0;j<A[0].length;j++){
                A[i][j] -= multiplier*A[leavingIndex][j];
            }
            b[i] -= multiplier*b[leavingIndex];
        }

        double multiplier = C[enteringIndex];
        for(int i=0;i<C.length;i++){
            C[i] -= multiplier*A[leavingIndex][i];
        }
        value -= multiplier*b[leavingIndex];
        return value;
    }

    private void output(double[] x, double value){
        for (int i = 1; i < x.length + 1; i++){
            System.out.println("x" + i + " = " + x[i - 1]);
        }
        System.out.println("Optimized value of objective function: " + value);
    }
}
