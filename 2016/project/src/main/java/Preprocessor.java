import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Anders on 26/10/2016.
 */
public class Preprocessor {

    public Problem preprocess(Problem problem){
        String s = problem.getS();



        //Prune R for invalid extensions
        HashMap<String, List<String>> R = pruneR(problem);

        //Prune T for subsets of other sets
        List<String> T = pruneT(problem);

        //preprocessed.setR(pruneRForFirstInsertInvalidExtensions(preprocessed));
        //True for init pruning
        Problem preprocessed = new Problem(s, T, R);

        return preprocessed;
    }

    private HashMap<String, List<String>> pruneR(Problem problem){

        HashSet<Character> RSet = new HashSet<>();

        problem.getT()
                .forEach(t -> {
                    char[] temp = t.toCharArray();
                    for(int i = 0; i < temp.length; i++){
                        if(Character.isUpperCase(temp[i])){
                            RSet.add(temp[i]);
                        }
                    }
                });

        System.out.println("Allowed Rs in problem:");
        RSet.forEach(r -> System.out.println(r));

        HashMap<String, List<String>> newR = new HashMap<>();

        for (Map.Entry<String, List<String>> R : problem.getR().entrySet()) {

            //Prunes not needed R values
            if(!RSet.contains(R.getKey().charAt(0))) {
                System.out.println("Pruned R: " + R.getKey());
                continue;
            }
            List<String> newRValues = R.getValue().stream().filter(r -> {
                return problem.getS().contains(r);
            }).collect(Collectors.toList());

            newR.put(R.getKey(), newRValues);
        }

        return newR;
    }

    private List<String> pruneT(Problem problem){
        List<String> list = new ArrayList<>(problem.getT());
        Collections.sort(list, (o1, o2) -> o2.length() - o1.length());

        List<String> prunedT = new ArrayList<>(list.size());

        list.forEach(sorted -> {
            //Skip if it only contains lowercase letters
            if(sorted.matches("[a-z]]")) return;

            //If prunedT contains sorted OR prunedT contains a superset of sorted, do not insert
            if(! ( prunedT.contains(sorted) || prunedT.stream().anyMatch(pruned -> pruned.contains(sorted) ) ) ){
                prunedT.add(sorted);
            } else {
                //prints the pruned T
                System.out.println("Pruned T: " + sorted);
            }
        });

        //Prints the new T
//        prunedT.forEach(t -> System.out.println(t));

        return list;
    }

    //Prunes all extensions that are invalid even if inserted all by it self

}
