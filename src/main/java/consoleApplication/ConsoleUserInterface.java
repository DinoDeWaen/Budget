package consoleApplication;

public class ConsoleUserInterface implements UserInterface{

	@Override
	public void writeMessage(String message) {
		System.out.println(message);
	}

}
