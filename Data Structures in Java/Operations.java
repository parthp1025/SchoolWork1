/**
 * HW2 Part 2 - Coding with bitwise operators
 * @author [Parth Patel]
 * 
 * *** NOTE: The rules are different for each java file! ***
 * 
 * The only things you are allowed to use in your code are the following:
 *	   - The bitwise operators |, &, ~, ^, <<, >>
 *     - Increment and Decrement (++ and --).
 *         - You may also add or subtract 1 from a number, but it must be only 1.
 *     - Boolean operators ||, &&, and !, which are only allowed in if-statements.
 *     - Conditional statements (if, if-else, switch-case, ? :).
 *         - However, you may only handle up to two cases.
 *         - You may only use up to 2 if/else-if statements.
 *     - Equality comparisons (==, !=, <=, >=, <, >).
 *
 * You are not allowed to use anything not mentioned above. This includes the following,
 * but is not an exhaustive list:
 *     - Multiplication or Division
 *     - Addition or Subtraction with numbers other than 1.
 *          - Iteration in functions other than toString.
 *          - The unsigned right shift operator >>> (C does not provide this operator).
 *     - Modulus (%).
 *     - Any functions found in Java libraries (especially the Math library).
 *         - Example: Math.pow, Integer.bitCount, etc.
 *     - Casting (you should not have cast anywhere!)
 *          - Any Java 7 feature / standard library function.
 *            (we will be grading using Java 6)
 *      
 *     - Switch-case ban, you may not use more than 2 if-statements, or two
 *       case-statements using switch. (see the next point)
 *     - Your code should be concise and simple. None of these functions should be more
 *       than about 5-7 lines of code. If your code is any longer than this, then you are
 *       doing it wrong or inefficiently.
 *       
 * Your code also must compile, not throw exceptions, or infinitely loop on any of the
 * test cases. If your code infinitely loops, you will get no credit for that function.
 * If your code does not compile or it throws exceptions, there will be a heavy penalty.
 *
 * Remember that for this assignment, All bit masks must be written in hexadecimal.
 * This is the convention for masks and makes it much easier for the programmer to
 * understand the code. If you write a mask in any other base you will lose points.
 *
 * All of these functions accept ints as parameters because if you pass in a number
 * (which is of type int by default) into a function accepting a byte, then the Java
 * compiler will complain even if the number would fit into that type.
 *    
 * Now, keep in mind the return value is also an int. Please read the comments about how
 * many significant bits to return and make sure that the other bits are not set or else
 * you will not get any points for that test case.
 * i.e if I say to return 6 bits and you return 0xFFFFFFFF, you will lose points.
 *
 * Definitions of types:
 * nibble (nybble) - 4 bits
 * byte - 8 bits
 * short - 16 bits
 * int - 32 bits
 */
public class HW2Operations
{
	/**
	 * Set an 8-bit byte in an int.
	 * 
	 * Ints are made of 4 bytes, numbered like so:
	 *   33333333 22222222 11111111 00000000
	 *
	 * For a graphical representation of the bits:
	 *   3 3 2 2 2 2 2 2 2 2 2 2 1 1 1 1 1 1 1 1 1 1
	 *   1 0 9 8 7 6 5 4 3 2 1 0 9 8 7 6 5 4 3 2 1 0 9 8 7 6 5 4 3 2 1 0
	 *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 *  |     Byte3     |     Byte2     |     Byte0     |     Byte0     |
	 *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 
	 * Examples:
	 *     setByte(0x12345678, 0xFF, 0); // => 0x123456FF
	 *     setByte(0xEEEEEEEE, 0x56, 2); // => 0xEE56EEEE
	 *     
	 * Remember, as per the instructions at the top of this file, multiplication
	 * is not allowed. We will check this, and penalize you if you use it here,
	 * or anywhere else in this file!
	 * 
	 * @param num The int that will be modified.
	 * @param theByte The byte to insert into the integer.
	 * @param which Selects which byte to modify - 0 for least-significant byte.
	 *            
	 * @return The modified int.
	 */
	public static int setByte(int num, int theByte, int which)
	{
		// push is something we are trying to push in after flipping and shifting 
		// it based on the user choosen which and getting to the correct nibble
		// After doing so, it shifts the nibble and sets it to num to be returned
		
		int push = 0xFF;
		
		push = ~(push << ((which) << 0x3));
		
		theByte = theByte << ((which) << 0x3);
		
		num = (num & push) | theByte;

		return num;
	}
	
