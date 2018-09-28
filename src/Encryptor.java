import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Encryptor {
	private Map<String, String> alphabetMap;
	private String[] alphabet;

	public Encryptor() {
		alphabet = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z" };//utility
	}

	public Map<String, String> getAlphabetMap() {
		return alphabetMap;
	}

	public void setAlphabetMap(Map<String, String> alphabetMap) {
		this.alphabetMap = alphabetMap;
	}
	
	public String decrypt(String inputString, int rotationValue) throws Exception{
		if (rotationValue == 0 || rotationValue > 20 || rotationValue < -20) {
			throw new Exception("Invalid rotation value");
		}
		if (inputString.equals("") || inputString.equals(null)) {
			throw new Exception("Invalid null value input String");
		}
		String outputValue = "";

		populateMap(rotationValue);
		char[] stringToCharArray = inputString.toCharArray();

		for (int i = 0; i <= stringToCharArray.length - 1; i++) {
			char selectedCh = stringToCharArray[i];
			String s = Character.toString(selectedCh);
			
			for (Entry<String, String> map : getAlphabetMap().entrySet()) {
				if (Character.isAlphabetic(selectedCh)) {
					if (Character.toString(selectedCh).equalsIgnoreCase(map.getValue())) {
						outputValue += map.getKey();
					}
				}
			} // end for traversing alphabet map
			if (Character.isDigit(selectedCh)) {
				outputValue += Character.toString(selectedCh);
			} else if (Character.isSpace(selectedCh)) {
				outputValue += " ";
			}
			else if(Character.toString(selectedCh).matches("[$&+,:;=?@#|'<>.^*()%!-]")) {
				outputValue += Character.toString(selectedCh);
			}
		
		}// end for traversing char array
		return outputValue;
	}

	public String encrypt(String inputString, int rotationValue) throws Exception {

		if (rotationValue == 0 || rotationValue > 20 || rotationValue < -20) {
			throw new Exception("Invalid rotation value");
		}
		if (inputString == null || inputString.equals("")) {
			throw new Exception("Invalid null value input String");
		}
		String outputValue = "";

		populateMap(rotationValue);
		char[] stringToCharArray = inputString.toCharArray();

		for (int i = 0; i <= stringToCharArray.length - 1; i++) {
			char selectedCh = stringToCharArray[i];
			String s = Character.toString(selectedCh);
			
			for (Entry<String, String> map : getAlphabetMap().entrySet()) {
				if (Character.isAlphabetic(selectedCh)) {
					if (Character.toString(selectedCh).equalsIgnoreCase(map.getKey())) {
						outputValue += map.getValue();
					}
				}
			} // end for traversing alphabet map
			if (Character.isDigit(selectedCh)) {
				outputValue += Character.toString(selectedCh);
			} else if (Character.isSpace(selectedCh)) {
				outputValue += " ";
			}
			else if(Character.toString(selectedCh).matches("[$&+,:;=?@#|'<>.^*()%!-]")) {
				outputValue += Character.toString(selectedCh);
			}
		
		}// end for traversing char array
		return outputValue;
	}

	public void printMap(int rotationValue) {
		System.out.println("Alphabet map for rotation value " + rotationValue);
		for (Entry<String, String> entry : getAlphabetMap().entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue());
		}
	}

	public void populateMap(int rotationValue) {
		Map<String, String> alphabetMap = new HashMap<String, String>();
		for (int i = 0; i <= alphabet.length - 1; i++) {
			String key = alphabet[i];
			String value = "";
			if ((i + rotationValue) > alphabet.length - 1) {
				try {
					value = alphabet[i + rotationValue];
				} catch (ArrayIndexOutOfBoundsException e) {
					int difference = ((alphabet.length) - i);
					value = alphabet[(0 + rotationValue) - difference];
				}
			} else {
				value = alphabet[i + rotationValue];
			}
			alphabetMap.put(key, value);
			setAlphabetMap(alphabetMap);

		}
		printMap(rotationValue);
	}

}
