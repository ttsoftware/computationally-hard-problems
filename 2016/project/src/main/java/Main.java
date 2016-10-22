import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = args[0];

        List<String> lines = readFile(filename);

        Problem problem = new Decoder().decode(lines);
        System.out.println(problem.getS());

    }

    private static List<String> readFile(String filename) throws IOException {
        return (List<String>) Files.lines(
                FileSystems.getDefault().getPath(filename)
        ).collect(Collectors.toList());
    }
}
