package solution.helpers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Thomas on 11-09-2015.
 */
public class FastaWriter {
    PrintWriter writer;

    public FastaWriter(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(filename + ".fasta", "UTF-8");
    }

    public void writeSequences(String seq1, String seq2){
        writer.println(">seq1");
        writer.println(seq1);
        writer.println();
        writer.println(">seq2");
        writer.println(seq2);
        writer.close();
    }
}
