package flapjack.reader;

import flapjack.exceptions.NonASCIICharacterException;

public class FJLookaheadReader {
	// TODO: Instance variables as needed to keep track of
	// 1) The string that contains the program
	// 2) The position in the string where we are currently at
	// 3) The length of the string
	private String program;
	private int position;
	private int programLength;
	private int numberOfOpeningBraces;
	private int numberOfClosingBraces;
	private int numberOfLines;
	private boolean commentLine = false;

	public FJLookaheadReader(String inputString)
			throws NonASCIICharacterException {
		/*
		 * Initialise the Lookaheadreader with the parameter string, which contains the program. Remember to set the current position and length as well as check that the string contains only legal characters.
		 */
		this.program = inputString;
		this.programLength = inputString.length();
		this.position = 0;
		this.numberOfClosingBraces = 0;
		this.numberOfOpeningBraces = 0;
		this.numberOfLines = 1; // For the reading of the client code
		int numOfLines = 0; // For the scanning of the client code if it has the appropriate characters, helps identify location of illegal character
		int intValueOfAsciiCharacter = 0;
		for (int i = 0; i < programLength; i++) {
			intValueOfAsciiCharacter = (int) (program.charAt(i)); // Int value of character
			if (program.charAt(i) == '\n') {
				numOfLines++;
			}
			boolean whiteSpace = intValueOfAsciiCharacter < 15 && intValueOfAsciiCharacter > 8;
			if ((intValueOfAsciiCharacter < 32 || intValueOfAsciiCharacter > 127) && !whiteSpace) {
				NonASCIICharacterException errorCrash = new NonASCIICharacterException(
						"Error in line: " + numOfLines
								+ " Illegal character found. Illegal character's octal value is: " + (int)(program.charAt(i)) +  "System will exit ");
				throw errorCrash;
			}
		}
	}

	public int getLengthOfProgram() {
		return this.programLength;
	}

	public boolean hasCurrentChar() {
		// return true if our current position in the string is not past the
		// string's end
		// (ie. there's at least one character in the string that hasn't been
		// consumed, the one at the
		// position we are currently looking at).
		return this.position < this.programLength;
	}

	public boolean hasNextChar() {
		// return true if the position *after* our current position in the
		// string is
		// not past the string's end (ie. there's at least two characters left
		// in
		// the string to consume, the current and the next one).
		// System.out.println(this.position+2 < this.programLength-1);
		int currentPositon = this.position + 1;
		int endOfReader = this.programLength;
		boolean hasIt = currentPositon < endOfReader;
		return hasIt;
	}

	public char getCurrentChar() {
		// Get the character at the current position
		return this.program.charAt(this.position);
	}

	public char getNextChar() {
		// Get the character at the next position
		return this.program.charAt(this.position + 1);
	}

	public boolean consumeChar() {
		// Consume the current character. Return true if a character could be
		// consumed, otherwise false.
		char consumableChar = this.getCurrentChar();
		boolean canBeConsumed = false;
		if (this.hasNextChar()) {
			this.position++;
			canBeConsumed = true;
			if (consumableChar == '\n') {
				this.numberOfLines++;
			}
		}
		return canBeConsumed;
	}

	public void consumeUntilEndOfLine() {
		// Consume characters until the end of line character is reached.
		int position = this.position;
		char currentChar = this.getCurrentChar();
		for (int i = this.position; i < this.programLength; i++) {
			if ((int) (currentChar) == 10) { // Reads from our current position
												// till it encounters the
												// "Enter" character, it then
												// assigns the position of our
												// reader to the that position
				position = i;
				break;
			}
			if (this.consumeChar()) {
				currentChar = this.getCurrentChar();
			} else {
				position = this.programLength -1;
				this.commentLine = true;
			}
		}
		this.position = position;
	}

	public int increaseNumberOfClosingBraces() {
		return this.numberOfClosingBraces++;
	}

	public int getNumberOfClosingBraces() {
		return this.numberOfClosingBraces;
	}

	public int getNumberOfOpeningBraces() {
		return this.numberOfOpeningBraces;
	}

	public int increaseNumberOfOpeningBraces() {
		return this.numberOfOpeningBraces++;
	}

	public int getNumberOfLines() { //For specifying the line of error
		return numberOfLines;
	}

	public boolean isCommentLine() {
		return commentLine;
	}
}
