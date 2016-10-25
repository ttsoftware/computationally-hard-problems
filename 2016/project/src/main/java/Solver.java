import com.google.common.base.CharMatcher;

import java.util.*;
import java.util.stream.Collectors;

public class Solver {

    private Problem problem;
    private Problem result;

    public Solver(Problem problem) {
        this.problem = problem;
    }

    public boolean solve() {

        problem = preprocess(problem);

        return false;
    }

    private Problem preprocess(Problem problem) {

        // TODO: Very important to note, R are not necessarily chars but entire strings
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

        List<Map.Entry<String, Integer>> sortedT = tTotalOccurence.entrySet().stream().collect(Collectors.toList());
        Collections.sort(sortedT, (t1, t2) -> (t2.getValue()).compareTo(t1.getValue()));



        //No need to loop over all - one assignment to the first should yield a valid value.
        boolean res = recursiveSearch(sortedT.get(0), sortedT, problem);

        System.out.println("Could solve: " + res);

        if(res){
            result.getResult()
                    .forEach((key, val) -> System.out.println("R: " + key + ", ext: " + val));

        }

        return result;
    }


    private boolean recursiveSearch(Map.Entry<String, Integer> entry, List<Map.Entry<String, Integer>> remaining, Problem problem){

        //Validate if problem is valid
//        if(!problem.isValid()){
//            //It's not - return false
//            return false;
//        }

        String key = entry.getKey();
        List<String> extensions = problem.getR().get(key);

        //Remove the already processed entry - this one
        List<Map.Entry<String, Integer>> newRemaining = new ArrayList<>(remaining);
        newRemaining.remove(entry);

        //This teminates on first match
        if(newRemaining.isEmpty()) {
//            System.out.println("Last R to replace.");
            return extensions.stream().anyMatch(e -> {
                result = new Problem(problem, key, e);
                boolean valid = result.isValid();
//                System.out.println("Applying " + key + " -> " + e + " is valid: " + valid);
                return valid;
            });
        }

        return newRemaining
                .stream()
                .anyMatch(ent ->
                        extensions
                        .stream()
                        .anyMatch(e ->
                        {
                            Problem newProblem = new Problem(problem, key, e);
                            boolean isValid = newProblem.isValid();
//                            System.out.println("Trying out " + key + " -> " + e + " : " + isValid);
                            if (isValid) {
//                                System.out.println("Applying " + key + " -> " + e + " is valid. Continue search with " + ent.getKey());
                                return recursiveSearch(ent, newRemaining, newProblem);
                            }
                            return false;
        }));
    }
}
