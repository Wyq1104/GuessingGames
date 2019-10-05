/**
 * Class used to keep track of the score and player
 */
public class GameInstance implements Comparable{
    private int score;
    private String player;

    /**
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * set score
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * return playerId
     */
    public String getPlayer() {
        return player;
    }

    /**
     * set this.player as player
     * @param player
     */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * compares scores
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        GameInstance other=(GameInstance)o;
        if(this.score>other.score){
            return 1;
        }else if(this.score<other.score){
            return -1;
        }else{
            return 0;
        }
    }

    /**
     *
     * @return String that represents gameinstance
     */
    @Override
    public String toString() {
        return "Player: "+player+" Score: "+score;
    }

    /**
     *
     * @param obj
     * @return if another object is equal to this object
     */
    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj==null||obj.getClass()!= GameInstance.class){
            return false;
        }
        GameInstance other=(GameInstance)obj;
        if(this.score==other.score && this.player.equals(other.player)){
            return true;
        }
        return false;
    }
}
