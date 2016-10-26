/**
 * Created by Anders on 26/10/2016.
 */
public class SearchClient {

    private final Node initialState;

    public SearchClient(Problem problem) {
        this.initialState = new Node(null, problem);
    }

    public Node Search(Strategy strategy) {


        strategy.addToFrontier(this.initialState);

        Node leafNode = null;

        int iterations = 0;
        while (true) {
            if (iterations % 10 == 0) {
                System.out.println(strategy.searchStatus());
            }
            if (Memory.shouldEnd()) {
                System.out.println(String.format("Memory limit almost reached, terminating search %s\n", Memory.stringRep()));
                return null;
            }
            if (strategy.timeSpent() > 300) { // Minutes timeout
                System.out.println(String.format("Time limit reached, terminating search %s\n", Memory.stringRep()));
                return null;
            }

            if (strategy.frontierIsEmpty()) {
                System.out.println("Frontier is empty. Returns null.");
                System.out.println(strategy.searchStatus());
                return null;
            }

            leafNode = strategy.getAndRemoveLeaf();

            if (leafNode.isGoalState()) {
                System.out.println("Summary for " + strategy);
                System.out.println("Found solution of length " + leafNode.g());
                System.out.println(strategy.searchStatus());
                return leafNode;
            }

//            strategy.addToExplored( leafNode );
            for (Node n : leafNode.getExpandedNodes()) { // The list of expanded nodes is shuffled randomly; see Node.java
                strategy.addToFrontier(n);

//                if ( !strategy.isExplored( n ) && !strategy.inFrontier( n ) ) {
//
//                }
//            }
                iterations++;
            }
        }
    }
}
