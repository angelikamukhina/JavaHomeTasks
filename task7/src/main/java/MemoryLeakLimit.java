import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class MemoryLeakLimit implements TestRule {
    static private long mbToBytes = 1024 * 1024;
    private long memoryLimit;

    public void limit(long mb) {
        memoryLimit = mb * mbToBytes;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.gc();
                long usedMemoryBefore = getUsedMemory();
                base.evaluate();
                System.gc();
                long usedMemoryAfter = getUsedMemory();
                if (usedMemoryAfter - usedMemoryBefore > memoryLimit) {
                    throw new Exception("Memory limit exceeded");
                }
            }

            private long getUsedMemory() {
                return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            }
        };
    }
}
