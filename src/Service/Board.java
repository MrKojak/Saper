package Service;

import java.util.Random;

import static java.lang.System.exit;

public class Board {

    private static final int WIDTH_OF_BOARD = 10;
    private static final int HEIGHT_OF_BOARD = 10;
    Field[][] tableOfFields = new Field[HEIGHT_OF_BOARD][WIDTH_OF_BOARD];

    int id = 0;

    private int BOMBS_AMOUNT = 40;
    Random bombPlacement = new Random();

    Field[][] fillBoard() {
        for (int coordinateX = 0; coordinateX < HEIGHT_OF_BOARD; coordinateX++) {
            for (int coordinateY = 0; coordinateY < WIDTH_OF_BOARD; coordinateY++) {
                tableOfFields[coordinateX][coordinateY] = new Field(id);
                id++;
            }
        }
        addBombsToBoard(tableOfFields);
        addNumbersToBoard(tableOfFields);
        return tableOfFields;


    }

    private void addBombsToBoard(Field[][] board) {
        for (int i = 0; i < BOMBS_AMOUNT; i++) {
            int bombCoordinateX = bombPlacement.nextInt(10);
            int bombCoordinateY = bombPlacement.nextInt(10);
            if (!board[bombCoordinateX][bombCoordinateY].isBombed()) {
                board[bombCoordinateX][bombCoordinateY].setBombToField();
            } else {
                BOMBS_AMOUNT++;
            }
        }
    }

    private void addNumbersToBoard(Field[][] board) {

        for (int coordinateX = 0; coordinateX < HEIGHT_OF_BOARD; coordinateX++) {
            for (int coordinateY = 0; coordinateY < WIDTH_OF_BOARD; coordinateY++) {

                if (board[coordinateX][coordinateY].isBombed()) {
                    checkNearFields(board, coordinateX, coordinateY);
                }
            }
        }
    }

    private void checkNearFields(Field[][] tableOfFields, int coordinateCheckedFieldX, int coordinateCheckedFieldY) {
        for (int coordinateToCheckIsOutOfBandX = -1; coordinateToCheckIsOutOfBandX < 2; coordinateToCheckIsOutOfBandX++) {
            for (int coordinateToCheckIsOutOfBandY = -1; coordinateToCheckIsOutOfBandY < 2; coordinateToCheckIsOutOfBandY++) {
                int currentCoordinateX = coordinateCheckedFieldX + coordinateToCheckIsOutOfBandX;
                int currentCoordinateY = coordinateCheckedFieldY + coordinateToCheckIsOutOfBandY;

                if (!isIndexOutOfBound(currentCoordinateX, currentCoordinateY)) {
                    if (!(currentCoordinateX == coordinateCheckedFieldX && currentCoordinateY == coordinateCheckedFieldY)) {
                        if (!tableOfFields[currentCoordinateX][currentCoordinateY].isBombed()) {
                            tableOfFields[currentCoordinateX][currentCoordinateY].increaseNumberOfBombsAround();//przerobić tak żeby zwracało true/false i użyć w GameService
                        }
                    }
                }

            }
        }

    }


    private boolean isIndexOutOfBound(int coordinateX, int coordinateY) {
        return (coordinateX < 0 || coordinateY < 0) || (coordinateX >= WIDTH_OF_BOARD || coordinateY >= HEIGHT_OF_BOARD);
    }

    public void checkFieldsToUncover(Field[][] tableOfFields, int coordinateCheckedFieldX, int coordinateCheckedFieldY) {


        for (int coordinateToCheckIsOutOfBandX = -1; coordinateToCheckIsOutOfBandX < 2; coordinateToCheckIsOutOfBandX++) {
            for (int coordinateToCheckIsOutOfBandY = -1; coordinateToCheckIsOutOfBandY < 2; coordinateToCheckIsOutOfBandY++) {
                int currentCoordinateX = coordinateCheckedFieldX + coordinateToCheckIsOutOfBandX;
                int currentCoordinateY = coordinateCheckedFieldY + coordinateToCheckIsOutOfBandY;

                if (!isIndexOutOfBound(currentCoordinateX, currentCoordinateY)) {
                    if (!(currentCoordinateX == coordinateCheckedFieldX && currentCoordinateY == coordinateCheckedFieldY)) {
                        if (!tableOfFields[currentCoordinateX][currentCoordinateY].isBombed()) {
                            if (tableOfFields[currentCoordinateX][currentCoordinateY].getFieldStatus() != FieldStatus.FLAGUE) {
                                if (tableOfFields[currentCoordinateX][currentCoordinateY].getFieldStatus() != FieldStatus.UNCOVER) {
                                    tableOfFields[currentCoordinateX][currentCoordinateY].setUncoverStatus();
                                    checkFieldsToUncover(tableOfFields, currentCoordinateX, currentCoordinateY);
                                }
                            }

                        }
                    }
                }

            }
        }

    }

}