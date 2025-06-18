package am.martirosyan.dormru.model;

public enum ComplaintStatus {
    CREATED,
    IN_PROGRESS,
    RESOLVED,
    REJECTED;

    public static ComplaintStatus toStatus(String status) {
        return switch (status) {
            case "\uD83C\uDD95 Создан" -> CREATED;
            case "⌛️ В процессе" -> IN_PROGRESS;
            case "✅ Решено" -> RESOLVED;
            case "❌ Отклонено" -> REJECTED;
            default -> throw new IllegalArgumentException("Unknown status: " + status);
        };
    }

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
