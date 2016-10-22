import java.io.File;

public class Main {

    public static void main(String[] args) {
        String filename = args[0];

        String contents = readFile(filename);
        Problem problem = new Decoder().decode(contents);
    }

    public static String readFile(String filename) {
        File stream = new File(filename);

    }
}
