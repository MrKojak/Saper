package service;

import DTO.Field;
import ui.UI;

import static java.lang.System.exit;

class GameService {
    private static final int START_GAME = 1;
    private static final int SAVE_THE_GAME = 2;
    private static final int SAVES = 3;
    BoardService boardService = new BoardService();
    private void startNewGame() {
        Field[][] tableOfField = boardService.fillBoard();
        UI.showTheBoard(tableOfField, true);
        do {
            boardService.flagOrUncover(tableOfField, chooseARow(), chooseAColumn());
            UI.showTheBoard(tableOfField,true);


        } while (boardService.areWeStillPlaying());
        if(boardService.areWeStillPlaying()) {
            UI.showWinCommunicate();
            UI.showTheBoard(tableOfField, true);
        }

        endTheGame();
    }

    void initializeMenu() {
        UI.showMenuOptions();
        choosePlayerMenu(UI.getMenuOption());
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
        return UI.getCoordinate();
    }

    private int chooseAColumn() {
        UI.chooseAColumnCommunicate();
        return UI.getCoordinate();
    }

    public static void endTheGame() {
        exit(0);
    }


}
