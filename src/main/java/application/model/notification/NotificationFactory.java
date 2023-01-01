package application.model.notification;

public class NotificationFactory {
    public static short getStatusID(NotificationStatus notificationStatus) {
        return switch (notificationStatus) {
            case UNREAD -> 0;
            case READ -> 1;
        };
    }
    public static NotificationStatus getStatus(short ID) {
        return switch (ID) {
            case 0 -> NotificationStatus.UNREAD;
            case 1 -> NotificationStatus.READ;
            default -> null;
        };
    }
    public static short getTypeID(NotificationType notificationType) {
        return switch (notificationType) {
            case INFORMATIVE -> 0;
            case FRIEND_REQUEST -> 1;
            case FRIEND_REQUEST_ACCEPTED -> 2;
            case FRIEND_REQUEST_REJECTED -> 3;
            case FRIEND_REMOVED -> 4;
        };
    }
    public static NotificationType getType(short ID) {
        return switch (ID) {
            case 0 -> NotificationType.INFORMATIVE;
            case 1 -> NotificationType.FRIEND_REQUEST;
            case 2 -> NotificationType.FRIEND_REQUEST_ACCEPTED;
            case 3 -> NotificationType.FRIEND_REQUEST_REJECTED;
            case 4 -> NotificationType.FRIEND_REMOVED;
            default -> null;
        };
    }
}
