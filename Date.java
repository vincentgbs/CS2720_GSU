public class Date {

	private int year;
	private int month;
	private int day;
	private static int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public Date(int year, int month, int day) {
		if (year > -5000) {
			this.year = year;
		} else {
			this.year = 1970; // DEFAULT VALUE if user enters invalid argument
		}
		if (month > 0 && month < 13) {
			this.month = month;
		} else {
			this.month = 1; // DEFAULT VALUE if user enters invalid argument
		}
		if (day < this.months[this.month]) {
			this.day = day;
		} else {
			this.day = 1; // DEFAULT VALUE if user enters invalid argument
		}
	}


	public void addDays(int days) {
		days = this.addDaysToYear(days);
		days = this.addDaysToMonth(days);
		this.day += days;
	}

	// helper function for addDays
	private int dayInYear() {
		int dayInYear = this.day;
		for (int i = 1; i < this.month; i++) {
			dayInYear += this.months[i];
		}
		return dayInYear;
	}

	// helper function for addDays
	private int addDaysToYear(int days) {
		int toNextYear = 365;
		if (this.isLeapYear()) {
			toNextYear = 366;
		}
		while (days >= toNextYear) {
			this.year++;
			days -= toNextYear;
			if (this.isLeapYear()) {
				toNextYear = 366;
			} else {
				toNextYear = 365;
			}
		}
		int dayInYear = this.dayInYear();
		if (dayInYear + days >= toNextYear) {
			year++;
			this.month = 1;
			this.day = 1;
			days -= ((toNextYear - dayInYear) + 1);
		}
		return days;
	}

	// helper function for addDays
	private int addDaysToMonth(int days) {
		int toNextMonth = this.months[this.month];
		while(days >= toNextMonth) {
			if (this.isLeapYear() && this.month == 2) {
				toNextMonth = 29;
			}
			this.month++;
			days -= toNextMonth;
			toNextMonth = this.months[this.month];
		}
		int dayInMonth = this.day;
		if (dayInMonth + days > toNextMonth) {
			this.month++;
			this.day = 1;
			days -= ((toNextMonth - dayInMonth) + 1);
		}
		return days;
	}

	public void addWeeks(int weeks) {
		this.addDays(7*weeks);
	}

	public int daysTo(Date other) {
		Date first;
		Date last;
		if (this.absoluteValue() < other.absoluteValue() ) {
			first = new Date(this.year, this.month, this.day);
			last = other;
		} else { // should not matter if they are equal
			first = new Date(other.year, other.month, other.day);
			last = this;
		}
		int daysTo = this.dayInYear() - other.dayInYear(); // can be negative
		while (first.year < last.year) {
			daysTo += 365;
			if (first.isLeapYear()) {
				daysTo++;
			}
			first.year++;
		}
		return daysTo;
	}

	/* helper function for daysTo; Not the "cleanest" solution
	*	but functions for all cases that it is used in. Should
	*	not be used for anything outside of the daysTo function.
	*/
	private int absoluteValue() {
		return (this.year * 1000) + this.dayInYear();
	}

	public int getDay() {
		return this.day;
	}

	public int getMonth() {
		return this.month;
	}

	public int getYear() {
		return this.year;
	}

	public boolean isLeapYear() {
		return (this.year%4==0 && (this.year%100!=0 || this.year%400==0));
	}

	public String toString() {
		return this.getYear() + "/" + this.getMonth() + "/" + this.getDay();
	}

	public static void main(String[] args) {
		Date test = new Date(-9999, 13, 32);
		System.out.println(test + " is the default date if the user gives invalid dates.");

		test = new Date(1956, 1, 1);
		System.out.println(test + " is a leap year: " + test.isLeapYear());
		test = new Date(1844, 1, 1);
		System.out.println(test + " is a leap year: " + test.isLeapYear());
		test = new Date(1600, 1, 1);
		System.out.println(test + " is a leap year: " + test.isLeapYear());
		test = new Date(2000, 1, 1);
		System.out.println(test + " is a leap year: " + test.isLeapYear());
		test = new Date(1983, 1, 1);
		System.out.println(test + " is a leap year: " + test.isLeapYear());
		test = new Date(2002, 1, 1);
		System.out.println(test + " is a leap year: " + test.isLeapYear());
		test = new Date(1700, 1, 1);
		System.out.println(test + " is a leap year: " + test.isLeapYear());
		test = new Date(1900, 1, 1);
		System.out.println(test + " is a leap year: " + test.isLeapYear());

		test = new Date(2019, 2, 15);
		System.out.println("1. " + test.toString()); // expect 2019/2/15
		test.addDays(180);
		System.out.println("2. " + test.toString()); // expect 2019/8/14
		test.addDays(360);
		System.out.println("3. " + test.toString()); // expect 2020/8/8
		test.addDays(900);
		System.out.println("4. " + test.toString()); // expect 2023/1/24
		test.addWeeks(1);
		System.out.println("5. " + test.toString()); // expect 2023/1/31
		test.addWeeks(10);
		System.out.println("6. " + test.toString()); // expect 2023/4/11

		Date compare = new Date(2019, 2, 15);
		System.out.println("There are " + compare.daysTo(test) + " days between " + compare + " to " + test); // expect 1405
	}

}
