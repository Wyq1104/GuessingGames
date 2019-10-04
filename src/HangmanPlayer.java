public interface HangmanPlayer {
    String getId();

    char nextGuess();

    boolean reset();
}
