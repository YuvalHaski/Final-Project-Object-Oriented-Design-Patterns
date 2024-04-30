package exceptions;

public class GeneralException extends Exception{
	public GeneralException() {
		super("Genearal exception: Error in program!");
	}
	
	public GeneralException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
