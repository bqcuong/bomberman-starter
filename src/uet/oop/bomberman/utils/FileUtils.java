package uet.oop.bomberman.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> readFileLineByLine(String path) throws IOException {
        File f=new File(path);
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        int c = 0;
        List<String> returnLine = new ArrayList<>();
        String s = "";
        while((c = br.read()) != -1)
        {
            char character = (char) c;
            if (c == '\n') {
                if (!s.equals("")) returnLine.add(s);
                s = "";
            }
            else s += character;
        }
        if (!s.equals("")) returnLine.add(s);
        return returnLine;
    }

//    public static void main(String[] args) throws IOException {
//        List<String> lines = FileUtils.readFileLineByLine("/Users/apple/Desktop/git/bomberman-starter/res/map.txt");
//
//        for (String l : lines) {
//            System.out.println(l);
//        }
//
//    }

}
