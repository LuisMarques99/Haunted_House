package AppTest;

import App.Menu;
import Exceptions.FileNotFoundException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * <h3>
 * <strong>Test class to test the static method searchLeaderBoards(String mapName) in the class {@link App.Menu Menu}</strong>
 * </h3>
 *
 * @author Francisco Pinto
 */
public class SearchLeaderBoardsTest {

    /**
     * Tests if the searchLeaderBoards(String mapName) returns true by giving it a valid map name
     * @throws ParseException
     * @throws IOException
     * @throws FileNotFoundException
     */
    @Test
    @DisplayName("searchLeaderBoards(String mapName) test with the parameter being an existing map name")
    public void searchLeaderBoardsWithValidInputTest_TC_109() throws ParseException, IOException, FileNotFoundException {
        Assertions.assertTrue(Menu.searchLeaderBoards("sexta-feira 13"));
    }

    /**
     * Tests if the searchLeaderBoards(String mapName) returns false by giving it a invalid map name
     * @throws ParseException
     * @throws IOException
     * @throws FileNotFoundException
     */
    @Test
    @DisplayName("searchLeaderBoards(String mapName) test with the parameter being a non existing map name")
    public void searchLeaderBoardsWithValidInputTest_TC_110() throws ParseException, IOException, FileNotFoundException {
        Assertions.assertFalse(Menu.searchLeaderBoards("quarta-feira 15"));
    }
}
