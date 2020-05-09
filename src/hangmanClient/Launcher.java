package hangmanClient;

import java.io.IOException;

public class Launcher {

	public static void main(String[] args) {
		boolean GUI = false;
		
		AHangmanClient client;
		
		if (GUI) {
			client = new HangmanClient();
		} else {
			client = new HangmanClientGUI();
		}
		try {
			client.connectToServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
