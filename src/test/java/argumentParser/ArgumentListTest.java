package argumentParser;

import static org.junit.Assert.*;

import org.junit.Test;

import consoleArgumentParser.Argument;
import consoleArgumentParser.ArgumentList;


public class ArgumentListTest {
	private final static String[]  names = {"argumentName1", "argumentName2"};
	private final static String[] values = {"argumentValue1", "argumentValue2"};
	
	private final ArgumentList args = new ArgumentList();
	
	@Test
	public void testRetrieveArgument(){
		for (int i = 0; i<names.length;i++)
	        args.addArgumentByName(names[i], Argument.newBuilder().withName(names[i]).withValue(values[i]).build());
		
		for (int i = 0; i<names.length;i++)
		    assertEquals(args.getArgumentByName(names[i]).getValue(), values[i]);
	}

}
