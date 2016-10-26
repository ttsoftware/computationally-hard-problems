import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static boolean print = true;

    public static void main(String[] args) throws IOException {
        String filename = args[0];

        List<String> lines = FileReader.readFile(filename);

        Problem problem = new Decoder().decode(lines);

        Solver solver = new Solver(problem);
        solver.solve();
    }
}
