package solution;


import solution.helpers.CharPair;
import solution.helpers.FastaParser;
import solution.helpers.MatrixParser;

import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) throws Exception
    {
        char[] seq1;
        char[] seq2;
        FastaParser fp;
        Map<CharPair,Integer> seqMatrix;
        int gapCost;
        MatrixParser matrixParser = new MatrixParser();

        matrixParser.parseFile("costMatrix.txt");
        seqMatrix = matrixParser.getCostMatrix();
        gapCost = matrixParser.getGapCostAlpha();

        Exact exact = new Exact(seqMatrix, gapCost);

        FastaParser fastaParser = new FastaParser("test2.fasta");
        List<String> resultList = fastaParser.parseFastaFile();

        int result = exact.calculateMinCost(resultList.get(0).toCharArray(), resultList.get(1).toCharArray(), resultList.get(2).toCharArray());

        System.out.println(result);

        //solution.Prompter p = new solution.Prompter();
        //p.prompt();
    }
}
