package service;

import databaseService.*;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import ui.UI;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class MenuService {

    private static final int START_GAME = 1;
    private static final int SAVE_THE_GAME = 2;
    private static final int SAVES = 3;
    private static final int BACK_TO_GAME = 4;
    private static final SessionFactory sessionFactory = SessionFactoryHolder.getFactory();
    DAO dao = new DAO(sessionFactory);
    MapperEntity mapperEntity = new MapperEntity();
    MapperDTO mapperDTO = new MapperDTO();

    private void choosePlayerMenu(int menuChoice, GameNameEntity gameNameEntity) {
        GameService gameService = new GameService();
        switch (menuChoice) {
            case START_GAME -> {
                gameService.startNewGame();
            }
            case SAVE_THE_GAME -> {
                   dao.saveGameToBase(gameNameEntity);
            }
            case SAVES -> {
                List<String> listOfGameNames = dao.getListOfGameNames();
                UI.showGameNameList(listOfGameNames);

                String selectedSave = UI.selectGameToLoad(listOfGameNames);
                Field[][] tableOfFields = mapperDTO.mapEntitiesToFieldList(dao.getFieldListByGameName(selectedSave));
                gameService.gameInProgress(tableOfFields,selectedSave);
            }
            //case BACK_TO_GAME -> gameService.backToGame();
            default -> GameService.endTheGame();
        }
    }

    public void initializeMenu(GameNameEntity gameName) {
        UI.showMenuOptions();
        choosePlayerMenu(UI.getMenuOption(), gameName);
    }


}
