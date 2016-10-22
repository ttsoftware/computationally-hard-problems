import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = args[0];

        List<String> lines = readFile(filename);

        lines.forEach(System.out::println);
    }

    public static List<String> readFile(String filename) throws IOException {
        Path path = FileSystems.getDefault().getPath(filename);
        return (List<String>) Files.lines(
                path
        ).collect(Collectors.toList());
    }
}
