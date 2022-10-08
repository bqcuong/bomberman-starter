package uet.oop.bomberman.event;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.*;


public class Upgrade {

    public static boolean Endgame(String state, int score) {
        Dialog<Boolean> dialog = new Dialog<>();
        if(state.equals("win")) {
            dialog.setHeaderText("VICTORY");
        } else dialog.setHeaderText("LOSE");
        ButtonType checkContinueButton = new ButtonType("OK!", ButtonBar.ButtonData.YES);
        dialog.getDialogPane().getButtonTypes().addAll(checkContinueButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        if(state.equals("win")) {
            grid.add(new Label("Your score is: " + score),0, 0);
        }

        int highscore = 0 ;

        try {
            highscore = highScore();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(state.equals("win")) {
            if(score < highscore) {
                highscore = score;
                FileWriter fw = null;
                BufferedWriter bw = null;
                try {
                    fw = new FileWriter("highscore.txt");
                    bw = new BufferedWriter(fw);
                    bw.write(Integer.toString(highscore));
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        grid.add(new Label("High Score: " + highscore), 0, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(button -> true);
        dialog.showAndWait();
        return dialog.getResult();
    }

    public static int highScore() throws IOException {
        FileReader fr = new FileReader("highscore.txt");
        BufferedReader br = new BufferedReader(fr);
        int temp = Integer.parseInt(br.readLine());
        fr.close();
        br.close();
        return temp;
    }

    public static void guide(AnimationTimer timer) {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setHeaderText("Guide");
        ButtonType checkContinueButton = new ButtonType("OK!", ButtonBar.ButtonData.YES);
        dialog.getDialogPane().getButtonTypes().addAll(checkContinueButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        grid.add(new Label("Di chuyển nhân vật: các phím mũi tên.\n" +
                "Đặt bomb: SPACE.\n" +
                "Item: sau khi phá hủy tường sẽ xuất hiện ngẫu nhiên item. Sử dụng Item để được nâng cấp.\n" +
                "Nếu nhân vật chạm phải địch hoặc bị bom nổ sẽ chết.\n" +
                "Diệt hết kẻ địch để qua màn.\n"),0, 0);dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(button -> {
            timer.start();
            return 1;
        });
        dialog.showAndWait();
    }

