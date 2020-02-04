package Haunted_House;

import Entities.JSONFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
    private static JSONFile file = new JSONFile();

    public static void readFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        

        while((line = br.readLine())!=null){
            if(line.contains("nome")){
                System.out.println();
            }
        }
        br.close();
    }
}
