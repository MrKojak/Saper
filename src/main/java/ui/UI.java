package ui;

import databaseService.Field;
import databaseService.FieldStatus;
import validate.Validation;

import java.util.List;
import java.util.Scanner;

public class UI {
    private static final int WIDTH_OF_BOARD = 10;
    private static final int HEIGHT_OF_BOARD = 10;
    public static final Scanner playerChoice = new Scanner(System.in);

    public static void showMenuOptions() {
        System.out.println("Choose the option: ");
        System.out.println("1. Start new game");
        System.out.println("2. Save the game");
        System.out.println("3. Load game");
        System.out.println("4. Remove save");
        System.out.println("5. Back to game");
    }

    public static void showTheBoard(Field[][] tableOfFields, boolean withBombs) {
        for (int coordinateX = 0; coordinateX < HEIGHT_OF_BOARD; coordinateX++) {
            for (int coordinateY = 0; coordinateY < WIDTH_OF_BOARD; coordinateY++) {

                if (tableOfFields[coordinateX][coordinateY].getFieldStatus() == FieldStatus.UNCOVER) {
                    System.out.print("[" + tableOfFields[coordinateX][coordinateY].getQuantityBombsAround() + "]");
                } else if (tableOfFields[coordinateX][coordinateY].getFieldStatus() == FieldStatus.FLAGGED) {
                    System.out.print("[F]");
                } else {
                    if (withBombs) {
                        if (tableOfFields[coordinateX][coordinateY].isBombed()) {
                            System.out.print("[X]");
                        } else {
                            System.out.print("[ ]");
                        }
                    } else {
                        System.out.print("[ ]");
                    }
                }
            }
            System.out.println();
        }
    }
    public static void chooseARowCommunicat() {
        System.out.println("Choose a row");
    }
    public static void chooseAColumnCommunicate() {
        System.out.println("Choose a column");
    }

    public static void showWinCommunicate() {
        System.out.println("Congratulation! You win!");
    }

    public static void loseGameCommunicate() {
        System.out.println("There was a bomb, You lose");
    }
    public static void saveExistCommunicate() {
        System.out.println("That name of save already exist");
    }

    public static void askAboutFlagOrUncoverOrUnflag() {
        System.out.println("F - flag field U - uncover field UF - unflag M - menu");
        System.out.println("Choose an option: ");
    }
    public static void wrongParameterCommunicate() {
        System.out.println("Something goes wrong! Try again: ");
    }
    public static void wrongSaveName() {
        System.out.println("That save does not exist. Try again: ");
    }
    public static void wrongGamename() {
        System.out.println("The name is too long");
    }
    public static String getFOrUOrUf() {
        String dataToValidate;
        do {
            dataToValidate = playerChoice.nextLine();
        } while (!Validation.validationGameOption(dataToValidate));
        return dataToValidate;
    }
    public static int getCoordinate() {
            String dataToValidate;
            do {
                dataToValidate = playerChoice.nextLine();
            } while (!Validation.validationSelectingField(dataToValidate));

            return Integer.parseInt(dataToValidate);
    }
    public static int getMenuOption() {
        String dataToValidate;
        do {
            dataToValidate = playerChoice.nextLine();
        } while (!Validation.validationMenuOption(dataToValidate));

        return Integer.parseInt(dataToValidate);

    }
    public static String getGameName() {
        String dataToValidate;
        System.out.println("Give me game name:");
        do {
            dataToValidate = playerChoice.nextLine();
        } while (!Validation.validationGameName(dataToValidate));

        return dataToValidate;

    }

    public static void showGameNameList(List<String> listOfGameNames) {
        for (String listOfGameName : listOfGameNames) {
            System.out.println(listOfGameName);
        }
    }

    public static String selectGameToLoad(List<String> listOfGameNames) {
        String dataToValidate;
        do {
            dataToValidate = playerChoice.nextLine();
        } while (!Validation.validationChoosenSave(dataToValidate,listOfGameNames));
        return dataToValidate;
    }
}