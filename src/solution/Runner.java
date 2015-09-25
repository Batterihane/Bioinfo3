package solution;


import solution.helpers.CentralSequenceFinder;
import solution.helpers.CharPair;
import solution.helpers.FastaParser;
import solution.helpers.MatrixParser;
import solution.tests.AlignmentToCost;

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
        List<char[]> resultList = fastaParser.parseFastaFile();

        Approx a = new Approx(seqMatrix,gapCost);
        String[] res = a.sp_approx(CentralSequenceFinder.findCentralSequence(seqMatrix, gapCost, resultList));

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

        System.out.println(AlignmentToCost.calculateCost(seqMatrix, gapCost, res));

        //solution.Prompter p = new solution.Prompter();
        //p.prompt();
    }
}
