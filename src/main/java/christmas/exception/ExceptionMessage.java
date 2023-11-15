package christmas.exception;

public enum ExceptionMessage {

    INVALID_DATE_EXCEPTION_MESSAGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER_EXCEPTION_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String message() {
        return message;
    }
}
