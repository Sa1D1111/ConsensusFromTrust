import java.util.Set ;
import java.util.HashSet ;
import java.util.ArrayList ;

public class CompliantNode implements Node 
{
    private Set<Integer> transactions ;
    private boolean[] followees ; 

    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) 
    {
        this.transactions = new HashSet<>() ;
    }

    public void setFollowees(boolean[] followees) 
    {
        this.followees = followees ;
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) 
    {
        for (Transaction tx : pendingTransactions) { transactions.add(tx.id) ; }
    }

    public Set<Transaction> getProposals() 
    {
        Set<Transaction> proposals = new HashSet<>() ;
        for (int txId : transactions) { proposals.add(new Transaction(txId)) ; }
        return proposals ;
    }

    public void receiveCandidates(ArrayList<Integer[]> candidates) 
    {
        for (Integer[] candidate : candidates) 
        {
            int txID = candidate[0] ; 
            int senderNode = candidate[1] ; 

            // Accept transaction if it came from a followee
            if (followees[senderNode]) { transactions.add(txID) ; }
        }
    }
}
