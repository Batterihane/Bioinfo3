package solution.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Balls on 06/09/2015.
 */
public class Prompter {
    /*
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

    AffineSequenceAligner seqAligner;
    public Prompter() throws Exception {
        writer = new FastaWriter("alignment");
        sc = new Scanner(System.in);
        intl = matrixParser.parseFile("costMatrix.txt");

        seqMatrix = matrixParser.getCostMatrix();
        gapCostAlpha = matrixParser.getGapCostAlpha();
        gapCostBeta = matrixParser.getGapCostBeta();

        seqAligner= new AffineSequenceAligner(seqMatrix, gapCostAlpha, gapCostBeta);
        File sf = new File("Seq.fasta");
        if (sf.exists()){
            f=sf;
            try{FastaParser fp = new FastaParser(f);
                seq1 = fp.parse("Seq1").toCharArray();
                seq2 = fp.parse("Seq2").toCharArray();
                System.out.println(" \"Seq1\" and \"Seq2\" were loaded from \"Seq.fasta\"");
            }
            catch(IOException ioe){System.out.println("No file named \"Seq.fasta\" was included. File must be selected manually");}
            catch(Exception e){System.out.println("\"Seq.fasta\" did not have sequences named \"Seq1\" and \"Seq2\". File and sequences must be selected manually");}
        }
        else {
            System.out.println("No file named \"Seq.fasta\" was included. File must be selected manually");
        }

    }

    public void prompt(){
        String s = ("type:\n\t" +
                "\"cost\" to see score matrix and gap cost\n\t" +
                "\"file\" to select a fasta file\n\t"+
                "\"seq\" to select sequences from the current file\n\t"+
                "\"run\" to compute a minimum global alignment on selected sequences with the current score matrix and gap cost\n\t" +
                "\"backtrack\" to compute and print out an optimal alignment after using \"run\"\n\t"+
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
                    s2 += "\nGapcost alpha: "+Integer.toString(intl.get(16));
                    s2 += "\nGapcost beta: "+Integer.toString(intl.get(17));
                    System.out.println(s2);

                    break;
                case "FILE":
                    System.out.println("Please input name of the fasta file");
                    inp = sc.next();
                    if (inp.endsWith(".fasta")) {
                        f = new File(inp);
                    }else {
                        f = new File(inp + ".fasta");
                    }
                    if (f.exists() && !f.isDirectory()) {
                        try {
                            fp = new FastaParser(f);
                            System.out.println("The file " + inp + " has been loaded.");
                        } catch (FileNotFoundException fnf) {
                            System.out.println("an error occurred");
                        }
                    }else
                        System.out.printf("No file named %s or %s", inp, inp + ".fasta\n");
                    break;
                case "SEQ":
                    if(fp==null)
                        System.out.println("Please first select a file in FASTA format to parse");
                    else{
                        System.out.println("Type '1' to change first sequence, and '2' to change second");
                        inp = sc.next();
                        if (!inp.equals("1") && !inp.equals("2")){
                            System.out.println("invalid command");
                            break;
                        }

                        try {
                            if (inp.equals("1")) {
                                System.out.println("Please input name of the first sequence (Case sensitive)");
                                seq1 = fp.parse(sc.next()).toCharArray();
                                System.out.println("Sequence 1 set");
                            }
                        }catch(IOException ioe){System.out.println(ioe.getMessage());}
                        catch(Exception e){System.out.println(e.getMessage());}
                        try {
                            if (inp.equals("2")) {
                                System.out.println("Please input name of the second sequence (Case sensitive)");
                                seq2 = fp.parse(sc.next()).toCharArray();
                                System.out.println("Sequence 2 set");
                            }
                        }catch(IOException ioe){System.out.println(ioe.getMessage());}
                        catch(Exception e){System.out.println(e.getMessage());}
                    }
                    break;
                case "RUN":
                    int costResult = seqAligner.calculateMinCost(seq1, seq2);
                    System.out.println(costResult);
                    break;
                case "BACKTRACK":
                    if (seqAligner == null || seqAligner.resultMap==null)
                        System.out.println("You can't backtrack until you run");
                    else {
                        String alignment = seqAligner.backtrack(seq1, seq2);
                        System.out.println(alignment);
                        String[] alignmentSplit = alignment.split("\\r?\\n\\r?\\n");
                        writer.writeSequences(alignmentSplit[0], alignmentSplit[1]);
                    }
                    break;
                case "Q"://do nothing and then it will quit
                    break;
                default:
                    System.out.println("Invalid command!\n"+s);


            }
        }





    }
    public void printstuff(){


    } */
}
