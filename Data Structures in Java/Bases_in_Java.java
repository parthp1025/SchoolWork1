/**
 * HW2 Part 1 - Coding with bases
 * @author [Parth Patel]
 * 
 * *** NOTE: The rules are different for each java file! ***
 *
 * You may not use any of the following:
 * - Any functions found in Java libraries (especially the Math library)
 *     - Example Math.pow, Integer.bitCount, String.valueOf, etc.
 *     - Basically, any function that does everything for you. The purpose of this
 *       homework is to learn how to do these operations yourself, instead of simply
 *       using the Java API!
 *     - The only exception to this rule is String.charAt to get a character from a
 *       String, and you may use String.length to get the size of the string.
 *     - Any Java 7 feature / standard library function (we will be grading using Java 6)
 *     
 * - You my not implement any Java library functions as helpers to use in other functions.
 *     
 * - String concatenation with a number for more than one digit.
 *     - For example, "" + 123 is banned. "" + 6 is allowed, though.
 *     
 * - You may only use 2 if-statements max.
 *     - This means you may have 2 separate conditions, making this okay:
 *     
 *     if(cond1) {
 *         code;
 *     } else {
 *         moreCode;
 *     }
 *     if(cond2) {
 *         code;
 *     } else {
 *         modeCode;
 *     }
 *     
 * - You may not use a switch statement.
 *     
 * - Recursion is not allowed.
 * 
 * - You may not reuse any function to implement another function.
 * 
 * - Typecasting IS allowed.
 * 
 * Your code also must compile, not throw exceptions, or infinitely loop on any of the
 * test cases. If your code infinitely loops, you will get no credit for that function.
 * If your code does not compile or it throws exceptions, there will be a heavy penalty.
 */
public class HW2Bases
{
	/**
	 * strdtoi - Decimal String to int
	 *
	 * Convert a string containing ASCII characters (in decimal) to an int.
	 * You do not have to handle negative numbers. The Strings we will pass in will be
	 * valid decimal numbers, and able to fit in a 32-bit signed integer.
	 * 
	 * Example: strdtoi("123"); // => 123
	 */
	public static int strdtoi(String decimal)
	{
		
		
		
		int counter = 0;
		int num = 0;
		int l = decimal.length();
		
		/*
		 * Counter allows me to go through everything in my string
		 * num is the result  I will be returning
		 * In the while loop I am multiplying each character by 10^x based on its place
		 * 
		 */
		
		while(counter < l){
			num *= 10;
			num += decimal.charAt(counter) - '0';
			counter++;
		}
		
		return num;
	}

	/**
	 * strbtoi - Binary String to int
	 *
	 * Convert a string containing ASCII characters (in binary) to an int.
	 * You do not have to handle negative numbers. The Strings we will pass in will be
	 * valid binary numbers, and able to fit in a 32-bit signed integer.
	 * 
	 * Example: strbtoi("111"); // => 7
	 */
	public static int strbtoi(String binary)
	{
		/*
		 * i goes through the whole string, starting at the
		 * first character and if it is 1, then based on where i is
		 * 2 is raised to that power - 1. 
		 */
		int result = 0;
	    for(int i = 0; i <binary.length(); i++){
	    	
	        if(binary.charAt(i) == '1'){
	         result = result + pow(2,binary.length()-1-i);
	     }

	    }
	    return  result;
	}
	/*
	 * This is a replica of the Math.pow function
	 */
	
	public static int pow(int num, int exponent) {
		int result = 1;
		
		for (int i = 0; i < exponent; i++){
		result *= num;
		}
		return result;
		}
		
	/**
	 * strxtoi - Hexadecimal String to int
	 *
	 * Convert a string containing ASCII characters (in hex) to an int.
	 * You do not have to handle negative numbers. The Strings we will pass in will be
	 * valid hexadecimal numbers, and able to fit in a 32-bit signed integer.
	 * 
	 * NOTE: Hexadecimal numbers are typically prefixed with a "0x". However, for the
	 * sake of this assignment, the strings passed into this function will not have
	 * this prefix.
	 * 
	 * Example: strxtoi("A6"); // => 166
	 */
	public static int strxtoi(String hex)
	{
		
		int num = 0;
		// A list of all hex characters
		// This gives me the ability to simply 
		// pick a character with predefined index, and give them a value
		String rep = "0123456789ABCDEF";
		hex = hex.toUpperCase();
		
		for (int i = 0; i < hex.length(); i++) {
			// to go through the hex string and pick out a char
            char j = hex.charAt(i);
            // Where the character is in rep
            int r = rep.indexOf(j);
            // The index on which the character is at is multiplied by 16 and returned
            num = 16 * num + r;
            
        }
        return num;
	}

	/**
	 * itostrb - int to Binary String
	 *
	 * Convert a int into a String containing ASCII characters (in binary).
	 * You do not have to handle negative numbers.
	 * The String returned should contain the minimum number of characters necessary to
	 * represent the number that was passed in.
	 * 
	 * Example: itostrb(7); // => "111"
	 */
	public static String itostrb(int binary)
	{
		/*
		 * This while loop divides the original number passed in by 2
		 * If the remainder happens to be 0, it represents 0, or else 1.
		 * Concats it to the string, and the keeps doing so until the end.
		 */
		
		String bin = "";
		while ( binary > 0 ) {
			
		bin = (((binary % 2) == 0) ? "0" : "1") + bin;
		binary /= 2;
		
		}
		
		return bin;
	}

	/**
	 * itostrx - int to Hexadecimal String
	 *
	 * Convert a int into a String containing ASCII characters (in hexadecimal).
	 * You do not have to handle negative numbers.
	 * The String returned should contain the minimum number of characters necessary to
	 * represent the number that was passed in.
	 * 
	 * NOTE: Hexadecimal numbers are typically prefixed with a "0x". However, for the
	 * sake of this assignment, the answers returned by this function should not have
	 * this prefix.
	 * 
	 * Example: itostrx(166); // => "A6"
	 */
	public static String itostrx(int hex)
	{
		// Almost the same procedue as hex to int is followed here
		// Based on where the first division takes the loop,
		// the character is added to the string and then hex is redivied again
		// the loop recognizes the last character from the rep string to be added
		// and stops
		String rep = "0123456789ABCDEF";
		String dec = "";
		while(hex > 0){
			int i = hex % 16;
			dec = rep.charAt(i) + dec;
			hex = hex / 16;
		}
		return dec;
	}
}
