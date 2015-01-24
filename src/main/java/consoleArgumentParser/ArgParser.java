package consoleArgumentParser;


public interface ArgParser {
	ArgumentList parse(String[] args, OptionList options);
}
