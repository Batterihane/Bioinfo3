package solution;

import solution.helpers.CharPair;
import solution.helpers.LinearSequenceAligner;
import solution.helpers.StringPair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 25-09-2015.
 */
//MSA's are arrays of linkedlists
public class Approx{
    StringBuilder sb;
    Map<CharPair, Integer> costmatrix;
    char[] center_seq;
    int gapcost;
    LinearSequenceAligner sa = new LinearSequenceAligner(costmatrix, gapcost);


    public LinkedList<Character>[] extend_MSA(LinkedList<Character>[] MSA, char[] newseq){
        LinkedList<Character> newseq_as_list = convert(newseq);
        sa.calculateMinCost(center_seq, newseq);
        StringPair alignment = sa.backtrack(center_seq, newseq);
        int j=0;
        char[] arra = alignment.getA().toCharArray();
        //calc new seq
        for(int i=0; i<MSA[0].size();i++){ //iter gennem MSA[0]


            Character c = MSA[0].get(i);
            Character a = arra[j];

            if(c.equals(a)){ //matches, go next
                j++;
            }
            else if(c.equals('-')){//gap not in pair, add gap in b
                newseq_as_list.add(i,'-');
            }
            else{ //there was a gap in pair, add gappy column and go next
                for (LinkedList<Character> l:MSA){
                    l.add(i,'-');
                    j++;
                }
            }
            i++;
        }
        //add new seq to MSA
        int k=1;
        List a=MSA[1];
        while( a != null){
            k++;
            a=MSA[k];
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


    public LinkedList<Character>[] sp_approx(List<char[]> seqs){//giver en MSA. Lav en score function der tæller den
        int n = seqs.size();
        LinkedList<Character>[] MSA = new LinkedList[n];
        int index_of_center_sequence = 0; //you can try other centerseqs for better results
        center_seq = seqs.get(index_of_center_sequence);
        seqs.remove(index_of_center_sequence);
        for (char[] s:seqs) {
            MSA = extend_MSA(MSA, s);
        }
        return MSA;
    }
}
