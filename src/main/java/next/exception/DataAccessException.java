package next.exception;

public class DataAccessException extends RuntimeException {

    public DataAccessException(Exception exception) {
        super(exception.getMessage());
    }
}
