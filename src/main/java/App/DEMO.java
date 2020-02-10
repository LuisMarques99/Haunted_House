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
    public static void main(String[] args) {
        //Menu.userDefinition();
        try {
            //Menu.loadFile();
            Menu.start();
            //Menu.copyright();
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        } catch (InvalidFileException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
