package application.utils;

import javafx.scene.image.Image;

import java.time.format.DateTimeFormatter;

/**
 * The class <b>Constants</b> contains different constants used in the application.
 */
public class Constants {
    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static Image APP_ICON = new Image("/media/ui/logo.png");
}
