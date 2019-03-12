class RecursiveSummation {

	// PRECONDITION end >= start (assumes user input is acceptable in question)
	public static int recursiveSummation(int start, int end) {
		if (start == end) {
			return end;
		} // else
		return recursiveSummation(start + 1, end) + start;
	}

	public static void main(String[] args) {
		System.out.println("From 5 to 5: " + recursiveSummation(5, 5)); // expect 5
		System.out.println("From 1 to 5: " + recursiveSummation(1, 5)); // expect 15
	}

}