import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	// part 2
	public void deck() {
		// creating the river array list
		ArrayList<deck> hand1 = new ArrayList<>();
		ArrayList<deck> hand2 = new ArrayList<>();

		// creating the deck object
		deck draw = new deck();

		// Creates and shuffles the deck
		draw.CreateDeck();
		draw.SuffleDeck();
		// for loop that draws the five cards we need for the river
		for (int i = 0; i < 10; i = i + 2) {
			hand1.add(draw.getdeck(i));
			hand2.add(draw.getdeck(i + 1));
		}
		// this prinln's just prints our hands in a nice output
		System.out.println("hand 1 is :");
		System.out.println(hand1);
		System.out.println("hand 2 is :");
		System.out.println(hand2);

		// Creates a scanning object to grab the amount of points the user want to
		// change
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the amount of cards you want to replace for hand 1:");
		// creates a count int
		int count = scn.nextInt();
		// count must be less the 5
		if (count <= 5) {
			// for loop that removes and adds new cards to the hand
			for (int i = 0; i < count; i++) {
				hand1.remove(i);
				// i have 1 plus 10 to ensure its a random card and not one from the top of the
				// deck
				hand1.add(draw.getdeck(i + 10));
			}
			// catch clause that cathes an invalid input
		} else {
			System.out.println("nope, enter the right amount");
		}
		// repeated system from above
		System.out.println("Enter the amount of cards you want to replace for hand 2:");
		count = scn.nextInt();
		// count once again must be 5 or less
		if (count <= 5) {
			for (int i = 0; i < count; i++) {
				hand2.remove(i);
				// this time i put 20 to ensure no card overlap
				hand2.add(draw.getdeck(i + 20));
			}
			// catch clauses that catches invalid input
		} else {
			System.out.println("nope, enter the right amount");
		}
		// i then printed our new hands to display the changes we made
		System.out.println("our new hand one is:");
		System.out.println(hand1);
		System.out.println("our new hand two is:");
		System.out.println(hand2);

		// created a game object to call the evaluation method
		Game eval = new Game();
		// finding the values for hand one and hand two
		int hc1 = eval.evaluator(hand1);
		int hc2 = eval.evaluator(hand2);
		// if they equal one another then both hands are equal
		if (hc1 == hc2) {
			System.out.println("both these hands are the same");
		}
		// if hand 2 has more then hand two wins
		if (hc1 < hc2) {
			System.out.println("Hand 2 wins");
		}
		// if hand 1 has more then hand one wins
		if (hc1 > hc2) {
			System.out.println("Hand 1 wins");
		}
		scn.close();
	}

	/*
	 * i decided that the easiest way to program this was to assign a value to each
	 * Evaluation based off of its rarity, for example a pair is the most common so
	 * its worth 1 point, but a royal flush is the most rare so its worth 9 points.
	 * This will make it so the most rare hand has the greater score, meaning it
	 * would win
	 *
	 */

	public Integer evaluator(ArrayList<deck> hand) {
		// Counter to calculate amount of card wins
		int Counter = 0;
		ArrayList<String> suits = new ArrayList<>();
		ArrayList<String> nums = new ArrayList<>();
		ArrayList<String> tempnums = new ArrayList<>();

		/*
		 * This for loop takes our input and splits on the comma to create two different
		 * arrays of type string. I was able to do it this way because i had a ToString
		 * method in my deck class that printed my cards with a comma separating each
		 * number with a suit. I split on the commas and saved the values to the
		 * Appropriate list
		 */
		for (int j = 0; j < hand.size(); j++) {
			// parsing the string i get from the deck
			String parse = "" + hand.get(j);
			// splitting on the comma
			String[] var = parse.split(",");
			// adding the values i need to the respective list
			suits.add(var[1]);
			nums.add(var[0]);
			tempnums.add(var[0]);
		}
		// creates the poker object
		poker eval = new poker();
		// checks if its a full house
		if (eval.FullHouse(nums) == false) {
			// this is our pair evaluator
			if (eval.pairevaluatior(nums) == true) {
				// counts amount of pairs
				Counter = Counter + 1;
			}
			// this is the three of a kind counter
			if (eval.ThreeOfKind(nums) == true) {
				// returns the amount of three of a kinds
				Counter = Counter + 3;
			}
			if (eval.FourOfKind(nums) == true) {
				// counts amount of four of a kinds
				Counter = Counter + 7;
			}

		}
		// clearing and adding back the numlist
		nums.clear();
		nums.addAll(tempnums);

		// flush counter
		if (eval.flush(suits) == true) {
			// Counts ever true flush
			Counter = Counter + 5;
		}
		nums.clear();
		nums.addAll(tempnums);

		// full house counter
		if (eval.FullHouse(nums) == true) {
			// returns the count of true full houses
			Counter = Counter + 6;
		}
		nums.clear();
		nums.addAll(tempnums);

		// return the amount of unique two pairs in the river
		if (eval.twopairs(nums) == true) {
			// counts each unique two pairs
			Counter = Counter + 2;
		}
		nums.clear();
		nums.addAll(tempnums);

		// return the count of how many straight
		if (eval.Straight(nums) == true) {
			// count of straight
			Counter = Counter + 4;
		}
		nums.clear();
		nums.addAll(tempnums);

		// returns the count of straight flush
		if (eval.flush(suits) == true) {
			nums.clear();
			nums.addAll(tempnums);
			if (eval.Straight(nums) == true) {
				// count of each straight flush
				Counter = Counter + 8;
			}

		}
		nums.clear();
		nums.addAll(tempnums);

		// returns the count of royal flush occurrences
		if (eval.royalFlush(nums, suits) == true) {
			// count of each royal flush
			Counter = Counter + 9;

		}

		nums.clear();
		nums.addAll(tempnums);
		return Counter;

	}
}
