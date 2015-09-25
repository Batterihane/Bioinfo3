package solution;


import solution.helpers.CharPair;
import solution.helpers.FastaParser;
import solution.helpers.MatrixParser;
import solution.tests.AlignmentToCost;

import java.util.List;
import java.util.Map;

public class ExactRunner {
    public static void main(String[] args) throws Exception
    {
        Map<CharPair,Integer> seqMatrix;
        int gapCost;
        MatrixParser matrixParser = new MatrixParser();
        char[] seq1, seq2, seq3;

        matrixParser.parseFile("costMatrix.txt");
        seqMatrix = matrixParser.getCostMatrix();
        gapCost = matrixParser.getGapCostAlpha();

        Exact exact = new Exact(seqMatrix, gapCost);

        FastaParser fastaParser = new FastaParser("brca1-testseqs.fasta");
        List<char[]> resultList = fastaParser.parseFastaFile();

        seq1 = resultList.get(0);
        seq2 = resultList.get(1);
        seq3 = resultList.get(2);
        int result = exact.calculateMinCost(seq1, seq2, seq3);

        System.out.println(result);

        String[] allignment = exact.backtrack(seq1, seq2, seq3);

        System.out.println(allignment[0]);
        System.out.println(allignment[1]);
        System.out.println(allignment[2]);

        System.out.println(AlignmentToCost.calculateCost(seqMatrix, gapCost, allignment));

        //solution.Prompter p = new solution.Prompter();
        //p.prompt();
    }
}
