
/**
 * encapsulate the code for looping through a set of games and recording the results
 */
public abstract class Game {
    public GamesRecord playAll(){
        GamesRecord gamesRecord=new GamesRecord();
        while(playNext()){
            GameInstance gameInstance=this.play();
            gamesRecord.add(gameInstance);
        }
        return gamesRecord;
    }

    /**
     * play a game
     * @return a GameInstance
     */
    public abstract GameInstance play();

    /**
     *asks if the next game should be played
     * @return a boolean
     */
    public abstract boolean playNext();

}
