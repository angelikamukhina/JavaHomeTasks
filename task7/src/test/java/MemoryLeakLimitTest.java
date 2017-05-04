import org.junit.Rule;
import org.junit.Test;


public class MemoryLeakLimitTest {
    @Rule
    final public MemoryLeakLimit memoryLeakIdentifier = new MemoryLeakLimit();

    @Test
    public void leakTest() {
        memoryLeakIdentifier.limit(1);
        long[] veryBigBuffer = new long[1000000];
    }
}