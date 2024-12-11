/*
 * RUNI version of the Scrabble game.
 */

import java.util.Dictionary;

public class Scrabble {

	// Note 1: "Class variables", like the five class-level variables declared
	// below,
	// are global variables that can be accessed by any function in the class. It is
	// customary to name class variables using capital letters and underline
	// characters.
	// Note 2: If a variable is declared "final", it is treated as a constant value
	// which is initialized once and cannot be changed later.

	// Dictionary file for this Scrabble game
	static final String WORDS_FILE = "dictionary.txt";

	// The "Scrabble value" of each letter in the English alphabet.
	// 'a' is worth 1 point, 'b' is worth 3 points, ..., z is worth 10 points.
	static final int[] SCRABBLE_LETTER_VALUES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
			1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

	// Number of random letters dealt at each round of this Scrabble game
	static int HAND_SIZE = 10;

	// Maximum number of possible words in this Scrabble game
	static int MAX_NUMBER_OF_WORDS = 100000;

	// The dictionary array (will contain the words from the dictionary file)
	static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];

	// Actual number of words in the dictionary (set by the init function, below)
	static int NUM_OF_WORDS;

	// Populates the DICTIONARY array with the lowercase version of all the words
	// read
	// from the WORDS_FILE, and sets NUM_OF_WORDS to the number of words read from
	// the file.
	public static void init() {
		// Declares the variable in to refer to an object of type In, and initializes it
		// to represent
		// the stream of characters coming from the given file. Used for reading words
		// from the file.
		In in = new In(WORDS_FILE);
		System.out.println("Loading word list from file...");
		NUM_OF_WORDS = 0;
		while (!in.isEmpty()) {
			// Reads the next "token" from the file. A token is defined as a string of
			// non-whitespace characters. Whitespace is either space characters, or
			// end-of-line characters.
			DICTIONARY[NUM_OF_WORDS++] = in.readString().toLowerCase();
		}
		System.out.println(NUM_OF_WORDS + " words loaded.");
	}

	// Checks if the given word is in the dictionary.
	public static boolean isWordInDictionary(String word) {
		if (word == "") {
			return false;
		}
		int num1, num2 = NUM_OF_WORDS;
		switch (word.charAt(0)) {
			case 'a':
				num1 = 0;
				num2 = 4623;
				break;
			case 'b':
				num1 = 4623;
				num2 = 10278;
				break;
			case 'c':
				num1 = 10278;
				num2 = 17461;
				break;
			case 'd':
				num1 = 17461;
				num2 = 21956;
				break;
			case 'e':
				num1 = 21956;
				num2 = 24775;
				break;
			case 'f':
				num1 = 24774;
				num2 = 28629;
				break;
			case 'g':
				num1 = 28629;
				num2 = 32049;
				break;
			case 'h':
				num1 = 32049;
				num2 = 35170;
				break;
			case 'i':
				num1 = 35170;
				num2 = 36938;
				break;
			case 'j':
				num1 = 36938;
				num2 = 37946;
				break;
			case 'k':
				num1 = 37946;
				num2 = 39287;
				break;
			case 'l':
				num1 = 39287;
				num2 = 42402;
				break;
			case 'm':
				num1 = 42402;
				num2 = 46792;
				break;
			case 'n':
				num1 = 46792;
				num2 = 48502;
				break;
			case 'o':
				num1 = 48502;
				num2 = 50858;
				break;
			case 'p':
				num1 = 50858;
				num2 = 57008;
				break;
			case 'q':
				num1 = 57008;
				num2 = 57465;
				break;
			case 'r':
				num1 = 57465;
				num2 = 62258;
				break;
			case 's':
				num1 = 62258;
				num2 = 72002;
				break;
			case 't':
				num1 = 72002;
				num2 = 76803;
				break;
			case 'u':
				num1 = 76803;
				num2 = 78783;
				break;
			case 'v':
				num1 = 78783;
				num2 = 80222;
				break;
			case 'w':
				num1 = 80222;
				num2 = 82690;
				break;
			case 'x':
				num1 = 82690;
				num2 = 82759;
				break;
			case 'y':
				num1 = 82759;
				num2 = 83241;
				break;
			case 'z':
				num1 = 83241;
				break;
			default:
				num1 = 0;
				break;
		}

		for (int i = num1; i < num2; i++) {
			if (word.equals(DICTIONARY[i])) {
				return true;
			}
		}

		return false;
	}

	// Returns the Scrabble score of the given word.
	// If the length of the word equals the length of the hand, adds 50 points to
	// the score.
	// If the word includes the sequence "runi", adds 1000 points to the game.
	public static int wordScore(String word) {
		int score = 0;
		int[] letters = new int[26];

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			letters[ch - 97]++;

		}
		for (int i = 0; i < letters.length; i++) {
			score += letters[i] * SCRABBLE_LETTER_VALUES[i];
		}
		score *= word.length();
		if (word.length() == HAND_SIZE) {
			score += 50;
		}
		if (MyString.subsetOf("runi", word)) {
			score += 1000;
		}
		return score;
	}

	// Creates a random hand of length (HAND_SIZE - 2) and then inserts
	// into it, at random indexes, the letters 'a' and 'e'
	// (these two vowels make it easier for the user to construct words)
	public static String createHand() {
		String ans = MyString.randomStringOfLetters(HAND_SIZE - 2);
		ans = MyString.insertRandomly('a', ans);
		ans = MyString.insertRandomly('e', ans);
		return ans;
	}

	// Runs a single hand in a Scrabble game. Each time the user enters a valid
	// word:
	// 1. The letters in the word are removed from the hand, which becomes smaller.
	// 2. The user gets the Scrabble points of the entered word.
	// 3. The user is prompted to enter another word, or '.' to end the hand.
	public static void playHand(String hand) {
		String temp = hand;
		int score = 0;
		// Declares the variable in to refer to an object of type In, and initializes it
		// to represent
		// the stream of characters coming from the keyboard. Used for reading the
		// user's inputs.
		In in = new In();
		while (temp.length() > 0) {
			System.out.println("Current Hand: " + MyString.spacedString(temp));
			System.out.println("Enter a word, or '.' to finish playing this hand:");
			// Reads the next "token" from the keyboard. A token is defined as a string of
			// non-whitespace characters. Whitespace is either space characters, or
			// end-of-line characters.
			String input = in.readString();
			if (input.equals(".")) {
				break;
			} else if (!(MyString.subsetOf(input, temp))) {
				System.out.println("Invalid word. Try again.");
			} else if (isWordInDictionary(input)) {
				score += wordScore(input);
				System.out.println(input + " earned " + wordScore(input) + " points. Score: " + score + " points");
				temp = MyString.remove(temp, input);
			} else {
				System.out.println("No such word in the dictionary. Try again.");
			}
		}
		if (temp.length() == 0) {
			System.out.println("Ran out of letters. Total score: " + score + " points");
		} else {
			System.out.println("End of hand. Total score: " + score + " points");
		}
	}

	// Plays a Scrabble game. Prompts the user to enter 'n' for playing a new hand,
	// or 'e'
	// to end the game. If the user enters any other input, writes an error message.
	public static void playGame() {
		// Initializes the dictionary
		init();
		// The variable in is set to represent the stream of characters
		// coming from the keyboard. Used for getting the user's inputs.
		In in = new In();

		while (true) {
			System.out.println("Enter n to deal a new hand, or e to end the game:");
			// Gets the user's input, which is all the characters entered by
			// the user until the user enter the ENTER character.
			String input = in.readString();
			if (input.equals("e")) {
				break;
			} else if (input.equals("n")) {
				playHand(createHand());
			}
		}
	}

	public static void main(String[] args) {
		//// Uncomment the test you want to run
		//// testBuildingTheDictionary();
		//// testScrabbleScore();
		//// testCreateHands();
		//// testPlayHands();
		playGame();
	}

	public static void testBuildingTheDictionary() {
		init();
		// Prints a few words
		for (int i = 0; i < 5; i++) {
			System.out.println(DICTIONARY[i]);
		}
		System.out.println(isWordInDictionary("mango"));
	}

	public static void testScrabbleScore() {
		System.out.println(wordScore("bee"));
		System.out.println(wordScore("babe"));
		System.out.println(wordScore("friendship"));
		System.out.println(wordScore("running"));
	}

	public static void testCreateHands() {
		System.out.println(createHand());
		System.out.println(createHand());
		System.out.println(createHand());
	}

	public static void testPlayHands() {
		init();
		playHand("ocostrza");
		playHand("arbffip");
		playHand("aretiin");
	}
}
