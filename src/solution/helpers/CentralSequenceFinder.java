package solution.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CentralSequenceFinder {

    public static List<char[]> findCentralSequence(Map<CharPair, Integer> seqMatrix, int gapCost, List<char[]> sequences){
        LinearSequenceAligner aligner = new LinearSequenceAligner(seqMatrix, gapCost);
        int[] scores = new int[sequences.size()];
        int tempResult;
        for (int i = 0; i < sequences.size() - 1; i++) {
            for (int j = i+1; j < sequences.size(); j++) {
                tempResult = aligner.calculateMinCost(sequences.get(i), sequences.get(j));
                scores[i] += tempResult;
                scores[j] += tempResult;
            }
        }
        int bestIndex = 0;
        int bestScore = Integer.MAX_VALUE;
        for(int i = 0 ; i < scores.length ; i++){
            if(scores[i] < bestScore){
                bestIndex = i;
                bestScore = scores[i];
            }
        }
        char[] centerSequence = sequences.remove(bestIndex);
        sequences.add(0, centerSequence);
        return sequences;
    }

}
