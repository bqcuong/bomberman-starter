package uet.oop.bomberman.graphics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Map {
    private char[][] map;

    public static int mapLever = 1;

    public static int r = 0;

    public static int c = 0;

    public Map() {

    }

    public char getMap(int i, int j) {
        return map[i][j];
    }


    public void setMap(int i, int j, char cha) {
        map[i][j] = cha;
    }

    public void upMap() {
        String mapName = "Level" + mapLever + ".txt";
        try {
            File file = new File("res/levels/" + mapName);
            FileReader input = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(input);
            String one = bufferedReader.readLine();
            String[] oneOne = one.split(" ");
            r = Integer.parseInt(oneOne[0]);
            c = Integer.parseInt(oneOne[1]);
            map = new char[r][c];
            for (int i = 0; i < r; ++i) {
                String row = bufferedReader.readLine();
                for (int j = 0; j < c; ++j) {
                    map[i][j] = row.charAt(j);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
