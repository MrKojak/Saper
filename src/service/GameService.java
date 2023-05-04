package service;

import ui.UI;

import java.util.Scanner;

import static java.lang.System.exit;

public class GameService {
    private static final int START_GAME = 1;
    private static final int SAVE_THE_GAME = 2;
    private static final int SAVES = 3;
    BoardService boardService = new BoardService();
    Scanner receivedData = new Scanner(System.in);


    private void startNewGame() {
        Field[][] tableOfField = boardService.fillBoard();
        UI.showTheBoardWithBombs(tableOfField, true);
        do {
            boardService.flagOrUncover(tableOfField, chooseARow(), chooseAColumn());
            UI.showTheBoard(tableOfField);

        } while (boardService.areWeStillPlaying());
        UI.showWinCommunicate();

        UI.showTheBoardWithBombs(tableOfField, true);
        endTheGame();


    }

    void initializeMenu() {
        UI.showMenuOptions();
        choosePlayerMenu(UI.getPlayerChoice());
    }

    private void choosePlayerMenu(int menuChoice) {
        switch (menuChoice) {
            case START_GAME -> startNewGame();
            case SAVE_THE_GAME -> saveTheGame();
            case SAVES -> startSavesMenu();
            default -> endTheGame();
        }
    }


    public void saveTheGame() {
    }

    public void startSavesMenu() {
    }

    private int chooseARow() {
        UI.chooseARowCommunicat();
        int choosenRow = receivedData.nextInt(); // Walidacja
        return choosenRow;
    }

    private int chooseAColumn() {

        UI.chooseAColumnCommunicate();

        return receivedData.nextInt(); //Walidacja
    }

    public static void endTheGame() {
        exit(0);
    }


}
