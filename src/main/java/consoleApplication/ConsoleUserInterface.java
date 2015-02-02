package consoleApplication;

import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface{

	@Override
	public void writeMessage(String message) {
		System.out.println(message);
	}

	public String askForString(String asktowritedino) {
		String name = "";
		writeMessage(asktowritedino);
		
		try (Scanner in = new Scanner(System.in)) {
			name = in.nextLine();		
		}
				
		return name;
	}

}
