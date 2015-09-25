package solution.helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FastaParser {

    private final File file;

    public FastaParser(String filePathName) {
        file = new File(filePathName);
    }

    public List<char[]> parseFastaFile() throws FileNotFoundException {
        ArrayList<char[]> resultList = new ArrayList<>();
        boolean first = true;
        String accLine = "";

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!(line.length() == 0) && line.charAt(0) == '>') {
                    if (first)
                        first = false;
                    else
                    {
                        resultList.add(accLine.toCharArray());
                        accLine = "";
                    }
                }
                else {
                    accLine += line;
                }
            }
        }
        resultList.add(accLine.toCharArray());
        return resultList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastaParser fastaParser = new FastaParser("brca1-testseqs.fasta");
        List<char[]> resultList = fastaParser.parseFastaFile();
        System.out.println(resultList.toString());

        System.out.println("size: " + resultList.size());
    }


}