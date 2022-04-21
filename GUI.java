

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class GUI{
	
	static int currentID = 0;
	static boolean win = false;
	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();
	static JButton buttonHuman = new JButton("GO");
	static JButton buttonComputer = new JButton("CPU GO");
	static JButton buttonRetry = new JButton("RETRY");
	static JLabel label1 = new JLabel("");
	static JLabel label2 = new JLabel("");
	static JLabel label3 = new JLabel("");
	static JLabel label4 = new JLabel("");
	static JLabel winLabel = new JLabel("");
	static JLabel kaudzeLabel = new JLabel("Nonemt no kaudzes");
	static JLabel amountLabel = new JLabel("kocinus");
	static JLabel labelState = new JLabel("HUMAN WINS");
	static SpinnerModel amount = new SpinnerNumberModel(1,1,10,1);
	static SpinnerModel kaudze = new SpinnerNumberModel(1,1,4,1);
	static JSpinner spinnerDaudzums = new JSpinner(amount);
	static JSpinner spinnerKaudze = new JSpinner(kaudze);
	
	public static void main(String[] args) {
		frame.setSize(400,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		panel.setLayout(null);
		
		
		label1.setBounds(50, 50, 150, 25);
		panel.add(label1);
		
		label2.setBounds(50, 100, 150, 25);
		panel.add(label2);
		
		label3.setBounds(50, 150, 150, 25);
		panel.add(label3);
		
		label4.setBounds(50, 200, 150, 25);
		panel.add(label4);
		
		winLabel.setBounds(50, 500, 150, 25);
		panel.add(winLabel);
		
		kaudzeLabel.setBounds(50, 250, 200, 25);
		panel.add(kaudzeLabel);
		
		amountLabel.setBounds(85, 300, 150, 25);
		panel.add(amountLabel);
		
		spinnerKaudze.setBounds(165, 250, 30, 25);
		panel.add(spinnerKaudze);
		
		
		spinnerDaudzums.setBounds(50, 300, 30, 25);
		panel.add(spinnerDaudzums);
		
		
		buttonHuman.setBounds(200, 350, 100, 25);
		panel.add(buttonHuman);
		buttonHuman.addActionListener(e -> humanButton());
		
		buttonComputer.setBounds(200, 400, 100, 25);
		panel.add(buttonComputer);
		buttonComputer.addActionListener(e -> computerButton());
		
		buttonRetry.setBounds(200, 450, 100, 25);
		panel.add(buttonRetry);
		buttonRetry.addActionListener(e -> retryButton());
		
		
		
		Spele.main(args);
		
	}

	public static void humanButton() {
		if(win) {
			return;
		}
		if(Spele.humanMove((int) spinnerKaudze.getValue(),(int) spinnerDaudzums.getValue(),currentID)==-1) {
			return;
		}
		currentID = Spele.humanMove((int) spinnerKaudze.getValue(),(int) spinnerDaudzums.getValue(),currentID);
		update(currentID);
		checkWin(currentID, 1);
	}
	
	public static void computerButton() {
		if(win) {
			return;
		}
		currentID = Spele.computerMove(currentID);
		update(currentID);
		checkWin(currentID, 0);
	}
	
	public static void retryButton() {
		win = false;
		currentID = 0;
		update(currentID);
		
	}
	
	public static void update(int id) {
		label1.setText("KAUDZE 1: " + String.valueOf(Spele.visas.get(id).h1));
		label2.setText("KAUDZE 2: " + String.valueOf(Spele.visas.get(id).h2));
		label3.setText("KAUDZE 3: " + String.valueOf(Spele.visas.get(id).h3));
		label4.setText("KAUDZE 4: " + String.valueOf(Spele.visas.get(id).h4));
		winLabel.setText("");
	}
	
	private static void checkWin(int id, int player) {
		if(Spele.loki.get(id).size()==0) {
			if(player == 1) {
				winLabel.setText("HUMAN WINS");
				win = true;
			} else {
				winLabel.setText("COMPUTER WINS");
				win = true;
			}
		}
	}


}
