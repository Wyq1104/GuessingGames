import java.util.*;

/**
 * Provide code essential to the MasterMind game
 */
public class MasterMind extends GuessingGame{
    public static final int CODESIZE=4;
    public static final char[] colorChars=new char[]{'R','G','B','Y','O','P'};
    public static final char[] lowerColorChars=new char[]{'r','g','b','y','o','p'};
    private Set<Character> characters;

    /**
     * constructor that store characters to Set characters
     */
    MasterMind() {
        characters = new HashSet<>();
        for (Character character : colorChars) {
            characters.add(character);
            characters.add(Character.toLowerCase(character));
        }
    }

    /**
     * generate a secret randomly
     */
    private void generateSecret(){
        String str="";
        Random random=new Random();
        for(int i=0;i<CODESIZE;i++){
            int randomIndex=random.nextInt(colorChars.length);
            char currentChar=colorChars[randomIndex];
            str+=currentChar;
        }
        secret=str;
    }

    /**
     * play a game
     * @return
     */
    @Override
    public GameInstance play() {
        reset();
        GameInstance gameInstance=getUsersId();
        System.out.println("Cheating: "+secret);
        int chancesLeft=TOTALCHANCES;
        System.out.println("Valid Characters representing colors: "+Arrays.toString(colorChars));
        while(chancesLeft>0){
            System.out.println("Chances Left: "+ chancesLeft);
            boolean bool=processGuess();
            if(bool){
                System.out.println("Congratualtions,you win.");
                gameInstance.setScore(chancesLeft);
                generateSecret();
                return gameInstance;
            }
            chancesLeft--;
        }
        System.out.println("Sorry you have no more chances.");
        gameInstance.setScore(0);
        return gameInstance;
    }

    /**
     * find how many characters are correct exactly
     * @param secretSB
     * @param guessSB
     * @return
     */
    public int checkExacts(StringBuilder secretSB, StringBuilder guessSB){
        int exacts=0;
        for(int i=0;i<secretSB.length();i++){
            if(secretSB.charAt(i)==guessSB.charAt(i)){
                secretSB.setCharAt(i,'-');
                guessSB.setCharAt(i,'*');
                exacts++;
            }
        }
        return exacts;
    }

    /**
     * find how many characters are correct partially
     * @param secretSB
     * @param guessSB
     * @return
     */
    public int checkPartials(StringBuilder secretSB, StringBuilder guessSB) {
        // compare secret to guess
        int i=0;

        int partials=0;
        while (i<CODESIZE) {
            int j=0;
            while (j<CODESIZE) {
                if (secretSB.charAt(i) == guessSB.charAt(j)) {
                    partials = partials + 1;
                    secretSB.setCharAt(i,'-');
                    guessSB.setCharAt(j,'*');
                }
                j++;
            }
            i++;
        }
        return partials;
    }

    /**
     * check and get user's input
     * @return
     */
    public String getGuess(){
        String guess=getReasonableInput(CODESIZE);
        for(int i=0;i<guess.length();i++){
            if(!isColor(guess.charAt(i))) {
                System.out.println("You input a character that does not represent color");
                return getGuess();
            }
        }
        return guess;
    }

    /**
     * check if a character represents color
     * @param c
     * @return
     */
    private boolean isColor(char c){
        return characters.contains(c) ;
    }

    /**
     * check if the answer is impeccable
     * @return
     */
    @Override
    public boolean processGuess(){
        String guess=getGuess();
        StringBuilder secretSB= new StringBuilder(secret);
        StringBuilder guessSB=new StringBuilder(guess);
        int exacts = checkExacts(guessSB,secretSB);
        int partials = checkPartials(guessSB,secretSB);
        String result=guess+": "+exacts+" exact, "+partials+" partial\n";
        System.out.println(result);
        previousGuesses+=result;
        if(exacts==CODESIZE){
            return true;
        }
        return false;
    }

    /**
     * reset to play again
     */
    private void reset(){
        generateSecret();
        previousGuesses="";
    }

    /**
     * run the game
     * @param args
     */
    public static void main(String[] args) {
        MasterMind masterMind=new MasterMind();
        GamesRecord gamesRecord=masterMind.playAll();
        System.out.println(gamesRecord);
        System.out.println("High Game List:\n"+gamesRecord.highGameList(5));
        System.out.println("Average point: "+gamesRecord.average());
    }
}
