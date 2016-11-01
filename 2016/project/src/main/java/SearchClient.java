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
                Printer.info(strategy.searchStatus());
            }
            if (Memory.shouldEnd()) {
                Printer.info(String.format("Memory limit almost reached, terminating search %s\n", Memory.stringRep()));
                return null;
            }
            if (strategy.timeSpent() > 300) { // Minutes timeout
                Printer.info(String.format("Time limit reached, terminating search %s\n", Memory.stringRep()));
                return null;
            }

            if (strategy.frontierIsEmpty()) {
                Printer.info("Frontier is empty. Returns null.");
                Printer.info(strategy.searchStatus());
                Printer.result("Explored nodes: " + iterations);

                return null;
            }

            leafNode = strategy.getAndRemoveLeaf();

            if (leafNode.isGoalState()) {
                Printer.info("Summary for " + strategy);
                Printer.info("Found solution of length " + leafNode.g());
                Printer.info(strategy.searchStatus());
                Printer.result("Explored nodes: " + iterations);

                return leafNode;
            }

            // The list of expanded nodes is shuffled randomly; see Node.java
            for (Node n : leafNode.getExpandedNodes()) {
                strategy.addToFrontier(n);
                iterations++;
            }
        }
    }
}
