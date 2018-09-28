import static org.junit.Assert.*;

import java.util.Map.Entry;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EncryptorTest {

	@Test
	public void whenGivenInputStringAndRotationValue_ReturnsEncryptedString() throws Exception {
		Encryptor encryptor = new Encryptor();
		assertEquals("f", encryptor.encrypt("a", 5));
		assertEquals("e", encryptor.encrypt("z", 5));
		assertEquals("ftnkj", encryptor.encrypt("aoife", 5));
		assertEquals("ftnkj", encryptor.encrypt("AOIFE", 5));
		assertEquals("ftnkj", encryptor.encrypt("aOiFe", 5));
		assertEquals("jgnnq", encryptor.encrypt("Hello", 2));
		assertEquals("jgnnqjgnnq", encryptor.encrypt("HelloHello", 2));
		assertEquals("jgnnq jgnnq", encryptor.encrypt("Hello Hello", 2));
		assertEquals("jgnnq jgnnq1", encryptor.encrypt("Hello Hello1", 2));
		assertEquals("jgnnq jgnnq!", encryptor.encrypt("Hello Hello!", 2));
		assertEquals("ofaf", encryptor.encrypt("java", 5));
		assertEquals("ofaf123!", encryptor.encrypt("java123!", 5));
	}

	@Test
	public void whenGivenInputStringAndRotationValue_ReturnsDecryptedString() throws Exception {
		Encryptor encryptor = new Encryptor();
		assertEquals("aoife", encryptor.decrypt("ftnkj", 5));
		assertEquals("aoife", encryptor.decrypt("FTNKJ", 5));
		assertEquals("aoife", encryptor.decrypt("FtnKJ", 5));
		assertEquals("!!!!", encryptor.decrypt("!!!!", 5));
		assertEquals("java", encryptor.decrypt("ofaf", 5));
		assertEquals("java123!", encryptor.decrypt("ofaf123!", 5));
	}

	@Test(expected = Exception.class)
	public void whenRotationValueIsInvalidEncrypt_expectExceptionThrown() throws Exception {
		Encryptor encryptor = new Encryptor();
		assertEquals("e", encryptor.encrypt("abc", 0));
		assertEquals("e", encryptor.encrypt("def", -21)); // won;t run
		assertEquals("kdjk", encryptor.encrypt("hij", 21));
	
	}
	
	
	@Test(expected = Exception.class)
	public void whenRotationValueIsInvalidDecrypt_expectExceptionThrown() throws Exception {
		Encryptor encryptor = new Encryptor();
		assertEquals("e", encryptor.decrypt("abc", 0));
		assertEquals("e", encryptor.decrypt("def", -21));
		assertEquals("kdjk", encryptor.decrypt("hij", 21));
	}


	public void whenInputStringIsNull_expectExceptionThrown() throws Exception {
		Encryptor encryptor = new Encryptor();
		encryptor.encrypt(null, 5);
		encryptor.encrypt("", 2);
	}

	@Test
	public void testRegexAndTypesOfCharacters() {

		assertEquals(true, Character.isAlphabetic('a'));
		assertEquals(true, Character.isAlphabetic('A'));
		assertEquals(false, Character.isAlphabetic('1'));

		assertEquals(false, Character.isDigit('a'));
		assertEquals(true, Character.isDigit('3'));

		assertEquals(true, Character.isSpace(' '));

		assertEquals(true, Character.toString('%').matches("[$&+,:;=?@#|'<>.^*()%!-]"));
		assertEquals(false, Character.toString('b').matches("[$&+,:;=?@#|'<>.^*()%!-]"));
	}

	@Test
	public void populateMap_AndCheckLetterIsMappedToCorrectOutput() {
		Encryptor encrypt = new Encryptor();
		encrypt.populateMap(5);
		encrypt.getAlphabetMap();

		for (Entry<String, String> entry : encrypt.getAlphabetMap().entrySet()) {
			assertEquals(entry.getValue().equals("f"), entry.getKey().equals("a"));
			assertEquals(entry.getValue().equals("t"), entry.getKey().equals("o"));
			assertEquals(entry.getValue().equals("a"), entry.getKey().equals("v"));
		}
	}
	
	@Test
	public void test() throws Exception {
		Encryptor encryptor = new Encryptor();
		encryptor.populateMap(10);
		encryptor.printMap(10);
		assertEquals("rovvy gybvn", encryptor.encrypt("Hello World", 10));
	}
}
