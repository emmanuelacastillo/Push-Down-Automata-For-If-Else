package com.eman.ifelseparser.stack;
import java.util.Stack;

import com.eman.ifelseparser.alphabet.Alphabet;
import com.eman.ifelseparser.sequence.Sequence;

public class IfUpdater implements StackUpdater 
{
	@Override
	public void update(Sequence sequence, Alphabet upcomingInput) throws StackUpdaterException
	{
		if(sequence.getStack().isEmpty())
			throw new StackUpdaterException("Stack is empty. It must at least contain z");
		
		switch(sequence.getState())
		{
			case Q1:
				updateSequenceInfo(sequence.getStack(), upcomingInput);
				break;
			default:
				throw new StackUpdaterException("Sequence is in an invalid state: " + sequence.getState());
		}
	}
	
	private void updateSequenceInfo(Stack<StackItem> stack, Alphabet upcomingInput) throws StackUpdaterException
	{
		switch(stack.peek())
		{
			case IFSTMT:
				updateStackForIfStmt(stack, upcomingInput);
				break;
			case ELSESTMT:
				stack.pop();
				stack.push(StackItem.ELSESTMT);
				break;
			default:
				throw new StackUpdaterException("Transition not defined for if input with current stack of " + stack.peek());
			
		}
	}
	
	private void updateStackForIfStmt(Stack<StackItem> stack, Alphabet upcomingInput) throws StackUpdaterException
	{
		switch(upcomingInput)
		{
			case ELSE:
				stack.pop();
				stack.push(StackItem.ELSESTMT);
				break;
			case LEFTBRACKET:
				stack.pop();
				stack.push(StackItem.BRACKET);
				break;
			case IF:
				stack.pop();
				stack.push(StackItem.IFSTMT);
				break;
			default:
				stack.pop();
				break;
		}
	}
}
