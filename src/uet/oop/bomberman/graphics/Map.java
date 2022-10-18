package uet.oop.bomberman.graphics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/** Bản đồ của game.*/
public class Map {
    /** Mảng map dùng để lưu tile map.*/
    private char[][] map;

    /** Biến mapLever dùng để lưu số màn đang chơi.*/
    public static int mapLever = 1;

    /** Biến r dùng để lưu số hàng của tile map.*/
    public static int r = 0;

    /** Biến c dùng để lưu số cột của tile map.*/
    public static int c = 0;

    /** Khởi tạo Map.*/
    public Map() {
        upMap();
    }

    /** Trả về kí tự của tile map.*/
    public char getMap(int i, int j) {
        return map[i][j];
    }

    /** Thay đổi kí tự của tile map.*/

    public void setMap(int i, int j, char cha) {
        map[i][j] = cha;
    }

    /** Đọc dữ liệu từ file vào map.*/
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
