import java.util.*;

public class Lab4 {

	static int test;
	/*
	 * The method does not have to handle punctuation,
	 * but must be able to handle any number of spaces
	 * between words, or at the beginning and/or end of
	 * the input string.
	 * */
		
	public static String toCamelCase(String toCamel) {
		toCamel = toCamel.trim();
		if (toCamel.indexOf(" ") == -1) {
			return toCamel.toLowerCase();
		}
		int index = toCamel.lastIndexOf(" ");
		String word = toCamel.substring(index, toCamel.length()).trim();
		word = word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
		return toCamelCase(toCamel.substring(0, index)) + word;
	}
	
	public static int treasureHunt(int[] caves, int[] flashLights) {
		int treasure = 0;
		for (int i = 0; i < caves.length; i++) {
//			System.out.println("Checking cave #" + caves[i]);
			for (int j = 0; j < flashLights.length; j++) {
//				System.out.println("Checking flashLight #" + flashLights[j]);
				if (searchCave(caves[i], flashLights[j])) {
					treasure += 1;
				}
			}
		}
		return treasure;
	}
	
	public static boolean searchCave(int cave, int flashLight) {
//      System.out.print("[" + flashLight + "]");
		if (flashLight < 10) {
//			System.out.println("-" + cave);
			if (flashLight == cave) {
				return true;
			} else {
				return false;
			}
		}
		int newFlashLight = 0;
        while (flashLight > 0) {
        	newFlashLight = newFlashLight + (flashLight % 10);
            flashLight = (int)Math.floor(flashLight/10);
        }
		return searchCave(cave, newFlashLight);
	}
	
	public static void main(String[] args) {
		String testStr = "   this    SENTENCE sHOULD be reTuRned in LOWER   CaMeL   Case      ";
		System.out.println("input:\n" + testStr);
		System.out.println("\noutput:\n" + toCamelCase(testStr));
		
		Random rand = new Random();
		int howmanyCaves = rand.nextInt(9) + 1;
		int howManyFlashLights = rand.nextInt(3) + 1;
		int[] caves = new int[howmanyCaves];
		int[] flashLights = new int[howManyFlashLights];
		
		for (int i = 0; i < caves.length ; i++) {
			caves[i] = rand.nextInt(8) + 1;
		}
		
		for (int i = 0; i < flashLights.length ; i++) {
			flashLights[i] = rand.nextInt(999999) + 1;
		}

		System.out.println("\n\nCaves: " + Arrays.toString(caves));
		System.out.println("\n\nFlashLights: " + Arrays.toString(flashLights));
		
		
		System.out.println("\nI have " + howmanyCaves + " caves to search and " + howManyFlashLights + " flashlights to do it with.");
		System.out.println("I've found " + treasureHunt(caves, flashLights) + " treasure chests!");
	}

}
 