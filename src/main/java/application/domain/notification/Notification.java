package application.domain.notification;

import application.domain.Entity;

import java.util.UUID;

public class Notification extends Entity<UUID> {
    private final UUID fromUser;
    private final UUID toUser;
    private final String message;
    private final NotificationType type;
    private final NotificationStatus status;
    public Notification(UUID uuid, UUID fromUser, UUID toUser, String message, NotificationType type, NotificationStatus status) {
        super(uuid);
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
        this.type = type;
        this.status = status;
    }

    public Notification(UUID uuid, UUID fromUser, UUID toUser, String message, String type, String status) {
        this(uuid, fromUser, toUser, message, NotificationType.valueOf(type), NotificationStatus.valueOf(status));
    }

    public UUID getFromUser() {
        return fromUser;
    }

    public UUID getToUser() {
        return toUser;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public NotificationStatus getStatus() {
        return status;
    }
}
