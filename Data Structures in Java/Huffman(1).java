import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

	/**
	 * Builds a frequency map of characters for the given string.
	 * 
	 * This should just be the count of each character.
	 * 
	 * @param s
	 * @return
	 */
	public static Map<Character, Integer> buildFrequencyMap(String s) {
		HashMap<Character, Integer> freqMap = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++) {
			char currChar = s.charAt(i);
			if(freqMap.containsKey(currChar)) freqMap.put(currChar, freqMap.get(currChar) + 1);
			else freqMap.put(currChar, 1);
			
			System.out.println(currChar + " " + freqMap.get(currChar));
		}
		
		System.out.println();
		return freqMap;
	}
	
	/**
	 * Build the Huffman tree using the frequencies given.
	 * 
	 * The frequency map will not necessarily come from the buildFrequencyMap() method.
	 * 
	 * @param freq
	 * @return
	 */
	public static Node buildHuffmanTree(Map<Character, Integer> freq) {
		PriorityQueue<Node> freqQueue= new PriorityQueue<Node>(freq.size());
		
		for(Character key: freq.keySet())
			freqQueue.offer(new Node(key, freq.get(key)));
		
        while (freqQueue.size() > 1) {
        	Node a = freqQueue.poll();
        	Node b = freqQueue.poll();
        	
        	Node temp = new Node(a,b);
        	freqQueue.offer(temp);
        	System.out.println("Left: " + a.character + " " + a.frequency);
        	System.out.println("Right: " + b.character + " " + b.frequency);
        	System.out.println("Node: " + temp.character + " " + temp.frequency);
        }
        
        Node tree = freqQueue.poll();
        System.out.println(tree.frequency + " " + tree.left.frequency + " " + tree.right.frequency);
        return tree;
        
	}
	
 	/**
 	 * Traverse the tree and extract the encoding for each character in the tree
 	 * 
 	 * The tree provided will be a valid huffman tree but may not come from the buildHuffmanTree() method.
 	 * 
 	 * @param tree
 	 * @return
 	 */
	
	
 	public static Map<Character, EncodedString> buildEncodingMap(Node tree) {
 		HashMap<Character,EncodedString> codeMap = new HashMap<Character, EncodedString>();
 		EncodedString code = new EncodedString();
 		codeMap = encodingMapHelper(tree, codeMap, code);
 		return codeMap;
 	}
 		
 	@SuppressWarnings("unchecked")
	public static HashMap<Character, EncodedString> encodingMapHelper(Node tree, HashMap encodedMap, EncodedString code){
 		EncodedString encoded = new EncodedString();
 		if(tree.character > 0) {
 			encoded.concat(code);
 			System.out.println(tree.character);
 			encoded.print();
 			encodedMap.put(tree.character, encoded);
 		}
 		
 		else if(tree.character == 0){
 			
 			//traverse left
 			code.zero();
 			encodingMapHelper(tree.left, encodedMap, code);
 			code.remove();
 			
 			//traverse right
 			code.one();
 			encodingMapHelper(tree.right, encodedMap, code);
 			code.remove();
 		}
 		
 		return encodedMap;
 	}
	
	/**
	 * Encode each character in the string using the map provided.
	 * 
	 * If a character in the string doesn't exist in the map ignore it.
	 * 
	 * The encoding map may not necessarily come from the buildEncodingMap() method, but will be correct
	 * for the tree given to decode() when decoding this method's output.
	 * 
	 * @param encodingMap
	 * @param s
	 * @return
	 */
	public static EncodedString encode(Map<Character, EncodedString> encodingMap, String s) {
		EncodedString encode = new EncodedString();
		for(int i = 0; i < s.length(); i++) {
			EncodedString code = encodingMap.get(s.charAt(i));
			encode.concat(code);
		}
		encode.print();
		return encode;
	}
	
	/**
	 * Decode the encoded string using the tree provided.
	 * 
	 * The encoded string may not necessarily come from encode, but will be a valid string for the given tree.
	 * 
	 * (tip: use StringBuilder to make this method faster -- concatenating strings is SLOW)
	 * 
	 * @param tree
	 * @param es
	 * @return
	 */
	public static String decode(Node tree, EncodedString es) {
		StringBuilder build = new StringBuilder();
		Node temp = tree;
		Iterator<Byte> iter = es.iterator();
		while(iter.hasNext()){
			byte next = iter.next();
			if(next == 0){
				temp = temp.left;
				next = es.iterator().next();
				
				if(temp.character > 0){
					char c = temp.character;
					build.append(c);
					temp = tree;	
				}
			}
			else if(next == 1){
				temp = temp.right;
				next = es.iterator().next();
				
				if(temp.character > 0){
					char c = temp.character;
					build.append(c);
					temp = tree;
				}
			}
		}
		
		String decoded = build.toString();
		System.out.println(decoded);
		return decoded;
	}
	
	public static void main(String[] args) {
		String s = "AAABBCCDDDEF";
		Map<Character, Integer> map = buildFrequencyMap(s);
		Node tree = buildHuffmanTree(map);
		Map<Character, EncodedString> encodeMap = buildEncodingMap(tree);
		EncodedString encode = encode(encodeMap, s);
		String decode = decode(tree, encode);
		
	}
}
