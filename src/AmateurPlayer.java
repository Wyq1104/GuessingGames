/**
 * a player that guesses by the frequency of character
 */
public class AmateurPlayer implements HangmanPlayer {
    private static int playerNum=1;
    private String Id="AmateurPlayer"+playerNum;
    private String alphabets="abcdefghijklmnopqrstuvwxyz";
    private int index;

    /**
     * constructor that increases playerNum
     */
    public AmateurPlayer(){
        playerNum++;
    }

    /**
     * get player's id
     * @return
     */
    @Override
    public String getId() {
        return Id;
    }

    /**
     * get player's next guess
     * @return
     */
    @Override
    public char nextGuess() {
        char guess=alphabets.charAt(index);
        index++;
        System.out.println("Guess this time: "+guess);
        return guess;
    }

    /**
     * reset player for a new game
     * @return
     */
    @Override
    public boolean reset() {
        index=0;
        return true;
    }
}
