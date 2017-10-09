package com.eman.ifelseparser.alphabet;

public enum Alphabet
{
	EMPTY(""), // This is not necessarily part of the alphabet but can be use to transition states.
	IF("if"),
	ELSE("else"),
	LEFTBRACKET("{"),
	RIGHTBRACKET("}"),
	NULL(null);
	
	public static int MAX_LENGTH = 4;
	private String alphabet;
	
	Alphabet(String alphabet)
	{
		this.alphabet = alphabet;
	}
	
	public String getAlphabet()
	{
		return this.alphabet;
	}
}
