import java.util.ArrayList;
import java.util.Collections;

public class poker extends deck {
	// part 1
	public void evaluator() {
		// this is a group of counter that i need to keep track off
		int pCounter = 0;
		int tCounter = 0;
		int fCounter = 0;
		int flCounter = 0;
		int fhCounter = 0;
		int upCounter = 0;
		int stCounter = 0;
		int sfCounter = 0;
		int rfCounter = 0;
		int otherCounter = 0;

		// amount of runs that the simulation will run
		double runs = 100000;

		// this for loop goes from 0 to the amount of runs we want
		for (int i = 0; i < runs; i++) {
			// declares three arrays of type string
			ArrayList<String> suits = new ArrayList<>();
			ArrayList<String> nums = new ArrayList<>();
			ArrayList<String> tempnums = new ArrayList<>();

			// declares an array of type deck that is going to hold our river
			ArrayList<deck> river = new ArrayList<>();

			// creates an object of type poker
			poker eval = new poker();
			// calls the river method
			river = eval.river();

			/*
			 * This for loop takes our input and splits on the comma to create two different
			 * arrays of type string. I was able to do it this way because i had a ToString
			 * method in my deck class that printed my cards with a comma separating each
			 * number with a suit. I split on the commas and saved the values to the
			 * Appropriate list
			 */
			for (int j = 0; j < river.size(); j++) {
				// parsing the string i get from the deck
				String parse = "" + river.get(j);
				// splitting on the comma
				String[] var = parse.split(",");
				// adding the values i need to the respective list
				suits.add(var[1]);
				nums.add(var[0]);
				tempnums.add(var[0]);
			}
			System.out.println(river);
			// print statement to see the river commented out for now
			// System.out.println(river);

			/*
			 * This code block calls the below method that evaluates the probability for
			 * each poker case. in some of these evaluation i messed with the num list so
			 * after each evaluation i cleared and added back the cards. Also some
			 * evaluation over lap such as a full house and pairs, so i wrote this into
			 * these if statement. Example a full house has a pair and a three of a kind so
			 * if its not a full house then i run the evaluation on the different pairs
			 */
			if (eval.FullHouse(nums) == false) {
				// this is our pair evaluator
				if (eval.pairevaluatior(nums) == true) {
					// counts amount of pairs
					pCounter++;
				}
				// this is the three of a kind counter
				if (eval.ThreeOfKind(nums) == true) {
					// returns the amount of three of a kinds
					tCounter++;
				}
				if (eval.FourOfKind(nums) == true) {
					// counts amount of four of a kinds
					fCounter++;
				}

			}
			// clearing and adding the list
			nums.clear();
			nums.addAll(tempnums);

			// flush counter
			if (eval.flush(suits) == true) {
				// Counts ever true flush
				flCounter++;
			}
			nums.clear();
			nums.addAll(tempnums);

			// full house counter
			if (eval.FullHouse(nums) == true) {
				// returns the count of true full houses
				fhCounter++;
			}

			// clearing and adding the list
			nums.clear();
			nums.addAll(tempnums);

			// return the amount of unique two pairs in the river
			if (eval.twopairs(nums) == true) {
				// counts each unique two pairs
				upCounter++;
			}
			nums.clear();
			nums.addAll(tempnums);

			// return the count of how many straight
			if (eval.Straight(nums) == true) {
				// count of straight
				stCounter++;
			}
			nums.clear();
			nums.addAll(tempnums);

			// returns the count of straight flush
			if (eval.flush(suits) == true) {
				nums.clear();
				nums.addAll(tempnums);
				if (eval.Straight(nums) == true) {
					// count of each straight flush
					sfCounter++;
				}

			}
			nums.clear();
			nums.addAll(tempnums);

			// returns the count of royal flush occurrences
			if (eval.royalFlush(nums, suits) == true) {
				// count of each royal flush
				rfCounter++;
			}

			nums.clear();
			nums.addAll(tempnums);
			// returns the other probability that we got nothing
			if (eval.pairevaluatior(nums) == false && eval.ThreeOfKind(nums) == false && eval.FourOfKind(nums) == false
					&& eval.flush(suits) == false && eval.Straight(nums) == false) {
				// adds one to other count
				otherCounter++;
			}
			nums.clear();
			river.clear();
			suits.clear();
		}
		/*
		 * This is our print statement for all of our probabilities i first took the
		 * count and dived it by runs, this would give us in decimal what are
		 * probabilities are. I then multiplied by 100 to get the percent of our
		 * evaluations. Some of these had a water fall effect, for example four of a
		 * kind is also a pair, so i subtracted the probability to get the appropriate
		 * amount
		 */
		System.out.println();
		System.out.println("Our pair odds are: " + ((pCounter - tCounter - fCounter) / runs * 100) + "%");
		System.out.println("Our three of a kind odds are: " + (tCounter - fCounter) / runs * 100 + "%");
		System.out.println("Our four of a kind odds are: " + fCounter / runs * 100 + "%");
		System.out.println("Our flush odds are: " + flCounter / runs * 100 + "%");
		System.out.println("Our full house odds are: " + fhCounter / runs * 100 + "%");
		System.out.println("Our mulitple pairs odds are: " + ((upCounter - fCounter) / runs * 100) + "%");
		System.out.println("Our straight odds are: " + stCounter / runs * 100 + "%");
		System.out.println("Our straight flush odds are: " + sfCounter / runs * 100 + "%");
		System.out.println("Our Royal flush odds are: " + rfCounter / runs * 100 + "%");
		System.out.println("Our nothing probabilty is: " + otherCounter / runs * 100 + "%");

	}

