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

        System.out.println("\nInitial problem:\n");

        demonstrate(S, D, C);

        System.out.println("\nNorth-West corner method solution:\n");
        demonstrate(S, D, northWest(S, D));

        System.out.println("\nVogel's approximation method solution:\n");
        demonstrate(S, D, vogel(copy(S), copy(D), copy(C)));

        System.out.println("\nRussel's aproximation method solution:\n");
        demonstrate(S, D, russle(copy(S), copy(D), copy(C)));
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

    private double[][] vogel(double[] S, double[] D, double[][] C){
        double[][] answ = new double[S.length][D.length];
        double[] rows = new double[S.length];
        double[] cols = new double[D.length];
        while (true) {
            rows = new double[S.length];
            cols = new double[D.length];
            double min1;
            double min2;
            for (int i = 0; i < S.length; i++) {
                min1 = -1;
                min2 = -1;
                for (int j = 0; j < D.length; j++){
                    if (C[i][j] == -1){
                        continue;
                    }
                    if (min1 == -1 || min1 > C[i][j]){
                        min2 = min1;
                        min1 = C[i][j];
                    }
                    else if (min2 == -1 || min2 > C[i][j]){
                        min2 = C[i][j];
                    }
                }
                if (min1 == -1){
                    rows[i] = -1;
                }
                else if (min2 == -1){
                    rows[i] = min1;
                }
                else {
                    rows[i] = min2 - min1;
                }
            }

            for (int j = 0; j < D.length; j++){
                min1 = -1;
                min2 = -1;
                for (int i = 0; i < S.length; i++){
                    if (C[i][j] == -1){
                        continue;
                    }
                    if (min1 == -1 || min1 > C[i][j]){
                        min2 = min1;
                        min1 = C[i][j];
                    }
                    else if (min2 == -1 || min2 > C[i][j]){
                        min2 = C[i][j];
                    }
                }
                if (min1 == -1){
                    cols[j] = -1;
                }
                else if (min2 == -1){
                    cols[j] = min1;
                }
                else {
                    cols[j] = min2 - min1;
                }
            }

            double min = -1;
            int ind = -1;
            boolean isRow = true;
            for (int i = 0; i < rows.length; i++){
                if (rows[i] == -1){
                    continue;
                }
                if (min == -1 || min < rows[i]){
                    min = rows[i];
                    ind = i;
                }
            }
            for (int i = 0; i < cols.length; i++){
                if (cols[i] == -1){
                    continue;
                }
                if (min == -1 || min < cols[i]){
                    isRow = false;
                    min = cols[i];
                    ind = i;
                }
            }

            if (min == -1){
                break;
            }

            min = -1;
            int elementInd = -1;
            if (isRow){
                for (int i = 0; i < D.length; i++){
                    if (C[ind][i] == -1){
                        continue;
                    }
                    if (min == -1 || min > C[ind][i]){
                        min = C[ind][i];
                        elementInd = i;
                    }
                }
            }
            else {
                for (int i = 0; i < S.length; i++){
                    if (C[i][ind] == -1){
                        continue;
                    }
                    if (min == -1 || min > C[i][ind]){
                        min = C[i][ind];
                        elementInd = i;
                    }
                }

                int temp = elementInd;
                elementInd = ind;
                ind = temp;
            }

            if (S[ind] >= D[elementInd]){
                answ[ind][elementInd] = D[elementInd];
                S[ind] -= D[elementInd];
                D[elementInd] = 0;
            }
            else {
                answ[ind][elementInd] = S[ind];
                D[elementInd] -= S[ind];
                S[ind] = 0;
            }

            if (S[ind] == 0) {
                for (int i = 0; i < D.length; i++){
                    C[ind][i] = -1;
                }
            }
            if (D[elementInd] == 0){
                for (int i = 0; i < S.length; i++){
                    C[i][elementInd] = -1;
                }
            }
        }

        return answ;
    }

    private double[][] russle(double[] S, double[] D, double[][] C){
        double[][] answ = new double[S.length][D.length];
        while (true) {
            double[] u = new double[S.length];
            double[] v = new double[D.length];

            double max;
            for (int i = 0; i < S.length; i++){
                max = -1;
                for (int j = 0; j < D.length; j++){
                    if (C[i][j] == -1){
                        continue;
                    }
                    if (max == -1 || max < C[i][j]){
                        max = C[i][j];
                    }
                }
                u[i] = max;
            }

            for (int j = 0; j < D.length; j++){
                max = -1;
                for (int i = 0; i < S.length; i++){
                    if (C[i][j] == -1){
                        continue;
                    }
                    if (max == -1 || max < C[i][j]){
                        max = C[i][j];
                    }
                }
                v[j] = max;
            }

            double[][] delta = copy(C);

            int row = -1;
            int col = -1;
            double min = 0;
            for (int i = 0; i < S.length; i++){
                for (int j = 0; j < D.length; j++){
                    if (delta[i][j] == -1){
                        continue;
                    }
                    delta[i][j] -= u[i] + v[j];
                    if (min == 0 || min > delta[i][j]){
                        min = delta[i][j];
                        row = i;
                        col = j;
                    }
                }
            }

            if (row == -1){
                break;
            }

            if (S[row] >= D[col]){
                answ[row][col] = D[col];
                S[row] -= D[col];
                D[col] = 0;
            }
            else {
                answ[row][col] = S[row];
                D[col] -= S[row];
                S[row] = 0;
            }
            if (S[row] == 0){
                for (int i = 0; i < D.length; i++){
                    C[row][i] = -1;
                }
            }
            if (D[col] == 0){
                for (int i = 0; i < S.length; i++){
                    C[i][col] = -1;
                }
            }
        }

        return answ;
    }

    private double[] copy(double[] d){
        double[] answ = new double[d.length];
        for (int i = 0; i < d.length; i++){
            answ[i] = d[i];
        }
        return answ;
    }

    private double[][] copy(double[][] d){
        double[][] answ = new double[d.length][d[0].length];
        for (int i = 0; i < d.length; i++){
            for (int j = 0; j < d[0].length; j++){
                answ[i][j] = d[i][j];
            }
        }
        return answ;
    }
}