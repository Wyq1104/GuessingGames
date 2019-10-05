import java.util.Scanner;

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
     *
     * @return if the guess is correct
     */
    public abstract boolean processGuess();



}
