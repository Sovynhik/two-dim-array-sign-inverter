package ru.rsreu.savushkin0417.twodimarray;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import com.prutzkow.twodimarray.TwoDimArray;

/**
 * This class handles operations on a two-dimensional array, extending the
 * functionality of the {@link TwoDimArray} class. It provides methods to invert
 * signs of elements based on minimal differences, fill the array with random
 * numbers, and produce a string representation of the array along with the sum
 * of the first and last elements.
 */
public class TwoDimArrayHandler extends TwoDimArray {

	private static final Resourcer RESOURCER = ProjectResourcer.getInstance();

	/**
	 * Constructs a new TwoDimArrayHandler with the specified dimensions.
	 *
	 * @param sizes the sizes of the two-dimensional array
	 * @throws IllegalArgumentException if the sizes are invalid
	 */
	public TwoDimArrayHandler(int... sizes) throws IllegalArgumentException {
		super(sizes);
	}

	/**
	 * Inverts the sign of the element in each row of the array that has the minimal
	 * difference between the sum of the elements to its left and the sum of the
	 * elements to its right.
	 */
	public void invertSignAtMinimalDifference() {
		for (int row = 0; row < this.getRowCount(); row++) {
			final int[] rowItems = this.arrayBody[row];

			if (rowItems.length <= 2) {
				continue;
			}

			int indexToInvert = findIndexWithMinimalDifference(rowItems);

			if (indexToInvert != -1) {
				rowItems[indexToInvert] = -rowItems[indexToInvert];
			}
		}
	}

	/**
	 * Fills the two-dimensional array with random integers within the specified
	 * borders.
	 *
	 * @param leftBorder  the lower bound (inclusive) for random numbers
	 * @param rightBorder the upper bound (inclusive) for random numbers
	 * @throws IllegalArgumentException if rightBorder is less than leftBorder or if
	 *                                  the array is empty
	 */
	public void fill(int leftBorder, int rightBorder) {
		if (rightBorder < leftBorder) {
			throw new IllegalArgumentException(TwoDimArrayHandler.RESOURCER.getString("invalidBordersException"));
		}
		if (this.getRowCount() == 0) {
			throw new IllegalArgumentException(TwoDimArrayHandler.RESOURCER.getString("emptyArrayProccessingException"));
		}
		for (int row = 0; row < this.getRowCount(); row++) {
			final int[] rowItems = this.arrayBody[row];
			for (int col = 0; col < rowItems.length; col++) {
				rowItems[col] = generateRandomNumberWithBorders(leftBorder, rightBorder);
			}
		}
	}

	/**
	 * Returns a string representation of the two-dimensional array, including the
	 * sum of the first and last elements.
	 *
	 * @return a string representation of the array
	 */
	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder();
		result.append(super.toString()).append(
				String.format(TwoDimArrayHandler.RESOURCER.getString("sumFirstAndLastItems"), this.getSumFirstLastElements()));
		return result.toString().trim();
	}

	/**
	 * Finds the index of the element in the specified row that has the minimal
	 * difference between the sum of the elements to its left and the sum of the
	 * elements to its right.
	 *
	 * @param rowItems the array of integers representing the row
	 * @return the index of the element with minimal difference, or -1 if not found
	 */
	private int findIndexWithMinimalDifference(int[] rowItems) {
		int minDifference = Integer.MAX_VALUE;
		int indexToInvert = -1;

		for (int col = 1; col < rowItems.length - 1; col++) {
			int leftSum = calculateLeftSum(rowItems, col);
			int rightSum = calculateRightSum(rowItems, col);
			int difference = Math.abs(leftSum - rightSum);

			if (difference < minDifference) {
				minDifference = difference;
				indexToInvert = col;
			}
		}
		return indexToInvert;
	}

	/**
	 * Calculates the sum of the elements to the left of the specified index in the
	 * given row.
	 *
	 * @param rowItems the array of integers representing the row
	 * @param index    the index for which to calculate the left sum
	 * @return the sum of the elements to the left of the specified index
	 */
	private int calculateLeftSum(int[] rowItems, int index) {
		int leftSum = 0;
		for (int leftCol = 0; leftCol < index; leftCol++) {
			leftSum += rowItems[leftCol];
		}
		return leftSum;
	}

	/**
	 * Calculates the sum of the elements to the right of the specified index in the
	 * given row.
	 *
	 * @param rowItems the array of integers representing the row
	 * @param index    the index for which to calculate the right sum
	 * @return the sum of the elements to the right of the specified index
	 */
	private int calculateRightSum(int[] rowItems, int index) {
		int rightSum = 0;
		for (int rightCol = index + 1; rightCol < rowItems.length; rightCol++) {
			rightSum += rowItems[rightCol];
		}
		return rightSum;
	}

	/**
	 * Calculates the sum of the first and last elements of the two-dimensional
	 * array.
	 *
	 * @return the sum of the first element (top-left) and the last element
	 *         (bottom-right) of the array
	 */
	private int getSumFirstLastElements() {
		final int firstItem = this.arrayBody[0][0];
		final int lastItem = this.arrayBody[arrayBody.length - 1][arrayBody[arrayBody.length - 1].length - 1];
		return firstItem + lastItem;
	}

	/**
	 * Generates a random integer within the specified borders (inclusive).
	 *
	 * @param leftBorder  the lower bound (inclusive) for the random number
	 * @param rightBorder the upper bound (inclusive) for the random number
	 * @return a random integer between leftBorder and rightBorder
	 */
	private static int generateRandomNumberWithBorders(int leftBorder, int rightBorder) {
		return (int) Math.round(Math.random() * (rightBorder - leftBorder) + leftBorder);
	}
}