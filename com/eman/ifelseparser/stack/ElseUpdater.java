package com.eman.ifelseparser.stack;
import java.util.Stack;

import com.eman.ifelseparser.alphabet.Alphabet;
import com.eman.ifelseparser.sequence.Sequence;
import com.eman.ifelseparser.state.State;

public class ElseUpdater implements StackUpdater 
{
	@Override
	public void update(Sequence sequence, Alphabet upcomingInput) throws StackUpdaterException
	{
		if(sequence.getStack().isEmpty())
			throw new StackUpdaterException("Stack is empty. It must at least contain z");
		
		switch(sequence.getState())
		{
			case Q1:
				updateSequenceInfo(sequence.getState(), sequence.getStack(), upcomingInput);
				break;
			default:
				throw new StackUpdaterException("Sequence is in an invalid state: " + sequence);
		}
	}
	
	private void updateSequenceInfo(State state, Stack<StackItem> stack, Alphabet upcomingInput) throws StackUpdaterException
	{
		switch(stack.peek())
		{
			case ELSESTMT:
				updateStackForElseStmt(stack, upcomingInput);
				break;
			default:
				throw new StackUpdaterException("Transition not defined for else input with current stack of " + stack.peek());
			
		}
	}
	
	private void updateStackForElseStmt(Stack<StackItem> stack, Alphabet upcomingInput) throws StackUpdaterException
	{	
		switch(upcomingInput)
		{
			case IF:
				stack.pop();
				stack.push(StackItem.IFSTMT);
				break;
			case LEFTBRACKET:
				stack.pop();
				stack.push(StackItem.BRACKET);
				break;
			default:
				stack.pop();
		}
	}
}
