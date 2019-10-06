import java.util.Scanner;

/**
 * provide datamembers and methods that can be used by guessing games
 */
public abstract class GuessingGame extends Game{
    public static final int TOTALCHANCES=10;
    protected String previousGuesses="";
    protected String secret;

    /**
     * reusable code for MasterMind and HangmanUserPlayer
     * @return
     */
    @Override
    public boolean playNext() {
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
     * get user's input id
     * @return gameinstance
     */
    protected GameInstance getUsersId(){
        GameInstance gameInstance=new GameInstance();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter your PlayerId");
        String player=scanner.nextLine();
        gameInstance.setPlayer(player);
        return gameInstance;
    }

    /**
     * show previousguesses and check if the input length is reasonable
     * @param length
     * @return
     */
    protected String getReasonableInput(int length){
        if(!previousGuesses.equals("")) {
            System.out.println("previousGuesses: \n" + previousGuesses);
        }
        Scanner scanner=new Scanner(System.in);
        String str1="";
        while(str1.length()!=length){
            System.out.println("Please guess "+length+" letter: ");
            str1=scanner.nextLine();
        }
        return str1;
    }

    /**
     *
     * @return if the guess is correct
     */
    public abstract boolean processGuess();

    /**
     *
     * @return the secret to be guessed
     */
    @Override
    public String toString() {
        return secret;
    }

    /**
     *
     * @param obj
     * @return whether obj is of class GuessingGame and are the secret of each object is the same
     */
    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj==null || obj.getClass()!=GuessingGame.class){
            return false;
        }
        GuessingGame other=(GuessingGame)obj;
        if(this.secret.equals(other.secret)){
            return true;
        }
        return false;
    }
}
