import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *Provide methods used by a concrete hangman game
 */
public abstract class Hangman extends GuessingGame {
    String phrase;
    List<String> phraseList;
    List<String > changeablePhraseList;
    StringBuilder hiddenPhrase;
    public static int TOTALCHANCES=15;
    String previousGuesses="";

    @Override
    public abstract GameInstance play();

    @Override
    public abstract boolean playNext();

    /**
     * get a list of phrases from the file
     * @param filename
     */
    public void readPhrases(String filename){
        try{
            phraseList= Files.readAllLines(Paths.get(filename));
            changeablePhraseList=new ArrayList<>(phraseList);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * get a random phrase from phraseList
     */
    public void randomPhrase(){
        Random random=new Random();
        int i1=random.nextInt(changeablePhraseList.size());
        this.phrase=changeablePhraseList.get(i1);
        changeablePhraseList.remove(phrase);
    }

    /**
     * get the hidden phrase from phrase
     */
    public void getHiddenPhrase(){
        hiddenPhrase=new StringBuilder();
        for(int j=0;j<this.phrase.length();j++){
            if(Character.isLetter(this.phrase.charAt(j))==true){
                this.hiddenPhrase.append('*');
            }else{
                this.hiddenPhrase.append(this.phrase.charAt(j));
            }
        }
    }

    /**
     * Modifies the partially hidden phrase, and modifies the hidden phrase if there is a match
     * @return whether a letter matches
     */
    public boolean processGuess(){
        String lowPhrase=makeLow();
        char guess=getGuess(previousGuesses);
        previousGuesses+=guess;
        char lowGuess=Character.toLowerCase(guess);
        if(lowPhrase.indexOf(lowGuess)!=-1){
            System.out.println("Yes, you are right.");
            for(int i=0;i<lowPhrase.length();i++){
                if(lowGuess==lowPhrase.charAt(i)) {
                    this.hiddenPhrase.setCharAt(i, this.phrase.charAt(i));
                }
            }
            return true;
        }else{
            System.out.println("Sorry, you missed");
            return false;
        }
    }

    /**
     *
     * @return the phrase with every character in lower case
     */
    private String makeLow(){
        String lowPhrase="";
        for(int i=0;i<this.phrase.length();i++){
            lowPhrase+=Character.toLowerCase(phrase.charAt(i));
        }
        return lowPhrase;
    }
    /**
     *
     * @param previousGuesses
     * @return
     */
    abstract char getGuess(String previousGuesses);
}