	public ArrayList<deck> river() {
		// creating the river array list
		ArrayList<deck> river = new ArrayList<>();
		// creating the deck object
		deck draw = new deck();

		// Creates and shuffles the deck
		draw.CreateDeck();
		draw.SuffleDeck();
		// for loop that draws the five cards we need for the river
		for (int i = 0; i < 5; i++) {
			river.add(draw.getdeck(i));
		}
		return river;

	}

	// method to solve for a pair takes a string list and returns true or false
	public boolean pairevaluatior(ArrayList<String> river) {
		// pair variable set to false
		boolean pair = false;
		// nested for loop to see if two values equal one another
		for (int i = 0; i < river.size(); i++) {
			for (int j = i + 1; j < river.size(); j++) {
				// if 1 equals j then we set the pair to true
				if (river.get(i).equals(river.get(j))) {
					// sets pair to true
					pair = true;
				}
			}
		}
		// return the pair variable
		return pair;
	}

	// method to solve for three of a kind takes a string list and returns true or
	// flase
	public boolean ThreeOfKind(ArrayList<String> river) {
		// variable set to false
		boolean three = false;
		// counter to count occurrences
		int counter = 0;
		// nest for loop to compare indexes
		for (int i = 0; i < river.size(); i++) {
			for (int j = i + 1; j < river.size(); j++) {
				// if i equals j then run the if statement
				if (river.get(i).equals(river.get(j))) {
					// add one to counter
					counter++;
				}
			}
		}
		// if counter is equal to 3 then set three to true
		if (counter == 3) {
			three = true;
		}
		// returns three
		return three;
	}

	// method to return four of a kind takes a string list returns true or false
	public boolean FourOfKind(ArrayList<String> nums) {
		// two variables one to count one set to false
		boolean fourOfAKind = false;
		int counter = 0;

		// nest for loop that compares index
		for (int i = 0; i < nums.size(); i++) {
			for (int j = i + 1; j < nums.size(); j++) {
				// if i equals j then add one to counter
				if (nums.get(i).equals(nums.get(j))) {
					// counter count increase
					counter++;
				}
			}
		}
		// if counter is 4 or more then we have four of a kind
		if (counter >= 4) {
			// four of a kind set to true
			fourOfAKind = true;

		}
		// return four of a kind
		return fourOfAKind;
	}

	// Method to return flush, takes a string list and returns true or false
	public boolean flush(ArrayList<String> suits) {
		// two variables one set to false other to count
		boolean Flush = false;
		int counter = 0;

		// for loop to compare suites
		for (int i = 0; i < suits.size() - 1; i++) {
			// if the suits equal one another add one to count
			if (suits.get(i).equals(suits.get(i + 1))) {
				// adds 1 to count
				counter++;
			}
		}
		// if counter is equal to 4 set flush to true
		if (counter == 4) {
			Flush = true;
		}

		// returns the flush variable
		return Flush;
	}

