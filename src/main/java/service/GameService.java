package service;

import databaseService.*;
import org.hibernate.SessionFactory;
import ui.UI;
import java.util.List;
import static java.lang.System.exit;


public class GameService {
    private static final String FLAG_FIELD = "F";
    private static final String UNCOVER_FIELD = "U";
    private static final String UNFLAG_FIELD = "UF";
    private static final String MENU = "M";
    private final BoardService boardService = new BoardService();
    private static final MapperEntity mapperEntity = new MapperEntity();
    MenuService menuService = new MenuService();
    private static final SessionFactory sessionFactory = SessionFactoryHolder.getFactory();
    DAO dao = new DAO(sessionFactory);
    MapperDTO mapperDTO = new MapperDTO();

    public void startNewGame() {
        Field[][] tableOfField = boardService.fillBoard();
        gameInProgress(tableOfField);
    }

    public void gameInProgress(Field[][] tableOfField) {

        UI.showTheBoard(tableOfField, false);

        //Główna plętla rozgrywki
        do {
            makeAChoice(tableOfField);
            UI.showTheBoard(tableOfField, false);
        } while (boardService.areWeStillPlaying());
        if (boardService.areWeStillPlaying()) {
            UI.showWinCommunicate();
            UI.showTheBoard(tableOfField, false);
        }

        endTheGame();
    }

    private int selectCoordinateX() {
        UI.chooseARowCommunicat();
        return UI.getCoordinate();
    }

    private int selectCoordinateY() {
        UI.chooseAColumnCommunicate();
        return UI.getCoordinate();
    }

    void makeAChoice(Field[][] tableOfField) {
        UI.askAboutFlagOrUncoverOrUnflag();
        String playerChoice = UI.getFOrUOrUf();

        switch (playerChoice) {
            case FLAG_FIELD -> tableOfField[selectCoordinateX()][selectCoordinateY()].setFlag();
            case UNFLAG_FIELD -> tableOfField[selectCoordinateX()][selectCoordinateY()].unFlag();
            case UNCOVER_FIELD ->
                    boardService.uncoverChosenField(tableOfField, selectCoordinateX(), selectCoordinateY());
            case MENU -> menuService.initializeMenu(tableOfField);

            default -> makeAChoice(tableOfField);
        }
    }

    void saveGameToBase(Field[][] tableOfField) {
        String gameName = UI.getGameName();

        if (!checkNameNotRepeated(gameName)) {
            gameName = UI.getGameName();
        }
        List<FieldEntity> fieldEntityList = mapperEntity.mapDtoToEntityList(tableOfField);
        GameNameEntity gameNameEntity = new GameNameEntity(gameName);
        gameNameEntity.setFieldEnities(fieldEntityList);

        for (FieldEntity entity : fieldEntityList) {
            entity.setGameName(gameNameEntity);
        }

        dao.saveGameToBase(gameNameEntity);
    }

    private boolean checkNameNotRepeated(String gameName) {
        List<String> gameNames = dao.getListOfGameNames();

        for (String gameNameFromBase : gameNames) {
            if (gameNameFromBase.equals(gameName)) {
                UI.saveExistCommunicate();
                return false;
            }
        }
        return true;
    }

    void savesMenu() {
        List<String> listOfGameNames = dao.getListOfGameNames();
        UI.showGameNameList(listOfGameNames);
        String selectedSave = UI.selectGameToLoad(listOfGameNames);

        Field[][] tableOfFields = mapperDTO.mapEntitiesToFieldList(dao.getFieldListByGameName(selectedSave));
        gameInProgress(tableOfFields);
    }
    void removeSave(Field[][] tableOfFields) {
        List<String> listOfGameNames = dao.getListOfGameNames();
        UI.showGameNameList(listOfGameNames);

        String gameName = UI.getGameName();

        dao.removeGameByName(gameName);

        makeAChoice(tableOfFields);
    }
    static void endTheGame() {
        exit(0);
    }



}
