package hangmanClient;

import java.io.IOException;

public class Launcher {

	public static void main(String[] args) {
		boolean GUI = true;
		
		AHangmanClient client;
		
		if (GUI) {
			client = new HangmanClientGUI();
		} else {
			client = new HangmanClient();
		}
		try {
			client.connectToServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
