package solution;


import solution.helpers.CentralSequenceFinder;
import solution.helpers.CharPair;
import solution.helpers.FastaParser;
import solution.helpers.MatrixParser;
import solution.tests.AlignmentToCost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) throws Exception
    {
        Map<CharPair,Integer> seqMatrix;
        int gapCost;
        MatrixParser matrixParser = new MatrixParser();

        matrixParser.parseFile("costMatrix.txt");
        seqMatrix = matrixParser.getCostMatrix();
        gapCost = matrixParser.getGapCostAlpha();

        FastaParser fastaParser = new FastaParser("brca1-testseqs.fasta");
        List<char[]> resultList = fastaParser.parseFastaFile().subList(0, 5);

        Approx a = new Approx(seqMatrix,gapCost);
        resultList = CentralSequenceFinder.findCentralSequence(seqMatrix, gapCost, resultList);
//        String[] res = a.sp_approx(resultList);
//
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[i]);
//        }
//
//        System.out.println(AlignmentToCost.calculateCost(seqMatrix, gapCost, res));
        runAllCombinationsOfApprox(seqMatrix, gapCost, resultList, a);

        //solution.Prompter p = new solution.Prompter();
        //p.prompt();
    }

    public static void runAllCombinationsOfApprox(Map<CharPair, Integer> seqMatrix, int gapCost, List<char[]> sequences, Approx approx){
        List<char[]> nextSequences;
        for (int i = 1; i < sequences.size(); i++) {
            for (int j = 1; j < sequences.size(); j++) {
                if(j == i) continue;
                for (int k = 1; k <sequences.size(); k++) {
                    if(k == i || k == j) continue;
                    for (int l = 1; l < sequences.size(); l++) {
                        if(l == i || l == j || l == k) continue;
                        nextSequences = new ArrayList<>();
                        nextSequences.add(0, sequences.get(0));
                        nextSequences.add(1, sequences.get(i));
                        nextSequences.add(2, sequences.get(j));
                        nextSequences.add(3, sequences.get(k));
                        nextSequences.add(4, sequences.get(l));
                        String[] alignment = approx.sp_approx(nextSequences);
                        for(String s : alignment){
                            System.out.println(s);
                        }
                        System.out.println(AlignmentToCost.calculateCost(seqMatrix, gapCost, alignment));
                    }
                }
            }
        }
    }
}
