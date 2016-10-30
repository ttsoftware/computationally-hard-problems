public class Solver {

    private final Problem initialProblem;

    public Solver(Problem problem) {
        this.initialProblem = problem;
    }

    public Node solve() {

        Problem preprocessed = new Preprocessor().preprocess(initialProblem);

        SearchClient sc = new SearchClient(preprocessed);

        // Node n = sc.Search(new Strategy.StrategyBestFirst(new Heuristic.WeightedAStar()));
        Node n = sc.Search(new Strategy.StrategyDFS());

        return n;
    }
}
