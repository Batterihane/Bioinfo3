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

    public void writeSequences(String ... seqs){
        for (int i = 0; i < seqs.length; i++) {
            writer.println(">seq" + (i+1));
            writer.println(seqs[i]);
        }
        writer.close();
    }
}
