package App;

import Exceptions.InvalidFileException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

/**
 * <h3>
 * <strong>Class that runs the project</strong>
 * </h3>
 */
public class DEMO {
    public static void main(String[] args) throws ParseException, InvalidFileException, IOException {
        Menu.userDefinition();
        Menu.loadFile();
        Menu.start();
        Menu.copyright();
    }
}
