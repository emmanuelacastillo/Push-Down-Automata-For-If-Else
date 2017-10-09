package com.eman.ifelseparser.stack;

import com.eman.ifelseparser.alphabet.Alphabet;
import com.eman.ifelseparser.sequence.Sequence;

public interface StackUpdater
{
	public void update(Sequence sequence, Alphabet upcomingInput) throws StackUpdaterException;
}
