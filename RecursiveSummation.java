class RecursiveSummation {

	// PRECONDITION end >= start (assumes user input is acceptable in question)
	public static int recursiveSummation(int start, int end) {
		if (start == end) {
			return end;
		} // else
		return recursiveSummation(start + 1, end) + start;
	}

	public static void main(String[] args) {
		System.out.println(recursiveSummation(5, 5)); // expect 5
		System.out.println(recursiveSummation(1, 5)); // expect 15
	}

}