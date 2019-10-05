import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * a player that guess character randomly
 */
public class RandomPlayer implements HangmanPlayer {
    static  int playerNum=1;
    private String Id="RandomPlayer"+playerNum;
    private String alphabets="abcdefghijklmnopqrstuvwxyz";
    private Set<Integer> guessedIndexes=new HashSet<>();

    /**
     * constructor that increases playerNum
     */
    public RandomPlayer(){
        playerNum++;
    }

    /**
     * get player's Id
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
        Random random=new Random();
        int index;
        do{
            index=random.nextInt(alphabets.length());
        }while (guessedIndexes.contains(index));
        guessedIndexes.add(index);
        char guess=alphabets.charAt(index);
        System.out.println("Guess this time: "+guess);
        return guess;
    }

    /**
     * reset player for a new game
     * @return
     */
    @Override
    public boolean reset() {
        guessedIndexes.clear();
        return true;
    }
}
