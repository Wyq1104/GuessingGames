import java.util.Scanner;

public abstract class GuessingGame extends Game{

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



}
