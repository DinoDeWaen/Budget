package consoleArgumentParser;

public class ArgumentParser implements ArgParser {

	private static final String EMPTY_STRING = "";

	private final String prefix = "-";

	public ArgumentParser() {
	}

	@Override
	public ArgumentList parse(String[] args, OptionList options)  throws Error {
		return parseArgs(args, options);
	}

	private ArgumentList parseArgs(final String[] args, final OptionList options)  throws Error {
		final ArgumentList result = new ArgumentList();
		
		int offset = 0;
		
		while (offset < args.length) {
			final String current = args[offset];
			
			final Option option = getOption(options, current);
			
			final Argument arg = getOrCreateAndAddArgument(result, removePrefix(current));
			
			
			if (option.hasParameter()) { 
				arg.addValue(offset < args.length ? args[++offset] : EMPTY_STRING);
			}
			
			offset++;
		}
		return result;
	}

	private Option getOption(final OptionList options, final String argument) throws Error {
		final Option option = options.getOptionByName(removePrefix(argument));
		
		if (option == null)
			throw new Error();
		
		return option;
	}
	
	private static Argument getOrCreateAndAddArgument(final ArgumentList args, final String id) {
		final Argument existingArg = args.getArgumentByName(id);
		if (existingArg != null) {
			return existingArg;
		}
		final Argument newArg = Argument.newBuilder().withName(id).build();
		args.addArgumentByName(id, newArg);
		return newArg;
	}
	private String removePrefix(final String id) {
		return id.replaceFirst(prefix, EMPTY_STRING);
	}
}
