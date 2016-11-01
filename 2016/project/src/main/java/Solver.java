public class Solver {

    private final Problem initialProblem;

    public Solver(Problem problem) {
        this.initialProblem = problem;
    }

    /**
     * Bound by O((b^d * R * |R| * |T|) + |R|*s + |T|^2)
     *      where b is the branching factor and d is the depth
     *      b = size of expansions in R
     *      d = |T|
     * @return
     */
    public Node solve() {

        Problem preprocessed = new Preprocessor().preprocess(initialProblem);

        SearchClient sc = new SearchClient(preprocessed);

        Node n = sc.Search(new Strategy.StrategyBestFirst(new Heuristic.WeightedAStar()));
        // Node n = sc.Search(new Strategy.StrategyBestFirst(new Heuristic.AStar()));
        // Node n = sc.Search(new Strategy.StrategyDFS());

        return n;
    }
}
