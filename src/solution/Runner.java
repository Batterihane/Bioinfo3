package solution;


import solution.helpers.CharPair;
import solution.helpers.FastaParser;
import solution.helpers.MatrixParser;

import java.util.Arrays;
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

        FastaParser fastaParser = new FastaParser("brca1-testseqs.fasta");
        List<char[]> resultList = fastaParser.parseFastaFile();

        Approx a = new Approx(seqMatrix,gapCost);
        String[] res = a.sp_approx(resultList);

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

        //solution.Prompter p = new solution.Prompter();
        //p.prompt();
    }
}
