package argumentParser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import consoleArgumentParser.*;

public class ArgumentParserTest {
	
	private static final ArgParser parser = new ArgumentParser();
	
	private static final String names [] = {"Option1", "Option2", "Option3"};
	private static final String unusedName = "OptionUnused";
	
	private static final Option OPTION_NAME1 = Option.newBuilder().withName(names[0]).withType(OptionType.NO_PARAMETERS).build();
	private static final Option OPTION_NAME2 = Option.newBuilder().withName(names[1]).withType(OptionType.ONE_PARAMETER).build();
	private static final Option OPTION_NAME3 = Option.newBuilder().withName(names[2]).withType(OptionType.NO_PARAMETERS).build();
	private static final Option OPTION_NAME4 = Option.newBuilder().withName(unusedName).withType(OptionType.NO_PARAMETERS).build();
	
	private static final OptionList options = new OptionList();
	static {
		options.addOptionByName(names[0], OPTION_NAME1);
		options.addOptionByName(names[1], OPTION_NAME2);
		options.addOptionByName(names[2], OPTION_NAME3);
		options.addOptionByName(unusedName, OPTION_NAME4);
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
