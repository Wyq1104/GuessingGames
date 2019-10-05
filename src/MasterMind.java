import java.util.*;

public class MasterMind extends GuessingGame{
    public static final int CODESIZE=4;
    public static final char[] colorChars=new char[]{'R','G','B','Y','O','P'};
    private Set<Character> characters=new HashSet(Arrays.asList(colorChars));

    private void generateSecret(){
        String str="";
        Random random=new Random();
        for(int i=0;i<CODESIZE;i++){
            int randomIndex=random.nextInt(colorChars.length);
            char currentChar=colorChars[randomIndex];
            str+=currentChar;
        }
        secret=str;
    }


    @Override
    public GameInstance play() {
        reset();
        GameInstance gameInstance=getUsersId();
        System.out.println("Cheating: "+secret);
        int chancesLeft=TOTALCHANCES;
        while(chancesLeft>0){
            System.out.println("Chances Left: "+ chancesLeft);
            boolean bool=processGuess();
            if(bool){
                System.out.println("Congratualtions,you win.");
                gameInstance.setScore(chancesLeft);
                generateSecret();
                return gameInstance;
            }
            chancesLeft--;
        }
        System.out.println("Sorry you have no more chances.");
        gameInstance.setScore(0);
        return gameInstance;
    }

    public int checkExacts(StringBuilder secretSB, StringBuilder guessSB){
        int exacts=0;
        for(int i=0;i<secretSB.length();i++){
            if(secretSB.charAt(i)==guessSB.charAt(i)){
                secretSB.setCharAt(i,'-');
                guessSB.setCharAt(i,'*');
                exacts++;
            }
        }
        return exacts;
    }
    public int checkPartials(StringBuilder secretSB, StringBuilder guessSB) {
        // compare secret to guess
        int i=0;

        int partials=0;
        while (i<CODESIZE) {
            int j=0;
            while (j<CODESIZE) {
                if (secretSB.charAt(i) == guessSB.charAt(j)) {
                    partials = partials + 1;
                    secretSB.setCharAt(i,'-');
                    guessSB.setCharAt(j,'*');
                }
                j++;
            }
            i++;
        }
        return partials;
    }

    public String getGuess(){
        System.out.println(previousGuesses);
        Scanner scanner=new Scanner(System.in);
        String guess="";
        while(guess.length()!=CODESIZE){
            System.out.println("Please guess "+CODESIZE+" letters: ");
            guess=scanner.nextLine();
        }
//        for(int i=0;i<guess.length();i++){
//            if(!isColor(guess.charAt(i))) {
//                System.out.println("You input a character that does not represent color");
//                return getGuess();
//            }
//        }
        return guess;
    }

    private boolean isColor(char c){
        return characters.contains(c);
    }

    @Override
    public boolean processGuess(){
        String guess=getGuess();
        StringBuilder secretSB= new StringBuilder(secret);
        StringBuilder guessSB=new StringBuilder(guess);
        int exacts = checkExacts(guessSB,secretSB);
        int partials = checkPartials(guessSB,secretSB);
        String result=guess+": "+exacts+" exact, "+partials+" partial\n";
        System.out.println(result);
        previousGuesses+=result;
        if(exacts==CODESIZE){
            return true;
        }
        return false;
    }

    private void reset(){
        generateSecret();
        previousGuesses="Previous Results:\n";
    }

    public static void main(String[] args) {
        MasterMind masterMind=new MasterMind();
        GamesRecord gamesRecord=masterMind.playAll();
        System.out.println(gamesRecord);

    }
}
