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

    /**
     * Tests if the generateField() method returns true by giving it a valid map that assures that there are more than one room
     * without a ghost exists
     * @throws FileNotFoundException
     * @throws InvalidFileException
     * @throws ParseException
     * @throws IOException
     */
    @Test
    @DisplayName("generateShield(ArrayUnorderedList<Room> vertices) test with the parameter being a valid map")
    public void generateShieldWithValidMapTest_TC_100() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        FileManager.readJsonFile("mapa1.json");
        Assertions.assertTrue(FileManager.generateShield(FileManager.getVertices()));
    }

    /**
     * Tests if the generateField() method returns false by giving it a invalid map that has no room without a ghost in it
     * @throws FileNotFoundException
     * @throws InvalidFileException
     * @throws ParseException
     * @throws IOException
     */
    @Test
    @DisplayName("generateShield(ArrayUnorderedList<Room> vertices) test with the parameter being a invalid map")
    public void generateShieldWithInvalidMapTest_TC_101() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        FileManager.readJsonFile("mapa2.json");
        Assertions.assertFalse(FileManager.generateShield(FileManager.getVertices()));
    }

    /**
     * Tests if the generateField() method returns true by giving it a valid map that assures only one room without a ghost in it
     * @throws FileNotFoundException
     * @throws InvalidFileException
     * @throws ParseException
     * @throws IOException
     */
    @Test
    @DisplayName("generateShield(ArrayUnorderedList<Room> vertices) test with the parameter being a valid map")
    public void generateShieldWithValidMapTest_TC_102() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        FileManager.readJsonFile("mapa3.json");
        Assertions.assertTrue(FileManager.generateShield(FileManager.getVertices()));
    }

    /**
     * Tests if the generateField() method returns true by giving it a valid map that assures that all rooms don´t have ghosts in them
     * @throws FileNotFoundException
     * @throws InvalidFileException
     * @throws ParseException
     * @throws IOException
     */
    @Test
    @DisplayName("generateShield(ArrayUnorderedList<Room> vertices) test with the parameter being a valid map")
    public void generateShieldWithValidMapTest_TC_103() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        FileManager.readJsonFile("mapa4.json");
        Assertions.assertTrue(FileManager.generateShield(FileManager.getVertices()));
    }
}