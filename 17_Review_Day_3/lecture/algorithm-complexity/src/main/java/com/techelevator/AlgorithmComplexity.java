package com.techelevator;

public class AlgorithmComplexity {

	public static void main(String[] args) {
		
		// Get the Array before the timing starts so it doesn't change our timing
		int count = 100;   // Change this to get arrays of different size  (WARNING: Start at 40.. STAY under 50 for O(2n))
		int[] array = getArrayOfSize(count);

		long startTime = System.currentTimeMillis();

		// O(1) : Constant Complexity
		//isLastElementEven(array);
		
		// O(n) : Linear Complexity
		//doesArrayContain10(array);

		// O(n^2) : Quadratic Complexity
		doesArrayContainDuplicates(array);
		
		// O(2n) : Exponential Complexity
		//Fibonacci(count);
		
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println("Duration: " + duration + " ms.\n");
	}

	/* O(1) - Constant-Time Complexity
	 * The number of operations for the algorithm doesn�t actually change as the problem size increases.
	 */
	public static boolean isLastElementEven(int[] array) {
	    return array[array.length - 1] % 2 == 0;
	}
	
	/* O(n) - Linear Complexity
	 * doubling the problem size also doubles the number of operations required..
	 */
	public static boolean doesArrayContain10(int[] array) {
	    boolean hasATen = false;
	    for(int i = 0; i < array.length; i++) {
	        if (array[i] == 10){
	            hasATen = true;
	        }
	    }

	    return hasATen;
	}
	
	/* O(n^2) - Quadratic Complexity
	 * Doubling the problem size multiplies the operation count by four
	 */
	public static boolean doesArrayContainDuplicates(int[] array) {
	    boolean duplicate = false;
	    for (int i = 0; i < array.length; i++){
	        for (int j = 0; j < array.length; j++) {
	            if (i == j){
	                continue;
	            }

	            if (array[i] == array[j]){
	                duplicate = true;
	            }
	        }
	    }

	    return duplicate;
	}
	
	/* O(2^n) - Exponential Complexity
	 * Increasing the problem size by 1 doubles the work
	 * CAUTION:  This method uses Recursion, and is an example of a poorly written
	 * 			 algorithm - Recursion is a powerful, but confusing tool that should only
	 * 			 be used with a solid understanding of what it is doing.  
	 * 			 DO NOT USE THIS AS AN EXAMPLE!  
	 */
	public static int Fibonacci(int number)
	{
	    if (number <= 1) return number;

	    return Fibonacci(number - 2) + Fibonacci(number - 1);
	}

	
	private static int[] getArrayOfSize(int count) {
		int[] array = new int[count];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		
		return array;
	}
}

