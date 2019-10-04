import java.util.ArrayList;
import java.util.List;

public class HangmanAIGame extends Hangman {
    private List<HangmanPlayer> hangmanPlayers=new ArrayList<>();
    private HangmanPlayer hangmanPlayer;
    private int playerIndex;

    /**
     * set the HangmanPlayer to a default player
     */
    public HangmanAIGame() {
        StupidPlayer stupidPlayer =new StupidPlayer();
        hangmanPlayers.add(stupidPlayer);
        readPhrases("phrases.txt");
    }

    /**
     * allow the client to specify a single concrete HangmanPlayer
     * @param player
     */
    public HangmanAIGame(HangmanPlayer player){
        hangmanPlayers.add(player);
        readPhrases("phrases.txt");
    }

    /**
     * accept a list of HangmanPlayers
     * @param players
     */
    public HangmanAIGame(List<HangmanPlayer> players){
        hangmanPlayers=players;
        readPhrases("phrases.txt");
    }

    @Override
    public GameInstance play() {
        GameInstance gameInstance=new GameInstance();
        if(hangmanPlayer==null){
            hangmanPlayer=hangmanPlayers.get(playerIndex);
        }
        gameInstance.setPlayer(hangmanPlayer.getId());
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
        hangmanPlayer.reset();
        return gameInstance;
    }

    @Override
    public boolean playNext() {
        if(changeablePhraseList.size()==0){
            changeablePhraseList=phraseList;
            hangmanPlayer=null;
            playerIndex++;
            if(playerIndex==hangmanPlayers.size()){
                System.out.println("Every player has played. See you!!!");
                return false;
            }
        }
        return true;
    }

    @Override
    char getGuess(String previousGuesses) {
        System.out.println(previousGuesses);
        return hangmanPlayer.nextGuess();
    }

    public void test1stConstr(){
        HangmanAIGame hangmanAIGame=new HangmanAIGame();
        GamesRecord record = hangmanAIGame.playAll();
        System.out.println(record);
    }

    public void test2ndConstr(){
        HangmanPlayer player1=new StupidPlayer();
        HangmanAIGame hangmanAIGame=new HangmanAIGame(player1);
        GamesRecord record = hangmanAIGame.playAll();
        System.out.println(record);
    }

    public void test3rdConstr(){
        HangmanPlayer player1=new StupidPlayer();
        HangmanPlayer player2=new StupidPlayer();
        List<HangmanPlayer> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        HangmanAIGame hangmanAIGame=new HangmanAIGame(players);
        GamesRecord record = hangmanAIGame.playAll();
        System.out.println(record);
    }

    public static void main(String[] args) {
        HangmanPlayer player1=new RandomPlayer();
        HangmanAIGame hangmanAIGame=new HangmanAIGame(player1);
        GamesRecord record = hangmanAIGame.playAll();
        System.out.println(record);

    }
}
