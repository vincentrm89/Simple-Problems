import java.io.*;
import java.util.*;
  
public class CharacterCount {
  public static void main (String[] args) {
    Character[] sortedInput = new Character[] {'A', 'A', 'B', 'B', 'B', 'C'};
    Character[] unsortedInput = new Character[] {'C', 'B', 'B', 'A', 'B', 'A'};
    
    // output: "A2B3C1"
    System.out.println(unsortedInput(sortedInput));
    
    // output: "A2B3C1"
    System.out.println(unsortedInput(unsortedInput));
    
    // output: "A2B3C1"
    System.out.println(sortedInput(input));
    
    // output: "C1B2A1B1A1"
    System.out.println(sortedInput(unsortedInput));
  }
  
  /**
    * Sorted input edge case, assumes input array is always sorted but will not result in
    * expected output string if it's not sorted.
    */
  public static String sortedInput(Character[] input) {
    StringBuilder output = new StringBuilder("");
    
    Character lastCharacter = null;
    int lastCharacterCount = 0;
    
    for (int i = 0; i < input.length; i++) {
      // new character check and output results of last
      if (i > 0 && lastCharacter != input[i]) {
        output.append(lastCharacter + "" + lastCharacterCount);
        // set to 0 since standard logic will increement to 1
        lastCharacterCount = 0;
      }
      
      lastCharacter = input[i];
      lastCharacterCount++;
      
      // last element check
      if (i == input.length - 1) {
        output.append(lastCharacter + "" + lastCharacterCount);
      }
    }
    
    return output.toString();
  }
  
  /**
    * Unsorted input case, assumes characters can be in any order. TreeMap will always
    * result in a sorted HashMap and general performance of the HashMap ensures faster
    * key/value retrieval. 
    */
  public static String unsortedInput(Character[] input) {
    Map<Character, Integer> sortedMap = new TreeMap<Character, Integer>();
    
    for (Character ch : input) {
      if (sortedMap.containsKey(ch)) {
        int currentValue = sortedMap.get(ch);
        sortedMap.replace(ch, currentValue, currentValue+1);
      } else {
        sortedMap.put(ch, 1);
      }
    }
    
    return convertMap(sortedMap);
  }
  
  public static String convertMap(Map<Character, Integer> sortedMap) {
    StringBuilder output = new StringBuilder("");
    for (Character key : sortedMap.keySet()) {
      output.append(key + "" + sortedMap.get(key));
    }
  
    return output.toString();
  }
}
