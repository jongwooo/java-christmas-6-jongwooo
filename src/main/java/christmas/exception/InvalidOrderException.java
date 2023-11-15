package christmas.exception;

public class InvalidOrderException extends IllegalArgumentException {

    public InvalidOrderException() {
        super(ExceptionMessage.INVALID_ORDER_EXCEPTION_MESSAGE.message());
    }
}
