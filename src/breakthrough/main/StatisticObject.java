package breakthrough.main;

public class StatisticObject
{
    private int lastStatusCode = -1;
    private int gamesRunning = -1;
    private int lastCalledGame = -1;
    private int lastCreatedGameIns = -1;
    private int nextGameToBeCreated = -1;
    private String lastHeader = null;
    private String lastHeaderData = null;
    private String lastValidMove = null;

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    public int getGamesRunning() {
        return gamesRunning;
    }

    public void setGamesRunning(int gamesRunning) {
        this.gamesRunning = gamesRunning;
    }

    public int getLastCalledGame() {
        return lastCalledGame;
    }

    public void setLastCalledGame(int lastCalledGame) {
        this.lastCalledGame = lastCalledGame;
    }

    public int getLastCreatedGameIns() {
        return lastCreatedGameIns;
    }

    public void setLastCreatedGameIns(int lastCreatedGameIns) {
        this.lastCreatedGameIns = lastCreatedGameIns;
    }

    public int getNextGameToBeCreated() {
        return nextGameToBeCreated;
    }

    public void setNextGameToBeCreated(int nextGameToBeCreated) {
        this.nextGameToBeCreated = nextGameToBeCreated;
    }

    public String getLastHeader() {
        return lastHeader;
    }

    public void setLastHeader(String lastHeader) {
        this.lastHeader = lastHeader;
    }

    public String getLastHeaderData() {
        return lastHeaderData;
    }

    public void setLastHeaderData(String lastHeaderData) {
        this.lastHeaderData = lastHeaderData;
    }

    public String getLastValidMove() {
        return lastValidMove;
    }

    public void setLastValidMove(String lastValidMove) {
        this.lastValidMove = lastValidMove;
    }
}
