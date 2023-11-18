import java.util.List;

public class Transform {

    public void processPlayerData(List<PlayerData> playerDataList) {

        for (PlayerData playerData : playerDataList) { // iterates through the playerData file
            String playerId = playerData.getPlayerId();
            int transactionAmount = playerData.getTransactionAmount();
            String playerOperation = playerData.getPlayerOperation();

            switch (playerOperation) {

                case "BET":
                    //todo add logic for counting the bet check if bet is valid meaning enough cash and that
                    // player is active
                    String matchId = playerData.getMatchId();
                    String betPlacement = playerData.getBetPlacement();
                    System.out.printf("A bet was made on: %s in the amount of :%d matchID:%s\n",betPlacement,
                            transactionAmount, matchId);
                    break;
                case "WITHDRAW":
                    System.out.println("A withdraw was made");
                    //todo add logic for the withdraw
                    break;
                case "DEPOSIT":
                    System.out.println("A deposit was made");
                    break;
                default:
                    System.out.println("Theres is an invalid transaction!");
                    break;

            }
            // if the operation is Withdraw check if there is enough funds for the withdraw if yes.. subtract the
            // ammount from the playe
            // if the operation is Deposit add the amount to the player

        }

    }

//    protected List<LegitimatePlayer> getLegitPlayerTransactions(List<PlayerData> playerData,
//                                                                List<IllegitimatePlayers> illegitimatePlayers,
//                                                                List<MatchData> matchData ) {
//
//        // Need a temporary list to store player transactions when summarizing them
//
//
//        return null; // todo change back from null
//    }

//    protected Map<String,Integer> getSumOfTransactions(List<PlayerData> playerDataList) {
//
//        Map<String,Integer> playerAccountBalance = new HashMap<>();
//
//        for(PlayerData playerData: playerDataList){
//                String playerId = playerData.getPlayerId();
//                String playerOperation  =playerData.getPlayerOperation();
//                int transactionAmount = Integer.parseInt(playerData.getTransactionAmount());
//        }
//
//
//
//        return Map<>;
//    }
}
