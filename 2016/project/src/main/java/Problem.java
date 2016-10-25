import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Problem {

    private String s;
    private List<String> t;
    private HashMap<String, List<String>> R;
    private HashMap<String, String> result;
    private static final String REPLACER = "[\\\\w]+";
    private static final String ADDON = "[\\w]*";
    private final boolean isValid;

    public Problem(String s, List<String> t, HashMap<String, List<String>> r){
        this(s, t, r, false);
    }

    public Problem(String s, List<String> t, HashMap<String, List<String>> r, boolean prune) {
        this.s = s;
        this.t = t;
        R = r;
        this.result = new HashMap<>();
        this.isValid = true;

        if(prune) {
            R = pruneRForFirstInsertInvalidExtensions();
        }
    }

    public Problem(Problem prev, String key, String extension){
        //Copy old data structures
        this.s = prev.getS();
        this.R = new HashMap<>(prev.getR());
        this.result = new HashMap<>(prev.getResult());

        //Replace key in the string with the given extension
        this.t = prev.getT().stream()
                        .map(tString -> tString.replaceAll(key, extension)).collect(Collectors.toList());

        //Store result and remove key from R
        this.result.put(key, extension);
        this.R.remove(key);
        this.isValid = this.validate(key);
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List<String> getT() {
        return t;
    }

    public void setT(List<String> t) {
        this.t = t;
    }

    public HashMap<String, List<String>> getR() {
        return R;
    }

    public void setR(HashMap<String, List<String>> r) {
        this.R = r;
    }

    public HashMap<String, String> getResult() {
        return result;
    }

    public boolean isValid(){
        return this.isValid;
    }

    private boolean validate(String key){
        //replace all uppercase letters and matches against s
//        List<Tuple> list =
        return this.t.stream()
                .filter(ent -> ent.contains(key))
                .map(ent -> {
                    String pat = ent.replaceAll("[A-Z]", REPLACER);
                    //Append a star if not a plus
                    if (pat.charAt(pat.length() - 1) != '+') {
                        pat += ADDON;
                    }

                    //Prepend a prefix in case there is a lowercase letter first
                    if (pat.charAt(0) != '[') {
                        pat = ADDON + pat;
                    }

                    return Pattern.compile(pat);
        })
                .allMatch(mat -> {
                    boolean match = mat.matcher(s).matches();
//                    System.out.println("Match for pattern " + mat.pattern() + " : " + match);
                    return match;
                });
        //.map(pat -> new Tuple(pat, pat.matcher(this.s)))
        //.collect(Collectors.toList());

//        list.forEach(tup -> System.out.println("Pattern: " + tup.pat.pattern() + ", match: " + tup.match.matches()));
    }

    private HashMap<String, List<String>> pruneRForFirstInsertInvalidExtensions(){
        HashMap<String, List<String>> prunedR = new HashMap<>(this.R.size());

        this.R.entrySet()
                .forEach(entry -> {
                    //Get R and insert new key in pruned array
                    String R = entry.getKey();
                    prunedR.put(R, new ArrayList<>());

                    entry.getValue()
                            .forEach(extension -> {
                                boolean valid = new Problem(this, R, extension)
                                        .isValid();

                                if(valid){
                                    prunedR.get(R).add(extension);
                                }
                            });
                });

        prunedR.forEach((key, extensionSet) -> {
            System.out.println(key + ":");
            extensionSet.forEach(extension -> System.out.println("\t" + extension));
        });

        return prunedR;
    }
}