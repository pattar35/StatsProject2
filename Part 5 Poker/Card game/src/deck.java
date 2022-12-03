import java.util.ArrayList;
import java.util.Random;

public class deck {
	// block of variable to hold my nums and my suits
	private String num;
	private String suite;

	// array lists of type deck and type string
	private ArrayList<deck> deck = new ArrayList<>();
	private ArrayList<String> suits = new ArrayList<>();

	// Constructor method to initialize the number and suit
	public deck(String num, String suite) {
		this.num = num;
		this.suite = suite;
	}

	// default constructor
	public deck() {

	}

	// method to create the deck
	public void CreateDeck() {
		// adds the 4 different suits to the suits array
		suits.add("spades");
		suits.add("clubs");
		suits.add("hearts");
		suits.add("diamonds");
		// nested for loop that adds 52 cards to our deck
		// set to 13 because there are 13 card types
		for (int i = 1; i <= 13; i++) {
			// set to <= 3 because there are 4 suits
			for (int j = 0; j <= 3; j++) {
				if (i == 1) {
					// adds the 4 different aces to our deck
					card cards = new card("Ace", suits.get(j));
					deck.add(cards);
				}
				if (i == 13) {
					// adds the 4 different kings to our deck
					card cards = new card("King", suits.get(j));
					deck.add(cards);
				}
				if (i == 12) {
					// adds the 4 different Queens to our deck
					card cards = new card("Queen", suits.get(j));
					deck.add(cards);
				}
				if (i == 11) {
					// adds the 4 different Jack to our deck
					card cards = new card("Jack", suits.get(j));
					deck.add(cards);
				}
				// this adds the rest of the cards to the deck that are not jack queen king ace
				if (i != 1 & i != 11 & i != 12 & i != 13) {
					card cards = new card("" + i, suits.get(j));
					deck.add(cards);
				}
			}

		}
	}

	// method to shuffle the deck
	public ArrayList<deck> SuffleDeck() {
		// rng generator
		Random rng = new Random();
		// for loop that runs 10 k times
		for (int i = 0; i <= 10000; i++) {
			// takes a random int
			int randomInt = rng.nextInt(51);
			// crates a hold variable to hold the card we are removing
			deck holder = deck.get(randomInt);
			// removes and adds the card back
			deck.remove(randomInt);
			// this take the card from a random index and pushes it to the back thus
			// randomizing it
			deck.add(holder);
		}
		return deck;
	}

	public String getnum() {
		return num;
	}

	public String getsuite() {
		return suite;
	}

	public deck getdeck(int i) {
		return deck.get(i);
	}

	// ToString method that takes the number and suit and prints it separated by a
	// comma
	public String toString() {
		return (this.getnum() + ", " + this.getsuite());
	}

}
