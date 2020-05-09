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
	
	private static boolean firstClick = true;

	/**
	 *
	 */
	@Override
	public void connectToServer() throws IOException {
		socket = new Socket("localhost", 8888);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
		createGUI();		
		setListener();		
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

	private void setListener() {
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String line1 = "";
				String line2 = "";
				String line3 = "";
				
				if (!firstClick) {
					String c = charTextField.getText();
					out.println(c);
				} else {
					firstClick = false;
				}		
				
				try {
					line1 = in.readLine();
					line2 = in.readLine();
					line3 = in.readLine();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				if (line1 == null) 
					line1 = "";
				setText1(line1);
				if (line2 == null) 
					line2 = "";
				setText2(line2);
				if (line3 == null) 
					line3 = "";
				setText3(line3);
			}
			
		});
		
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

}
















