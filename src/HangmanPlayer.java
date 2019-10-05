/**
 * define the method(s) needed by all HangmanPlayers
 */
public interface HangmanPlayer {
    /**
     * get player's id
     * @return
     */
    String getId();

    /**
     * player's next guess
     * @return
     */
    char nextGuess();

    /**
     * reset player for a new game
     * @return
     */
    boolean reset();
}
