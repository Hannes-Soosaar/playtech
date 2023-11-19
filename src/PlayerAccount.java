import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PlayerAccount {

    private String playerID;
    private int betsPlaced;
    private int betsWon;
    private long playerBalance;
    private BigDecimal winRate;
    private boolean isActive;

    public PlayerAccount(String playerID, int betsPlaced, int betsWon , int playerBalance, BigDecimal winRate,
                         boolean isActive) {
        this.playerID = playerID;
        this.betsPlaced = betsPlaced;
        this.betsWon =betsWon;
        this.playerBalance = playerBalance;
        this.winRate = winRate;
        this.isActive = isActive;

    }

    protected void updateBalance(int amount) {
        this.playerBalance += amount;
    }

    protected void updateWinRate(int betsPlaced, int betsWon) {

        if ( betsPlaced > 0){
             this.winRate =  BigDecimal.valueOf(betsWon).divide(BigDecimal.valueOf(betsPlaced));
        } else {
            this.winRate = BigDecimal.ZERO;
        }


    }

    protected void addBetsWon(){
        betsWon++;
    }

    protected void setPlayerToInactive() {
        isActive = false;
    }

    protected void addABetPlaced() {
        betsPlaced++;
    }

    protected long getPlayerBalance() {
        return playerBalance;
    }

    protected boolean isActive() {
        return isActive;
    }

    protected String getPlayerId() {
        return playerID;
    }

    protected int getBetsPlaced() {
        return betsPlaced;
    }
    protected int getBetsWon() {
        return betsWon;
    }

    protected BigDecimal getWinRate() {
        return winRate.setScale(2, RoundingMode.DOWN);
    }

    protected boolean getIsActive() {
        return isActive;
    }
    public static List<PlayerAccount> initializeAccounts(List<PlayerData> playerDataList) {
        List<PlayerAccount> playerAccounts = new ArrayList<>();
        for (PlayerData playerData : playerDataList) {
            String playerId = playerData.getPlayerId();
            boolean accountExists = false;
            for (PlayerAccount existingAccounts : playerAccounts) {
                if (existingAccounts.getPlayerId().equals(playerId)) {
                    accountExists = true;
                    break;
                }
            }
            if (!accountExists) {
                PlayerAccount playerAccount = new PlayerAccount(playerId, 0, 0,0, BigDecimal.ZERO, true);
                playerAccounts.add(playerAccount);
            }
        }
        Config.displayRunStatus("Player Accounts created");
        return playerAccounts;
    }


}
