package stas.misc;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

//import java.awt.events.*;

public class JavaSwingTest extends JFrame {
	/**
	 * 
	 */

	JButton button1;
	JTextField textField1;
	JTextArea textArea1;
	int buttonClicked;

	public JavaSwingTest() {
		// TODO Auto-generated constructor stub

		this.setSize(400, 400);
		Toolkit tk = Toolkit.getDefaultToolkit();

		Dimension dim = tk.getScreenSize();
		tk.beep();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);

		this.setLocation(xPos, yPos);
		// this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("My First Frame");
		JPanel thePanel = new JPanel();

		JLabel label1 = new JLabel("Tell me something");
		label1.setText("new text");
		label1.setToolTipText("Doesnt do anything");
		thePanel.add(label1);

		button1 = new JButton("Send");
		button1.setText("New Button");
		button1.setToolTipText("It's a button");

		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);

		thePanel.add(button1);

		textField1 = new JTextField("", 15);
		ListenForKeys lForKeys = new ListenForKeys();
		textField1.addKeyListener(lForKeys);

		thePanel.add(textField1);

		textArea1 = new JTextArea(15, 20);
		textArea1.setText("Tracking events \n");

		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(true);

		// int numberOfLines = textArea1.getLineCount();
		// textArea1.append("number of Lines:" + numberOfLines);
		JScrollPane scrollbar1 = new JScrollPane(textArea1,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		thePanel.add(scrollbar1);

		this.add(thePanel);

		ListenForWindow lForWindow = new ListenForWindow();
		this.addWindowListener(lForWindow);

		this.setVisible(true);

		ListenForMouse lForMouse = new ListenForMouse();
		thePanel.addMouseListener(lForMouse);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JavaSwingTest();
	}

	// Implement Listeners
	private class ListenForButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == button1) {
				buttonClicked++;

				textArea1
						.append("Button clicked " + buttonClicked + " times\n");
				e.getSource().toString();

			}
		}
	}

	private class ListenForKeys implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			textArea1.append("Key Hit:" + e.getKeyChar() + "\n");

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class ListenForWindow implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			textArea1.append("Window Created");

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			textArea1.append("Window is Minimized");

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			textArea1.append("Window in Normal State");

		}

		@Override
		public void windowActivated(WindowEvent e) {
			textArea1.append("Window is active");

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			textArea1.append("Window is not active");

		}
	}

	private class ListenForMouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			textArea1.append("Mouse Panel pos: " + e.getX() + " " + e.getY());
			textArea1.append("Mouse Screen pos: " + e.getXOnScreen() + " "
					+ e.getYOnScreen());
			textArea1.append("Mouse Button: " + e.getButton());
			textArea1.append("Mouse Clicks: " + e.getClickCount());

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
