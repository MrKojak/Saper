package Service;

import UI.UIService;

import java.util.Scanner;

import static java.lang.System.exit;

public class GameService {

    private static final int WIDTH_OF_BOARD = 10;
    private static final int HEIGHT_OF_BOARD = 10;
    Board board = new Board();
    Scanner receivedData = new Scanner(System.in);
    Field[][] tableOfField;
    public static void main(String[] args) {


        GameMenu gameMenu = new GameMenu();
        int menuOption;
        Scanner playerChoice = new Scanner(System.in);



        gameMenu.showMenuOptions();
        System.out.println("Choose the option: ");
        menuOption = playerChoice.nextInt();
        gameMenu.menuControler(menuOption);

    }

    public void startNewGame(){
        tableOfField = board.fillBoard();
        UIService.showTheBoardAdmin(tableOfField);
        while(true) {
            flagueOrUncover(tableOfField,chooseARow(),chooseAColumn());

            UIService.showTheBoard(tableOfField);
        }


    }

    private void flagueOrUncover(Field[][] tableOfField,int selectedRow, int selectedColumn) {

        String playersChooice;
        System.out.println("F - flague field U - uncover field");
        System.out.println("Choose an option: ");
        playersChooice = receivedData.next();


        if(playersChooice.equals("F")){
            tableOfField[selectedRow][selectedColumn].setFlagueStatus();

        }else if (playersChooice.equals("U")) {
            System.out.println("kopytko");
            uncoverChoosenField(tableOfField, selectedRow, selectedColumn);
        } else {
            System.out.println("Wrong choice, try again");
            flagueOrUncover(tableOfField,selectedRow,selectedColumn);
        }
    }

    private void uncoverChoosenField(Field[][] tableOfField,int selectedRow,int selectedColumn) {
       if (tableOfField[selectedRow][selectedColumn].isBombed()) {
            endTheGame();
        }
        board.verifyAndUncoverFields(tableOfField,selectedRow,selectedColumn);
    }

    public void saveTheGame() {

    }
    public void startSavesMenu() {

    }

    private int chooseARow(){



        System.out.println("Choose a row");


        return receivedData.nextInt();
        
    }

//    private boolean isCorrect(String playersChoice) {
//
//
//    }

    private int chooseAColumn(){
        
        System.out.println("Choose a column");
        return receivedData.nextInt();
    }
    public void endTheGame() {
        System.out.println("BOMB WAS HERE!");
        exit(0);
    }


}
