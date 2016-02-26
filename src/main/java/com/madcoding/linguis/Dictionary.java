package com.madcoding.linguis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
	public final static String DICTIONARY_FILENAME = "./dic_words.txt";
	public static Dictionary INSTANCE = null;
	private Map<String, Word> dictonaryMap = null;

	public synchronized static Dictionary getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Dictionary();
		}
		return INSTANCE;
	}

	private Dictionary() {
		this.dictonaryMap = new HashMap<String, Word>();
		try {
			DictionaryLoader.loadDictionary(DICTIONARY_FILENAME, dictonaryMap);
		} catch (Exception e) {

		}
	}
	
	public Word getWord(String word){
		word = word.trim();
		return dictonaryMap.get(word);
	}
	
	public int size(){
		return this.dictonaryMap.keySet().size();
	}

}
