// import java.util.ArrayList;
// import java.util.Set;

// /* CompliantNode refers to a node that follows the rules (not malicious)*/
// public class CompliantNode implements Node {

//     public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
//         // IMPLEMENT THIS
//     }

//     public void setFollowees(boolean[] followees) {
//         // IMPLEMENT THIS
//     }

//     public void setPendingTransaction(Set<Transaction> pendingTransactions) {
//         // IMPLEMENT THIS
//     }

//     public Set<Transaction> getProposals() {
//         // IMPLEMENT THIS
//     }

//     public void receiveCandidates(ArrayList<Integer[]> candidates) {
//         // IMPLEMENT THIS
//     }
// }

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CompliantNode implements Node {

    private boolean[] followees;
    private Set<Transaction> pendingTransactions;
    private Map<Transaction, Integer> transactionCount;
    private int numRounds;
    private int currentRound;

    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        // IMPLEMENT THIS
        this.numRounds = numRounds;
        this.currentRound = 0;
        this.pendingTransactions = new HashSet<>();
        this.transactionCount = new HashMap<>();
    }

    public void setFollowees(boolean[] followees) {
        // IMPLEMENT THIS
        this.followees = followees;
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        // IMPLEMENT THIS
        this.pendingTransactions.addAll(pendingTransactions);
        for (Transaction tx : pendingTransactions) {
            transactionCount.put(tx, 1); // trust own txs
        }
    }

    public Set<Transaction> getProposals() {
        // IMPLEMENT THIS
        currentRound++;
        return new HashSet<>(pendingTransactions); // no filtering yet
    }

    public void receiveCandidates(ArrayList<Integer[]> candidates) {
        // IMPLEMENT THIS
        for (Integer[] pair : candidates) {
            int sender = pair[0];
            int txId = pair[1];

            if (sender < 0 || sender >= followees.length) continue;
            if (!followees[sender]) continue;

            Transaction tx = new Transaction(txId);
            pendingTransactions.add(tx);
            transactionCount.put(tx, transactionCount.getOrDefault(tx, 0) + 1);
        }
    }
}

