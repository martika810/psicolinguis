package com.madcoding.linguis;

public class Word {
	private String name;
	private String type;
	private String definition;
	
	public Word(String name, String type, String definition) {
		super();
		this.name = name;
		this.type = type;
		this.definition = definition;
	}
	
	public Word() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	
	
	

}
