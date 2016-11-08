import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = args[0];

        List<String> lines = FileReader.readFile(filename);

        Problem problem = new Decoder().decode(lines);

        Solver solver = new Solver(problem);
        Node n = solver.solve();

        if (n != null) {
            String solutionFile = new File(filename).getName().replace(".SWE", "") + ".SOL";
            FileWriter outputFile = new FileWriter(solutionFile);
//            Printer.result("YES");
            HashMap<String, String> result = n.getResult();
            problem.getR()
                    .forEach((key, val) -> {
                        try {

                            String extension = val.get(0);

                            if (result.containsKey(key)) {
                                extension = result.get(key);
                            }

                            outputFile.append(key + ":" + extension + "\n");

                        } catch (IOException e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                    });

            outputFile.flush();
            outputFile.close();

        } else {
            Printer.result("NO");
        }
    }
}
