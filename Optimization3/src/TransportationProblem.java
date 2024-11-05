public class TransportationProblem {
    public void initialBasicVariable(double[] S, double[] D, double[][] C){
        if (checkApplicability(S, D, C)){
            System.out.println("The method is not applicable!");
            return;
        }
        if (checkBalance(S, D)){
            System.out.println("The problem is not balanced!");
            return;
        }

        demonstrate(S, D, C);

        System.out.println("\nNorth-West corner method solution:\n");
        demonstrate(S, D, northWest(S, D));
    }

    private boolean checkBalance(double[] S, double[] D){
        double s = 0;
        double d = 0;
        for (double i: S){
            s += i;
        }
        for (double i: D){
            d += i;
        }
        return s != d;
    }

    private boolean checkApplicability(double[] S, double[] D, double[][] C){
        
        for (double d: S){
            if (d < 0){
                return true;
            }
        }
        for (double d: D){
            if (d < 0){
                return true;
            }
        }
        for (double[] dd: C){
            for (double d: dd){
                if (d < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void demonstrate(double[] S, double[] D, double[][] C){
        System.out.print("\t|");
        for (int i = 1; i < D.length + 1; i++){
            System.out.print(i + "\t");
        }
        System.out.println("|");
        for (int i = 0; i < D.length; i++){
            System.out.print("-----------");
        }
        System.out.println();
        for (int i = 0; i < S.length; i++){
            System.out.print((i + 1) + "\t|");
            for (int j = 0; j < D.length; j++){
                System.out.print(C[i][j] + "\t");
            }
            System.out.println("|" + S[i]);
        }
        for (int i = 0; i < D.length; i++){
            System.out.print("-----------");
        }
        System.out.print("\n\t|");
        for (int i = 0; i < D.length; i++){
            System.out.print(D[i] + "\t");
        }
        System.out.println("|");
    }

    private double[][] northWest(double[] S, double[] D){
        double[][] answ = new double[S.length][D.length];
        int i = 0;
        int j = 0;
        double curSup = S[0];
        double curDem = D[0];
        while (true){
            if (curSup >= curDem){
                answ[i][j] = curDem;
                curSup -= curDem;
                j++;
                if (j == D.length){
                    break;
                }
                curDem = D[j];
            }
            else {
                answ[i][j] = curSup;
                curDem -= curSup;
                i++;
                if (i == S.length){
                    break;
                }
                curSup = S[i];
            }
        }
        return answ;
    }
}