	/**
	 * Get a nibble from an int.
	 * 
	 * Examples: 
	 *     getNibble(0x56781234, 3); // => 0x1
	 *     getNibble(0xFF254545, 7); // => 0xF
	 * 
	 * @param num The int to get a nibble from.
	 * @param which Determines which nibble gets returned - 0 for least-significant nibble.
	 *            
	 * @return A short corresponding to the "which" parameter from num.
	 */
	public static int getNibble(int num, int which)
	{

		int result = 0;
		// Both if statements, shift the passed in number based on the
		// bit being accessed.
		if (which == 3) {
			
			result = (num >> 0x5) & 0xF;
		}

		if (which == 7) {
			

			result = (num >> 0x1C) & 0xF;

		}

		return result;
	}
		
	

	/**
	 * Pack 4 nibbles into a short.
	 * 
	 * The nibbles should be placed consecutively in the 16 bit short in the order
	 * specified by the parameters.
	 * 
	 * Example:
	 *     pack(0x1, 0x2, 0x3, 0x4); // => 0x1234
	 *     pack(0xB, 0xE, 0xE, 0xF); // => 0xBEEF
	 * 
	 * @param n1 Most significant nibble (will always be a 4-bit number).
	 * @param n2 2nd nibble (will always be a 4-bit number).
	 * @param n3 3rd nibble (will always be a 4-bit number).
	 * @param n4 4rd nibble (will always be a 4-bit number).
	 *            
	 * @return a 16-bit value formatted like so: n1n2n3n4
	 */
	public static int pack(int n1, int n2, int n3, int n4)
	{
		// in order, from most significant to least significant nibble
		int result = 0;
		// This shifts n1 to the right by 12 places and adds it to result
		// In order each nibble is shifter by 12, 8 , 4 and the last one 
		// is not shifted since its the least significant
		result = (n1 << 0xC) | result;
		
		result = (n2 << 8) | result;
		
		result = (n3 << 4) | result;
		
		result = n4;

		return result;
		
	}
	
	/**
	 * Take the absolute value of an n-bit number.
	 * 
	 * Examples:
	 *     abs(0x00001234, 16); // => 0x00001234
	 *     abs(0x00001234, 13); // => 0x00000DCC
	 * 
	 * Note: We will only pass in values 1 to 31 for n.
	 * 
	 * @param num An n-bit 2's complement number.
	 * @param n The bit length of the number.
	 * @return The n-bit absolute value of num.
	 */
	public static int abs(int num, int n)
	{
		int result = 0;
		//taking the nbits of the number
		int x = (1 << n - 1);
		int y = (num & x);
		// first case is for taking care of negatives
		if( y < 0){
			int z = ~x + 1;
			result = (num | z);
			result = ~result + 1;
		}
		// if positive
		else if(y == 0){
			result = num;
		}
		
		return result;
	}

	/**
	 * NOTE: For this method, you may only use &, |, and ~.
	 *
	 * Perform an exclusive-or on two 32-bit ints.
	 *
	 * Examples:
	 *     xor(0xFF00FF00, 0x00FF00FF); // => 0xFFFFFFFF
	 *     xor(0x12345678, 0x87654321); // => 0x95511559
	 * 
	 * @param num1 An int
	 * @param num2 Another int
	 *
	 * @return num1 ^ num2
	 */
	public static int xor(int num1, int num2)
	{
		// think of this as 2 input pins in logism
		// Or and And of the 2 inputs are first acquired
		// And then getting their Or again will return a XOR
		int x = (~num1) & num2;
		int y = (~num2) & num1;
		int result = x | y;
		return result;
	}
	
	/**
	 * Return true if the given number is a power of 2.
	 * 
	 * Examples:
	 *     powerOf2(1024); // => true
	 *     powerOf2(23);   // => false
	 *
	 * Note: Make sure you handle ALL the cases!
	 * 
	 * @param Num a 32-bit int. Since this is an int, it is SIGNED!
	 * @return true if num is a power of 2.
	 */
	public static boolean powerOf2(int num)
	{
		// for example, if num is 4, 4 & 3 should be 0 since 100 & 011 is 0
		return (num != 0) && (num & (num - 1)) == 0;
	}
}
