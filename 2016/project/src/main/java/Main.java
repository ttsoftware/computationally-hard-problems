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

        lines.forEach(s -> {
            Pattern pattern = Pattern.compile("^([A-Z]{1}):([a-z,]+[a-z]+)$");
            Matcher matcher = pattern.matcher(s);

            if (matcher.matches()) {
                // System.out.println(matcher.group(0));
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(2));
            }
        });


    }

    private static List<String> readFile(String filename) throws IOException {
        return (List<String>) Files.lines(
                FileSystems.getDefault().getPath(filename)
        ).collect(Collectors.toList());
    }
}
