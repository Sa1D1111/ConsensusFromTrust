import java.util.*;

public class CompliantNode implements Node {
    private Set<Integer> transactions; // Transactions this node believes are valid
    private boolean[] followees; // Array to track which nodes are followed
    private int numRounds; // Total simulation rounds
    private int currentRound; // Current round count

    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        this.numRounds = numRounds;
        this.currentRound = 0;
        this.transactions = new HashSet<>();
    }

    public void setFollowees(boolean[] followees) {
        this.followees = followees;
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        for (Transaction tx : pendingTransactions) {
            transactions.add(tx.id);
        }
    }

    public Set<Transaction> getProposals() {
        // Convert the set of known transactions into a set of Transaction objects
        Set<Transaction> proposals = new HashSet<>();
        for (int txId : transactions) {
            proposals.add(new Transaction(txId));
        }
        return proposals;
    }

    public void receiveCandidates(ArrayList<Integer[]> candidates) {
        currentRound++; // Track rounds
        for (Integer[] candidate : candidates) {
            int txID = candidate[0]; // Transaction ID
            int senderNode = candidate[1]; // Node that proposed this transaction

            // Accept transaction if it came from a followee
            if (followees[senderNode]) {
                transactions.add(txID);
            }
        }
    }
}
