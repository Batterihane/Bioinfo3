package solution;

import solution.helpers.CharPair;
import solution.helpers.StringPair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class Exact {
    Map<CharPair, Integer> seqMatrix;
    int gapCost;
    int[][][] resultMap;
    int[][] boundaryResultMap;

    public Exact(Map<CharPair, Integer> seqMatrix, int gapCost)
    {
        this.seqMatrix = seqMatrix;
        this.gapCost = gapCost;
    }

    public int calculateMinCost(char[] seq1, char[] seq2, char[] seq3)
    {
        int d1, d2, d3, d4, d5, d6, d7;
        int cij, cik, cjk;
        resultMap = computeBoundaryCells(seq1, seq2, seq3);
        for(int i = 1 ; i < seq1.length + 1; i++){
            for(int j = 1 ; j < seq2.length + 1; j++){
                for (int k = 1; k < seq3.length + 1; k++) {
                    cij = seqMatrix.get(new CharPair(seq1[i-1], seq2[j-1]));
                    cik = seqMatrix.get(new CharPair(seq1[i-1], seq3[k-1]));
                    cjk = seqMatrix.get(new CharPair(seq2[j-1], seq3[k-1]));

                    d1 = resultMap[i-1][j-1][k-1] + cij + cik + cjk;
                    d2 = resultMap[i-1][j-1][k] + cij + 2*gapCost;
                    d3 = resultMap[i-1][j][k-1] + cik + 2*gapCost;
                    d4 = resultMap[i][j-1][k-1] + cjk + 2*gapCost;
                    d5 = resultMap[i-1][j][k] + 2*gapCost;
                    d6 = resultMap[i][j-1][k] + 2*gapCost;
                    d7 = resultMap[i][j][k-1] + 2*gapCost;

                    resultMap[i][j][k] = Math.min(d1, Math.min(d2, Math.min(d3, Math.min(d4, Math.min(d5, Math.min(d6, d7))))));
                }
            }
        }
        return resultMap[seq1.length][seq2.length][seq3.length];
    }

    private int[][][] computeBoundaryCells(char[] seq1, char[] seq2, char[] seq3) {
        int[][][] result = new int[seq1.length + 1][seq2.length + 1][seq3.length + 1];
        computeBoundaryResultMap(seq1, seq2);
        for (int i = 0; i < seq1.length + 1; i++) {
            for (int j = 0; j < seq2.length + 1; j++) {
                result[i][j][0] = boundaryResultMap[i][j] + (i+j)*gapCost;
            }
        }
        computeBoundaryResultMap(seq1, seq3);
        for (int i = 0; i < seq1.length + 1; i++) {
            for (int k = 0; k < seq3.length + 1; k++) {
                result[i][0][k] = boundaryResultMap[i][k] + (i+k)*gapCost;
            }
        }
        computeBoundaryResultMap(seq2, seq3);
        for (int j = 0; j < seq2.length + 1; j++) {
            for (int k = 0; k < seq3.length + 1; k++) {
                result[0][j][k] = boundaryResultMap[j][k] + (j+k)*gapCost;
            }
        }
        result[0][0][0] = 0;
        return result;
    }


    public void computeBoundaryResultMap(char[] seq1, char[] seq2){
        boundaryResultMap = new int[seq1.length + 1][seq2.length + 1];
        for(int i = 0 ; i < seq1.length + 1 ; i++){
            for(int j = 0 ; j < seq2.length + 1 ; j++){
                boundaryResultMap[i][j] = Integer.MIN_VALUE;
            }
        }
        cost(seq1, seq2, seq1.length, seq2.length);
    }

    private int cost(char[] seq1, char[] seq2, int i, int j){
        if(boundaryResultMap[i][j] != Integer.MIN_VALUE){
            return boundaryResultMap[i][j];
        }

        int v1, v2, v3, v4;
        v1 = v2 = v3 = v4 = Integer.MAX_VALUE;
        if(i > 0 && j > 0){
            v1 = cost(seq1, seq2, i-1, j-1) + seqMatrix.get(new CharPair(seq1[i-1], seq2[j-1]));
        }
        if(i > 0 && j >= 0){
            v2 = cost(seq1, seq2, i-1, j)  + gapCost;
        }
        if(i >= 0 && j > 0){
            v3 = cost(seq1, seq2, i, j-1) + gapCost;
        }
        if(i == 0 && j == 0){
            v4 = 0;
        }

        boundaryResultMap[i][j] = Math.min(v1, Math.min(v2, Math.min(v3, v4)));
        return boundaryResultMap[i][j];
    }

    public String[] backtrack(char[] seq1, char[] seq2, char[] seq3){
        return recBacktrack(seq1, seq2, seq3, resultMap.length-1, resultMap[0].length-1, resultMap[0][0].length-1, "", "", "");
    }

    private String[] recBacktrack(char[] seq1, char[] seq2, char[] seq3, int i, int j, int k, String res1, String res2, String res3){
        int cij, cik, cjk;
        if(i > 0 && j > 0 && k > 0){
            cij = seqMatrix.get(new CharPair(seq1[i-1], seq2[j-1]));
            cik = seqMatrix.get(new CharPair(seq1[i-1], seq3[k-1]));
            cjk = seqMatrix.get(new CharPair(seq2[j-1], seq3[k-1]));

            if(resultMap[i-1][j-1][k-1] + cij + cik + cjk == resultMap[i][j][k]){
                res1 = seq1[i-1] + res1;
                res2 = seq2[j-1] + res2;
                res3 = seq3[k-1] + res3;
                return recBacktrack(seq1, seq2, seq3, i-1, j-1, k-1, res1, res2, res3);
            }
        }
        if(i > 0 && j > 0 && k >= 0){
            cij = seqMatrix.get(new CharPair(seq1[i-1], seq2[j-1]));
            if(resultMap[i-1][j-1][k] + cij + 2*gapCost == resultMap[i][j][k]){
                res1 = seq1[i-1] + res1;
                res2 = seq2[j-1] + res2;
                res3 = "-" + res3;
                return recBacktrack(seq1, seq2, seq3, i-1, j-1, k, res1, res2, res3);
            }
        }
        if(i > 0 && j >= 0 && k > 0){
            cik = seqMatrix.get(new CharPair(seq1[i-1], seq3[k-1]));
            if(resultMap[i-1][j][k-1] + cik + 2*gapCost == resultMap[i][j][k]){
                res1 = seq1[i-1] + res1;
                res2 = "-" + res2;
                res3 = seq3[k-1] + res3;
                return recBacktrack(seq1, seq2, seq3, i-1, j, k-1, res1, res2, res3);
            }
        }
        if(i >= 0 && j > 0 && k > 0){
            cjk = seqMatrix.get(new CharPair(seq2[j-1], seq3[k-1]));
            if(resultMap[i][j-1][k-1] + cjk + 2*gapCost == resultMap[i][j][k]){
                res1 = "-" + res1;
                res2 = seq2[j-1] + res2;
                res3 = seq3[k-1] + res3;
                return recBacktrack(seq1, seq2, seq3, i, j-1, k-1, res1, res2, res3);
            }
        }
        if(i > 0 && j >= 0 && k >= 0){
            if(resultMap[i-1][j][k] + 2*gapCost == resultMap[i][j][k]){
                res1 = seq1[i-1] + res1;
                res2 = "-" + res2;
                res3 = "-" + res3;
                return recBacktrack(seq1, seq2, seq3, i-1, j, k, res1, res2, res3);
            }
        }
        if(i >= 0 && j > 0 && k >= 0){
            if(resultMap[i][j-1][k] + 2*gapCost == resultMap[i][j][k]){
                res1 = "-" + res1;
                res2 = seq2[j-1] + res2;
                res3 = "-" + res3;
                return recBacktrack(seq1, seq2, seq3, i, j-1, k, res1, res2, res3);
            }
        }
        if(i >= 0 && j >= 0 && k > 0){
            if(resultMap[i][j][k-1] + 2*gapCost == resultMap[i][j][k]){
                res1 = "-" + res1;
                res2 = "-" + res2;
                res3 = seq3[k-1] + res3;
                return recBacktrack(seq1, seq2, seq3, i, j, k-1, res1, res2, res3);
            }
        }

        String[] result = new String[3];
        result[0] = res1;
        result[1] = res2;
        result[2] = res3;
        return result;
    }
}
