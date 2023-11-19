
import java.util.ArrayList;
import java.util.List;

public class IllegitimatePlayers {
    private String playerId; //value is player ID – A random UUID.
    private String playerOperation; // One of 3 operations: DEPOSIT, BET, WITHDRAW.
    private String matchId;   // match Id – A random UUID. Can be null
    private int transactionSum; //coin number player use for that operation.
    private String betPlacement; // the side of the match the player places the bet on value can be either A or B

    public IllegitimatePlayers(String playerId, String playerOperation, String matchId, int transactionSum,
                               String betPlacement) {
        this.playerId = playerId;
        this.playerOperation = playerOperation;
        this.matchId = matchId;
        this.transactionSum = transactionSum;
        this.betPlacement = betPlacement;
    }

    protected String getPlayerId() {
        return playerId;
    }

    protected String getPlayerOperation() {
        return playerOperation;
    }

    protected String getMatchId() {
        return matchId;
    }

    protected int getTransactionSum() {
        return transactionSum;
    }

    protected String getBetPlacement() {
        return betPlacement;
    }

    public static List<IllegitimatePlayers> getIllegitimatePlayersList() {
        return illegitimatePlayersList;
    }

    private static List<IllegitimatePlayers> illegitimatePlayersList = new ArrayList<>();

    // todo move the  function
    public static void addIllegitimatePlayer(PlayerData playerData) {
        //todo if time permits redo this, it is too cheap, should contain the function to set the player inactive.
        if (playerData.getPlayerOperation().equals("WITHDRAW")) {
            IllegitimatePlayers illegitimatePlayer = new IllegitimatePlayers(
                    playerData.getPlayerId(),
                    playerData.getPlayerOperation(),
                    "null",
                    playerData.getTransactionAmount(),
                    "null");
            illegitimatePlayersList.add(illegitimatePlayer);
        } else {
            IllegitimatePlayers illegitimatePlayer = new IllegitimatePlayers(
                    playerData.getPlayerId(),
                    playerData.getPlayerOperation(),
                    playerData.getMatchId(),
                    playerData.getTransactionAmount(),
                    playerData.getBetPlacement());
            illegitimatePlayersList.add(illegitimatePlayer);
        }
    }

}
