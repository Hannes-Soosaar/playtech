import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transform {

    protected List<IllegitimatePlayers> getIllegitimatePlayers(List<PlayerData> playerData){ // todo needs to take in
        // todo a summary list of playerTransactions

        // reads the playerID at the time when the balance turns negative adds the player to the IllegitimatePlayers
        // list with the single line. if the player already is not illegitimate (deactive).

        // o	The list is ordered by player ID.
        // o	In case of WITHDRAW operation, empty values should be presented as “null”, e.g
        // 4925ac98-833b-454b-9342-13ed3dfd3ccf WITHDRAW null 8093 null


        return null; // todo change back from null
    }

    protected List<LegitimatePlayer> getLegitPlayerTransactions(List<PlayerData> playerData,
                                                                List<IllegitimatePlayers> illegitimatePlayers,
                                                                List<MatchData> matchData ) {

        // Need a temporary list to store player transactions when summarizing them


        return null; // todo change back from null
    }

    protected Map<String,Integer> getSumOfTransactions(List<PlayerData> playerDataList) {

        Map<String,Integer> playerAccountBalance = new HashMap<>();

        for(PlayerData playerData: playerDataList){
                String playerId = playerData.getPlayerId();
                String playerOperation  =playerData.getPlayerOperation();
                int transactionAmount = Integer.parseInt(playerData.getTransactionAmount());
        }




    }




}
