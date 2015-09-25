package solution.helpers;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Nikolaj on 04-09-2015.
 */
public class MatrixParser
{
    private HashMap<CharPair, Integer> costMatrix;
    private int gapCostAlpha;
    private int gapCostBeta;

    public List<Integer> parseFile(String fileName)
    {
        try {
            Path filePath = Paths.get(fileName);
            Scanner scanner = new Scanner(filePath);
            List<Integer> costMatrix = new ArrayList<>();
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    costMatrix.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }

            initFields(costMatrix);

            return costMatrix;
        }
        catch (Exception e) {
            System.out.println("File could not be found! noobz");
            return null;
        }
    }

    private void initFields(List<Integer> integers)
    {
        HashMap<CharPair, Integer> costMatrix = new HashMap<CharPair, Integer>();

        costMatrix.put(new CharPair('A', 'A'), integers.get(0));
        costMatrix.put(new CharPair('A', 'C'), integers.get(1));
        costMatrix.put(new CharPair('A', 'G'), integers.get(2));
        costMatrix.put(new CharPair('A', 'T'), integers.get(3));
        costMatrix.put(new CharPair('C', 'A'), integers.get(4));
        costMatrix.put(new CharPair('C', 'C'), integers.get(5));
        costMatrix.put(new CharPair('C', 'G'), integers.get(6));
        costMatrix.put(new CharPair('C', 'T'), integers.get(7));
        costMatrix.put(new CharPair('G', 'A'), integers.get(8));
        costMatrix.put(new CharPair('G', 'C'), integers.get(9));
        costMatrix.put(new CharPair('G', 'G'), integers.get(10));
        costMatrix.put(new CharPair('G', 'T'), integers.get(11));
        costMatrix.put(new CharPair('T', 'A'), integers.get(12));
        costMatrix.put(new CharPair('T', 'C'), integers.get(13));
        costMatrix.put(new CharPair('T', 'G'), integers.get(14));
        costMatrix.put(new CharPair('T', 'T'), integers.get(15));

        this.costMatrix = costMatrix;
        gapCostAlpha = integers.get(16);
        gapCostBeta = integers.get(17);
    }


    public static void main(String[] args)
    {
        MatrixParser matrixParser = new MatrixParser();
        List<Integer> matrix = matrixParser.parseFile("costMatrix.txt");
        System.out.println("matrix: " + matrix.toString());
        System.out.println("costMatrix: " + matrixParser.getCostMatrix().toString());
        System.out.println("alpha: " + matrixParser.getGapCostAlpha());
        System.out.println("beta: " + matrixParser.getGapCostBeta());
    }

    public HashMap<CharPair, Integer> getCostMatrix() {
        return costMatrix;
    }

    public int getGapCostAlpha() {
        return gapCostAlpha;
    }

    public int getGapCostBeta() {
        return gapCostBeta;
    }
}
