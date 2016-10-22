import org.junit.Test;

import java.io.IOException;

public class MainTest {

    @Test
    public void testMain() throws IOException {

        String[] args = {"src/test/resources/test01.SWE"};

        Main.main(args);
    }
}
