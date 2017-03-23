import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    private Main() {}
    public static void main(String[] args) {
        LOG.fatal("fatal");
        LOG.error("error");
        LOG.warn("warn");
        LOG.info("info");
        LOG.debug("debug");
        LOG.trace("trace");
    }
}
