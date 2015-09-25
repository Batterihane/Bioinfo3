package solution.tests;

import solution.helpers.CharPair;

import java.util.Map;

public class AlignmentToCost {

    public static int calculateCost(Map<CharPair, Integer> seqMatrix, int gapCost, String[] alignment){
        int result = 0;
        for (int i = 0; i < alignment.length-1; i++) {
            for (int j = i; j < alignment.length-1; j++) {
                result += calculateCostOfTwoStrings(seqMatrix, gapCost, alignment[i], alignment[j+1]);
            }
        }
        return result;
    }

    private static int calculateCostOfTwoStrings(Map<CharPair, Integer> seqMatrix, int gapCost, String seq1, String seq2){
        int result = 0;
        for (int i = 0; i < seq1.length(); i++) {
            char a = seq1.charAt(i);
            char b = seq2.charAt(i);
            if(a == '-'){
                if(b == '-'){
                    continue;
                }
                result += gapCost;
                continue;
            } else if(b == '-'){
                result += gapCost;
                continue;
            }
            result += seqMatrix.get(new CharPair(a, b));
        }
        return result;
    }

}
