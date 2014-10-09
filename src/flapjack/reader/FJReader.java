package flapjack.reader;

///import flapjack.exceptions.MissingStackEndTokenException;
import flapjack.exceptions.MissingStackEndTokenException;
import flapjack.exceptions.ReadFailureException;
import flapjack.exceptions.SymbolClashException;
import flapjack.exceptions.UnexpectedTokenCharacterException;
import flapjack.types.FJChar;
import flapjack.types.FJDouble;
import flapjack.types.FJInteger;
import flapjack.types.FJPackage;
import flapjack.types.FJStack;
import flapjack.types.FJSymbol;
import flapjack.types.FlapjackObject;

public class FJReader {
	public static void consumeWhitespace(FJLookaheadReader reader) {
		// TODO: Consume all whitespace or comments
		char consumableChar = reader.getCurrentChar();
		boolean itCanBeConsumed = consumableChar == '\n'
				|| consumableChar == '\t' || consumableChar == ' '
				|| consumableChar == '\\';
		while (itCanBeConsumed) {
			if (reader.getCurrentChar() == '\\') { //
				reader.consumeUntilEndOfLine();
			} else {
				if(!reader.consumeChar()) {
					break;
				}
				/*
				 * if(reader.hasNextChar()) { reader.getNextChar(); } else {
				 * break; }
				 */
			}
			consumableChar = reader.getCurrentChar();
			itCanBeConsumed = consumableChar == '\n' || consumableChar == '\t'
					|| consumableChar == ' ' || consumableChar == '\\';
		}
	}

	public static FlapjackObject readForm(FJPackage pkg,
			FJLookaheadReader reader) throws ReadFailureException, SymbolClashException {
		// TODO: Determine whether the form is a FJSymbol, FJInteger or
		// FJBoolean and handle accordingly. Throw a
		// UnexpectedTokenCharacterException if it's none of the above
		FlapjackObject readData = null;
		consumeWhitespace(reader);
		if (reader.hasCurrentChar()) {
			consumeWhitespace(reader);
			char currentChar = reader.getCurrentChar();
			boolean itIsInteger = FJInteger.stillValidNumber(currentChar);
			if (itIsInteger) {
				if (reader.getCurrentChar() != '-') {
					readData = FJInteger.readForm(reader);
					return readData;
				} else {
					if(reader.hasNextChar()) itIsInteger = FJInteger.stillValidNumber(reader.getNextChar());
					else itIsInteger = false;
					if (itIsInteger && reader.consumeChar()) {
						FJInteger readDataInteger = FJInteger.readForm(reader);
						/*if(readDataInteger instanceof FJDouble) {
							FJDouble readDataDouble = (FJDouble)(readDataInteger);
						}*/
						readDataInteger.isNegative();
						readData = readDataInteger;
						return readData;
					}
				}
			}
			if((reader.getCurrentChar() + "").equals("'")) {
				if(reader.consumeChar()) {
					FJChar c = FJChar.readForm(reader);
					if(reader.consumeChar()) {
						if((int)(reader.getCurrentChar()) != 39) {
							throw new ReadFailureException("No closing quote on the character");
						}
					} else {
						throw new ReadFailureException("No closing quote of the character");
					}
					return c;
				} else {
					throw new ReadFailureException("Failed to read character");
				}
			}
			boolean itIsSymbol = false;
			consumeWhitespace(reader);
			for (int i = 33; i < 127; i++) {
				if ((int) (currentChar) == i && !itIsInteger && i != 125 && i != 123) {
					itIsSymbol = true;
					return FJSymbol.readForm(pkg, reader);
				}
			}
			if (reader.getCurrentChar() == '{') {
				readData = FJStack.readForm(pkg, reader);
				if (reader.getCurrentChar() != '}') {
					throw new MissingStackEndTokenException("Reader stopped reading, did not encounter a closing brace. System will exit");
				} else if (reader.consumeChar()) return readData;
				return readData;
			}
			consumeWhitespace(reader);
			if (reader.getCurrentChar() == '}') {
					throw new UnexpectedTokenCharacterException("There is a mismatched closing brace in line: " + reader.getNumberOfLines()+ ". System will exit.");
			}
			if (!itIsSymbol && !itIsInteger && /*reader.getCurrentChar() != '}' &&*/ reader.hasNextChar()) {
				ReadFailureException crash = new ReadFailureException("FJReader.readForm failed to recognise data located at line "
								+ reader.getNumberOfLines()+ " as either a symbol, boolean or an integer. System will exit");
				throw crash;
			}
			return readData;
		} else {
			return null;
		}
	}

	public static FJStack readAllFormsFromString(FJPackage pkg, String input)
			throws ReadFailureException, SymbolClashException {
		/* NOT FOR NOW */
		FJStack allForms = FJStack.getEmptyStack();
		FJLookaheadReader reader = new FJLookaheadReader(input);
		FlapjackObject obj = readForm(pkg, reader);
		FJStack x = FJStack.getEmptyStack();
		//System.out.println(input + "\n");
		allForms = new FJStack(obj, FJStack.getEmptyStack());
		while(reader.hasNextChar()) {
			//System.out.println("jkdf");
			obj = readForm(pkg, reader);
			if(obj!= null)
			allForms = allForms.pushed(obj);
		}
		allForms = allForms.reverse();
		//System.out.println(allForms);
		//System.out.println(allForms.toString() + " kjsdvbh");
		// TODO: Initialize the lookaheadreader and iteratively read the forms
		// in the string.
		// Follow the output scheme in the project description for handling the
		// processed forms.
		
		return allForms;
	}
}
