package solution;


import solution.helpers.*;
import solution.tests.AlignmentToCost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Runner {

    public static final String INPUT_FILE_NAME = "brca1-full";
    public static final String OUTPUT_FILE_FOLDER = "results\\";
    public static final String OUTPUT_FILE_ENDING = "-result";
    public static final String FASTA_FILE_ENDING = ".fasta";

    public static void main(String[] args) throws Exception
    {
        Map<CharPair,Integer> seqMatrix;
        int gapCost;
        MatrixParser matrixParser = new MatrixParser();

        matrixParser.parseFile("costMatrix.txt");
        seqMatrix = matrixParser.getCostMatrix();
        gapCost = matrixParser.getGapCostAlpha();

        FastaParser fastaParser = new FastaParser(INPUT_FILE_NAME + FASTA_FILE_ENDING);
        FastaWriter writer = new FastaWriter(OUTPUT_FILE_FOLDER + INPUT_FILE_NAME + OUTPUT_FILE_ENDING + FASTA_FILE_ENDING);
        List<char[]> resultList = fastaParser.parseFastaFile(); //.subList(0, 6);

        for(char[] foo : resultList){
            for(char bar : foo){
                if(bar != 'A' && bar != 'C' && bar != 'T' && bar != 'G'){
                    System.out.println("Her: " + bar);
                }
            }
        }

        Approx a = new Approx(seqMatrix,gapCost);
        resultList = CentralSequenceFinder.findCentralSequence(seqMatrix, gapCost, resultList);
        String[] res = a.sp_approx(resultList);

        writer.writeSequences(res);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

        System.out.println(AlignmentToCost.calculateCost(seqMatrix, gapCost, res));

        //runAllCombinationsOfApprox(seqMatrix, gapCost, resultList, a);

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
