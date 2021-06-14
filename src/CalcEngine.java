
/**
 * The main part of the calculator doing the calculations.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class CalcEngine {

	public static boolean HexaMode = false;
	protected static String displayValue;
	private static PostFix p;
	private static int value;

	/**
	 * Create a CalcEngine.
	 */
	public CalcEngine() {
		clear();
		p = new PostFix();
		value = 0;
	}

	public static String getDisplayValue() {
		return displayValue;
	}

	public static void stringInput(String input) throws StackUnderflow, Exception {
		
		if (input.equals("=")) {
			int outcome;
			outcome = p.evaluate(p.infixToPostfix(displayValue), HexaMode);
			
			if (HexaMode)
				displayValue = Integer.toHexString(outcome).toUpperCase();
			else
				displayValue = Integer.toString(outcome);
	     	} else if (input.matches("\\w")) {

			if (HexaMode) {
				value = value * 16 + Integer.decode("#" + input);
				displayValue += String.valueOf(Integer.toHexString(value)).toUpperCase();

			} else {
				value = value * 10 + Integer.parseInt(input);
				displayValue += String.valueOf(value);
			}
		} else if (input.equals("AC")) {
			clear();
		} else {
			value = 0;
			displayValue += " " + input + " ";
		}
	}

	/**
	 * The 'C' (clear) button was pressed. Reset everything to a starting state.
	 */
	public static void clear() {
		displayValue = "";
		value = 0;
	}

	public String getTitle() {
		return "Java Calculator 2021";
	}

}
