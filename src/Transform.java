import java.util.List;

public class Transform {

    public HostBalance processPlayerData(List<PlayerData> playerDataList,
                                         List<PlayerAccount> playerAccountList,
                                         List<MatchData> matchDataList) {

        for (PlayerData playerData : playerDataList) {
            String playerId = playerData.getPlayerId();
            int transactionAmount = playerData.getTransactionAmount();
            String playerOperation = playerData.getPlayerOperation();

            switch (playerOperation) {
                case "BET":
                    if (isValidBet(playerAccountList, playerData,transactionAmount, playerId)) {
                        processBet(playerAccountList, matchDataList, playerData);
                    }
                    break;
                case "WITHDRAW":
                    withdrawFromPlayerAccount(playerAccountList, playerData);
                    break;
                case "DEPOSIT":
                    depositToPlayerAccount(playerAccountList, playerId, transactionAmount);
                    break;
                default:
                    break;
            }
        }
        return generateHostBalance(playerAccountList, playerDataList, matchDataList);
    }

    public void processBet(List<PlayerAccount> playerAccountList,
                           List<MatchData> matchDataList,
                           PlayerData playerData) {
        MatchData match = findMatch(matchDataList, playerData);
        PlayerAccount playerAccount = findPlayerAccount(playerData.getPlayerId(), playerAccountList);
        updateBalances(playerAccount, match, playerData);
    }

    /* Can not create the host balance runtime as not all player statuses are known are validated players*/
    public void updateBalances(PlayerAccount playerAccount, MatchData match, PlayerData playerData) {
        switch (match.getMatchResult().toLowerCase()) {
            case "a":
                if (playerData.getBetPlacement().equals("A")) {
                    playerAccount.updateBalance(
                            (int) Math.floor(match.getRateOfReturnSideA() * (double) playerData.getTransactionAmount())
                    );
                    playerAccount.addBetsWon();

                } else {
                    playerAccount.updateBalance(-playerData.getTransactionAmount());
                }
                break;
            case "b":
                if (playerData.getBetPlacement().equals("B")) {
                    playerAccount.updateBalance(
                            (int) Math.floor(match.getRateOfReturnSideB() * (double) playerData.getTransactionAmount())
                    );
                    playerAccount.addBetsWon();
                } else {
                    playerAccount.updateBalance(-playerData.getTransactionAmount());
                }
                break;
            case "draw":
                playerAccount.updateBalance(playerData.getTransactionAmount());
                break;
            default:
                break;
        }
        playerAccount.addABetPlaced();
        playerAccount.updateWinRate(playerAccount.getBetsPlaced(), playerAccount.getBetsWon());
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

    public void depositToPlayerAccount(List<PlayerAccount> playerAccountList, String playerId,
                                       int transactionAmount) {
        for (PlayerAccount playerAccount : playerAccountList) {
            if (playerAccount.getPlayerId().equals(playerId)) {
                playerAccount.updateBalance(transactionAmount);
            }
        }
    }

    public boolean isValidBet(List<PlayerAccount> playerAccountList, PlayerData playerData,  int transactionAmount,
                              String playerId) {
        for (PlayerAccount playerAccount : playerAccountList) {
            if (playerAccount.getPlayerId().equals(playerId) &&
                    playerAccount.getPlayerBalance() >= transactionAmount &&
                    playerAccount.getIsActive()) {
                return true;
            } else if (playerAccount.getPlayerId().equals(playerId) &&
                    playerAccount.getPlayerBalance() < transactionAmount &&
                    playerAccount.getIsActive()) {
                playerAccount.setPlayerToInactive();
                IllegitimatePlayers.addIllegitimatePlayer(playerData);
            }

        }
        return false;
    }

    public MatchData findMatch(List<MatchData> matchDataList, PlayerData playerData) {
        for (MatchData matchData : matchDataList) {
            if (matchData.getMatchId().equals(playerData.getMatchId())) {
                return matchData;
            }
        }
        return new MatchData("", "", "", "");
    }

    public PlayerAccount findPlayerAccount(String playerId, List<PlayerAccount> playerAccountList) {
        for (PlayerAccount playerAccount : playerAccountList) {
            if (playerAccount.getPlayerId().equals(playerId)) {
                return playerAccount;
            }
        }
        Config.displayRunStatus("No account for the player in player_data");
        return null;
    }

    public HostBalance generateHostBalance(List<PlayerAccount> playerAccountList,
                                           List<PlayerData> playerDataList,
                                           List<MatchData> matchDataList) {
        HostBalance hostBalance = new HostBalance();
        for (PlayerData playerData : playerDataList) {
            MatchData match = findMatch(matchDataList, playerData);
            PlayerAccount playerAccount = findPlayerAccount(playerData.getPlayerId(), playerAccountList);
            int matchTotalAmount = 0;
            if (playerAccount.getIsActive()
                    && playerAccount.getPlayerId().equals(playerData.getPlayerId())
                    && playerData.getMatchId().equals(match.getMatchId())) {

                switch (match.getMatchResult().toLowerCase()) {
                    case "a":
                        matchTotalAmount =
                                ((int) Math.floor(match.getRateOfReturnSideA()
                                        * (double) playerData.getTransactionAmount()));
                        if (playerData.getBetPlacement().equalsIgnoreCase("a")) {
                            hostBalance.setBalance(matchTotalAmount, true);
                        } else {
                            hostBalance.setBalance(matchTotalAmount, false);
                        }
                        break;
                    case "b":
                        matchTotalAmount =
                                ((int) Math.floor(match.getRateOfReturnSideB()
                                        * (double) playerData.getTransactionAmount()));
                        if (playerData.getBetPlacement().equalsIgnoreCase("b")) {
                            hostBalance.setBalance(matchTotalAmount, true);
                        } else {
                            hostBalance.setBalance(matchTotalAmount, false);
                        }
                        break;
                    case "darw":
                        // do nothing, but it is a valid case.
                    default:
                        break;
                }
            }
        }
        return hostBalance;
    }

}