	// Method to return Full House, takes a string list and returns true or false
	public boolean FullHouse(ArrayList<String> nums) {
		// two boolean variable set to false
		boolean fullhouse = false;
		boolean three = false;
		// one counter and one place holder variable both set to zero
		int counter = 0;
		int tk = 0;
		// array list of type integer called cards
		ArrayList<Integer> cards = new ArrayList<>();

		// this for loop converts the string array to a integer array
		for (int i = 0; i < nums.size(); i++) {
			// string variable that holds the value of index i
			String compare = nums.get(i);
			/*
			 * if block that compares each string value and sets it equal to its appropriate
			 * integer value. For example jack is 11 queen is 12 king is 13. The ace is
			 * special case where it is either a 14 or a 0. This is depending on the hand
			 * you have if you have a 1 then you want 0, but if you have a king then you
			 * want 14. For the other cases i just added the correct value to the integer
			 * array
			 */

			// if statement that checks for jacks
			if (compare.equals("Jack")) {
				cards.add(11);
			}
			// if statement that checks for queens

			if (compare.equals("Queen")) {
				cards.add(12);
			}
			// if statement that checks for kings

			if (compare.equals("King")) {
				cards.add(13);
			}
			// if statement that checks for aces
			if (compare.equals("Ace")) {
				// if the river contains a king then it adds 14, if not it adds 1
				if (nums.contains("King")) {
					cards.add(14);
				} else {
					cards.add(1);
				}
			}
			// if statement that handles the other cases
			if (compare.equals("2") || compare.equals("3") || compare.equals("4")
					|| compare.equals("5") || compare.equals("6") || compare.equals("7") || compare.equals("8")
					|| compare.equals("9") || compare.equals("10")) {
				// parse the string to an integer and adds it to the card array
				cards.add(Integer.parseInt(nums.get(i)));

			}
		}
		// calls collection sort to sort the cards in numerical order
		Collections.sort(cards);
		// nested for loop to check if we have three of a kind or not
		for (int i = 0; i < cards.size(); i++) {
			for (int j = i + 1; j < cards.size(); j++) {
				if (cards.get(i) == cards.get(j)) {
					counter++;
					if (counter == 3) {
						// setting the temporary value to the card value
						tk = cards.get(i);
						// removing the values
						cards.remove(i);
						cards.remove(i);
						// setting three to true
						three = true;
					}
				}
			}

		}
		// seeing if we have three of a kind before we run the for loops
		if (three == true) {
			counter = 0;
			// nested for loop that find if we have a pair
			for (int i = 0; i < cards.size(); i++) {
				for (int j = i + 1; j < cards.size(); j++) {
					// adds one to counter if we have a pair
					if (cards.get(i) == cards.get(j)) {
						counter++;
						// if we have a pair and its not the same pair as the three of a kind sett
						// full house to true
						if (counter == 1 && cards.get(i) != tk) {
							fullhouse = true;
						}
					}
				}

			}
		}
		// return full house
		return fullhouse;
	}

	// method to check if we have to unique pairs it takes a string list and returns
	// true or false
	public boolean twopairs(ArrayList<String> nums) {
		// variable set to false and counter set to 0
		boolean twopairs = false;
		int counter = 0;
		// array list of type integer
		ArrayList<Integer> cards = new ArrayList<>();

		// this for loop converts the string array to a integer array
		for (int i = 0; i < nums.size(); i++) {
			// string variable that holds the value of index i
			String compare = nums.get(i);

			/*
			 * if block that compares each string value and sets it equal to its appropriate
			 * integer value. For example jack is 11 queen is 12 king is 13. The ace is
			 * special case where it is either a 14 or a 0. This is depending on the hand
			 * you have if you have a 1 then you want 0, but if you have a king then you
			 * want 14. For the other cases i just added the correct value to the integer
			 * array
			 */

			// if statement that checks for jacks
			if (compare.equals("Jack")) {
				cards.add(11);
			}
			// if statement that checks for queens

			if (compare.equals("Queen")) {
				cards.add(12);
			}
			// if statement that checks for kings

			if (compare.equals("King")) {
				cards.add(13);
			}
			// if statement that checks for aces
			if (compare.equals("Ace")) {
				// if the river contains a king then it adds 14, if not it adds 1
				if (nums.contains("King")) {
					cards.add(14);
				} else {
					cards.add(1);
				}
			}
			// if statement that handles the other cases
			if (compare.equals("2") || compare.equals("3") || compare.equals("4")
					|| compare.equals("5") || compare.equals("6") || compare.equals("7") || compare.equals("8")
					|| compare.equals("9") || compare.equals("10")) {
				// parse the string to an integer and adds it to the card array
				cards.add(Integer.parseInt(nums.get(i)));

			}
		}

		// sorting the list of cards
		Collections.sort(cards);

		// nested for loop that compares the two card values
		for (int i = 0; i < cards.size(); i++) {
			for (int j = i + 1; j < cards.size(); j++) {
				if (cards.get(i).equals(cards.get(j))) {
					// if the card values equal add one to counter
					counter++;
					if (counter == 2) {
						// if counter is equal to 2 then remove two values from river
						cards.remove(i);
						cards.remove(i);
						// re setting counter to 0
						counter = 0;
						// nest for loop that does the same thing as above
						for (int k = 0; k < cards.size(); k++) {
							for (int l = k + 1; l < cards.size(); l++) {

								if (cards.get(k).equals(cards.get(l))) {

									counter++;
									if (counter == 1) {
										// setting the variable to true
										twopairs = true;

									}
								}
							}
						}

					}
				}
			}

		}
		// returns the boolean variable
		return twopairs;
	}

