package service;

import databaseService.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ui.UI;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

@NoArgsConstructor
public class GameService {
    private static final int WIDTH_OF_BOARD = 10;
    private static final int HEIGHT_OF_BOARD = 10;
    private static final String FLAG_FIELD = "F";
    private static final String UNCOVER_FIELD = "U";
    private static final String UNFLAG_FIELD = "UF";
    private static final String MENU = "M";
    private final BoardService boardService = new BoardService();
    private static final MapperEntity mapperEntity = new MapperEntity();
    MenuService menuService = new MenuService();

    public void startNewGame() {
        Field[][] tableOfField = boardService.fillBoard();
        gameInProgress(tableOfField,UI.getGameName());
    }

    public void gameInProgress(Field[][] tableOfField,String gameName) {
        GameNameEntity gameNameEntity = new GameNameEntity(gameName);

        UI.showTheBoard(tableOfField, true);

        do {
            List<FieldEntity> entityList;
            entityList = mapperEntity.mapDtoToEntityList(tableOfField);
            for (FieldEntity entity : entityList
            ) {
                entity.setGameName(gameNameEntity);
            }
            gameNameEntity.setFieldEnities(entityList);
            makeAChoice(tableOfField, gameNameEntity);
            UI.showTheBoard(tableOfField, true);
        } while (boardService.areWeStillPlaying());
        if (boardService.areWeStillPlaying()) {
            UI.showWinCommunicate();
            UI.showTheBoard(tableOfField, true);
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

    void makeAChoice(Field[][] tableOfField, GameNameEntity gameNameEntity) {
        UI.askAboutFlagOrUncoverOrUnflag();
        String playerChoice = UI.getFOrUOrUf();

        switch (playerChoice) {
            case FLAG_FIELD -> tableOfField[selectCoordinateX()][selectCoordinateY()].setFlag();
            case UNFLAG_FIELD -> tableOfField[selectCoordinateX()][selectCoordinateY()].unFlag();
            case UNCOVER_FIELD ->
                    boardService.uncoverChosenField(tableOfField, selectCoordinateX(), selectCoordinateY());
            case MENU -> menuService.initializeMenu(gameNameEntity);

            default -> makeAChoice(tableOfField, gameNameEntity);
        }
    }

    public static void endTheGame() {
        exit(0);
    }


}
