/**
 * Class used to keep track of the score and player
 */
public class GameInstance implements Comparable{
    private int score;
    private String player;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayer() {
        return player;
    }

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

    @Override
    public String toString() {
        return "Player: "+player+" Score: "+score;
    }
}
