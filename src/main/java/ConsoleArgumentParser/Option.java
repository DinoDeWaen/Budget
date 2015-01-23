package ConsoleArgumentParser;

public class Option {
	private String name;
	private String help;
	private OptionType type;
	
	private Option(Builder builder) {
		this.name = builder.name;
		this.help = builder.help;		
		this.type = builder.type;
	}

	public static Builder newBuilder(){
		return new Builder();
	}
	public boolean hasParameter(){
		return type.equals(OptionType.ONE_PARAMETER);
	}
	
	public static final class Builder {
		private String name;
		private String help;		
		private OptionType type;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		public Builder withHelp(String help) {
			this.help = help;
			return this;
		}		

		public Builder withType(OptionType type) {
			this.type = type;
			return this;
		}

		public Option build() {
			return new Option(this);
		}
	}
}
