package com.eman.ifelseparser.alphabet;

public class AlphabetExtractor 
{
	private static final char SKIP_CHARS[] = {'\n','\r'};
	
	private StringBuilder charInputs = new StringBuilder();
	private Alphabet extractedAlphabet = Alphabet.NULL;
	
	public boolean isFullAlphabetObtained(char input) throws Exception
	{
		for(char skipChars : SKIP_CHARS)
			if(input == skipChars)
				return false;
		
		charInputs.append(input);
		
		for(Alphabet alphabet : Alphabet.values())
		{
			if(charInputs.toString().equals(alphabet.getAlphabet()))
			{
				extractedAlphabet = alphabet;
				return true;
			}
		}
		
		if(charInputs.length() > Alphabet.MAX_LENGTH)
			throw new Exception("Invalid input detected: " + charInputs.toString());
		
		return false;
	}
	
	public Alphabet getAlphabet() throws Exception
	{
		if(extractedAlphabet == Alphabet.NULL)
			throw new Exception("No valid alphabet has been obtained");
		
		Alphabet alphabet = extractedAlphabet;
		
		// Reset AlphabetExtractor
		charInputs.setLength(0);
		extractedAlphabet = Alphabet.NULL;
		
		return alphabet;
	}
}
