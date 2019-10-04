public class AmateurPlayer implements HangmanPlayer {
    static int playerNum=1;
    private String Id="StupidPlayer"+playerNum;
    private String alphabets="abcdefghijklmnopqrstuvwxyz";
    private int index;

    public AmateurPlayer(){
        playerNum++;
    }
    @Override
    public String getId() {
        return Id;
    }

    @Override
    public char nextGuess() {
        char guess=alphabets.charAt(index);
        index++;
        System.out.println("Guess this time: "+guess);
        return guess;
    }

    @Override
    public boolean reset() {
        index=0;
        return true;
    }
}
