package service;

import databaseService.*;
import lombok.NoArgsConstructor;
import ui.UI;

@NoArgsConstructor
public class MenuService {

    private static final int START_GAME = 1;
    private static final int SAVE_THE_GAME = 2;
    private static final int SAVES = 3;
    private static final int REMOVE_SAVE = 4;
    private static final int BACK_TO_GAME = 5;

    private void choosePlayerMenu(int menuChoice, Field[][] tableOfField) {
        GameService gameService = new GameService();
        switch (menuChoice) {
            case START_GAME -> gameService.startNewGame();
            case SAVE_THE_GAME -> gameService.saveGameToBase(tableOfField);
            case SAVES -> gameService.savesMenu();
            case REMOVE_SAVE -> gameService.removeSave(tableOfField);
            case BACK_TO_GAME -> gameService.makeAChoice(tableOfField);

            default -> GameService.endTheGame();
        }
    }

    public void initializeMenu(Field[][] tableOfField) {
        UI.showMenuOptions();
        choosePlayerMenu(UI.getMenuOption(), tableOfField);
    }


}
