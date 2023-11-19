import java.util.ArrayList;
import java.util.List;

public class IllegitimatePlayers {
    private String playerId;
    private String playerOperation;
    private String matchId;
    private int transactionSum;
    private String betPlacement;

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

    public static void addIllegitimatePlayer(PlayerData playerData) {
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
