package edu.school21.app;


import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;


public class Map {
    private ColoredPrinter coloredPrinter;
    private GameObject[] map;
    private int size;
    private int[] enemiesCoordinates;
    private int playerCoordinates;
    private int goalCoordinates;
    ProcessBuilder processBuilder;

    public GameObject getGameObject(int coord){
        return map[coord];
    }

    public void setGameObject(GameObject gameObject, int coord){
        map[coord] = gameObject;
    }

    public int[] getEnemiesCoordinates(){
        return enemiesCoordinates;
    }

    public void setEnemiesCoordinates(int i, int coord){
        this.enemiesCoordinates[i] = coord;
    }

    public int getPlayerCoordinates(){
        return playerCoordinates;
    }

    public int getGoalCoordinates(){
        return goalCoordinates;
    }

    private Map(){
        coloredPrinter = new ColoredPrinter.Builder(1, false).build();
        processBuilder = new ProcessBuilder("clear");
    }

    public static Map generateMap(int size, int countWalls, int countEnemies){
        Map playingMap = new Map();
        playingMap.size = size;
        playingMap.map = new GameObject[size * size];
        playingMap.enemiesCoordinates = new int[countEnemies];
        do{
            Arrays.fill(playingMap.map, new GameObject(GameObject.ObjectType.EMPTY));
            setCoordinates(playingMap, countEnemies, GameObject.ObjectType.ENEMY);
            setCoordinates(playingMap, countWalls, GameObject.ObjectType.WALL);
            setCoordinates(playingMap, 1, GameObject.ObjectType.PLAYER);
            setCoordinates(playingMap, 1, GameObject.ObjectType.GOAL);
        }while (!playingMap.goalValidation());
        return playingMap;
    }

    private static void setCoordinates(Map map, int count, GameObject.ObjectType type){
        int x;
        for (int i = 0; i < count; ) {
            x = (int) (map.size * map.size * Math.random());
            if (map.map[x].type().equals(GameObject.ObjectType.EMPTY)) {
                map.map[x] = new GameObject(type);
                if (type.equals(GameObject.ObjectType.ENEMY)) {
                    map.enemiesCoordinates[i] = x;
                } else if (type.equals(GameObject.ObjectType.PLAYER)) {
                    map.playerCoordinates = x;
                } else if (type.equals(GameObject.ObjectType.GOAL)) {
                    map.goalCoordinates = x;
                }
                ++i;
            }
        }
    }

    private boolean goalValidation(){
        int[] arr = new int[size * size];
        return recursionCheck(arr, playerCoordinates);
    }

    private boolean recursionCheck(int[] array, int index){
        if (map[index].type().equals(GameObject.ObjectType.GOAL)) {
            return true;
        } else {
            array[index] = 1;
        }
        if ((index + 1) % size != 0 && array[index + 1] == 0 && !map[index + 1].type().equals(GameObject.ObjectType.WALL) && recursionCheck(array, index + 1)) {
            return true;
        }
        if (index % size != 0 && array[index - 1] == 0 && !map[index - 1].type().equals(GameObject.ObjectType.WALL) && recursionCheck(array, index - 1)) {
            return true;
        }
        if (index >= size && array[index - size] == 0 && !map[index - size].type().equals(GameObject.ObjectType.WALL) && recursionCheck(array, index - size)) {
            return true;
        }
        return index < size * (size - 1) && array[index + size] == 0 && !map[index + size].type().equals(GameObject.ObjectType.WALL) && recursionCheck(array, index + size);
    }

    public int[] getIntBlock(){
        int[] arr = new int[size * size];
        for (int i = 0; i < arr.length; ++i) {
            if(map[i].type().equals(GameObject.ObjectType.WALL) || map[i].type().equals(GameObject.ObjectType.GOAL)){
                arr[i] = -2;
            } else if(map[i].type().equals(GameObject.ObjectType.ENEMY)){
                arr[i] = -1;
            }
        }
        return arr;
    }

    public void drawMap() throws IOException, InterruptedException {
        Ansi.BColor color;
        int length = size * size;
        if (Game.getInstance().getProfile().equals(Game.Profile.PRODUCTION)) {
            Process startProcess = processBuilder.inheritIO().start();
            startProcess.waitFor();
        }
        System.out.println();
        for (int i = 0; i < length; ++i) {
            color = Ansi.BColor.valueOf(map[i].type().getColor().toString());
            coloredPrinter.print(map[i].type().getSymbol(), Ansi.Attribute.NONE, Ansi.FColor.BLACK, color);
            if ((i + 1) % size == 0) {
                System.out.println();
            }
        }
    }

    public void loadProperties(Game.Profile profile){
        String error = null;
        char[] ch = new char[5];
        GameObject.MapColor[] color = new GameObject.MapColor[5];
        int i = 0;
        try (InputStream input = Main.class.getResourceAsStream("/application-" + profile.toString().toLowerCase() + ".properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String string;
            for (GameObject.ObjectType type : GameObject.ObjectType.values()) {
                string = type.name().toLowerCase();
                ch[i] = properties.getProperty(string + ".char").charAt(0);
                color[i] = GameObject.MapColor.fromStringToEnum(properties.getProperty(string + ".color"));
                ++i;
            }
        } catch (Exception e) {
            error = "Error loading settings!";
            System.err.println(error);
        }
        if (error == null) {
            i = 0;
            for (GameObject.ObjectType type : GameObject.ObjectType.values()) {
                type.setSymbol(ch[i]);
                type.setColor(color[i]);
                ++i;
            }
        }
    }

    public boolean movePlayerUp(){
        int i = playerCoordinates;
        if(i >= size &&
                !map[i - size].type().equals(GameObject.ObjectType.ENEMY) &&
                !map[i - size].type().equals(GameObject.ObjectType.WALL))
        {
            map[i - size] = map[i];
            map[i] = new GameObject(GameObject.ObjectType.EMPTY);
            playerCoordinates = i - size;
            return true;
        }
        return false;
    }

    public boolean movePlayerDown(){
        int i = playerCoordinates;
        if(i < size * (size - 1) &&
                !map[i + size].type().equals(GameObject.ObjectType.ENEMY) &&
                !map[i + size].type().equals(GameObject.ObjectType.WALL))
        {
            map[i + size] = map[i];
            map[i] = new GameObject(GameObject.ObjectType.EMPTY);
            playerCoordinates = i + size;
            return true;
        }
        return false;
    }

    public boolean movePlayerRight(){
        int i = playerCoordinates;
        if((i + 1) % size != 0 &&
                !map[i + 1].type().equals(GameObject.ObjectType.ENEMY) &&
                !map[i + 1].type().equals(GameObject.ObjectType.WALL))
        {
            map[i + 1] = map[i];
            map[i] = new GameObject(GameObject.ObjectType.EMPTY);
            playerCoordinates = i + 1;
            return true;
        }
        return false;
    }

    public boolean movePlayerLeft(){
        int i = playerCoordinates;
        if(i % size != 0 &&
                !map[i - 1].type().equals(GameObject.ObjectType.ENEMY) &&
                !map[i - 1].type().equals(GameObject.ObjectType.WALL))
        {
            map[i - 1] = map[i];
            map[i] = new GameObject(GameObject.ObjectType.EMPTY);
            playerCoordinates = i - 1;
            return true;
        }
        return false;
    }

}
