package argumentParser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import consoleArgumentParser.*;

public class ArgumentParserTest {
	
	private static final ArgParser parser = new ArgumentParser();
	
	private static final String names [] = {"Option1", "Option2", "Option3"};
	private static final String unusedName = "OptionUnused";
	
	private static final OptionList options = new OptionList();
	static {
		options.addOption(names[0], OptionType.NO_PARAMETERS);
		options.addOption(names[1], OptionType.ONE_PARAMETER);
		options.addOption(names[2], OptionType.NO_PARAMETERS);
		options.addOption(unusedName, OptionType.NO_PARAMETERS);
	}

	@Test
	public void argumentParserTest(){
		String[] args = {"-Option1","-Option2","test","-Option3"};
		
		final ArgumentList arguments = parser.parse(args, options);
		
		for(int i = 0; i<names.length;i++){
			assertTrue(arguments.hasArgument(names[i]));
		}
		
		assertFalse(arguments.hasArgument(unusedName));
		
		assertEquals(arguments.getArgumentValue(names[0]), "");
		assertEquals(arguments.getArgumentValue(names[1]), "test");
		assertEquals(arguments.getArgumentValue(names[2]), "");		
	}
	
	@Test
	public void argumentParserTestOptionalAgrument(){
		String[] args = {"-Option1","-Option2","-Option3"};
		
		final ArgumentList arguments = parser.parse(args, options);
		
		assertEquals(arguments.getArgumentValue(names[0]), "");
		assertEquals(arguments.getArgumentValue(names[1]), "");
		assertEquals(arguments.getArgumentValue(names[2]), "");		
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void catchErrorTest(){
		String[] incorrectArgs = {"-WrongArgument"};
		final ArgumentList arguments = parser.parse(incorrectArgs, options);		
	}

}
