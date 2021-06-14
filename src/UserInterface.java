
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being done
 * here. This class is responsible just for putting up the display on screen. It
 * then refers to the "CalcEngine" to do all the real work.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class UserInterface implements ActionListener {
	protected CalcEngine calc;
	protected JFrame frame;
	protected JTextField display;

	/**
	 * Create a user interface.
	 * 
	 * @param engine The calculator engine.
	 */
	public UserInterface(CalcEngine engine) {
		calc = engine;
		makeFrame();
		frame.setVisible(true);
	}

	/**
	 * Set the visibility of the interface.
	 * 
	 * @param visible true if the interface is to be made visible, false otherwise.
	 */
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	/**
	 * Make the frame for the user interface.
	 */
	private void makeFrame() {
		frame = new JFrame(calc.getTitle());

		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.setLayout(new BorderLayout(8, 8));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		display = new JTextField();
		display.setEditable(false);
		contentPane.add(display, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel(new GridLayout(6, 5));

		addButton(buttonPanel, "A");
		addButton(buttonPanel, "B");
		addButton(buttonPanel, "C");
		buttonPanel.add(new JLabel(" "));
		JCheckBox check = new JCheckBox("HEX");
		check.addActionListener(this);
		buttonPanel.add(check);

		addButton(buttonPanel, "D");
		addButton(buttonPanel, "E");
		addButton(buttonPanel, "F");
		addButton(buttonPanel, "(");
		addButton(buttonPanel, ")");

		addButton(buttonPanel, "7");
		addButton(buttonPanel, "8");
		addButton(buttonPanel, "9");
		addButton(buttonPanel, "AC");
		addButton(buttonPanel, "^");

		addButton(buttonPanel, "4");
		addButton(buttonPanel, "5");
		addButton(buttonPanel, "6");
		addButton(buttonPanel, "*");
		addButton(buttonPanel, "/");

		addButton(buttonPanel, "1");
		addButton(buttonPanel, "2");
		addButton(buttonPanel, "3");
		addButton(buttonPanel, "+");
		addButton(buttonPanel, "-");

		buttonPanel.add(new JLabel(" "));
		addButton(buttonPanel, "0");
		buttonPanel.add(new JLabel(" "));
		addButton(buttonPanel, "=");

		contentPane.add(buttonPanel, BorderLayout.CENTER);

		frame.pack();
		hexToggle();

	}

	/**
	 * Add a button to the button panel.
	 * 
	 * @param panel      The panel to receive the button.
	 * @param buttonText The text for the button.
	 */
	protected void addButton(Container panel, String buttonText) {
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		panel.add(button);
	}

	/**
	 * An interface action has been performed. Find out what it was and handle it.
	 * 
	 * @param event The event that has occured.
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("HEX")) {
			if (CalcEngine.HexaMode) {
				CalcEngine.HexaMode = false;
			} else {
				CalcEngine.HexaMode = true;
			}
			hexToggle();
		} else {
			try {
				CalcEngine.stringInput(event.getActionCommand());
			} catch (StackUnderflow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			redisplay();
		}
	}

	/*
	 * Creates a toggle for Hexa-Mode
	 */// https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Component.html
	private void hexToggle() {
		JPanel content = (JPanel) frame.getContentPane();
		JPanel innerContent = (JPanel) content.getComponents()[1];
		Component[] com = innerContent.getComponents();

		for (Component button : com) {
			if (button instanceof JButton) {
				JButton hexButton = (JButton) button;
				if (hexButton.getText().matches("[A-F]")) {

					if (hexButton.isVisible()) {
						hexButton.setVisible(false);
					} else
						hexButton.setVisible(true);

				}
			}
		}
	}

	/**
	 * Update the interface display to show the current value of the calculator.
	 */
	protected void redisplay() {
		display.setText("" + calc.getDisplayValue());
	}

}