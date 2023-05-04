package ui;

import service.Field;
import service.FieldStatus;

import java.util.Scanner;

public class UI {
    private static final Integer WIDTH_OF_BOARD = 10;
    private static final int HEIGHT_OF_BOARD = 10;

    private static Scanner playerChoice = new Scanner(System.in);
    public static void showMenuOptions(){
        System.out.println("Choose the option: ");
        System.out.println("1. Start new game");
        System.out.println("2. Save the game");
        System.out.println("3. Load game");
    }

    public static void chooseARowCommunicat(){
        System.out.println("Choose a row");
    }
    public static void showTheBoard(Field[][] tableOfFields) {

        for (int coordinateX = 0; coordinateX < HEIGHT_OF_BOARD; coordinateX++) {
            for (int coordinateY = 0; coordinateY < WIDTH_OF_BOARD; coordinateY++) {

                if (tableOfFields[coordinateX][coordinateY].getFieldStatus()== FieldStatus.UNCOVER) {
                    if(tableOfFields[coordinateX][coordinateY].isBombed()){
                        System.out.print("[X]");
                    } else if (tableOfFields[coordinateX][coordinateY].getFieldStatus() == FieldStatus.FLAGGED) {
                        System.out.print("[F]");
                    } else {
                        System.out.print("["+tableOfFields[coordinateX][coordinateY].getQuantityBombsAround()+"]");
                    }
                }else if(tableOfFields[coordinateX][coordinateY].getFieldStatus()== FieldStatus.COVER) {
                    System.out.print("[ ]");
                }else if (tableOfFields[coordinateX][coordinateY].getFieldStatus() == FieldStatus.FLAGGED) {
                    System.out.print("[F]");
                }


            }
            System.out.println();     }


    }
    public static void chooseAColumnCommunicate(){
        System.out.println("Choose a column");
    }
    public static void showTheBoardWithBombs(Field[][] tableOfFields,boolean withBombs) {

        
        for (int coordinateX = 0; coordinateX < HEIGHT_OF_BOARD; coordinateX++) {
            for (int coordinateY = 0; coordinateY < WIDTH_OF_BOARD; coordinateY++) {
                if(tableOfFields[coordinateX][coordinateY].isBombed()) {
                    System.out.print("[X]");
                }
                    else if (tableOfFields[coordinateX][coordinateY].getFieldStatus() == FieldStatus.UNCOVER) {
                        if (tableOfFields[coordinateX][coordinateY].getFieldStatus() == FieldStatus.FLAGGED) {
                            System.out.print("[F]");
                        } else {
                            System.out.print("[" + tableOfFields[coordinateX][coordinateY].getQuantityBombsAround() + "]");
                        }
                    } else if (tableOfFields[coordinateX][coordinateY].getFieldStatus() == FieldStatus.COVER) {
                        System.out.print("[ ]");
                    } else if (tableOfFields[coordinateX][coordinateY].getFieldStatus() == FieldStatus.FLAGGED) {
                        System.out.print("[F]");
                    }


            }
            System.out.println();     }


    }
    public static void showWinCommunicate() {
        System.out.println("Congratulation! You win!");
    }

    public static void loseGameCommunicate() {
        System.out.println("There was a bomb, You lose");
    }

    public static void askAboutFlagOrUncover() {
        System.out.println("F - flague field U - uncover field");
        System.out.println("Choose an option: ");
    }

    public static String receivePlayerChoose() {
        Scanner playerChoose = new Scanner(System.in);
        return playerChoose.nextLine();
    }

    public static void wrongParameterCommunicate() { System.out.println("You choose wrong parameter, try again");}

    public static int getPlayerChoice() {

        return playerChoice.nextInt();
    }
}