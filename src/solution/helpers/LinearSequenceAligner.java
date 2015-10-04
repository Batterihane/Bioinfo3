package solution.helpers;

import java.util.Map;

/**
 * Created by Thomas on 27-08-2015.
 */
public class LinearSequenceAligner {
    Map<CharPair, Integer> seqMatrix;
    int gapCost;
    int[][] resultMap;

    public LinearSequenceAligner(Map<CharPair, Integer> seqMatrix, int gapCost){
        this.seqMatrix = seqMatrix;
        this.gapCost = gapCost;
    }

    public int calculateMinCost(char[] seq1, char[] seq2){
        resultMap = new int[seq1.length + 1][seq2.length + 1];
        for(int i = 0 ; i < seq1.length + 1 ; i++){
            for(int j = 0 ; j < seq2.length + 1 ; j++){
                resultMap[i][j] = Integer.MIN_VALUE;
            }
        }
        return cost(seq1, seq2, seq1.length, seq2.length);
    }

    private int cost(char[] seq1, char[] seq2, int seq1Length, int seq2Length){
        for (int i = 0; i < seq1Length + 1; i++) {
            for (int j = 0; j < seq2Length + 1; j++) {
                int v1, v2, v3, v4;
                v1 = v2 = v3 = v4 = Integer.MAX_VALUE;
                if(i > 0 && j > 0){
                    v1 = resultMap[i-1][j-1] + seqMatrix.get(new CharPair(seq1[i-1], seq2[j-1]));
                }
                if(i > 0 && j >= 0){
                    v2 = resultMap[i-1][j] + gapCost;
                }
                if(i >= 0 && j > 0){
                    v3 = resultMap[i][j-1] + gapCost;
                }
                if(i == 0 && j == 0){
                    v4 = 0;
                }

                resultMap[i][j] = Math.min(v1, Math.min(v2, Math.min(v3, v4)));
            }
        }
        return resultMap[seq1Length][seq2Length];
//        if(resultMap[i][j] != Integer.MIN_VALUE){
//            return resultMap[i][j];
//        }
//
//        int v1, v2, v3, v4;
//        v1 = v2 = v3 = v4 = Integer.MAX_VALUE;
//        if(i > 0 && j > 0){
//            v1 = cost(seq1, seq2, i-1, j-1) + seqMatrix.get(new CharPair(seq1[i-1], seq2[j-1]));
//        }
//        if(i > 0 && j >= 0){
//            v2 = cost(seq1, seq2, i-1, j)  + gapCost;
//        }
//        if(i >= 0 && j > 0){
//            v3 = cost(seq1, seq2, i, j-1) + gapCost;
//        }
//        if(i == 0 && j == 0){
//            v4 = 0;
//        }
//
//        resultMap[i][j] = Math.min(v1, Math.min(v2, Math.min(v3, v4)));
//        return resultMap[i][j];
    }

    public StringPair backtrack(char[] seq1, char[] seq2){
        return recBacktrack(seq1, seq2, resultMap.length-1, resultMap[0].length-1, "", "");
    }

    public StringPair recBacktrack(char[] seq1, char[] seq2, int seq1Length, int seq2Length, String res1, String res2){
        int i = seq1Length;
        int j = seq2Length;
        while (i >= 0 || j >= 0) {
            if(i > 0 && j > 0){
                if(resultMap[i-1][j-1] + seqMatrix.get(new CharPair(seq1[i-1], seq2[j-1])) == resultMap[i][j]){
                    res1 = seq1[i-1] + res1;
                    res2 = seq2[j-1] + res2;
                    i--;
                    j--;
                    continue;
                }
            }
            if(i > 0 && j >=0){
                if(resultMap[i-1][j] + gapCost == resultMap[i][j]){
                    res1 = seq1[i-1]  + res1;
                    res2 = "-" + res2;
                    i--;
                    continue;
                }
            }
            if(i >= 0 && j > 0){
                if(resultMap[i][j-1] + gapCost == resultMap[i][j]){
                    res1 = "-" + res1;
                    res2 = seq2[j-1] + res2;
                    j--;
                    continue;
                }
            }
            break;
        }
        return new StringPair(res1,res2);

//        if(i > 0 && j > 0){
//            if(resultMap[i-1][j-1] + seqMatrix.get(new CharPair(seq1[i-1], seq2[j-1])) == resultMap[i][j]){
//                res1 = seq1[i-1] + res1;
//                res2 = seq2[j-1] + res2;
//                return recBacktrack(seq1, seq2, i-1, j-1, res1, res2);
//            }
//        }
//        if(i > 0 && j >=0){
//            if(resultMap[i-1][j] + gapCost == resultMap[i][j]){
//                res1 = seq1[i-1]  + res1;
//                res2 = "-" + res2;
//                return recBacktrack(seq1, seq2, i-1, j, res1, res2);
//            }
//        }
//        if(i >= 0 && j > 0){
//            if(resultMap[i][j-1] + gapCost == resultMap[i][j]){
//                res1 = "-" + res1;
//                res2 = seq2[j-1] + res2;
//                return recBacktrack(seq1, seq2, i, j-1, res1, res2);
//            }
//        }
//        return new StringPair(res1,res2);
    }
}


