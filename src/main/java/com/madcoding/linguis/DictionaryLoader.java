package com.madcoding.linguis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryLoader {
	public final static String SEPARATOR_BETWEEN_WORD_AND_REST = "  ";
	public final static String SEPARATOR = " ";
	public final static char POINT = '.';
	public final static String PATTERN_WORD_AT_BEGINNING = "^(\\w+\\s+)";
	public final static String PATTERN_WORD_TYPE = "(-{0,1})(adv|adj|n|v|Adv|adv|abbr|gram|symb)(\\.{1})(\\s+)";

	public static void loadDictionary(String filename, Map<String, Word> wordMap)
			throws IOException {
		BufferedReader reader = null;
		try {
			String nextLine;
			reader = new BufferedReader(new FileReader(filename));
			nextLine = reader.readLine();
			if (nextLine == null) {
				reader.close();
				throw new IOException("Word file empty");
			}

			Word newWord = parseWord(nextLine);
			if (newWord != null) {
				wordMap.put(newWord.getName(), newWord);
			}

			while ((nextLine = reader.readLine()) != null) {
				if (!nextLine.isEmpty() && nextLine.length() > 1) {
					newWord = parseWord(nextLine);
					if (newWord != null && newWord.getName() != null
							&& newWord.getType() != null) {
						wordMap.put(newWord.getName(), newWord);
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Error parsing dict.txt");
		} finally {
			reader.close();
		}
	}

	public static Word parseWord(String text) {
		Pattern pattern = Pattern.compile(PATTERN_WORD_AT_BEGINNING);
		Pattern patternWordType = Pattern.compile(PATTERN_WORD_TYPE);
		Matcher matcher = pattern.matcher(text);
		//Matcher matcherWordType = patternWordType.matcher(text);
		
		
		Word wordToReturn = new Word();
		if (text == null || text.isEmpty() || text.length() < 3) {
			return null;
		}
		try {
			String[] wordText = text.split(SEPARATOR_BETWEEN_WORD_AND_REST);
			if (wordText.length == 2) {
				wordToReturn.setName(wordText[0].toLowerCase());
				String[] moreWordText = wordText[1].split(SEPARATOR);
				String wordType = moreWordText[0].replace("-", "");
				if (isWordType(wordType)) {
					wordToReturn.setType(wordType);
					wordToReturn.setDefinition(moreWordText[1]);
				} else if (isWordType(moreWordText[1])) {
					wordToReturn.setType(moreWordText[1]);
					wordToReturn.setDefinition(moreWordText[2]);
				}
			}else if(wordText.length == 3){
				wordToReturn.setName(wordText[0].toLowerCase());
				String wordType = wordText[1].replace("-", "");
				if (isWordType(wordType)) {
					wordToReturn.setType(wordType);
					wordToReturn.setDefinition(wordText[2]);
				} 
			}
			return wordToReturn;
		} catch (Exception e) {
			System.out.println("LINE: " + text);
			e.printStackTrace();
			return wordToReturn;

		}
	}
	
	public static Word parseLine(String text) {
		Pattern pattern = Pattern.compile(PATTERN_WORD_AT_BEGINNING);
		Pattern patternWordType = Pattern.compile(PATTERN_WORD_TYPE);
		
		Word wordToReturn = new Word();
		if (text == null || text.isEmpty() || text.length() < 3) {
			return null;
		}
		try {
			Matcher matcher = pattern.matcher(text);
			//String wordNameAux1 = matcher.group();
			boolean matchFound = matcher.lookingAt();
			String wordName = (matchFound)?matcher.group():"";
			text = text.replace(wordName, "");
			if (wordName.trim().length() > 2) {
				wordToReturn.setName(wordName.trim().toLowerCase());
				Matcher wordTypeMatcher = patternWordType.matcher(text);
				String wordType = wordTypeMatcher.lookingAt()?wordTypeMatcher.group():"";
				if (wordType.trim().length()>1 && isWordType(wordType.trim().replace("-", ""))) {
					text = text.replace(wordType, "");
					wordToReturn.setType(wordType.trim().replace("-", ""));
					wordToReturn.setDefinition(text);
				} 
			}
			return wordToReturn;
		} catch (Exception e) {
			System.out.println("LINE: " + text);
			e.printStackTrace();
			return wordToReturn;

		}
	}

	public static boolean isWordType(String word) {
		if (word == null)
			return false;
		if (word.length() <= 0)
			return false;
		if (word.charAt(word.length() - 1) == POINT
				&& WordType.fromString(word) != null) {
			return true;
		} else {
			return false;
		}
	}

}
