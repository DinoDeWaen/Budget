package consoleApplication;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

public class ConsoleUserInterfaceTest {

	private static final ConsoleUserInterface cui = new ConsoleUserInterface ();
	private static final String message = "Hello world";
	
	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();
	
	@Test
	public void testWriteToScreen() {
		cui.writeMessage (message);
		assertEquals(message, log.getLog().trim());
	}
}
