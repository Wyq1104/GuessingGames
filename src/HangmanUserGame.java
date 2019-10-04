
import java.util.Scanner;

public class HangmanUserGame extends Hangman {
    HangmanUserGame(){
        readPhrases("test.txt");
    }
    /**
     * play a game
     * @return a GameInstance
     */
    @Override
    public GameInstance play() {
        GameInstance gameInstance=new GameInstance();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter your PlayerId");
        String player=scanner.nextLine();
        gameInstance.setPlayer(player);
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
            System.out.println("The correct answer is: "+phrase);
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
        if(changeablePhraseList.size()==0) {
            System.out.println("No more games, thank you for your support.");
            return false;
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("Do you want to play one more game?");
        System.out.println("Yes: enter Y. No: enter anything else.");
        String response=scanner.nextLine();
        if(response.length()==1 && (response.charAt(0)=='Y' || response.charAt(0)=='y')){
            System.out.println("Ok, let's go.");
            return true;
        }else {
            System.out.println("I'm sorry to hear that. See you next time");
            return false;
        }
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

    public static void main(String[] args) {
        HangmanUserGame hangmanUserGame = new HangmanUserGame();
        GamesRecord record = hangmanUserGame.playAll();
        System.out.println(record);
    }
}
