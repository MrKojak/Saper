package Service;

public class GameMenu{
    GameService gameControler = new GameService();
    public void showMenuOptions(){
        System.out.println("1. Start new game");
        System.out.println("2. Save the game");
        System.out.println("3. Load game");
    }


public void menuControler(int menuOption) {
    switch (menuOption) {
        case 1 -> gameControler.startNewGame();
        case 2 -> gameControler.saveTheGame();
        case 3 -> gameControler.startSavesMenu();
    }
}
}
