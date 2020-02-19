package AppTest;

import App.FileManager;
import Exceptions.FileNotFoundException;
import Exceptions.InvalidFileException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * <h3>
 * <strong>Test class to test the static method generateShield(ArrayUnorderedList<Room> vertices) in the class {@link App.FileManager FileManager}</strong>
 * </h3>
 *
 * @author Francisco Pinto
 */
public class GenerateShieldTest {
    @Test
    @DisplayName("generateShield(ArrayUnorderedList<Room> vertices) test with the parameter being a valid input")
    public void generateShieldWithValidMapTest_TC_100() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        FileManager.readJsonFile("mapa1.json");
        Assertions.assertTrue(FileManager.generateShield(FileManager.getVertices()));
    }

    @Test
    @DisplayName("generateShield(ArrayUnorderedList<Room> vertices) test with the parameter being a invalid input")
    public void generateShieldWithInvalidMapTest_TC_100() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        FileManager.readJsonFile("mapa2.json");
        Assertions.assertFalse(FileManager.generateShield(FileManager.getVertices()));
    }
}
