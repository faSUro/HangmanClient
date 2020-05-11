package hangmanClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client che si connette ad un server per giocare
 * una parita all'impiccato via console.
 * 
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 */
public class HangmanClient extends AHangmanClient {

	private BufferedReader in;
	private PrintWriter out;
	private BufferedReader console;
	
	private String line1 = "";
	private String line2 = "";
	private String line3 = "";
	
	/**
	 * Ad ogni iterazione, il server stampa 3 righe (salvate in line1,line2 e 
	 * line3) e aspetta che venga inserita una lettera dalla console.
	 * Il ciclo while continua indefinitamente, fino a quando line1 non indica
	 * che la parita è finita.
	 * 
	 * @throws IOException
	 */
	@Override
	public void connectToServer() throws IOException {
		Socket socket = new Socket("localhost", 8888);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		console = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			printLines();
			if (line1.equals("Hai perso!") || line1.equals("Hai indovinato!")) break;
			out.println(console.readLine());
		}
		
		socket.close();
	}
	
	
	/**
	 * Metodo che legge la stringa in entrata dal BufferedReader (della socket) e la suddivide
	 * per poi mostrarla tramite le JLabel.
	 */
	protected void printLines() {
		String[] lineArray = null;
		
		try {
			lineArray = divideLines(in.readLine()); //chiamata al metodo che divide la stringa in entrata in più parti
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		/*
		 * Blocco che gestisce i diversi casi che si possono verificare
		 * a seconda del numero di "TAB" presenti nella stringa in entrata.
		 */
		switch(lineArray.length) {
		case 0:
			line1 = "Si è verificato un errore";
			line2 = "";
			line3 = "";
			break;
		case 1:
			line1 = lineArray[0];
			line2 = "";
			line3 = "";
			break;
		case 2:
			line1 = lineArray[0];
			line2 = lineArray[1];
			line3 = "";
			break;
		case 3:
			line1 = lineArray[0];
			line2 = lineArray[1];
			line3 = lineArray[2];
			break;
		}
		
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(line3);		
	}
	
	/**
	 * Metodo che riceve una stringa e restituisce un
	 * array di stringhe ottenuto tramite il metodo split
	 * (parametro "TAB").
	 * @param readLine
	 * @return splitLine
	 */
	private String[] divideLines(String readLine) {
		return readLine.split("	");
	}	

}
