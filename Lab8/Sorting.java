import java.util.Arrays;

/* Our Lab TA told us that he just expected people to copy the code for these functions since it's too easy to find online, but I decided to create my own implementations of the algorithms without any references for the display functions. For the timed examples, I copied the code from a source he provided to give more consistent results with the PDF for the assignment. */

public class Sorting {

	public static int selectSwaps;
	public static int bubbleSwaps;
	public static int insertSwaps;
	public static int insertShifts;

	public static void selectionSortDisplay(int[] array)
	{
		for (int i = 0; i < array.length; i++) {
			int temp = array[array.length - i - 1];
			int max = array[array.length - i - 1];
			int switchIndex = array.length - i - 1;
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > max) {
					max = array[j];
					switchIndex = j;
				}
			}
			if (switchIndex != array.length - i - 1) {
				selectSwaps++;
				array[array.length - i - 1] = max;
				array[switchIndex] = temp;
				System.out.println(Arrays.toString(array));
			}
		}
	}

	public static long selectionSortTime(int[] arr)
	{
		long start = System.nanoTime();
		// run function without print statements
        int n = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
		// end copied function
		long now = System.nanoTime();
		return now - start;
	}

	public static long selectionSortTimeMine(int[] array)
	{
		long start = System.nanoTime();
		// run function without print statements
		for (int i = 0; i < array.length; i++) {
			int temp = array[array.length - i - 1];
			int max = array[array.length - i - 1];
			int switchIndex = array.length - i - 1;
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > max) {
					max = array[j];
					switchIndex = j;
				}
			}
			if (switchIndex != array.length - i - 1) {
//				selectSwaps++;
				array[array.length - i - 1] = max;
				array[switchIndex] = temp;
//				System.out.println(Arrays.toString(array));
			}
		}
		// end copied function
		long now = System.nanoTime();
		return now - start;
	}

	public static void bubbleSortDisplay(int[] array)
	{
		for (int j = 0; j < array.length; j++) {
			for (int i = 0; i < array.length - 1; i++) {
				int curr = (int)array[i];
				int next = (int)array[i + 1];
				if (next < curr) {
					bubbleSwaps++;
					array[i] = next;
					array[i + 1] = curr;
					System.out.println(Arrays.toString(array));
				}
			}
		}
	}

	public static long bubbleSortTime(int[] arr)
	{
		long start = System.nanoTime();
		// run function without print statements
		int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
		// end copied function
		long now = System.nanoTime();
		return now - start;
	}

	public static long bubbleSortTimeMine(int[] array)
	{
		long start = System.nanoTime();
		// run function without print statements
		for (int j = 0; j < array.length; j++) {
			for (int i = 0; i < array.length - 1; i++) {
				int curr = (int)array[i];
				int next = (int)array[i + 1];
				if (next < curr) {
//					bubbleSwaps++;
					array[i] = next;
					array[i + 1] = curr;
//					System.out.println(Arrays.toString(array));
				}
			}
		}
		// end copied function
		long now = System.nanoTime();
		return now - start;
	}

	public static void insertionSortDisplay(int[] array)
	{
		// starts at 1 because it assumed the "sorted section" is the 0th element
		for (int i = 1; i < array.length; i++) {
			int key = array[i]; // key to be inserted
			// if key is less than highest value in sorted section of the array
			if (key < array[i - 1]) { // key needs to be inserted into sorted section
				int temp = key; // probably could use key, but using temp for function clarity
				insertSwaps++;
				// start at right most index of sorted section, decrement to beginning
				for (int j = i - 1; j >= 0; j--) {
					if (temp < array[j]) {
						insertShifts++;
						array[j + 1] = array[j];
						System.out.println(Arrays.toString(array));
						array[j] = temp;
						System.out.println(Arrays.toString(array));
					}
				}
			} /* else: the key is higher than the highest value in the sorted section
			* so you can leave the value where it it (at the top of the sorted array)
			* and just increase the key index (continue with outer for loop) */
		}
	}

	public static long insertionSortTime(int[] arr)
	{
		long start = System.nanoTime();
		// run function without print statements
		int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
		// end copied function
		long now = System.nanoTime();
		return now - start;
	}

	public static long insertionSortTimeMine(int[] array)
	{
		long start = System.nanoTime();
		// run function without print statements
		// starts at 1 because it assumed the "sorted section" is the 0th element
				for (int i = 1; i < array.length; i++) {
					int key = array[i]; // key to be inserted
					// if key is less than highest value in sorted section of the array
					if (key < array[i - 1]) { // key needs to be inserted into sorted section
						int temp = key; // probably could use key, but using temp for function clarity
//						insertSwaps++;
						// start at right most index of sorted section, decrement to beginning
						for (int j = i - 1; j >= 0; j--) {
							if (temp < array[j]) {
//								insertShifts++;
								array[j + 1] = array[j];
//								System.out.println(Arrays.toString(array));
								array[j] = temp;
//								System.out.println(Arrays.toString(array));
							}
						}
					} /* else: the key is higher than the highest value in the sorted section
					* so you can leave the value where it it (at the top of the sorted array)
					* and just increase the key index (continue with outer for loop) */
				}
		// end copied function
		long now = System.nanoTime();
		return now - start;
	}

	public static int[] createReverseArray(int size)
	{
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = (size - i - 1);
		}
		return array;
	}

	public static void main(String[] args) {
		int[] select = createReverseArray(10);
		int[] bubble = createReverseArray(10);
		int[] insert = createReverseArray(10);

		System.out.println("Selection Sort:");
		System.out.println(Arrays.toString(select) + "\n");
		selectionSortDisplay(select);
		System.out.println("Swap count: " + selectSwaps + "\n");

		System.out.println("Bubble Sort:");
		System.out.println(Arrays.toString(bubble) + "\n");
		bubbleSortDisplay(bubble);
		System.out.println("Swap count: " + bubbleSwaps + "\n");

		System.out.println("Insertion Sort:");
		System.out.println(Arrays.toString(insert) + "\n");
		insertionSortDisplay(insert);
		System.out.println("Shift count: " + insertShifts + "\n");
		System.out.println("Swap count: " + insertSwaps + "\n");

//		for (int i = 0; i < 10; i++) { // testing my algorithm against the provided ones
			select = createReverseArray(10000);
			bubble = createReverseArray(10000);
			insert = createReverseArray(10000);
			System.out.println("Selection sort time: " + selectionSortTime(select) + "ns");
			System.out.println("Bubble sort time:    " + bubbleSortTime(bubble) + "ns");
			System.out.println("Insertion sort time: " + insertionSortTime(insert) + "ns\n");

			select = createReverseArray(10000);
			bubble = createReverseArray(10000);
			insert = createReverseArray(10000);
			System.out.println("My selection sort time: " + selectionSortTimeMine(select) + "ns");
			System.out.println("My bubble sort time:    " + bubbleSortTimeMine(bubble) + "ns");
			System.out.println("My insertion sort time: " + insertionSortTimeMine(insert) + "ns\n");
//		}
	}

}
