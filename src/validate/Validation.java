package validate;

import ui.UI;

public class Validation {
    public static boolean validationGameOption(String dataToValidate) {
        if (!(dataToValidate.equals("F") || dataToValidate.equals("U")||dataToValidate.equals("UF"))) {
            UI.wrongParameterCommunicate();
            return false;
        }
        return true;
    }
    public static boolean validationSelectingField(String dataToValidate) {
        if (dataToValidate.matches("\\d+")) {
            int daToValidateInt = Integer.parseInt(dataToValidate);
            return daToValidateInt > -1 && daToValidateInt < 10;
        }
        UI.wrongParameterCommunicate();
        return false;
    }

    public static boolean validationMenuOption(String dataToValidate) {
        if (dataToValidate.matches("\\d+")) {
            int dataToValidateInt = Integer.parseInt(dataToValidate);
            return dataToValidateInt > 0 && dataToValidateInt < 4;
        }
        UI.wrongParameterCommunicate();
        return false;
    }
}
