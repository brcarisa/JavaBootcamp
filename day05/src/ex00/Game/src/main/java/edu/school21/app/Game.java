package edu.school21.app;
import edu.school21.logic.ChaseLogic;

import java.io.IOException;
import java.util.Scanner;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Game {

    @Parameter(names = "--enemiesCount", required = true)
    private int enemiesCount;

    @Parameter(names = "--profile", required = true)
    private Profile profile;

    @Parameter(names = "--wallsCount", required = true)
    private int wallsCount;

    @Parameter(names = "--size", required = true)
    private int size;

    private GameStatus gameStatus;
    private ChaseLogic chaseLogic;
    private final Scanner scanner;

    public enum Profile{
        PRODUCTION, DEV
    }

    public Profile getProfile(){
        return this.profile;
    }

    public enum GameStatus{
        WIN, FAIL, CONTINUE
    }

    private static final class GameHolder{
        private static final Game instance = new Game();
    }

    private Game(){
        gameStatus = GameStatus.CONTINUE;
        scanner = new Scanner(System.in);
    }

    public static Game getInstance() {
        return GameHolder.instance;
    }

    public void start() throws IllegalParametersException, IOException, InterruptedException {
        if (enemiesCount < 0) {
            throw new IllegalParametersException("invalid value of the 'enemiesCount' parameter (should be greater)");
        } else if (wallsCount < 0) {
            throw new IllegalParametersException("invalid value of the 'wallsCount' parameter (should be greater)");
        } else if (size < 2) {
            throw new IllegalParametersException("invalid value of the 'size' parameter (should be greater)");
        } else if (enemiesCount + wallsCount + 2 > size * size) {
            throw new IllegalParametersException("it's impossible to place objects on the map");
        }
        Map map = Map.generateMap(size, wallsCount, enemiesCount);
        map.loadProperties(profile);
        chaseLogic = new ChaseLogic(map.getIntBlock(), size);
        startGame(map);
    }
    private void startGame(Map map) throws IOException, InterruptedException {
        map.drawMap();
        while (gameStatus.equals(GameStatus.CONTINUE)){
            if(!playerMove(map)){
                continue;
            } else {
                map.drawMap();
                if(map.getPlayerCoordinates() == map.getGoalCoordinates()){
                    System.out.println("K.O.");
                    gameStatus = GameStatus.WIN;
                }

            }
            int[] enemies = map.getEnemiesCoordinates().clone();
            int move;
            for(int i = 0; i < enemies.length && gameStatus.equals(GameStatus.CONTINUE); ++i){
                move = chaseLogic.getMove(enemies[i], map.getPlayerCoordinates());
                if(move != enemies[i]){
                    map.setGameObject(map.getGameObject(enemies[i]), move);
                    map.setGameObject(new GameObject(GameObject.ObjectType.EMPTY), enemies[i]);
                    map.setEnemiesCoordinates(i, move);
                    enemies[i] = move;
                    if(profile.equals(Profile.DEV)){
                        System.out.print("\n\33[2KInput '8'\33[1A\33[2K\r");
                        String string = scanner.nextLine();
                        while (!string.equals("8") && !string.equals("9")) {
                            System.out.print("\33[2KIncorrect command! Input '8' - move enemy, '9' - end game\33[1A\33[2K\r");
                            string = scanner.nextLine();
                        }
                        if (string.equals("9")) {
                            gameStatus = GameStatus.FAIL;
                            System.out.println("\33[2KGame over!");
                        }
                    }
                    map.drawMap();
                    if (move == map.getPlayerCoordinates()) {
                        gameStatus = GameStatus.FAIL;
                        System.out.println("\33[2KGame over!");
                        break;
                    }
                }
            }

        }
    }

    private boolean playerMove(Map map){
        String data = scanner.nextLine();
        if(data.isEmpty()){
            System.out.print("\33[1A");
            return false;
        }
        switch (data){
            case "W", "w":
                if(map.movePlayerUp()){
                    return true;
                }
                System.out.println("\33[2KCan't move up!\33[1A\33[2K\r");
                break;
            case "S", "s":
                if(map.movePlayerDown()){
                    return true;
                }
                System.out.print("\33[2KCan't move down!\33[1A\33[2K\r");
                break;
            case "D", "d":
                if(map.movePlayerRight()){
                    return true;
                }
                System.out.print("\33[2KCan't move right!\33[1A\33[2K\r");
                break;
            case "A", "a":
                if(map.movePlayerLeft()){
                    return true;
                }
                System.out.print("\33[2KCan't move left!\33[1A\33[2K\r");
                break;
            case "9":
                gameStatus = GameStatus.FAIL;
                System.out.println("\33[2KGame over!");
                break;
            default:
                System.out.print("\33[2KInput 'w' - up, 'a' - left, 's' - down, 'd' - right or '9' - end game!\33[1A\33[2K\r");
        }
        return false;
    }
}
























