package UI;

import Service.Field;
import Service.FieldStatus;

public class UIService {
    private static final Integer WIDTH_OF_BOARD = 10;
    private static final int HEIGHT_OF_BOARD = 10;

    public static void showTheBoardAdmin(Field[][] tableOfFields) {

        for (int coordinateX = 0; coordinateX < HEIGHT_OF_BOARD; coordinateX++) {
            for (int coordinateY = 0; coordinateY < WIDTH_OF_BOARD; coordinateY++) {

                if (tableOfFields[coordinateX][coordinateY].isBombed()) {

                    System.out.print("X");

                }else {
                    System.out.print(tableOfFields[coordinateX][coordinateY].getQuantityBombsAround());
                }

            }
            System.out.println();     }


    }


    public static void showTheBoard(Field[][] tableOfFields) {

        for (int coordinateX = 0; coordinateX < HEIGHT_OF_BOARD; coordinateX++) {
            for (int coordinateY = 0; coordinateY < WIDTH_OF_BOARD; coordinateY++) {

                if (tableOfFields[coordinateX][coordinateY].getFieldStatus()== FieldStatus.UNCOVER) {
                    if(tableOfFields[coordinateX][coordinateY].isBombed()){
                        System.out.print("[X]");
                    } else if (tableOfFields[coordinateX][coordinateY].getFieldStatus() == FieldStatus.FLAGUE) {
                        System.out.print("[F]");
                    } else {
                        System.out.print("["+tableOfFields[coordinateX][coordinateY].getQuantityBombsAround()+"]");
                    }
                }else if(tableOfFields[coordinateX][coordinateY].getFieldStatus()== FieldStatus.COVER) {
                    System.out.print("[ ]");
                }else if (tableOfFields[coordinateX][coordinateY].getFieldStatus() == FieldStatus.FLAGUE) {
                    System.out.print("[F]");
                }


            }
            System.out.println();     }


    }
}