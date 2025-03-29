import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CompliantNode implements Node {

    public double p_graph ; 
    public double p_malicious ; 
    public double p_txDistribution ; 
    public int numRounds ; 
    public boolean[] followees ; 
    public Set<Transaction> pendingTransactions ; 

    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        this.p_graph = p_graph ; 
        this.p_malicious = p_malicious ; 
        this.p_txDistribution = p_txDistribution ; 
        
        this.numRounds = numRounds;
        this.pendingTransactions = new HashSet<>() ;
    }

    public void setFollowees(boolean[] followees) {
        this.followees = followees ;
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        //  this.pendingTransactions.addAll(pendingTransactions);
        for (Transaction tx : pendingTransactions) { this.pendingTransactions.add(tx) ; }
    }

    public Set<Transaction> getProposals() {
        return this.pendingTransactions ; 
        // return new HashSet<>(pendingTransactions); // no filtering yet
    }

    public void receiveCandidates(ArrayList<Integer[]> candidates) {
        for (Integer[] pair : candidates) {
            int sender = pair[0];
            int txId = pair[1];

            if (sender < 0 || sender >= followees.length) continue;
            if (!followees[sender]) continue;

            Transaction tx = new Transaction(txId);
            this.pendingTransactions.add(tx);
        }
    }
}

