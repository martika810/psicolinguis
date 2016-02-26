package com.madcoding.linguis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestDictionaryLoader {
	
	
	@Test
	public void testIsWordType(){
		assertTrue(DictionaryLoader.isWordType("abbr."));
		assertFalse(DictionaryLoader.isWordType("-v."));
		assertFalse(DictionaryLoader.isWordType("(-sing)"));
	}
	
	@Test
	public void testParseLine1(){
		String lineDictionary = "Aback  adv.  take aback surprise, disconcert. [old english: related to *a2]";
		Word word = DictionaryLoader.parseLine(lineDictionary);
		assertTrue(word!=null);
		assertTrue(word.getName().equals("aback"));
		assertTrue(word.getType().equals(WordType.ADVERB.getType()));
	}
	
	@Test
	public void testParseWord2(){
		String lineDictionary = "Name  -n. 1 word by which an individual person, family, animal, place, or thing is spoken of etc. 2 a (usu. Abusive) term used of a person etc. (called him names). B word denoting an object or esp. A class of objects etc. (what is the name of those flowers?). 3 famous person. 4 reputation, esp. A good one. —v. (-ming) 1 give a name to. 2 state the name of. 3 mention; specify; cite. 4 nominate.  have to one's name possess. In the name of as representing; by virtue of (in the name of the law). In name only not in reality. Make a name for oneself become famous.  nameable adj. [old english]";
		Word word = DictionaryLoader.parseWord(lineDictionary);
		assertTrue(word!=null);
		assertTrue(word.getName().equals("name"));
		assertTrue(WordType.NOUN.getType().equals(word.getType()));
	}
	
	@Test
	public void testLoadDictionary(){
		assertTrue(Dictionary.getInstance().getWord("name")!=null);
		assertTrue(Dictionary.getInstance().getWord("basket")!=null);
		assertTrue(Dictionary.getInstance().getWord("basket").getType().equals(WordType.NOUN.getType()));
		assertTrue(Dictionary.getInstance().size()>30000);
	}

}
