package application.domain.notification;

public class NotificationFactory {
    public static NotificationStatus getStatus(String status) {
        return switch (status) {
            case "READ" -> NotificationStatus.READ;
            case "UNREAD" -> NotificationStatus.UNREAD;
            default -> null;
        };
    }
    public static NotificationType getType(String type) {
        return switch (type) {
            case "FRIEND_REQUEST_RECEIVED" -> NotificationType.FRIEND_REQUEST;
            case "FRIEND_REQUEST_ACCEPTED" -> NotificationType.FRIEND_REQUEST_ACCEPTED;
            case "FRIEND_REQUEST_REJECTED" -> NotificationType.FRIEND_REQUEST_REJECTED;
            default -> null;
        };
    }
}
