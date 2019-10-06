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
    protected List<String> phraseList;
    protected List<String > changeablePhraseList;
    protected StringBuilder hiddenPhrase;

    /**
     * change the changeablephraselist which removes guessed phrases
     * @param phraseList
     */
    public void setChangeablePhraseList(List<String> phraseList) {
        this.changeablePhraseList = new ArrayList<>(phraseList);
    }

    /**
     * get a list of phrases from the file
     * @param filename
     */
    public void readPhrases(String filename){
        try{
            phraseList= Files.readAllLines(Paths.get(filename));
            setChangeablePhraseList(phraseList);
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
        this.secret =changeablePhraseList.get(i1);
        changeablePhraseList.remove(secret);
    }

    /**
     * get the hidden phrase from phrase
     */
    public void getHiddenPhrase(){
        hiddenPhrase=new StringBuilder();
        for(int j = 0; j<this.secret.length(); j++){
            if(Character.isLetter(this.secret.charAt(j))==true){
                this.hiddenPhrase.append('*');
            }else{
                this.hiddenPhrase.append(this.secret.charAt(j));
            }
        }
    }

    /**
     * Modifies the partially hidden phrase, and modifies the hidden phrase if there is a match
     * @return whether a letter matches
     */
    @Override
    public boolean processGuess(){
        String lowPhrase=makeLow();
        char guess=getGuess(previousGuesses);
        previousGuesses+=guess;
        char lowGuess=Character.toLowerCase(guess);
        if(lowPhrase.indexOf(lowGuess)!=-1){
            System.out.println("Yes, you are right.");
            for(int i=0;i<lowPhrase.length();i++){
                if(lowGuess==lowPhrase.charAt(i)) {
                    this.hiddenPhrase.setCharAt(i, this.secret.charAt(i));
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
        for(int i = 0; i<this.secret.length(); i++){
            lowPhrase+=Character.toLowerCase(secret.charAt(i));
        }
        return lowPhrase;
    }


    /**
     *
     * @return a phraseList read from file
     */
    @Override
    public String toString() {
        return phraseList.toString();
    }

    /**
     *
     * @param obj
     * @return if this and other are both hangman and their phraseList are the same
     */
    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj==null || obj.getClass()!= Hangman.class){
            return false;
        }
        Hangman other=(Hangman)obj;
        if(phraseList.equals(other.phraseList)){
            return true;
        }
        return false;
    }
}
