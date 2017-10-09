package com.eman.ifelseparser.stack;

import com.eman.ifelseparser.alphabet.Alphabet;

public class StackUpdaterFactory 
{
	public static StackUpdater getStackUpdater(Alphabet alphabet) throws StackUpdaterException
	{
		switch(alphabet)
		{
			case EMPTY:
				return new EmptyUpdater();
			case IF:
				return new IfUpdater();
			case ELSE:
				return new ElseUpdater();
			case LEFTBRACKET:
				return new LeftBracketUpdater();
			case RIGHTBRACKET:
				return new RightBracketUpdater();
			default:
				throw new StackUpdaterException("Invalid input alphabet: " + alphabet);
		}
	}
}
