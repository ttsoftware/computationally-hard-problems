import java.util.*;

public class Node {

    private static Random rnd = new Random( 1 );

    public Node parent;
    public String key;
    public String extension;

    private int g;
    public final Problem problem;

    public Node(Node parent, Problem problem) {

        this.problem = problem;
        this.parent = parent;

        this.problem.pruneProblem();

        if ( parent == null ) {
            g = 0;
            //Other init options?

        } else {
            this.key = problem.key;
            this.extension = problem.extension;
            g = parent.g() + 1;
        }
    }

    public int g() {
        return g;
    }

    public boolean isInitialState() {
        return this.parent == null;
    }

    public boolean isGoalState() {
        // termination state - if it is valid and all has been replaced.
        return problem.getR().isEmpty() && problem.isValid();
    }

    public ArrayList< Node > getExpandedNodes() {


        ArrayList< Node > expandedNodes = new ArrayList< Node >();

        Map.Entry<String, List<String>> entry = this.problem.getRwithLowestNumberOfExtensions();

        if(entry == null) return expandedNodes;

        this.problem.getFutureProblems(entry.getKey())
                .forEach(prob -> expandedNodes.add(new Node(this, prob)));

        //Something with looking through all the pruned states or so. Maybe.. I should move it here instead.
        //Should create a node for each possible search

        //Fine with a little shuffle maybe - not sure.
        Collections.shuffle( expandedNodes, rnd );
        return expandedNodes;
    }

    public HashMap<String, String> getResult(){
        HashMap<String, String> result = new HashMap<>();

        Node n = this;
        while( !n.isInitialState() ) {
            result.put(n.key, n.extension);
            n = n.parent;
        }
        return result;
    }

    public LinkedList< Node > extractPlan() {
        LinkedList< Node > plan = new LinkedList< Node >();
        Node n = this;
        while( !n.isInitialState() ) {
            plan.addFirst( n );
            n = n.parent;
        }
        return plan;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        this.extractPlan()
                .forEach(node -> s.append(node.key + " : " + node.extension + "\n"));

        return s.toString();
    }

}