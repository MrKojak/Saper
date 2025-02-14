package service;

import com.sun.xml.bind.v2.TODO;
import databaseService.Field;
import databaseService.FieldStatus;
import lombok.Getter;
import ui.UI;

import java.util.Random;

import static java.lang.System.exit;

class BoardService {
    @Getter
    private static final int WIDTH_OF_BOARD = 10;
    @Getter
    private static final int HEIGHT_OF_BOARD = 10;
    private static final int BOMBS_AMOUNT = 20;
    private int uncoverFieldsAmount = 0;
    private final Random bombPlacement = new Random();
    private static final int BOARD_SIZE = HEIGHT_OF_BOARD * WIDTH_OF_BOARD;
    private boolean areWeStillPlaying = true;

    Field[][] fillBoard() {
        int currentIdToSet = 0;
        Field[][] tableOfFields = new Field[HEIGHT_OF_BOARD][WIDTH_OF_BOARD];
        for (int coordinateX = 0; coordinateX < HEIGHT_OF_BOARD; coordinateX++) {
            for (int coordinateY = 0; coordinateY < WIDTH_OF_BOARD; coordinateY++) {
                tableOfFields[coordinateX][coordinateY] = new Field(currentIdToSet);
                currentIdToSet++;
            }
        }
        addBombsToBoard(tableOfFields);
        addNumbersToBoard(tableOfFields);
        return tableOfFields;
    }

    private void addBombsToBoard(Field[][] tableOfFields) {
        for (int i = 0; i < BOMBS_AMOUNT; i++) {
            int bombCoordinateX = bombPlacement.nextInt(10);
            int bombCoordinateY = bombPlacement.nextInt(10);
            if (!tableOfFields[bombCoordinateX][bombCoordinateY].isBombed()) {
                tableOfFields[bombCoordinateX][bombCoordinateY].setBomb();
            }
        }
    }

    private void addNumbersToBoard(Field[][] tableOfFields) {

        for (int coordinateX = 0; coordinateX < HEIGHT_OF_BOARD; coordinateX++) {
            for (int coordinateY = 0; coordinateY < WIDTH_OF_BOARD; coordinateY++) {

                if (tableOfFields[coordinateX][coordinateY].isBombed()) {
                    checkFieldToPutNumbers(tableOfFields, coordinateX, coordinateY);
                }
            }
        }
    }

    private void checkFieldToPutNumbers(Field[][] tableOfFields, int checkedX, int checkedY) {
        for (int xShift = -1; xShift < 2; xShift++) {
            for (int yShift = -1; yShift < 2; yShift++) {
                int currentX = checkedX + xShift;
                int currentY = checkedY + yShift;

                if (!isOutOfTable(currentX, currentY)) {
                    boolean isBombedField = tableOfFields[currentX][currentY].isBombed();
                    boolean isCenterField = currentX == checkedX && currentY == checkedY;
                    if (!isCenterField || !isBombedField)
                        tableOfFields[currentX][currentY].increaseBombsAround();
                }

            }
        }

    }



    void uncoverChosenField(Field[][] tableOfField, int selectedRow, int selectedColumn) {
        if (tableOfField[selectedRow][selectedColumn].isBombed()) {
           loseTheGame();
        }else {verifyAndUncoverFields(tableOfField, selectedRow, selectedColumn);}
    }
    private void loseTheGame(){
        UI.loseGameCommunicate();
        areWeStillPlaying = false;
        exit(0);
    }

  private void verifyAndUncoverFields(Field[][] fieldsTable, int checkedX, int checkedY) {
        uncoverField(fieldsTable,checkedX,checkedY);
            if (fieldsTable[checkedX][checkedY].getQuantityBombsAround() == 0) {

                for (int xShift = -1; xShift < 2; xShift++) {
                    for (int yShift = -1; yShift < 2; yShift++) {
                        int currentX = checkedX + xShift;
                        int currentY = checkedY + yShift;
                        boolean isCenterField = currentX == checkedX && currentY == checkedY;
                        if (!(isOutOfTable(currentX, currentY) || isCenterField || fieldsTable[currentX][currentY].getFieldStatus()==FieldStatus.UNCOVER)) {
                            verifyAndUncoverFields(fieldsTable, currentX, currentY);
                        }
                    }
                }
            }
        }


    private void uncoverField(Field[][] fieldsTable, int checkedX, int checkedY) {
        boolean isBombHere = fieldsTable[checkedX][checkedY].isBombed();
        boolean isFieldCover = fieldsTable[checkedX][checkedY].getFieldStatus() == FieldStatus.COVER;

        if (!isBombHere && isFieldCover) {
            fieldsTable[checkedX][checkedY].setUncover();
            uncoverFieldsAmount++;
    }
}
    private boolean isOutOfTable(int coordinateX, int coordinateY) {
        return (coordinateX < 0 || coordinateY < 0) || (coordinateX >= WIDTH_OF_BOARD || coordinateY >= HEIGHT_OF_BOARD);
    }

    public boolean areYouWinningTheGame() {
        return uncoverFieldsAmount == BOARD_SIZE - BOMBS_AMOUNT;
    }

    public boolean areWeStillPlaying(){
        return areWeStillPlaying && !areYouWinningTheGame();
    }


}