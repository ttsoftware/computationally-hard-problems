public class Solver {

    private Problem problem;

    public Solver(Problem problem) {
        this.problem = problem;
    }

    public boolean solve() {

        this.problem = new Preprocessor().preprocess(problem);

        SearchClient sc = new SearchClient(problem);

        Node n = sc.Search(new Strategy.StrategyBestFirst(new Heuristic.WeightedAStar()));

        Printer.result("Result found:");

        if(n != null){
            Printer.result("Solved.");
            Printer.result(n.toString());
            return true;
        } else {
            Printer.result("Cannot be solved.");
        }
        return false;
    }
}
