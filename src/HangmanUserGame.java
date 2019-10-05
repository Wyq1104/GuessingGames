import java.util.List;
import java.util.Scanner;

/**
 * Hangman game for human user to play
 */
public class HangmanUserGame extends Hangman {
    /**
     * constructor that read phrases from file
     */
    HangmanUserGame(){
        readPhrases("test.txt");
    }

    /**
     * play a game
     * @return a GameInstance
     */
    @Override
    public GameInstance play() {
        GameInstance gameInstance=getUsersId();
        randomPhrase();
        getHiddenPhrase();
        System.out.println(hiddenPhrase);
        int chancesLeft=TOTALCHANCES;
        previousGuesses="";
        while(chancesLeft>0){
            boolean bool=processGuess();
            if(!bool){
                chancesLeft--;
            }
            System.out.println("Chances left: "+chancesLeft);
            System.out.println(hiddenPhrase);
            if(hiddenPhrase.indexOf("*")==-1){
                break;
            }
        }
        if(chancesLeft==0){
            System.out.println("Sorry, You lose!!!");
            System.out.println("The correct answer is: "+ secret);
            gameInstance.setScore(0);
        }else{
            System.out.println("Congratulation, you win!!!");
            gameInstance.setScore(chancesLeft);
        }
        return gameInstance;
    }

    /**
     *asks if the next game should be played
     * @return a boolean
     */
    @Override
    public boolean playNext() {
        if(phraseList.size()==0) {
            System.out.println("No more games, thank you for your support.");
            return false;
        }
        return super.playNext();
    }

    /**
     * get user's input from command line and add it to previousGuesses
     * @param previousGuesses
     * @return
     */
    @Override
    char getGuess(String previousGuesses) {
        System.out.println("Previous guesses: "+previousGuesses);
        Scanner scanner=new Scanner(System.in);
        String str1="";
        while(str1.length()!=1){
            System.out.println("Please guess a letter: ");
            str1=scanner.next();
        }
        char guess=str1.charAt(0);
        if(!Character.isLetter(guess)){
            System.out.println("You don't input a letter");
            return getGuess(previousGuesses);
        }
        if(previousGuesses.indexOf(guess)!=-1){
            System.out.println("Please do not enter guessed letter");
            return getGuess(previousGuesses);
        }
        return guess;
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
     * @return if this and other are both hangmanusergame and their phraseList are the same
     */
    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj==null || obj.getClass()!= HangmanUserGame.class){
            return false;
        }
        HangmanUserGame other=(HangmanUserGame) obj;
        if(phraseList.equals(other.phraseList)){
            return true;
        }
        return false;
    }


    /**
     * run the game and give feedbacks
     * @param args
     */
    public static void main(String[] args) {
        HangmanUserGame hangmanUserGame = new HangmanUserGame();
        GamesRecord record = hangmanUserGame.playAll();
        System.out.println(record);
        List highGameList=record.highGameList(2);
        System.out.println("High Game List:\n"+highGameList);
        float average=record.average();
        System.out.println("Average Score: "+average);
    }
}
