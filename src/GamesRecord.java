import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Record games
 */
public class GamesRecord {
    private List<GameInstance> gameInstances=new ArrayList<>();
    /**
     * adds a Game to the GamesRecord
     * @param gameInstance
     */
    public void add(GameInstance gameInstance){
        gameInstances.add(gameInstance);
    }

    /**
     * returns the average score for all games added to the record
     */
    public float average(){
        // TODO: 9/28/19
        float sum=0;
        for(GameInstance gameInstance: gameInstances){
            sum+=gameInstance.getScore();
        }
        return sum/gameInstances.size();
    }

    /**
     * returns the average score for all games of a particular player
     * @param playerId
     */
    public float average(String playerId){
        float sum=0;
        float num=0;
        for(GameInstance gameInstance: gameInstances){
            if(gameInstance.getPlayer().equals(playerId)){
                sum+=gameInstance.getScore();
                num++;
            }
        }
        if(num==0){
            return 0;
        }else {
            return sum/num;
        }
    }

    /**
     *
     * @param n
     * @return a sorted list of the top n scores
     */
    public List<GameInstance> highGameList(int n){
        List<GameInstance> newGameInstances=new ArrayList<>(gameInstances);
        Collections.sort(newGameInstances);
        Collections.reverse(newGameInstances);
        if(gameInstances.size()>=n){
            List<GameInstance> highestN=newGameInstances.subList(0,n);
            return highestN;
        }else {
            return newGameInstances;
        }
    }

    /**
     *
     * @param playerId
     * @param n
     * @return a sorted list of the top n scores for the specified player
     */
    public List<GameInstance> highGameList(String playerId, int n){
        List<GameInstance> playerGameInstances=new ArrayList<>();
        for(int i=0;i<gameInstances.size();i++){
            if(gameInstances.get(i).getPlayer().equals(playerId)){
                playerGameInstances.add(gameInstances.get(i));
            }
        }
        Collections.sort(playerGameInstances);
        Collections.reverse(playerGameInstances);
        if(playerGameInstances.size()>=n){
            List<GameInstance> highestN=playerGameInstances.subList(0,n);
            return highestN;
        }else {
            return playerGameInstances;
        }
    }

    /**
     *
     * @return gameInstances
     */
    public List<GameInstance> getGameInstances() {
        return gameInstances;
    }

    /**
     *
     * @return Stirng that represents game record
     */
    @Override
    public String toString() {
        String str="";
        for(GameInstance gameInstance: gameInstances){
            str+=gameInstance+"\n";
        }
        return str;
    }

    /**
     *
     * @param obj
     * @return if this and obj are both object of GamesRecord and have the same gameInstances
     */
    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj==null || obj.getClass()!= GamesRecord.class){
            return false;
        }
        GamesRecord other=(GamesRecord)obj;
        if(this.gameInstances.equals(other.gameInstances)){
            return true;
        }
        return false;
    }
}
