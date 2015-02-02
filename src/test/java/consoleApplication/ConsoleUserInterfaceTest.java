package consoleApplication;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class ConsoleUserInterfaceTest {

	private static final ConsoleUserInterface cui = new ConsoleUserInterface ();
	private static final String message = "Hello world";
	private static final String askToWriteDino = "Please write 'Dino'";
	private static final String dino = "Dino";
	
	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();
	
	@Test
	public void testWriteToScreen() {
		cui.writeMessage (message);
		assertEquals(message, log.getLog().trim());
	}
	  @Rule
	  public final TextFromStandardInputStream systemInMock= emptyStandardInputStream();
	  
	@Test
	public void testAskForUserInput() {
		systemInMock.provideText(dino);
		log.clear();
		assertEquals("Dino", cui.askForString (askToWriteDino));
		assertEquals(askToWriteDino, log.getLog().trim());
	}
}
