package am.martirosyan.dormru.exception;

public class RoomNotExistException extends RuntimeException {
    public RoomNotExistException(String message) {
        super(message);
    }
}
