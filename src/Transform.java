import java.util.List;

public class Transform {

    public void processPlayerData(List<PlayerData> playerDataList, List<PlayerAccount> playerAccounts,
                                  List<MatchData> matchData) {

        for (PlayerData playerData : playerDataList) { // iterates through the playerData file
            String playerId = playerData.getPlayerId();
            int transactionAmount = playerData.getTransactionAmount();
            String playerOperation = playerData.getPlayerOperation();

            switch (playerOperation) {

                case "BET":
                    if (isValidBet(playerAccounts, transactionAmount, playerId)) {
                        processBet(playerAccounts, matchData, playerData);
                    } else {
                        IllegitimatePlayers.addIllegitimatePlayer(playerData);
                    }
                    break;
                case "WITHDRAW":
                    withdrawFromPlayerAccount(playerAccounts, playerData);
                    break;
                case "DEPOSIT":
                    depositToPlayerAccount(playerAccounts, playerId, transactionAmount);
                    break;
                default:
                    System.out.println("Theres is an invalid transaction!");
                    break;

            }
        }
    }

    public void depositToPlayerAccount(List<PlayerAccount> playerAccountList, String playerId,
                                       int transactionAmount) {
        for (PlayerAccount playerAccount : playerAccountList) {
            if (playerAccount.getPlayerId().equals(playerId)) {
                playerAccount.updateBalance(transactionAmount);
            }
        }
    }

    public void withdrawFromPlayerAccount(List<PlayerAccount> playerAccountList,
                                          PlayerData playerData) {
        for (PlayerAccount playerAccount : playerAccountList) {
            if (playerAccount.getPlayerId().equals(playerData.getPlayerId())
                    && playerAccount.getIsActive()
                    && playerAccount.getPlayerBalance() >= playerData.getTransactionAmount()) {
                playerAccount.updateBalance(-playerData.getTransactionAmount());
            } else if (playerAccount.getPlayerId().equals(playerData.getPlayerId())
                    && playerAccount.getIsActive()
                    && playerAccount.getPlayerBalance() < playerData.getTransactionAmount()) {
                playerAccount.setPlayerToInactive();
                IllegitimatePlayers.addIllegitimatePlayer(playerData);
            }
        }
    }

    public boolean isValidBet(List<PlayerAccount> playerAccountList, int transactionAmount, String playerId) {

        for (PlayerAccount playerAccount : playerAccountList) {
            if (playerAccount.getPlayerId().equals(playerId) &&
                    playerAccount.getPlayerBalance() > transactionAmount &&
                    playerAccount.getIsActive()) {
                return true;
            }
        }
        return false;
    }

    public void processBet(List<PlayerAccount> playerAccountList, List<MatchData> matchDataList, PlayerData playerData) {
        MatchData match = findMatch(matchDataList, playerData);
        PlayerAccount playerAccount = findPlayerAccount(playerData.getPlayerId(), playerAccountList);
        updateBalances(playerAccount, match, playerData);
    }

    public MatchData findMatch(List<MatchData> matchDataList, PlayerData playerData) {
        for (MatchData matchData : matchDataList) {
            if (matchData.getMatchId().equals(playerData.getMatchId())) {
                return matchData;
            }
        }
        return null;  // todo handle the error.
    }

    public PlayerAccount findPlayerAccount(String playerId, List<PlayerAccount> playerAccountList) {
        for (PlayerAccount playerAccount : playerAccountList) {
            if (playerAccount.getPlayerId().equals(playerId)) {
                return playerAccount;
            }
        }
        return null; // todo handle the error
    }

    public void updateBalances(PlayerAccount playerAccount, MatchData match, PlayerData playerData) {
        switch (match.getMatchResult()) { //todo the data type is wrong for the return rate
            case "A":
                if (playerData.getBetPlacement().equals("A")) {
                    playerAccount.updateBalance(
                            (int) Math.floor(match.getRateOfReturnSideA() * (double) playerData.getTransactionAmount())
                    );
                } else {
                    playerAccount.updateBalance(-playerData.getTransactionAmount());
                }
                break;
            case "B":
                if (playerData.getBetPlacement().equals("B")) {
                    playerAccount.updateBalance(
                            (int) Math.floor(match.getRateOfReturnSideB() * (double) playerData.getTransactionAmount())
                    );
                } else {
                    playerAccount.updateBalance(-playerData.getTransactionAmount());
                }
                break;
            case "DRAW":
                playerAccount.updateBalance(playerData.getTransactionAmount());
                break;
            default:
                // todo handle any other case
                break;
        }

        playerAccount.addABetPlaced();
    }


}

