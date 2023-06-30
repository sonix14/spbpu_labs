package exceptions;

public class InvalidFileFormatException extends Exception{

	public InvalidFileFormatException() {
		super("Invalid format of input file!");
	}
	
	public InvalidFileFormatException(String msg) {
		super(msg);
	}
}
