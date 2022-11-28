import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.apache.commons.math3.stat.StatUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class SalterSmoother {
	/**
	 * i first declared multiple series and Data set variables from the jframe
	 * libraries, these x and y series hold my x and y values that i can then pass
	 * to the data set. The data set is used to make a graph in jframe so i have to
	 * pass my series values off to my data set before i graph my values.
	 */

	private XYSeries values = new XYSeries("values");
	private XYSeries Saltedvalues = new XYSeries("Salted values");
	private XYSeries Smoothedvalues = new XYSeries("Salted values");
	private XYSeriesCollection Dataset = new XYSeriesCollection();
	private XYSeriesCollection SaltedDataset = new XYSeriesCollection();
	private XYSeriesCollection SmoothedDataset = new XYSeriesCollection();

	/**
	 * i know i only used one method for creating my first graph it was just easier
	 * to do this since this was my first time using jframe. I attempt several
	 * return methods that would return my graph but it was above my skill.
	 */

	public void Graph() {
		// created graph object
		SalterSmoother graph = new SalterSmoother();

		// user input to determine quantity of points
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the amount of point you would like must be greater then 20: ");
		int limit = scn.nextInt();
		if (limit == 20) {
			System.out.println("Please enter a value greater then 20");
		} else {
			// loop that fills the values set with x and y values using mx+b formula
			for (int i = 0; i < limit; i++) {
				int x = i;
				int y = 2 * i + (i + 2);
				values.add(x, y);
			}
			// next we add the values set to the data set
			Dataset.addSeries(values);
			/**
			 * I then created a variable call scatter plot that will hold my plot
			 * information for my x and y data set. The cartFactort will handle the creation
			 * of the graph it self i just have to pass it values. since this was my fist
			 * time using JfreeChart this part took my several hours. After a while of
			 * researching and error looking up, eclipse auto generated me this try and
			 * catch statement that solved all my issues. Without the catch IOExpection this
			 * statement doesn't work.
			 */
			JFreeChart scatterPlot = ChartFactory.createScatterPlot("Normal Graph", "X values", "Y values", Dataset);
			try {
				// saving my chart as a png file, with the graph size of 750 by 750
				ChartUtilities.saveChartAsJPEG(new File("NormalGraph.png"), scatterPlot, 750, 750);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * This methods salts our data, it takes data that was created in the above
	 * method and salts it. I looked into an Apache library to do this for me, but i
	 * decided its easier if i just do it using default java libraries
	 * 
	 */
	public void Salter() {
		// created rng generator
		Random rng = new Random();
		// for loop that salts data
		for (int i = 0; i < Dataset.getSeries(0).getItemCount(); i++) {
			// finding our random int to multiple to our y values
			int randomInt = rng.nextInt(8) + 1;
			// setting our new y value equal to our appropriate graph value
			double newy = (double) Dataset.getSeries(0).getY(i);
			// salting process
			newy = newy * randomInt;
			// adding the values to our series
			Saltedvalues.add(Dataset.getSeries(0).getX(i), newy);
		}
		// adding the series to our data set
		SaltedDataset.addSeries(Saltedvalues);
		// using j free chart to graph it
		JFreeChart scatterPlot = ChartFactory.createScatterPlot("Salted Graph", "X values", "Y values", SaltedDataset);
		try {
			// saving my chart as a png file, with the graph size of 750 by 750
			ChartUtilities.saveChartAsJPEG(new File("SaltedGraph.png"), scatterPlot, 750, 750);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Using this library was very informative but difficult i had lots of issues
	 * getting my running mean to work properly, in the end i followed some
	 * tutorials and Apache notes, i linked the Apache notes below
	 * https://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/math4/stat/descriptive/StorelessUnivariateStatistic.html
	 * https://commons.apache.org/proper/commons-math/userguide/stat.html
	 * 
	 */
	// method to smooth data
	public void Smoother() {
		// i first just passed my length of my data set to a a double called length
		double length = SaltedDataset.getSeries(0).getItemCount();
		/**
		 * in my experience Apache works best with arrays of type double, at least this
		 * is what is displayed on their web site, which i used for help.
		 */
		double[] ysmoothed = new double[(int) (length + 1)];
		/**
		 * this for loop takes our values from our salted data set and adds it to our
		 * new double array. Because we are working with lots of different java
		 * libraries i convert our data set into an array, and like i said above Apache
		 * works better with double arrays anyway
		 * 
		 */
		for (int i = 0; i < length; i++) {
			ysmoothed[i] = (double) SaltedDataset.getSeries(0).getY(i);
		}

		/**
		 * This for loop runs from i to the length of our data set. This is the way i
		 * found from the Apache web site to create running means. double mean =
		 * StatUtils.mean(y smoothed, i, 10); this statement take an i value and find
		 * the mean of 10 values next to it. I then added this value to my value set.
		 * ALthough i only really used one line from the Apache library its all i need
		 * to smooth my graph.
		 * 
		 * i couldn't figure out a away to account for every single x and y value while
		 * not producing a bounds error. this is the best solution i could produce.
		 * Although it misses 10 x and y values it smoothes the data really nice. i
		 * could have made it only miss 3 x and y values but then the graph smoothing
		 * looks really rough. This way lacks points but produces and better answer
		 */
		for (int i = 0; i < length - 10; i++) {
			// mean variable, mean is from i to 10 intervals above
			double mean = StatUtils.mean(ysmoothed, i, 10);
			// added the values to my smoothed values variable
			Smoothedvalues.add(SaltedDataset.getSeries(0).getX(i), mean);

		}
		// add the series to my data set
		SmoothedDataset.addSeries(Smoothedvalues);
		// used j free chart to plot my data
		JFreeChart scatterPlot = ChartFactory.createScatterPlot("Smoothed Graph", "X values", "Y values",
				SmoothedDataset);
		try {
			// saving my chart as a png file, with the graph size of 750 by 750
			ChartUtilities.saveChartAsJPEG(new File("SmoothedGraph.png"), scatterPlot, 750, 750);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
