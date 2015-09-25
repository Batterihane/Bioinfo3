package solution.helpers;

import solution.Approx;
import solution.Exact;
import solution.tests.AlignmentToCost;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Balls on 06/09/2015.
 */
public class Prompter {

    char[] seq1;
    char[] seq2;
    FastaParser fp;
    FastaWriter writer;
    Scanner sc;
    Map<CharPair,Integer> seqMatrix;
    int gapCostAlpha;
    int gapCostBeta;
    List<Integer> intl;
    MatrixParser matrixParser = new MatrixParser();
    File f;
    Exact exact;
    Approx approx;
    List<char[]> l;
    public Prompter() throws Exception {
        writer = new FastaWriter("alignment");
        sc = new Scanner(System.in);
        intl = matrixParser.parseFile("costMatrix.txt");

        seqMatrix = matrixParser.getCostMatrix();
        gapCostAlpha = matrixParser.getGapCostAlpha();

        exact = new Exact(seqMatrix, gapCostAlpha);
        approx = new Approx(seqMatrix, gapCostAlpha);
        String sf = new String("Seq.fasta");
        if (new File(sf).exists()){
            try{FastaParser fp = new FastaParser(sf);
                l = fp.parseFastaFile();
                System.out.println("'Seq.fasta' was loaded.");
            }
            catch(IOException ioe){System.out.println("No file named \"Seq.fasta\" was included. Nothing will work now");}
            catch(Exception e){System.out.println("");}
        }
        else {
            System.out.println("No file named \"Seq.fasta\" was included. Nothing will work now");
        }

    }

    public void prompt(){
        String s = ("type:\n\t" +
                "\"cost\" to see score matrix and gap cost\n\t" +
                "\"exact_3\" to compute an MSA of 3 sequences with the exact algorithm and see its score\n\t" +
                "\"approx\" to show an MSA with the approximation algorithm and see its score\n\t\t"+
                    "if there are more than three sequences in the fasta file loaded, the algorithm will be run on the first three elements\n\t" +
                "\"q\" to quit\n\t"
        );

        System.out.println(s);
        String inp = "";
        while(!(inp.equals("Q")|| inp.equals("QUIT"))){
            inp = sc.next().toUpperCase();

            switch (inp) {
                case "COST":
                    String s2 = "Scorematrix (ACGT/ACGT):";
                    for (int i=0; i<16;i++){
                        if (i%4==0)
                            s2+="\n";
                        s2 += " "+Integer.toString(intl.get(i));
                    }
                    s2 += "\nGapcost: "+Integer.toString(intl.get(16));
                    System.out.println(s2);

                    break;
                case "E":
                case "EXACT_3":
                    int costResult = exact.calculateMinCost(l.get(0), l.get(1),l.get(2));
                    //fastawrite results in exact_3_results.fasta
                    String[] res = exact.backtrack(l.get(0),l.get(1),l.get(2));
                    for (int i = 0; i < res.length; i++) {
                        System.out.println(res[i]);
                    }
                    System.out.println(costResult);
                    break;
                case "A":
                case "APPROX":
                    System.out.println("How many?");
                    inp = sc.next();
                    String[] MSA = approx.sp_approx(l.subList(0,Integer.parseInt(inp)));
                    for (int i = 0; i < MSA.length; i++) {
                        System.out.println(MSA[i]);
                    }
                    System.out.println(AlignmentToCost.calculateCost(seqMatrix, gapCostAlpha, MSA));
                     //   writer.writeSequences(alignmentSplit[0], alignmentSplit[1]);
                    break;
                case "Q"://do nothing and then it will quit
                    break;
                default:
                    System.out.println("Invalid command!\n"+s);


            }
        }





    }
    public void printstuff(){


    }
}
