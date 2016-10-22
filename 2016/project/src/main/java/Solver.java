import com.google.common.base.CharMatcher;

import java.util.*;
import java.util.stream.Collectors;

public class Solver {

    private Problem problem;

    public Solver(Problem problem) {
        this.problem = problem;
    }

    public boolean solve() {

        problem = preprocess(problem);

        return false;
    }

    private Problem preprocess(Problem problem) {

        // TODO: 1. Remove all chars in R which do not appear in s.

        HashMap<String, List<String>> newR = new HashMap<>();

        for (Map.Entry<String, List<String>> R : problem.getR().entrySet()) {
            List<String> newRValues = R.getValue().stream().filter(r -> {
                return problem.getS().contains(r);
            }).collect(Collectors.toList());

            newR.put(R.getKey(), newRValues);
        }

        problem.setR(newR);

        // TODO: Gather statistics on t's
        HashMap<String, Integer> tMaxWordOccurence = new HashMap<>();
        HashMap<String, Integer> tTotalOccurence = new HashMap<>();

        problem.getR().keySet().forEach(R -> {
            problem.getT().forEach(t -> {
                int tCount = CharMatcher.anyOf(R).countIn(t);

                Integer maxCount = tMaxWordOccurence.get(R);
                if (maxCount == null || tCount > maxCount) {
                    tMaxWordOccurence.put(R, tCount);
                }

                Integer totalCount = tTotalOccurence.get(R);
                if (totalCount != null) {
                    tTotalOccurence.put(R, tCount + totalCount);
                }
                else {
                    tTotalOccurence.put(R, tCount);
                }
            });
        });

        // TODO: 4. Sort by number of times a letter in t appears in a word t:
        // A, D, B, E
        // Try the word with most occurences first. Eliminate impossible letters in R.

        List<Map.Entry<String, Integer>> sortedT = tMaxWordOccurence.entrySet().stream().collect(Collectors.toList());
        Collections.sort(sortedT, (t1, t2) -> (t2.getValue()).compareTo(t1.getValue()));

        // TODO: Implement this recursively dumbass
        sortedT.forEach(tEntry -> {
            String t = tEntry.getKey();

            List<String> choices = problem.getR().get(t);

            choices.forEach(r -> {

            });
        });

        return problem;
    }
}
