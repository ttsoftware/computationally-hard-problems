import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.io.IOException;

public class MainTest {

    @Test
    public void testMain() throws IOException {

        String[] args = {
//                "src/test/resources/test00.SWE",
//                "src/test/resources/test01.SWE",
//                "src/test/resources/test02.SWE",
//                "src/test/resources/test03.SWE",
//                "src/test/resources/test04.SWE",
//                "src/test/resources/test05.SWE",
                "src/test/resources/test06.SWE",
        };

        for(String arg : args){
            Stopwatch watch = Stopwatch.createStarted();

            for(int i = 0 ; i < 1; i++){
                Main.main(new String[] {arg});
            }
            watch.stop();
            System.out.println(watch.toString());
        }


        //Main.main(args);
    }
}
