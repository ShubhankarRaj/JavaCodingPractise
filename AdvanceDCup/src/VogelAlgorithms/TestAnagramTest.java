package VogelAlgorithms;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestAnagramTest {
	
	@Test
	public void testTrueAnagram(){
		assertEquals(true, AnagramTest.areAnagrams("chunky", "kunchy"));
	}
	
	@Test
	public void testFalseAnagram(){
		assertEquals(false, AnagramTest.areAnagrams("raja", "rani"));
	}

}
