package uet.oop.bomberman.exceptions;

public class GameException extends Exception {
	public GameException() {
	}
	
	public GameException(String str) {
		super(str);
		
	}
	
	public GameException(String str, Throwable cause) {
		super(str, cause);
		
	}
	
	public GameException(Throwable cause) {
		super(cause);
		
	}

}
