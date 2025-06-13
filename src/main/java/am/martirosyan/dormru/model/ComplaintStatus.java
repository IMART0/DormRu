package am.martirosyan.dormru.model;

public enum ComplaintStatus {
    CREATED,
    IN_PROGRESS,
    RESOLVED,
    REJECTED;

    @Override
    public String toString() {
        return switch (this) {
            case CREATED -> "\uD83C\uDD95 Создан";
            case IN_PROGRESS -> "⌛️ В процессе";
            case RESOLVED -> "✅ Решено";
            case REJECTED -> "❌ Отклонено";
        };
    }
}
