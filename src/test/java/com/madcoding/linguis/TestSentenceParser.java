package com.madcoding.linguis;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.Semaphore;

import org.junit.Test;

public class TestSentenceParser {
	public static final String SENTENCE1="my name is marta, i am a girl , i want to dance ";
	@Test
	public void testParseSentence(){
		SentenceSemanticResult results1 = SentenceParser.parseSentence(SENTENCE1);
		assertTrue(results1.getTotalNumWords()==8);
		//assertTrue(results1.getAverageLengthWord()==2.82);
		assertTrue(results1.getNumNouns()==2);
		assertTrue(results1.getNumVerbs()==2);
	}

}
