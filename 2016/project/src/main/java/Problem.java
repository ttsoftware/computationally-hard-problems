import java.util.HashMap;
import java.util.List;

public class Problem {

    private String s;
    private List<String> t;
    private HashMap<String, List<String>> R;

    public Problem() {
    }

    public Problem(String s, List<String> t, HashMap<String, List<String>> r) {
        this.s = s;
        this.t = t;
        R = r;
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
}
