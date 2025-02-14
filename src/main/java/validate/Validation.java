package validate;
import ui.UI;

import java.util.List;

public class Validation {
    private static final int MAX_GAMENAME_LENGHT = 10;
    public static boolean validationGameOption(String dataToValidate) {
        if (!(dataToValidate.equals("F") || dataToValidate.equals("U")||dataToValidate.equals("UF")||dataToValidate.equals("M"))) {
            UI.wrongParameterCommunicate();
            return false;
        }
        return true;
    } public static boolean validationGameName(String dataToValidate) {
        if (dataToValidate.length()>MAX_GAMENAME_LENGHT) {
            UI.wrongGamename();
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
    public static boolean validationChoosenSave(String dataToValidate, List<String> listOfGameNames) {
        for (String listOfGameName : listOfGameNames) {
            if(listOfGameName.equals(dataToValidate))
                return true;
        }
        UI.wrongSaveName();
        return false;
    }
}
