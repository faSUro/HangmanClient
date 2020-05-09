package hangmanClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * Client che si connette ad un server per giocare
 * una parita all'impiccato (la partita è giocata automaticamente
 * ed in maniera casuale).
 * 
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 */
public class HangmanClient extends AHangmanClient {
	
	/**
	 * Ad ogni iterazione, il server stampa 3 righe (salvate in line1,line2 e 
	 * line3), tranne quando il gioco è concluso e ne vengono stampate solo 2.
	 * Il ciclo while è basato su questo e si interrompe nel caso line3 sia nulla.
	 * Non è possibile giocare "attivamente", la lettera inviata al server è generata
	 * in maniera casuale.
	 * 
	 * @throws IOException
	 */
	@Override
	public void connectToServer() throws IOException {
		Random randomGenerator = new Random();
		String line1 = "";
		String line2 = "";
		String line3 = "";
		
		Socket socket = new Socket("localhost", 8888);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		while (line3 != null) {
			line1 = in.readLine();
			line2 = in.readLine();
			line3 = in.readLine();
			
			System.out.println(line1 + "\n" + line2);
			if (line3 != null) {
				System.out.println(line3 + "\n");
			}
			
			char c = (char) ('A' + randomGenerator.nextInt(26));
			out.println(c);
		}
		
		socket.close();
	}

}
