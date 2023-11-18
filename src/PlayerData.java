class PlayerData {

    private String playerId; //value is player ID – A random UUID.
    private String playerOperation; // One of 3 operations: DEPOSIT, BET, WITHDRAW.
    private String matchId;   // match Id – A random UUID. Can be null
    private String transactionAmount; //coin number player use for that operation.
    private String betPlacement; // the side of the match the player places the bet on value can be either A or B

    public PlayerData(String playerId, String playerOperation, String matchId, String transactionAmount,
                      String betPlacement) {
        this.playerId = playerId;
        this.playerOperation = playerOperation;
        this.matchId = matchId;
        this.transactionAmount = transactionAmount;
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

    protected int getTransactionAmount() {
        return Integer.valueOf(transactionAmount);
    }

    protected String getBetPlacement() {
        return betPlacement;
    }
}