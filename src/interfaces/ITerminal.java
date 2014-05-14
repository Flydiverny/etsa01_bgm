package interfaces;

public interface ITerminal {
	public void input(char c);
	
	enum State {
		AWAITING_PIN,
		AWAITING_SCAN,
		AWAITING_OP,
		AWAITING_OPERATOR
	}
}
