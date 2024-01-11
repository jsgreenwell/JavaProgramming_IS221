import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HelloTests {
  // Need to test console output so redirect stdout & stderr
  // They are bytestreams (not strings) so setup conversion vars
  private final ByteArrayOutputStream outst = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errst = new ByteArrayOutputStream();
  private final PrintStream originalst = new PrintStream(outst);
  private final PrintStream originalerr = new PrintStream(errst);

  @Before
  public void changeStreams() {
    // change the streams to my captured ones
    System.setErr(new PrintStream(errst));
    System.setOut(new PrintStream(outst));
  }

  @After
  public void rollbackStreams() {
    // rollback the changes
    System.setErr(originalerr);
    System.setOut(originalst);
  }

  @Test
  public void testSolution() {
    // All that to test if the output is Hello World:
    // and a little functional programming to remove newlines
    // so print or println work.
    HelloTask.hello();
    Assert.assertEquals("Hello, World!", outst
            .toString()
            .replaceAll("\\R", ""));
  }
}