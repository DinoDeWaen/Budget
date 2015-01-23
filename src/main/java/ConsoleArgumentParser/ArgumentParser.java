package ConsoleArgumentParser;

public class ArgumentParser implements ArgParser {

	private static final String EMPTY_STRING = "";

	private final String prefix = "-";

	public ArgumentParser() {
	}

	@Override
	public ArgumentList parse(String[] args, OptionList options) {
		return parseArgs(args, options);
	}

	private ArgumentList parseArgs(final String[] args, final OptionList options) {
		final ArgumentList result = new ArgumentList();
		
		int offset = 0;
		
		while (offset < args.length) {
			final String current = args[offset];

			if (!current.startsWith(prefix)) {
				throw new Error();
			}
			
			final Option option = options.getOptionByName(current.replaceFirst(prefix, EMPTY_STRING));
			
			if (option == null) { 
				throw new Error();
			}
			
			final Argument arg = getOrCreateAndAddArgument(result, current.replaceFirst(prefix, EMPTY_STRING));
			
			if (option.hasParameter()) { 
				arg.addValue(offset < args.length ? args[offset++] : EMPTY_STRING);
			}
			
			offset++;
		}
		return result;
	}
	
	private static Argument getOrCreateAndAddArgument(final ArgumentList actuators, final String id) {
		final Argument existingArg = actuators.getArgumentByName(id);
		if (existingArg != null) {
			return existingArg;
		}
		final Argument newArg = Argument.newBuilder().withName(id).build();
		actuators.addArgumentByName(id, newArg);
		return newArg;
	}



}
