import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlayerAccount {

    private String playerID;
    private int playerBalance;
    //(wonGames/NumberOfBetsPlaced)
    private BigDecimal winRate;
    // a)In case of an illegitimate actions the account is deactivated all counts start
    // b) all counts start as active
    private boolean isActive;  // In case of an illegitimate action

    // s the account is deactivated all counts start
    // as active
    public PlayerAccount(String playerID, int playerBalance, BigDecimal winRate, boolean isActive) {
        this.playerID = playerID;
        this.playerBalance = playerBalance;
        this.winRate = winRate;
        this.isActive = isActive;
    }

    protected void updateBalance(int amount) {
        this.playerBalance += amount;
    }

    protected void updateWinRate(BigDecimal updatedWinRate) {
        this.winRate = winRate;
    }

    protected boolean isActive() {
        return isActive;
    }

    protected String getPlayerId() {
        return playerID;
    }


    public static void initializeAccounts(List<PlayerData> playerDataList) {
        List<PlayerAccount> playerAccounts = new ArrayList<>();
        for (PlayerData playerData : playerDataList) {
            String playerId = playerData.getPlayerId();
            boolean accountExists = false;
            for (PlayerAccount existingAccounts : playerAccounts) {
                if (existingAccounts.getPlayerId().equals(playerId)) ;
                {
                    accountExists = true;
                    break;
                }
            }
            if (!accountExists){
                PlayerAccount playerAccount = new PlayerAccount(playerId,0,0,true)
                playerAccounts.add(playerAccount);
            }
        }
        Config.displayRunStatus("Player Accounts created");
    }

    // IDs followed with their final balance and their betting win rate (Win rate is calculated by number of won game
    // / number of placed bets)
    //	win rate is big decimal number with 2 decimal characters
    // ordered by player ID.
}
