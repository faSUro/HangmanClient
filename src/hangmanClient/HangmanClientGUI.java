package hangmanClient;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Client dotato di interfaccia grafica che permette di giocare una
 * partita all'impiccato.
 * 
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 */
public class HangmanClientGUI extends AHangmanClient {
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 300;
	
	private JFrame gui;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextField charTextField;
	private JButton sendButton;
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	/**
	 * Metodo che permette al client di connettersi al
	 * server tramite una socket.
	 * Si occupa anche di creare la GUI e settarne il listener.
	 */
	@Override
	public void connectToServer() throws IOException {
		socket = new Socket("localhost", 8888);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
		createGUI();	
		
		printLines();
		
		setListener();		
	}

	/**
	 * Metodo che si occupa di inizializzare la variabile
	 * di classe "gui". Essa è composta da diverse JLabel,
	 * un JTexField ed un JButton.
	 */
	private void createGUI() {
		gui = new JFrame();
		gui.setTitle("Hangman");
		gui.setSize(WIDTH, HEIGHT);
		
		JPanel mainPanel = new JPanel();
		Container contentPane = gui.getContentPane();
		contentPane.add(mainPanel);
		
		mainPanel.setLayout(new GridLayout(8, 1));
		
		
		JPanel[] emptyPanel = new JPanel[3]; //label vuote, utili esclusivamente per il layout
		for (int i = 0; i < 2; i++) {
			emptyPanel[i] = new JPanel();
		}
		
		JPanel labelPanel1 = new JPanel();
		label1 = new JLabel();
		labelPanel1.add(label1);
		
		JPanel labelPanel2 = new JPanel();
		label2 = new JLabel();
		labelPanel2.add(label2);
		
		JPanel labelPanel3 = new JPanel();
		label3 = new JLabel();
		labelPanel3.add(label3);
		
		JPanel sendCharPanel = new JPanel();
		charTextField = new JTextField(20);
		sendButton = new JButton(" Send character ");
		sendCharPanel.add(charTextField); sendCharPanel.add(sendButton);
		
		mainPanel.add(emptyPanel[0]); mainPanel.add(labelPanel1); mainPanel.add(labelPanel2); 
		mainPanel.add(labelPanel3);	mainPanel.add(emptyPanel[1]); mainPanel.add(sendCharPanel);
		
		gui.add(mainPanel);
		
		gui.setVisible(true);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}


	/**
	 * Metodo che aggiunge un listener al JButton.
	 * Una volta premuto, stampa il testo nel JTextField tramite il PrintWriter.
	 * Poi, chiama il metodo per mostrare la situazione della partita.
	 * Infine, "svuota" il JTextField del suo contenuto.
	 */
	private void setListener() {
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {					
				String c = charTextField.getText();
				out.println(c);	
				
				printLines();

				charTextField.setText("");	
				
				if (label1.getText().equals("Hai perso!") || label1.getText().equals("Hai indovinato!")) {
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	/**
	 * Metodo che legge la stringa in entrata dal BufferedReader e la suddivide
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
			setText1("Si è verificato un errore");
			setText2("");
			setText3("");
			break;
		case 1:
			setText1(lineArray[0]);
			setText2("");
			setText3("");
			break;
		case 2:
			setText1(lineArray[0]);
			setText2(lineArray[1]);
			setText3("");
			break;
		case 3:
			setText1(lineArray[0]);
			setText2(lineArray[1]);
			setText3(lineArray[2]);
			break;
		}
		
	}

	private void setText1(String text) {
		label1.setText(text);
	}
	
	private void setText2(String text) {
		label2.setText(text);
	}
	
	private void setText3(String text) {
		label3.setText(text);
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
















