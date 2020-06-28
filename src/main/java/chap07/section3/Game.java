package chap07.section3;

public class Game {

    private GameNumGen gameNumGen;
    private String gameNum;

    public Game(GameNumGen gameNumGen) {
        this.gameNumGen = gameNumGen;
    }

    public void init(GameLevel gameLevel) {
        this.gameNum = gameNumGen.generate(gameLevel);
    }
}
