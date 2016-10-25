import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Problem {

    private String s;
    private List<String> t;
    private HashMap<String, List<String>> R;
    private HashMap<String, String> result;
    private final String replacer = "[\\w]+";

    public Problem(String s, List<String> t, HashMap<String, List<String>> r) {
        this.s = s;
        this.t = t;
        R = r;
        result = new HashMap<>();
    }

    public Problem(Problem prev, String key, String extension){
        //Copy old data structures
        this.s = prev.getS();
        this.R = new HashMap<>(prev.getR());
        this.result = new HashMap<>(prev.getResult());

        //Replace key in the string with the given extension
        this.t = prev.getT().stream().map(tString -> tString.replaceAll(key, extension)).collect(Collectors.toList());

        //Store result and remove key from R
        this.result.put(key, extension);
        this.R.remove(key);
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
        R = r;
    }

    public HashMap<String, String> getResult() {
        return result;
    }

    public boolean isValid(){

        //replace all uppercase letters and matches against s
        return this.t.stream().map(ent -> Pattern.compile(ent.replaceAll("[A-Z]", replacer)))
                .map(pat -> pat.matcher(this.s))
                .allMatch(Matcher::matches);
    }
}
