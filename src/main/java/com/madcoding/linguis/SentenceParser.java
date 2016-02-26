package com.madcoding.linguis;

public class SentenceParser {
	private final static String WHITE_SPACE_SEPARATOR = " ";

	public static SentenceSemanticResult parseSentence(String sentence) {
		SentenceSemanticResult semanticResults = new SentenceSemanticResult();
		String[] words = sentence.split(WHITE_SPACE_SEPARATOR);
		for (int i = 0; i < words.length; i++) {
			updateResults(semanticResults,words[i],
					Dictionary.getInstance().getWord(words[i]));
		}

		return semanticResults;
	}

	public static void updateResults(SentenceSemanticResult results,
			String wordText, Word word) {
		results.setTotalNumWords(results.getTotalNumWords() + 1);
		double averageChars = (results.getAverageLengthWord() + wordText.length()) / 2;
		results.setAverageLengthWord(averageChars);
		if (word != null) {
			if (word.getType().equals(WordType.ABBR.getType())) {
				results.setNumAbbr(results.getNumAbbr() + 1);
			}
			if (word.getType().equals(WordType.ADJECTIVE.getType())) {
				results.setNumAdjectives(results.getNumAdjectives() + 1);
			}
			if (word.getType().equals(WordType.ADVERB.getType())) {
				results.setNumAdverbs(results.getNumAdverbs() + 1);
			}
			if (word.getType().equals(WordType.NOUN.getType())) {
				results.setNumNouns(results.getNumNouns() + 1);
			}
			if (word.getType().equals(WordType.VERB.getType())) {
				results.setNumVerbs(results.getNumVerbs() + 1);
			}
		}

	}

}
