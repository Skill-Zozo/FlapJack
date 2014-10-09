package flapjack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import flapjack.exceptions.ReadFailureException;
import flapjack.reader.FJReader;
import flapjack.types.FJPackage;
import flapjack.types.FJStack;
import flapjack.machine.*;

public class FlapJackTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ReadFailureException
	 */
	public static void main(String[] args) throws IOException,
			ReadFailureException {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader("FlapJack.txt"));
			String program = "";
			for (String NewLine = ""; NewLine != null; NewLine = br.readLine()) {
				if (program == "") {
					program += br.readLine();
				} else {
					program = program + '\n' + NewLine; // Re - installing the new line characters omitted by the buffered reader.
				}
			}
			br.close();
			System.out.println(program + "\n\nTHE INTERPRETATION");
			/*FJLookaheadReader reader = new FJLookaheadReader(program);*/
			FJPackage pkg = new FJPackage();
			String programPart = "";
			FJStack stack = FJReader.readAllFormsFromString(pkg, programPart);
			System.out.println(stack.toString());
			/*while (reader.hasNextChar()) {
				FlapjackObject obj = FJReader.readForm(pkg, reader);
				stack = new FJStack(obj, stack);
				if (obj != null) {
					programPart = obj.toString();
					System.out.println(programPart);
				}
			}
			if (reader.getCurrentChar() == '}') {
				if (reader.getNumberOfClosingBraces() + 1 != reader.getNumberOfOpeningBraces()) {
					throw new MissingStackEndTokenException("Incorrect syntax. Missing curly brace. System will exit");
				}
			} else {
				if (reader.getNumberOfClosingBraces() != reader.getNumberOfOpeningBraces()) {
					throw new MissingStackEndTokenException("Incorrect syntax. Missing curly brace. System will exit");
				}
			}*/
		} catch (Exception ex) {
			System.out.println(ex.getClass());
			System.out.println(ex.getMessage());
			System.exit(1);
		}
	}
}