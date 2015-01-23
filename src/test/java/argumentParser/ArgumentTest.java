package argumentParser;

import static org.junit.Assert.*;

import org.junit.Test;

import ConsoleArgumentParser.Argument;

public class ArgumentTest {
	private final static String name = "argumentName";
	private final static String value = "argumentValue";
	
	private Argument arg;
	
	@Test
	public void argumentBuilderTest(){
	arg = Argument.newBuilder().withName(name).withValue(value).build();	
	
	assertEquals(arg.getValue(), value);		
	}
	@Test
	public void argumentSetValueTest(){
	arg = Argument.newBuilder().withName(name).build();
	arg.addValue(value);
	
	assertEquals(arg.getValue(), value);		
	}
}