	// method that calculates a straight it inputs a string list as returns true or
	// False
	public boolean Straight(ArrayList<String> nums) {
		// straight variable
		boolean straight = false;
		// array list of type integer
		ArrayList<Integer> cards = new ArrayList<>();
		int counter = 0;
		// this for loop converts the string array to a integer array
		for (int i = 0; i < nums.size(); i++) {
			// string variable that holds the value of index i
			String compare = nums.get(i);

			/*
			 * if block that compares each string value and sets it equal to its appropriate
			 * integer value. For example jack is 11 queen is 12 king is 13. The ace is
			 * special case where it is either a 14 or a 0. This is depending on the hand
			 * you have if you have a 1 then you want 0, but if you have a king then you
			 * want 14. For the other cases i just added the correct value to the integer
			 * array
			 */

			// if statement that checks for jacks
			if (compare.equals("Jack")) {
				cards.add(11);
			}
			// if statement that checks for queens

			if (compare.equals("Queen")) {
				cards.add(12);
			}
			// if statement that checks for kings

			if (compare.equals("King")) {
				cards.add(13);
			}
			// if statement that checks for aces
			if (compare.equals("Ace")) {
				// if the river contains a king then it adds 14, if not it adds 1
				if (nums.contains("King")) {
					cards.add(14);
				} else {
					cards.add(1);
				}
			}
			// if statement that handles the other cases
			if (compare.equals("2") || compare.equals("3") || compare.equals("4")
					|| compare.equals("5") || compare.equals("6") || compare.equals("7") || compare.equals("8")
					|| compare.equals("9") || compare.equals("10")) {
				// parse the string to an integer and adds it to the card array
				cards.add(Integer.parseInt(nums.get(i)));

			}
		}

		// sorting the list of cards
		Collections.sort(cards);
		// for loop that compares the index of i
		for (int i = 0; i < cards.size() - 1; i++) {
			// if it is in order it adds one to counter
			if (cards.get(i) + 1 == cards.get(i + 1)) {
				counter++;
			}
		}
		// if counter is 4 then return true
		if (counter == 4) {

			straight = true;
		}
		// returning the boolean variable
		return straight;

	}

	// this method calculates a royal flush it takes two string list and returns
	// true or false
	public boolean royalFlush(ArrayList<String> nums, ArrayList<String> suits) {
		// three variables two set to false one set to 0
		boolean flush = false;
		boolean royal = false;
		int counter = 0;
		// for loop that compares suits if equal it adds to counter
		for (int i = 0; i < suits.size() - 1; i++) {
			if (suits.get(i).equals(suits.get(i + 1))) {
				counter++;
			}
		}
		// if counter is 4 then sets flush to true
		if (counter == 4) {
			flush = true;
		}
		// if statement that sees if it contains the royal part of the flush
		if (nums.contains("10") && nums.contains("Jack") && nums.contains("Queen") && nums.contains("King")
				&& nums.contains("Ace")) {

			royal = true;
		}
		// if its royal and a flush then return true
		if (royal == true && flush == true) {

			return true;
			// if not return false
		} else {
			return false;
		}
	}

}