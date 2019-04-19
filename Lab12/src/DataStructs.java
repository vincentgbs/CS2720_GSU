import java.io.*;
import java.util.*;

public class DataStructs {
	
	File fileOut = new File("alphaOutput.txt");
	FileOutputStream fileOutputStream=null;
	PrintStream printStream=null;
	
	public List<String> readToLinkedList(String filename) {
		List<String> ll = new LinkedList<String>();
		File fileIn = new File(filename);
		try {
			Scanner scanner = new Scanner(fileIn);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				ll.add(line);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("The "+filename+" file must be in the current working directory.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ll;
	}

	public String[] LinkedListtoArray(List<String> ll) {
		String[] arr = new String[ll.size()];
		Iterator<String> iter = ll.iterator();
		int i = 0;
		while (iter.hasNext()) {
			arr[i] = iter.next();
			i++;
		}
		return arr;
	}

	public void openFile() {
		try {
			this.fileOutputStream = new FileOutputStream(fileOut);
			this.printStream = new PrintStream(fileOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeArrayToFile(String[] arr) {
		try {
			this.printStream.println("Words from Array");
			for (int i = 0; i < arr.length; i++) {
				String line = arr[i];
				this.printStream.println(line);
			}
			this.printStream.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Set<String> randomArrayToSet(String[] arr) {
		Set<String> set = new HashSet<String>();
		int[] used = new int[arr.length];
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			int j = rand.nextInt(arr.length);
			while (used[j] != 0) {
				j = rand.nextInt(arr.length);
			}
			used[j] = 1;
			set.add(arr[j]);
		}
		return set;
	}
	
	public void writeSetToFile(Set<String> set) {
		try {
			this.printStream.println("Words from Set");
			Iterator<String> iter = set.iterator();
			while (iter.hasNext()) {
				this.printStream.println(iter.next());
			}
			this.printStream.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PriorityQueue<String> SetToPriorityQueue(Set<String> set) {
		PriorityQueue<String> pri = new PriorityQueue<String>();
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			pri.add(iter.next());
		}
		return pri;
	}
	
	public void writePriorityQueueToFile(PriorityQueue<String> pri) {
		try {
			this.printStream.println("Words from Priority Queue");
			while (!pri.isEmpty()) {
				this.printStream.println(pri.poll());
			}
//			this.printStream.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeFile() {
		this.printStream.close();
	}
	
	public static void main(String[] args) {
		DataStructs run = new DataStructs();
		run.openFile();
		List<String> test1 = run.readToLinkedList("alphaWords.txt");
//		System.out.println(test1);
		String[] test2 = run.LinkedListtoArray(test1);
//		System.out.println(Arrays.toString(test2));
		run.writeArrayToFile(test2);
		Set<String> test3 = run.randomArrayToSet(test2);
//		System.out.println(test3);
		run.writeSetToFile(test3);
		PriorityQueue test4 = run.SetToPriorityQueue(test3);
//		System.out.println(test4);
		run.writePriorityQueueToFile(test4);
		run.closeFile();
	}

}