package hangmanClient;

import java.io.IOException;

/**
 * Metodo che fa partire uno fra i due client disponibili
 * (con o senza GUI). Per decidere si basa sul valore della
 * variabile booleana GUI (può essere modificata solo manualmente).
 * 
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 */
public class Launcher {

	public static void main(String[] args) {
		boolean GUI = false;
		
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
