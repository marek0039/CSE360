public class FailedException extends Exception {    //custom exception to print a custom error message.
    public FailedException(String errorMessage) {
        super(errorMessage);
    }
}