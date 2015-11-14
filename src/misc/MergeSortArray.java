package misc;

import java.util.ArrayList;
/**
 * Merge sort, example of extndability where I can use other sorting method instead of implementing comparator.
 * @author Chang En Kai
 *
 */
public class MergeSortArray {

	ArrayList<String> stringList = new ArrayList<String>();

	public MergeSortArray(ArrayList<String> input) {
		System.out.print("Unsorted List:");
		for (int i = 0; i < input.size(); i++) {
			stringList.add(input.get(i));
			System.out.print(input.get(i) + ",");
		}
	}

	public void sort() {

		stringList = mergeSort(stringList);

	}

	public ArrayList<String> mergeSort(ArrayList<String> stringList2) {
		ArrayList<String> left = new ArrayList<String>();
		ArrayList<String> right = new ArrayList<String>();
		int middle;

		if (stringList2.size() == 1)
			return stringList2;
		else {
			middle = stringList2.size() / 2;
			// copy the left half of stringList into the left.
			for (int i = 0; i < middle; i++) {
				left.add(stringList2.get(i));
			}

			// copy the right half of stringList into the new arraylist.
			for (int i = middle; i < stringList2.size(); i++) {
				right.add(stringList2.get(i));
			}

			// Sort the left and right halves of the arraylist.
			left = mergeSort(left);
			right = mergeSort(right);

			// Merge the results back together.
			merge(left, right, stringList2);

		}
		return stringList2;
	}

	private void merge(ArrayList<String> left, ArrayList<String> right,
			ArrayList<String> stringList) {

		int leftIndex = 0;
		int rightIndex = 0;
		int intListIndex = 0;

		// As long as neither the left nor the right arraylist has
		// been used up, keep taking the smaller of left.get(leftIndex)
		// or right.get(rightIndex) and adding it at both.get(bothIndex).
		while (leftIndex < left.size() && rightIndex < right.size()) {

			if ((left.get(leftIndex).compareTo(right.get(rightIndex))) < 0) {
				stringList.set(intListIndex, left.get(leftIndex));
				leftIndex++;

			} else {
				stringList.set(intListIndex, right.get(rightIndex));
				rightIndex++;

			}
			intListIndex++;
		}

		ArrayList<String> rest;
		int restIndex;
		if (leftIndex >= left.size()) {

			rest = right;
			restIndex = rightIndex;
		} else {

			rest = left;
			restIndex = leftIndex;
		}

		// Copy the rest of whichever arraylist (left or right) was
		// not used up.
		for (int i = restIndex; i < rest.size(); i++) {
			stringList.set(intListIndex, rest.get(i));
			intListIndex++;
		}
	}

}