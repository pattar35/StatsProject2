import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StatsLibary {

	private ArrayList<Integer> subsetA = new ArrayList<Integer>();
	private ArrayList<Integer> subsetB = new ArrayList<Integer>();
	private ArrayList<Integer> numlist = new ArrayList<Integer>();
	private ArrayList<Integer> numberlist = new ArrayList<Integer>();

	// Constructors
	public StatsLibary() {

	}

//constructor for the mean median and mode array list
//this is a simple method that makes a random number generator, generates 10 different numbers and adds the to a list
	public StatsLibary(ArrayList<Integer> numlist) {
		this.numlist = numlist;
		Random rng = new Random();
		int randomInt = rng.nextInt(9);
		// this loops adds 10 random values to the array list numlist
		for (int i = 0; i < 10; i++) {
			numlist.add(randomInt);
			randomInt = rng.nextInt(9);
		}
		// i call collection.sort to sort my array, i know this is not needed but it
		// makes my ans look nice
		Collections.sort(numlist);
	}

	// constructor for the union intersect and complement
	// this is a simple method that makes a random number generator, generates 10
	// different numbers and adds the to a list
	public StatsLibary(ArrayList<Integer> subsetA, ArrayList<Integer> subsetB) {
		this.subsetA = subsetA;
		this.subsetB = subsetB;
		Random rng = new Random();
		int randomInt = rng.nextInt(9);

		// this loops adds 10 random values to different array list subseta and subsetB
		for (int i = 0; i < 10; i++) {
			subsetA.add(randomInt);
			subsetB.add(randomInt + 1);
			randomInt = rng.nextInt(9);
		}
		// I then once again call collections.sort to make my numbers neeat and easy to
		// work with
		Collections.sort(subsetA);
		Collections.sort(subsetB);
	}

	// this is our method for finding mean, this is pretty basic all we want to do
	// is loop through our values do our calculations and return our results
	public double mean(ArrayList<Integer> SomeNumbers) {
		double Results = 0;
		double SizeOfInput = SomeNumbers.size();
		// loop that loops through my array someNumbers and adds up all the values to
		// results
		for (int i = 0; i < SizeOfInput; i++) {
			Results = Results + SomeNumbers.get(i);

		}
		return Results / SizeOfInput;
	}

	// This is our method for finding median, this one is a little more complex
	// because we have to account for two different situations
	// the first situation we have to account for is if our size of input is even.
	// the second situation we have to account for is if our size of input is odd

	public double median(ArrayList<Integer> SomeNumbers) {
		int SizeOfInput = SomeNumbers.size();
		// this if statement checks to see if this list of numbers is odd or even
		// if it is even it then does the median calculation, which is done by finding
		// the 2 middle values then calculating the average.
		if (SizeOfInput % 2 == 0) {
			int middle = SizeOfInput / 2;
			double firstNum = SomeNumbers.get(middle);
			double secondNum = SomeNumbers.get(middle - 1);
			double avrg = (firstNum + secondNum) / 2;
			return (avrg);
		}
		// this else statement catches the possibility of the num list being odd
		// it then does the same process above but we don't have to average because we
		// Don't have two middle values
		else {
			int middle = SizeOfInput / 2;
			int firstNum = SomeNumbers.get(middle);
			return (firstNum);
		}
	}

	// This is our method for finding the mode, there is no trick to this one all we
	// have to do is count the number of occurrences and take the one with the most
	public double mode(ArrayList<Integer> SomeNumbers) {
		double SizeOfInput = SomeNumbers.size();
		double Occurances = 0;
		int ans = 0;
		for (int i = 0; i < SizeOfInput; i++) {
			double Counter = 0;
			for (int j = 0; j < SizeOfInput; j++) {
				if (SomeNumbers.get(i) == SomeNumbers.get(j)) {
					Counter++;
				}
			}
			if (Counter >= Occurances) {
				Occurances = Counter;
				ans = SomeNumbers.get(i);
			}
		}
		return ans;
	}

	// this is our method for finding our standard deviation, to find the sd we
	// first need to find the mean then after we find the mean we need to find the
	// value of the mean - each individual value squared, after that we find that
	// mean and then find the sqrt of it
	public double standardDeveation(ArrayList<Integer> SomeNumbers) {
		double mean = 0;
		double meanTwo = 0;
		double SizeOfInput = SomeNumbers.size();
		ArrayList<Double> numberSet = new ArrayList<Double>();
		ArrayList<Double> numberlist = new ArrayList<Double>();

		// this loop finds the mean of our integer array
		for (int i = 0; i < SizeOfInput; i++) {
			mean = mean + SomeNumbers.get(i);
		}
		mean = mean / SizeOfInput;
		// this loop adds all the values of our calculation of the individual number
		// minus the mean then stores it in the array numberSet
		for (int i = 0; i < SizeOfInput; i++) {
			numberSet.add(SomeNumbers.get(i) - mean);
		}
		// this loop takes our values squares it and adds it to a new numberlist
		for (int i = 0; i < SizeOfInput; i++) {
			numberlist.add(Math.pow(numberSet.get(i), 2));
		}
		// this loop adds our new mean together
		for (int i = 0; i < SomeNumbers.size(); i++) {
			meanTwo = meanTwo + numberlist.get(i);
		}
		meanTwo = meanTwo / numberlist.size();
		// i then return the square root of the answer
		return Math.sqrt(meanTwo);
	}

	// Union for the union we want to add all unique values to the same array, i
	// used the .contains method to help me delete duplicates

	public ArrayList<Integer> unionOf(ArrayList<Integer> setA, ArrayList<Integer> setB) {
		ArrayList<Integer> numberSet = new ArrayList<Integer>();
		// i used a for each statement to loop through each value in my array lists
		// i then used the .contains method to determine duplicates
		// i then only added the unique to my new array numberSet
		for (int someElements : setA) {
			if (!numberSet.contains(someElements)) {
				numberSet.add(someElements);
			}
		}
		// i then repeated the step above for subsetB
		for (int someElements : setB) {
			if (!numberSet.contains(someElements)) {
				numberSet.add(someElements);
			}

		}
		return numberSet;

	}

	// intersection for this method we want to combine the values that are only
	// found in BOTH seta and setb
	public ArrayList<Integer> intersectionOf(ArrayList<Integer> setA, ArrayList<Integer> setB) {
		ArrayList<Integer> numberSet = new ArrayList<Integer>();
		ArrayList<Integer> intersect = new ArrayList<Integer>();

		// this for loop will check each index from seta and setb and compare it if its
		// the same it will add it too a new array called numberSet
		for (int i = 0; i < setA.size(); i++) {
			for (int j = 0; j < setB.size(); j++) {
				if (setA.get(i).equals(setB.get(j))) {
					numberSet.add(setA.get(i));
				}
			}
		}
		// this for each statement will use the .contain method to determine if that
		// value is already in the array list intersect
		// if it is not it then adds that value to the list
		// this will delete all duplicates
		for (int someElements : numberSet) {
			if (!intersect.contains(someElements)) {
				intersect.add(someElements);
			}

		}
		return intersect;

	}

	// this is our complement method, this method is supposed to find the values
	// that are unique to seta when comparing it to setb
	public ArrayList<Integer> complementOf(ArrayList<Integer> setA, ArrayList<Integer> setB) {
		ArrayList<Integer> numberSet = new ArrayList<Integer>();
		ArrayList<Integer> complement = new ArrayList<Integer>();

		// this for each statement checks to see if some element is contained in setb if
		// not it adds it to our numberset
		for (int someElements : setA) {
			if (!setB.contains(someElements)) {
				numberSet.add(someElements);
			}
		}
		// this for each statement sort through the array and only adds the unique
		// values to our new array list, i did this by using the .contains method to
		// Determine unique values i then added those unique values to my new array list
		for (int someElements : numberSet) {
			if (!complement.contains(someElements)) {
				complement.add(someElements);
			}

		}
		return complement;
	}

	// this is our combination calculator, this takes two diffrent inputs n and r
	// and calculates the combination based on those numbers.
	public double combination(double plugin1, double plugin2) {
		int factor = 1;
		int factorsecond = 1;
		int factorthird = 1;
		// this for loop will take the values up to n and factor them, this means will
		// have 1*1 2*2 3*3 ....n*n
		for (int i = 1; i <= plugin1; i++) {
			factor *= i;
		}
		// this for loop does the same thing above but goes up to r
		for (int i = 1; i <= plugin2; i++) {
			factorsecond *= i;
		}
		// i then ran this loop to get the factorial for n-r
		double length = plugin1 - plugin2;
		for (int i = 1; i <= length; i++) {
			factorthird *= i;
		}

		// i then plug it into the formula and returned the ans
		double ans = factor / (factorsecond * factorthird);
		return ans;
	}

	// this is our combination calculator, this takes two diffrent inputs n and r
	// and calculates the permutation based on those numbers.
	public double permutation(int n, int r) {
		int factor = 1;
		int factorsecond = 1;
		// this loop finds the first factorial
		for (int i = 1; i <= n; i++) {
			factor *= i;
		}
		int length = n - r;
		// this loop finds the factorial of n-r
		for (int i = 1; i <= length; i++) {
			factorsecond *= i;
		}
		double ans = factor / (factorsecond);
		return ans;
	}

	// Binomal Distrubution
	// for this method we want to calculate the Binomial distribution which we can
	// just do by plugging and solving using our formula i'm assuming we are going
	// to
	// be receiving our p and q as decimals and not percents
	public double BinomialDistribution(int n, int y, double p, double q) {
		// First i just declared a couple variables to break down the problem a little
		// bit further
		double first;
		double prob;
		double ans;

		// i then called my above combination solver to calculate my n of y combination
		first = combination(n, y);
		// i then calculated the probability of success and the probability of failure
		// rate
		prob = Math.pow(p, y) * Math.pow(q, n - y);
		// i then calculated total by multiply the first part by our success and failure
		// rate
		ans = first * prob;
		// converted to a percentage and return
		ans = ans * 100;
		return ans;
	}

	// Geometric Distribution
	// in this method my thought process was i need to now when the success occurs
	// in other words p (x<=n) or P(x<n) or p(x>=n) or p(x>n)
	// i was fixing my big decimal but i ran out of time, because i wanted to focus
	// on studying more
	public BigDecimal GeometricDistribution(double p, double n, String occures) {
		if (occures == "<=") {
			BigDecimal ans = new BigDecimal(1 - (Math.pow((1 - p), n)));
			return ans;
		}
		if (occures == "<") {
			BigDecimal ans = new BigDecimal(1 - (Math.pow((1 - p), n - 1)));
			return ans;
		}
		if (occures == ">=") {
			BigDecimal ans = new BigDecimal(Math.pow((1 - p), n - 1));
			return ans;
		}
		if (occures == ">") {
			BigDecimal ans = new BigDecimal(Math.pow((1 - p), n));
			return ans;
		} else {
			return null;
		}
	}

	/*
	 * This is the only method i add to this project it just my possion distribution
	 * calculation
	 * 
	 */
	public double PossoionDistrubution(double lamda, double y) {
		// declaring my first four variables i need
		double firstPart = 0;
		double secondPart = 0;
		double factorial = 1;
		double ans = 0;
		// setting my first part equal to lamda to the y
		firstPart = Math.pow(lamda, y);

		// next i solved the exponential part of the equation
		secondPart = (Math.exp(-(lamda)));

		// this for loop solves for the factorial part
		for (int i = 1; i < y; i++) {
			// setting factorial equal to factorial times i that goes until i = y
			factorial = factorial * i;
		}
		// next i plugged in all my answers and solved
		ans = (firstPart / factorial) * secondPart;
		// Returned my answer
		return ans;
	}

	// this is just our printer method to print out all of our test statements for
	// all of our above methods.
	public void printer() {
		StatsLibary setlist = new StatsLibary(subsetA, subsetB);
		StatsLibary numlist = new StatsLibary(numberlist);
		StatsLibary test = new StatsLibary();

		System.out.println("our number list is: " + numberlist);

		System.out.println();
		System.out.println("our calculated mean of numberlist is: " + numlist.mean(numberlist));

		System.out.println();
		System.out.println("our calculated median of numberlist is: " + numlist.median(numberlist));

		System.out.println();
		System.out.println("our calculated mode of numberlist is: " + numlist.mode(numberlist));

		System.out.println();
		System.out
				.println("our calculated standardDeveation of numberlist is: " + numlist.standardDeveation(numberlist));

		System.out.println();
		System.out.println("SubSetA is equal to: " + subsetA);
		System.out.println();
		System.out.println("SubSetB is equal to: " + subsetB);

		System.out.println();
		System.out.println("SubSetA union SubSetB " + setlist.unionOf(subsetA, subsetB));

		System.out.println();
		System.out.println("SubSetA intersect SubSetB " + setlist.intersectionOf(subsetA, subsetB));

		System.out.println();
		System.out.println("SubSetA complement SubSetB " + setlist.complementOf(subsetA, subsetB));

		System.out.println();
		System.out.println("our combination is: " + test.combination(10, 5));

		System.out.println();
		System.out.println("our permutation is: " + test.permutation(10, 5));

		System.out.println();
		System.out.println("our Binomal distribution is: " + test.BinomialDistribution(5, 3, .75, .25));

		System.out.println();
		System.out.printf("our Geomtric distributution is: " + "%.3f", test.GeometricDistribution(0.04, 6, ">"));

		System.out.println();
		System.out.println();
		System.out.println("our poisson  distributution is: " + test.PossoionDistrubution(2, 4));

	}

}
