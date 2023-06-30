package exceptions;

public class FileReadException extends Exception{

	public FileReadException() {
		super("Unable to read the file!");
	}
	
	public FileReadException(String msg) {
		super(msg);
	}
}
