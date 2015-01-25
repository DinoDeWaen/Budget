package consoleArgumentParser;

public class ArgumentParser implements ArgParser {

	private static final String EMPTY_STRING = "";

	private final String prefix = "-";

	public ArgumentParser() {
	}

	@Override
	public ArgumentList parse(String[] args, OptionList options)  throws IllegalArgumentException {
		return parseArgs(args, options);
	}

	private ArgumentList parseArgs(final String[] args, final OptionList options)  throws Error {
		final ArgumentList result = new ArgumentList();
		
		int offset = 0;
		
		while (offset < args.length) {
			final String current = removePrefix(args[offset]);
			
			if (! options.hasOption(current))
				throw new IllegalArgumentException(current + " is not an valid argument see help");
			
			result.addArgumentByName(current, Argument.newBuilder().withName(current).build());
			
			if (options.hasParameter(current) && nextArgumentExists(args, offset) && isValue(args, offset))
				result.addArgumentValue(current, args[++offset]);
			
			offset++;
		}
		return result;
	}

	private boolean isValue(final String[] args, int offset) {
		return ! args[offset+1].startsWith(prefix);
	}

	private boolean nextArgumentExists(final String[] args, int offset) {
		return offset < args.length -1;
	}
	
	private String removePrefix(final String id) {
		return id.replaceFirst(prefix, EMPTY_STRING);
	}
}
