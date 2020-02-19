package AppTest;

import App.FileManager;
import Entities.JSONFile;
import Exceptions.FileNotFoundException;
import Exceptions.InvalidFileException;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

/**
 * <h3>
 * <strong>Test class to test the static method readJsonFile(String filePath) in the class {@link App.FileManager FileManager}</strong>
 * </h3>
 *
 * @author Francisco Pinto
 */
public class ReadJsonFileTest {

    /**
     * Tests if the readJsonFile(String filePath) returns not null by giving it a valid map
     * @throws FileNotFoundException
     * @throws InvalidFileException
     * @throws ParseException
     * @throws IOException
     */
    @Test
    @DisplayName("readJsonFile(String filePath) test with the parameter being a valid map")
    public void readJsonFileWithValidMapTest_TC_104() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        JSONFile testFile = FileManager.readJsonFile("mapa1.json");
        Assertions.assertNotNull(testFile.getMap());
    }

    /**
     * Tests if the readJsonFile(String filePath) returns null by giving it a map that cannot be won by the life points
     * compared to the minimum ghost points
     * @throws FileNotFoundException
     * @throws InvalidFileException
     * @throws ParseException
     * @throws IOException
     */
    @Test
    @DisplayName("readJsonFile(String filePath) test with the parameter being a valid map")
    public void readJsonFileWithInvalidMapTest_TC_105() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        JSONFile testFile = FileManager.readJsonFile("mapa5.json");
        Assertions.assertNull(testFile.getMap());
    }

    /**
     * Tests if the readJsonFile(String filePath) returns null by giving it a map that cannot be loaded by the inexistance of
     * an entry division
     * @throws FileNotFoundException
     * @throws InvalidFileException
     * @throws ParseException
     * @throws IOException
     */
    @Test
    @DisplayName("readJsonFile(String filePath) test with the parameter being a valid map")
    public void readJsonFileWithInvalidMapTest_TC_106() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        JSONFile testFile = FileManager.readJsonFile("mapa6.json");
        Assertions.assertNull(testFile.getMap());
    }

    /**
     * Tests if the readJsonFile(String filePath) returns null by giving it a map that cannot be loaded by the inexistance of
     * an exit division
     * @throws FileNotFoundException
     * @throws InvalidFileException
     * @throws ParseException
     * @throws IOException
     */
    @Test
    @DisplayName("readJsonFile(String filePath) test with the parameter being a valid map")
    public void readJsonFileWithInvalidMapTest_TC_107() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        JSONFile testFile = FileManager.readJsonFile("mapa7.json");
        Assertions.assertNull(testFile.getMap());
    }
}
