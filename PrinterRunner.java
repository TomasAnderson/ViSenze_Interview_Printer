import java.util.*;

class Printer {
	private static final int LINES_PER_PAGE = 99;
	private static final int CHAR_PER_LINE = 105;
	private static final String PAGE_BREAKER = "=========================================================================================================\n";

	private static int lineCounter = 0;
	private StringBuilder sBuilder;


	public Printer(Scanner sc) {
		sBuilder = new StringBuilder();
		String line;
		while (sc.hasNextLine()) {
			if((line = sc.nextLine()) != null) {
				parse(line);
			}
		}
	}

	/*
		print content of the sBuilder
	*/
	public void print() {
		sBuilder.setLength(sBuilder.length()-1);//remove last new line character
		System.out.print(sBuilder.toString());
	}

	/*
		parse one line, append parsed lines to the sBuilder
	*/
	private void parse(String line) {
		if (line.length() == 0) return;
		if (line.replaceAll("\t", "").replaceAll(" ", "").length() == 0) return;

		int lineLength = 0;
		int index = 0;

		String currWord;
		StringBuilder sb = new StringBuilder();

		LinkedList<String> wordsList = getWordsList(line);
		while (index<wordsList.size()) {
			currWord = wordsList.get(index);
			if (lineLength + currWord.trim().length()>CHAR_PER_LINE) {
				sb.append("\n");
				lineLength = 0;
			} 
			lineLength+=currWord.length();
			sb.append(currWord);
			index++;
		}
		sb.append("\n");

		String[] lines = sb.toString().split("\n");
		for(String e: lines) {
			sBuilder.append(e.trim());
			sBuilder.append("\n");
			incrementLineCounter();
		}

	}
	/*
		increament the line counter. print page break if necessary
	*/
	private void incrementLineCounter() {
		lineCounter++;
		if (lineCounter == LINES_PER_PAGE) {
			lineCounter = 0;
			sBuilder.append(PAGE_BREAKER);
		}

	}
	/*
		convert line into list of string, space and tab included
		@param line:a input line
		@return a list of words. Spaces may be attached at the end.
	*/
	public LinkedList<String> getWordsList(String line) {
		String[] words = line.replaceAll("\t", "    ").trim().split("\\s{1}(?!\\s)");//preserve multiple spaces

		LinkedList<String> wordsList = new LinkedList<String>();

		for(int i=0; i<words.length; i++) {
			wordsList.add(words[i]+" ");
		}
		return wordsList;
	}

}


public class PrinterRunner {
	public static void main(String[] args) {
		//read string buffer
		Scanner sc = new Scanner(System.in);
		Printer printer = new Printer(sc);
		
		//print
		printer.print();
	}
}