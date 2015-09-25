package solution;

import solution.helpers.CharPair;
import solution.helpers.LinearSequenceAligner;
import solution.helpers.StringPair;

import java.util.*;

/**
 * Created by Thomas on 25-09-2015.
 */
//MSA's are arrays of linkedlists
public class Approx{

    StringBuilder sb;
    Map<CharPair, Integer> costmatrix;
    char[] center_seq;
    int gapcost;
    LinearSequenceAligner sa;

    public Approx(Map<CharPair,Integer> cm,int g){
        costmatrix = cm;
        gapcost = g;
        sa = new LinearSequenceAligner(costmatrix, gapcost);

    }


    public LinkedList<Character>[] extend_MSA(LinkedList<Character>[] MSA, char[] newseq){

        sa.calculateMinCost(center_seq, newseq);
        StringPair alignment = sa.backtrack(center_seq, newseq);
        LinkedList<Character> newseq_as_list = convert(alignment.getB().toCharArray());
        int j=0;
        char[] arra = alignment.getA().toCharArray();
        //calc new seq
        Character c;
        Character a;

        for(int i=0; i<MSA[0].size() || j < arra.length; i++){ //iter gennem MSA[0]
            c = i < MSA[0].size() ? MSA[0].get(i) : '_';
            a = j < arra.length ? arra[j] : '_';

            if(c.equals(a)){ //matches, go next
                j++;
            }
            else if(c.equals('-')){//gap not in pair, add gap in a
                newseq_as_list.add(i,'-');
            }
            else{ //there was a gap in pair, add gappy column and go next
                j++;
                for (LinkedList<Character> l:MSA){
                    if(l!=null) {
                        l.add(i, '-');
                    }
                }
            }
        }
        //add new seq to MSA
        int k=1;
        while( MSA[k] != null){
            k++;
        }
        MSA[k]=newseq_as_list;


        return MSA;
    }
    public LinkedList<Character> convert (char[] s){
        LinkedList<Character> res = new LinkedList<Character>();
        for (char c : s){
            res.add(c);
        }
    return res;
    }

    public String[] MSA_to_strings(LinkedList<Character>[] MSA){
        int n = MSA.length;
        String[] res = new String[n];
        for(int i=0;i<n;i++){
            sb = new StringBuilder(MSA[0].size());
            for(Character c:MSA[i]){
                sb.append(c);
            }
            res[i]=sb.toString();
        }
        return res;
    }


    public String[] sp_approx(List<char[]> seqs){//giver en MSA. Lav en score function der tæller den
        int n = seqs.size();
        LinkedList<Character>[] MSA = new LinkedList[n];
        int index_of_center_sequence = 0; //you can try other centerseqs for better results
        center_seq = seqs.get(index_of_center_sequence);
        seqs.remove(index_of_center_sequence);
        MSA[0] = convert(center_seq);
        for (char[] s:seqs) {
            extend_MSA(MSA, s);
        }
        String[] str_list = MSA_to_strings(MSA);
        return str_list;
    }
}
