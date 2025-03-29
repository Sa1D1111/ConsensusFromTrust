import java.util.ArrayList;
import java.util.Set;

/* CompliantNode refers to a node that follows the rules (not malicious)*/
public class CompliantNode implements Node {

    int numRounds;
    int round = 0; 
    int num_followees; 
    boolean[] followees; 
    Set<Transaction> pendingTransactions;

    // CompliantNode.java class requires a 4 argument constructor as defined in Simulation.java
    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        this.numRounds = numRounds ; 
    }

    public void setFollowees(boolean[] followees) {
        this.followees = followees ; 
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        this.pendingTransactions = pendingTransactions ; 
    }

    public Set<Transaction> getProposals() {
        return this.pendingTransactions ;
    }

    public void receiveCandidates(ArrayList<Integer[]> candidates) {
        // IMPLEMENT THIS
    }
}
