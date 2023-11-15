package christmas.exception;

public class InvalidDateException extends IllegalArgumentException {

    public InvalidDateException() {
        super(ExceptionMessage.INVALID_DATE_EXCEPTION_MESSAGE.message());
    }
}
