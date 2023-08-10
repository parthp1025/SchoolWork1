/**
 * HW2 Part 3 - Coding a bit vector
 * @author [Parth Patel]
 * 
 * *** NOTE: The rules are different for each java file! ***
 * 
 * This class represents a bit vector, which is 32 bits which can be manipulated through
 * the use of the methods that this class provides.
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
 *     - String concatenation (+) only in the toString method.
 *     - Iteration may be used for the onesCount, zerosCount, size, and toString methods.
 *     - The assignment operator (of course).
 *
 * You are not allowed to use anything not mentioned above. This includes the following,
 * but is not an exhaustive list:
 *     - Multiplication or Division
 *     - Addition or Subtraction with numbers other than 1.
 *     - Iteration in functions other than the ones listed 7 lines above.
 *     - The unsigned right shift operator >>> (C does not provide this operator).
 *     - Modulus (%).
 *     - Any functions found in Java libraries (especially the Math library).
 *          - Example: Math.pow, Integer.bitCount, etc.
 *     - Casting (you should not have cast anywhere!)
 *     - Any Java 7 feature / standard library function.
 *            
 * Your code also must compile, not throw exceptions, or infinitely loop on any of the
 * test cases. If your code infinitely loops, you will get no credit for that function.
 * If your code does not compile or it throws exceptions, there will be a heavy penalty.
 */
public class HW2BitVector
{
	/** 
	 * 32-bit data initialized to all zeros. Here is what you will be using to represent
	 * the Bit Vector. Do not change its scope from private.
	 */
	private int bits;
	
	/** You may not add any more fields to this class other than the given one. */

	/**
	 * Sets the bit (sets to 1) pointed to by index.
	 * @param index index of which bit to set.
	 *        0 for the least significant bit (right most bit).
	 *        31 for the most significant bit.
	 */
	public void set(int index)
	{
		// Opposite of clearing, so we can just make a shifter
		// Set the bit using an Or.
		bits = bits | (1 << index);
	}

	/**
	 * Clears the bit (sets to 0) pointed to by index.
	 * @param index index of which bit to set.
	 * 	      0 for the least significant bit (right most bit).
	 * 	      31 for the most significant bit.
	 */
	public void clear(int index)
	{
		// Since And erases unlike bits, a created shift that is flipped and combines
		// with And should do it.
		bits = bits & ~(1 << index);
	}

	/**
	 * Toggles the bit (sets to the opposite of its current value) pointed to by index.
	 * @param index index of which bit to set.
	 * 	      0 for the least significant bit (right most bit).
	 * 	      31 for the most significant bit.
	 */
	public void toggle(int index)
	{
		int getto = (1 << index);
		int bin = bits & getto;
		if(bin == 1){
			// orig is flipped
			int flip = ~(1 << index);
			bits = bits & flip;
		}
		else if(bin == 0){
			// flip
			bits = bits | getto;
		}
	}
	
	/**
	 * Returns true if the bit pointed to by index is currently set.
	 * @param index index of which bit to check.  
	 * 	      0 for the least significant bit (right-most bit).
	 * 	      31 for the most significant bit.
	 * @return true if the bit is set, false if the bit is clear.
	 *         If the index is out of range (index >= 32), then return false.
	 */
	public boolean isSet(int index)
	{
		// if bit is 1, return true
		return (bits | (1 << index)) == 1;
	}
	
	/**
	 * Returns true if the bit pointed to by index is currently clear.
	 * @param index index of which bit to check.  
	 * 	      0 for the least significant bit (right-most bit).
	 * 	      31 for the most significant bit.
	 * @return true if the bit is clear, false if the bit is set.
	 *         If the index is out of range (index >= 32), then return true.
	 */
	public boolean isClear(int index)
	{
		// if bit is 0, return true
		return (bits & ~(1 << index)) == 0;
	}
	
	/**
	 * Returns a string representation of this object.
	 * Return a string with the binary representation of the bit vector.
	 * You may use String concatenation (+) here. 
	 * You must return a 32-bit string representation.
	 * i.e if the bits field was 2, then return "00000000000000000000000000000010"
	 */
	public String toString()
	{
		// loop goes through the whole bit vector
		// and if the variable binary is 0, it concats a0 to the string
		// if not, then 1.
		String bin = "";
		for(int i = 0; i < 32; i++){
			int binary = bits & (1 << i);
			bin = ((binary == 0) ? "0" : "1") + bin;
		}
		return bin;
	}

	/**
	 * Returns the number of bits currently set (=1) in this bit vector.
	 * You may obviously use the ++ operator to increment your counter. 
	 */
	public int onesCount()
	{
		int trav = 0;
		//loop for traversing thru the bit vector
		// From 0 to 32 and checks for 1 or 0s
		// if 1 is found it adds 1 to the counter
		for (int i = 0; i < 32; i ++){
			int bin = bits & (1 << i);
			trav = (bin != 0) ? trav++ : trav;
				
		}
		return trav;
	}
	
	/**
	 * Returns the number of bits currently clear (=0) in this bit vector.
	 * You may obviously use the ++ operator to increment your counter. 
	 */
	public int zerosCount()
	{
		// instead of bin != 0, bin == 0, since we are looking for 0s
		int trav = 0;
		
		for (int i = 0; i < 32; i ++){
			int bin = bits & (1 << i);
			trav = (bin == 0) ? trav++ : trav;
				
		}
		return trav;
	}
	
	/**
	 * Returns the "size" of this BitVector. The size of this bit vector is defined
	 * to be the minimum number of bits that will represent all of the ones.
	 * For example, the size of the bit vector 00010000 will be 5.
	 */
	public int size()
	{
		// Almost the same functionality as ones and zerosCount
		// in this case it simply adds up the number, based on what index the loop is on
		// Whereever bin is not 0 the size will be piled up and returned
		int number = 0;
		
		for( int i = 0; i < 32; i++){
			int bin = bits & (1 << i);
			number = (bin != 0) ? i : number;	
		}
		return number;
	}
	
	// *** Do not change any code below here! ***
	
	public HW2BitVector() {
		bits = 0;
	}
	
	public int getBits() {
		return bits;
	}
}
