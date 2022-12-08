package application.controller.list;

public class ElementTypeFactory {
    public static String getFXML(Class listElementController) {
        if (listElementController.equals(UserSummaryController.class)) {
            return "user-summary.fxml";
        }
        return null;
    }
}
