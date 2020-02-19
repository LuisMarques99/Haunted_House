package AppTest;

import App.FileManager;
import Exceptions.FileNotFoundException;
import Exceptions.InvalidFileException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * <h3>
 * <strong>Test class to test the static method searchDivision(String divisao) in the class {@link App.FileManager FileManager}</strong>
 * </h3>
 *
 * @author Francisco Pinto
 */
public class SearchDivisionTest {
    @BeforeAll
    public static void setup() throws FileNotFoundException, InvalidFileException, ParseException, IOException {
        FileManager.readJsonFile("mapa1.json");
    }

    /**
     * Tests if the searchDivision(String divisao) returns not null by giving it a existing division name
     * @throws FileNotFoundException
     * @throws InvalidFileException
     * @throws ParseException
     * @throws IOException
     */
    @Test
    @DisplayName("searchDivision(String divisao) test with the parameter being an existing division name")
    public void searchDivisionWithValidInputTest_TC_111() {
        Assertions.assertNotNull(FileManager.searchDivision("escritorio"));
    }

    /**
     * Tests if the searchDivision(String divisao) returns null by giving it a non existing division name
     * @throws FileNotFoundException
     * @throws InvalidFileException
     * @throws ParseException
     * @throws IOException
     */
    @Test
    @DisplayName("searchDivision(String divisao) test with the parameter being a non existing division name")
    public void searchDivisionWithValidInputTest_TC_112() {
        Assertions.assertNull(FileManager.searchDivision("jardim"));
    }
}