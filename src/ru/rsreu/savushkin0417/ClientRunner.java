package ru.rsreu.savushkin0417;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.savushkin0417.twodimarray.TwoDimArrayHandler;

/**
 * The ClientRunner class is designed to run an application that demonstrates
 * working with two-dimensional arrays. It initializes the arrays, fills them
 * with random values, and performs the sign inversion of the element with the
 * minimal difference between the sums of elements on its left and right.
 * 
 * @author Daniil Savushkin
 * @version 1.0.0
 */
public class ClientRunner {

	/**
	 * Resource object for obtaining string resources.
	 */
	private static final Resourcer RESOURCER = ProjectResourcer.getInstance();

	/**
	 * Minimum value for filling the array.
	 */
	private static final int ARRAY_MINIMUM_VALUE = 10;

	/**
	 * Maximum value for filling the array.
	 */
	private static final int ARRAY_MAXIMUM_VALUE = 50;

	/**
	 * Array of sizes for creating the two-dimensional array.
	 */
	private static final int[] ARRAY_SIZES = { 5, 6, 7, 8, 9 };

	/**
	 * Private constructor of the ClientRunner class.
	 * 
	 * The private constructor prevents the creation of instances of the class, as
	 * the class is intended only to run the main method.
	 */
	private ClientRunner() {
	}

	/**
	 * The main method of the application.
	 * 
	 * This method initializes a two-dimensional array, fills it with random values
	 * within the specified range, and performs the sign inversion of the element
	 * with the minimal difference. The results are printed to the console.
	 *
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(
				String.format(ClientRunner.RESOURCER.getString("originalConstants"), ARRAY_MINIMUM_VALUE, ARRAY_MAXIMUM_VALUE));

		final TwoDimArrayHandler twoDimArrayItemsHandler = new TwoDimArrayHandler(ClientRunner.ARRAY_SIZES);
		stringBuilder
				.append(String.format(ClientRunner.RESOURCER.getString("orginalTwoDimArray"), twoDimArrayItemsHandler));

		twoDimArrayItemsHandler.fill(ARRAY_MINIMUM_VALUE, ARRAY_MAXIMUM_VALUE);
		stringBuilder.append(
				String.format(ClientRunner.RESOURCER.getString("initiaztionRandomItemsTwoDimArray"), twoDimArrayItemsHandler));

		twoDimArrayItemsHandler.invertSignAtMinimalDifference();
		stringBuilder.append(String.format(ClientRunner.RESOURCER.getString("invertSignAtMinimalDifferenceItemTwoDimArray"),
				twoDimArrayItemsHandler));

		System.out.println(stringBuilder.toString().trim());
	}
}
