import java.util.List;

public class Main {
    
    public static void main(String[] args) {

        Extract extract = new Extract();
        List<PlayerData> playerDataTransactions = extract.readInPlayerData();
        List<MatchData> matchOutcomes = extract.readInMatchData();
        List<PlayerAccount> playerAccountList =
                PlayerAccount.initializeAccounts(playerDataTransactions);
        
        Transform transform = new Transform();
        HostBalance hostBalance = transform.processPlayerData(playerDataTransactions,
                playerAccountList,
                matchOutcomes);
        List<IllegitimatePlayers> illegitimatePlayerList =
                IllegitimatePlayers.getIllegitimatePlayersList();

        WriteToFile.activePlayerAccounts(playerAccountList);
        WriteToFile.illegalPlayers(illegitimatePlayerList);
        WriteToFile.hostBalance(hostBalance.getBalance());
    }
}
