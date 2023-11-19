package mancala;

public class InvalidMoveException extends Exception{
    private static final long serialVersionUID = 1L;
    
    public InvalidMoveException() {
        super("\n---------------\nInvalid Move\n---------------\n");
    }

    public InvalidMoveException(final Throwable cause) {
        super(cause);
    }
}
