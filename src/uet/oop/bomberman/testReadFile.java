package uet.oop.bomberman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class testReadFile {
    public static void main(String[] args) throws FileNotFoundException {
        Path a= Paths.get("res/levels/Level"+1+".txt");
        BufferedReader file = new BufferedReader(new FileReader(a.toString()));
        try {
            Scanner sc= new Scanner(file);
            String str= sc.nextLine();
            System.out.println(str);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
