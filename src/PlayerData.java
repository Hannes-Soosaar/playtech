class PlayerData {

    private String playerId; 
    private String playerOperation; 
    private String matchId;  
    private String transactionAmount; 
    private String betPlacement; 

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
