package com.eman.ifelseparser.sequence;
import java.util.Stack;

import com.eman.ifelseparser.alphabet.Alphabet;
import com.eman.ifelseparser.stack.StackItem;
import com.eman.ifelseparser.stack.StackUpdater;
import com.eman.ifelseparser.stack.StackUpdaterException;
import com.eman.ifelseparser.stack.StackUpdaterFactory;
import com.eman.ifelseparser.state.State;

public class Sequence 
{
	private State state;
	private Stack<StackItem> stack;
	private StringBuilder input;
	
	public Sequence()
	{
		state = State.QO;
		input = new StringBuilder("");
		stack = new Stack<StackItem>();
		stack.push(StackItem.Z);
	}
	
	public Stack<StackItem> getStack()
	{
		return this.stack;
	}
	
	public State getState()
	{
		return this.state;
	}
	
	public void setState(State state)
	{
		this.state = state;
	}
	
	public void updateSequence(Alphabet inputAlphabet, Alphabet upcomingInput) throws StackUpdaterException
	{
		this.input.append(inputAlphabet.getAlphabet());
		StackUpdater stackUpdater = StackUpdaterFactory.getStackUpdater(inputAlphabet);
		stackUpdater.update(this, upcomingInput);
	}
}
