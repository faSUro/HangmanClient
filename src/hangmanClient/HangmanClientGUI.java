package hangmanClient;

import java.awt.Container;
import java.awt.GridLayout;
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
	 *
	 */
	@Override
	public void connectToServer() throws IOException {
		/*socket = new Socket("localhost", 8888);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);*/
		
		createGUI();
		setListeners();		
	}

	private void createGUI() {
		gui = new JFrame();
		gui.setSize(WIDTH, HEIGHT);
		
		JPanel mainPanel = new JPanel();
		Container contentPane = gui.getContentPane();
		contentPane.add(mainPanel);
		
		mainPanel.setLayout(new GridLayout(8, 1));
		
		
		JPanel[] emptyPanel = new JPanel[3]; //utili esclusivamente per il layout
		for (int i = 0; i < 3; i++) {
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
		sendButton = new JButton(" Send ");
		sendCharPanel.add(charTextField); sendCharPanel.add(sendButton);
		
		mainPanel.add(emptyPanel[0]); mainPanel.add(labelPanel1); mainPanel.add(labelPanel2); 
		mainPanel.add(labelPanel3);	mainPanel.add(emptyPanel[1]); mainPanel.add(emptyPanel[2]); 
		mainPanel.add(sendCharPanel);
		
		gui.add(mainPanel);
		
		gui.setVisible(true);
		gui.setLocationRelativeTo(null);
		gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void setListeners() {
		// TODO Auto-generated method stub
		
	}

}
















