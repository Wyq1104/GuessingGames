/**
 * a player that guesses according to alphabetic order
 */
public class StupidPlayer implements HangmanPlayer {
    static int playerNum=1;
    private String Id="StupidPlayer"+playerNum;
    private String alphabets="abcdefghijklmnopqrstuvwxyz";
    private int index;

    /**
     * constructor that increase playerNum
     */
    public StupidPlayer(){
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
     * get player's nextGuess
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
     * reset player to prepare for a new game
     * @return
     */
    @Override
    public boolean reset() {
        index=0;
        return true;
    }


}
