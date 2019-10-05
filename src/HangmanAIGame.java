import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hangman game for AI user to play
 */
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

    /**
     * play a game
     * @return a gameinstance
     */
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

    /**
     * check if there still exists AI player to play the game
     * @return
     */
    @Override
    public boolean playNext() {
        if(changeablePhraseList.size()==0){
            setChangeablePhraseList(phraseList);
            hangmanPlayer=null;
            playerIndex++;
            if(playerIndex==hangmanPlayers.size()){
                System.out.println("Every player has played. See you!!!");
                return false;
            }
        }
        return true;
    }

    /**
     * get guess from AI player
     * @param previousGuesses
     * @return
     */
    @Override
    char getGuess(String previousGuesses) {
        System.out.println(previousGuesses);
        return hangmanPlayer.nextGuess();
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
        if(obj==null || obj.getClass()!= HangmanAIGame.class){
            return false;
        }
        HangmanAIGame other=(HangmanAIGame) obj;
        if(phraseList.equals(other.phraseList)){
            return true;
        }
        return false;
    }

    /**
     * play game and give feedbacks
     * @param args
     */
    public static void main(String[] args) {
        HangmanPlayer player1=new RandomPlayer();
        HangmanPlayer player2=new StupidPlayer();
        HangmanPlayer player3=new AmateurPlayer();
        HangmanPlayer player4=new RandomPlayer();
        List<HangmanPlayer> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        HangmanAIGame hangmanAIGame=new HangmanAIGame(players);
        GamesRecord record = hangmanAIGame.playAll();
        System.out.println("\n"+record);
        Set<String> playerIds=new HashSet<>();
        for(GameInstance gameInstance: record.getGameInstances()){
            playerIds.add(gameInstance.getPlayer());
        }
        System.out.println("Every player's high game list:");
        for(String Id : playerIds){
            System.out.println(record.highGameList(Id,3));
        }
        float average=record.average();
        System.out.println("\nTotal Average Score: "+average+"\n");
        for(String Id : playerIds){
            float playerAverage=record.average(Id);
            System.out.println(Id+"'s average score is "+ playerAverage);
        }
    }
}
