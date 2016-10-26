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

    public LinkedList< Node > extractPlan() {
        LinkedList< Node > plan = new LinkedList< Node >();
        Node n = this;
        while( !n.isInitialState() ) {
            plan.addFirst( n );
            n = n.parent;
        }
        return plan;
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + agentCol;
//        result = prime * result + agentRow;
//        result = prime * result + Arrays.deepHashCode( boxes );
//        result = prime * result + Arrays.deepHashCode( goals );
//        result = prime * result + Arrays.deepHashCode( walls );
//        return result;
//    }
//
//    @Override
//    public boolean equals( Object obj ) {
//        if ( this == obj )
//            return true;
//        if ( obj == null )
//            return false;
//        if ( getClass() != obj.getClass() )
//            return false;
//
//        return true;
//    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        this.extractPlan()
                .forEach(node -> s.append(node.key + " : " + node.extension + "\n"));

        return s.toString();
    }

}