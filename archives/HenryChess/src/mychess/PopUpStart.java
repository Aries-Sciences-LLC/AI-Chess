package mychess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopUpStart extends JFrame {
	private JLabel label1, label2, label3, label4;
	private JPasswordField passwordField;
	private JTextField field1,field2;
	private JRadioButton whiteButton, blackButton, neitherButton, bothButton,
		easyButton, mediumButton, difficultButton;
	private String password;
	private int color= 0;
        private int level = 2;   // 2=hardest
	private ChessJApplet applet;
	private ButtonGroup radioGroup;
	
	public PopUpStart(ChessJApplet applet){
		this.applet = applet;
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
                Icon icon = new ImageIcon(getClass().getResource("/Gifs/Hearts&Gambits.gif"));
		label1 = new JLabel(" Welcome!",icon, SwingConstants.LEFT);
		label1.setFont(new Font("Serif", Font.BOLD, 18));
		label4 = new JLabel("Enter password and then press \"enter\".");
		label4.setHorizontalTextPosition(SwingConstants.LEFT);
		label4.setVerticalTextPosition(SwingConstants.BOTTOM);
		passwordField = new JPasswordField ("Password");
		c.add(label1);
		c.add(label4);
		c.add (passwordField);
		TextFieldHandler tHandler = new TextFieldHandler();
		passwordField.addActionListener(tHandler);
		setSize(260,180);
		setVisible(true);
	}
	
	private class TextFieldHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if (event.getSource()==passwordField){
				JPasswordField pwd = (JPasswordField)event.getSource();
				password = new String(passwordField.getPassword());
				//applet.hasChosen();
				//applet.hasFocus();
			}
		}
	}
	
	public String password(){
		return password;
	}
	
}
