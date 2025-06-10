package am.martirosyan.dormru.model;

public enum ComplaintStatus {
    CREATED,
    IN_PROGRESS,
    RESOLVED,
    REJECTED;

    @Override
    public String toString() {
        return switch (this) {
            case CREATED -> "Создан";
            case IN_PROGRESS -> "В процессе";
            case RESOLVED -> "Решено";
            case REJECTED -> "Отклонено";
        };
    }
}
