package solution.tests;

import solution.Approx;
import solution.Exact;
import solution.helpers.CentralSequenceFinder;
import solution.helpers.CharPair;
import solution.helpers.FastaParser;
import solution.helpers.MatrixParser;
import solution.tests.AlignmentToCost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreExperimentRunner {
    public static void main(String[] args) throws Exception
    {
        Map<CharPair,Integer> seqMatrix;
        int gapCost;
        MatrixParser matrixParser = new MatrixParser();

        matrixParser.parseFile("costMatrix.txt");
        seqMatrix = matrixParser.getCostMatrix();
        gapCost = matrixParser.getGapCostAlpha();
        System.out.println(gapCost);

        ArrayList<Integer> exactScores = new ArrayList<>();
        ArrayList<Integer> approxScores = new ArrayList<>();

        String path;
        FastaParser fastaParser;
        Approx approx;
        Exact exact = new Exact(seqMatrix, gapCost);
        List<char[]> resultList;
        String[] res;

        for (int i = 10; i <= 200 ; i = i + 10) {
            path = "testseqs\\testseqs_"+i+"_3.fasta";
            fastaParser = new FastaParser(path);
            resultList = fastaParser.parseFastaFile();
            approx = new Approx(seqMatrix,gapCost);

            int exactScore = exact.calculateMinCost(resultList.get(0), resultList.get(1), resultList.get(2));

            res = approx.sp_approx(CentralSequenceFinder.findCentralSequence(seqMatrix, gapCost, resultList));
            int approxScore = AlignmentToCost.calculateCost(seqMatrix, gapCost, res);

            exactScores.add(exactScore);
            approxScores.add(approxScore);
        }

        System.out.println("exact scores: " + exactScores.toString());
        System.out.println("approx scores: " + approxScores.toString());

    }

}

