public class IllegitimatePlayers{
    private String playerId; //value is player ID – A random UUID.
    private String playerOperation; // One of 3 operations: DEPOSIT, BET, WITHDRAW.
    private String matchId;   // match Id – A random UUID. Can be null
    private String transactionSum; //coin number player use for that operation.
    private String betPlacement; // the side of the match the player places the bet on value can be either A or B
    // constructor
    public IllegitimatePlayers(String playerId, String playerOperation, String matchId, String transactionSum,
                      String betPlacement) {
        this.playerId = playerId;
        this.playerOperation = playerOperation;
        this.matchId = matchId;
        this.transactionSum = transactionSum;
        this.betPlacement = betPlacement;
    }

    // getters
    protected String getPlayerId() {
        return playerId;
    }

    protected String getPlayerOperation() {
        return playerOperation;
    }

    protected String getMatchId() {
        return matchId;
    }

    protected String getTransactionSum() {
        return transactionSum;
    }

    protected String getBetPlacement() {
        return betPlacement;
    }




    // o	The list is ordered by player ID.
    // o	In case of WITHDRAW operation, empty values should be presented as “null”, e.g
    // 4925ac98-833b-454b-9342-13ed3dfd3ccf WITHDRAW null 8093 null

}
