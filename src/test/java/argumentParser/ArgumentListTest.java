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
		buildArguments();
		
		testRetreiveArguments();
		
		testRetreiveNotUsedArgument();
		
		validateValues();
	}

	private void testRetreiveNotUsedArgument() {
		assertFalse(args.hasArgument("fake"));
	}

	private void validateValues() {
		for (int i = 0; i<names.length;i++)
		    assertEquals(args.getArgumentValue(names[i]), values[i]);
	}

	private void testRetreiveArguments() {
		for (int i = 0; i<names.length;i++)
			assertTrue(args.hasArgument(names[i]));
	}

	private void buildArguments() {
		for (int i = 0; i<names.length;i++)
	        args.addArgument(names[i], Argument.newBuilder().withName(names[i]).withValue(values[i]).build());
	}

}
