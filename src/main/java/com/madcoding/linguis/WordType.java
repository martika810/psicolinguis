package com.madcoding.linguis;

public enum WordType {
	NOUN("n."),
	ADJECTIVE("adj."),
	VERB("v."),
	COLLOQIAL("colloq."),
	ABBR("abbr."),
	SYMB("symb."),
	GRAMAT("gram."),
	ADVERB("Adv.");

	private String type;

	WordType(String type) {
		this.type = type.toLowerCase();
	}
	
	public String getType() {
		return type;
	}
	
	public static WordType fromString(String text) {
	    if (text != null) {
	      for (WordType b : WordType.values()) {
	        if (text.equalsIgnoreCase(b.type)) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
	
	 

}
