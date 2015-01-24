package argumentParser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import consoleArgumentParser.Option;
import consoleArgumentParser.OptionType;

public class OptionTest {
	private final static String name = "optionName";
	private final static String help = "optionHelp";
	private static OptionType type;
	
	private Option opt;
	
	@Test
	public void optionWithParameterTest(){
		type = OptionType.ONE_PARAMETER;
    	opt = Option.newBuilder().withName(name).withHelp(help).withType(type).build();	
	
	    assertEquals(opt.hasParameter(), true);		
	}
	@Test
	public void optionWithoutParameterTest(){
		type = OptionType.NO_PARAMETERS;
    	opt = Option.newBuilder().withName(name).withHelp(help).withType(type).build();	
	
	    assertEquals(opt.hasParameter(), false);			
	}

}
