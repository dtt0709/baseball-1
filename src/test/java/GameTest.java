import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void createGame(){
        assertNotNull(game);
    }

    @Test
    public void throwIllegalArgumentExceptionInvalidInput(){
        assertIllegalArgument(null);
        assertIllegalArgument("12");
        assertIllegalArgument("1234");
        assertIllegalArgument("12s");
        assertIllegalArgument("121");
    }

    private void assertIllegalArgument(String guessNumber) {
        try{
            game.guess(guessNumber);
            fail();
        }
        catch (IllegalArgumentException e){

        }
    }

    @Test
    void returnSolvedResultIfMatchedNumber(){
        generateQuestion("123");
        assertMAtchedNumber(game.guess("123"), true, 3, 0);
    }

    private void generateQuestion(String questionNumber) {
        game.question=questionNumber;
    }

    @Test
    void returnSolvedResultIfUnMatchedNumber(){
        generateQuestion("123");
        assertMAtchedNumber(game.guess("456"), false, 0, 0);
    }

    private static void assertMAtchedNumber(GuessResult result, boolean solved, int strikes, int balls) {
        assertThat(result).isNotNull();
        assertThat(result.isSolved()).isEqualTo(solved);
        assertThat(result.getStrikes()).isEqualTo(strikes);
        assertThat(result.getBalls()).isEqualTo(balls);
    }
}
