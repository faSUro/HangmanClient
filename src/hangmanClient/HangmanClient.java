package hangmanClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class HangmanClient {
	
	@SuppressWarnings("resource")
	public void connectToServer() throws IOException {
		Random randomGenerator = new Random();
		String line1 = "";
		String line2 = "";
		String line3 = "";
		
		int i = 0;
		
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
	
	public static void main(String[] args) {
		HangmanClient client = new HangmanClient();
		try {
			client.connectToServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
