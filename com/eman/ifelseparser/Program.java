package com.eman.ifelseparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import com.eman.ifelseparser.alphabet.Alphabet;
import com.eman.ifelseparser.alphabet.AlphabetExtractor;
import com.eman.ifelseparser.sequence.Sequence;
import com.eman.ifelseparser.state.State;

public class Program 
{	
	private static final String FILE_PATH = "sequence.txt";
	
	public static void main(String[] args) throws Exception 
	{
		File fileToParse = new File(FILE_PATH);
		if(!fileToParse.exists()) {
			throw new FileNotFoundException("File " + FILE_PATH + " does not exist");
		}
		
		InputStream inputStream = null;
		Reader reader = null;
		
		try
		{	
			Sequence sequence = new Sequence();
			AlphabetExtractor sequenceAlphabetAnalyzer = new AlphabetExtractor();
			
			inputStream = new FileInputStream(fileToParse);
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));

			int input;
			
			/* Initialize stack to starting stack */
			sequence.updateSequence(Alphabet.EMPTY, null);
			
			/* Read file until end of file, reading through each email block */
			while((input = reader.read()) != -1)
			{
				if(sequenceAlphabetAnalyzer.isFullAlphabetObtained((char) input))
					sequence.updateSequence(sequenceAlphabetAnalyzer.getAlphabet(), getFutureAlphabet(reader));
			}
			
			/* Input empty string to clean up stack. Errors out if invalid transition is detected */
			while(sequence.getState() != State.QF)
				sequence.updateSequence(Alphabet.EMPTY, null);
			
			System.out.println("Correct sequence");
		}
		catch(Exception e)
		{
			System.out.println("Sequence is syntactically incorrect");
		}
		/* Close I/O resources */
		finally
		{
			if(inputStream != null)
				inputStream.close();
			
			if(reader != null)
				reader.close();
		}
	}
	
	public static Alphabet getFutureAlphabet(Reader reader) throws Exception
	{
		reader.mark(Alphabet.MAX_LENGTH);
		
		AlphabetExtractor futureAlphabetExtractor = new AlphabetExtractor();
		int input;
		int readBytes = 0;
		Alphabet alphabet = Alphabet.EMPTY;
		
		/* Read file until end of file, reading through each email block */
		while(((input = reader.read()) != -1) && readBytes < Alphabet.MAX_LENGTH)
		{
			if(futureAlphabetExtractor.isFullAlphabetObtained((char) input))
			{
				alphabet = futureAlphabetExtractor.getAlphabet();
				break;
			}
			readBytes++;
		}

		reader.reset();
		return alphabet;
	}
}
