package com.eman.ifelseparser.stack;

import com.eman.ifelseparser.alphabet.Alphabet;
import com.eman.ifelseparser.sequence.Sequence;
import com.eman.ifelseparser.state.State;

public class EmptyUpdater implements StackUpdater 
{
	@Override
	public void update(Sequence sequence, Alphabet upcomingInput) throws StackUpdaterException
	{
		if(sequence.getStack().isEmpty())
			throw new StackUpdaterException("Stack is empty. It must at least contain z");	
		
		switch(sequence.getState())
		{
			case QO:
				sequence.setState(State.Q1);
				sequence.getStack().push(StackItem.IFSTMT);
				break;
			case Q1:
				updateSequenceInfo(sequence);
				break;
			default:
				throw new StackUpdaterException("Sequence is in an invalid state: " + sequence.getState());
		}
	}
	
	private void updateSequenceInfo(Sequence sequence) throws StackUpdaterException
	{
		switch(sequence.getStack().peek())
		{
			case Z:
				sequence.setState(State.QF);
				break;
			case IFSTMT:
				sequence.getStack().pop();
				break;
			default:
				throw new StackUpdaterException("Transition not defined for empty input with current stack of " + sequence.getStack().peek());
			
		}
	}
}
