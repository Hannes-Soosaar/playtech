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

    public PlayerAccount(String playerID, int betsPlaced, int betsWon, int playerBalance, BigDecimal winRate,
                         boolean isActive) {
        this.playerID = playerID;
        this.betsPlaced = betsPlaced;
        this.betsWon = betsWon;
        this.playerBalance = playerBalance;
        this.winRate = winRate;
        this.isActive = isActive;

    }

    protected void updateBalance(int amount) {
        this.playerBalance += amount;
    }

    protected void updateWinRate(int betsPlaced, int betsWon) {
        if (betsPlaced > 0) {
            this.winRate = BigDecimal.valueOf(betsWon).divide(BigDecimal.valueOf(betsPlaced),
                    2,RoundingMode.HALF_UP);
        } else {
            this.winRate = BigDecimal.ZERO;
        }
    }

    protected void addBetsWon() {
        this.betsWon++;
    }

    protected void setPlayerToInactive() {
       this.isActive = false;
    }

    protected void addABetPlaced() {
       this.betsPlaced++;
    }

    protected long getPlayerBalance() {
        return this.playerBalance;
    }

    protected String getPlayerId() {
        return this.playerID;
    }

    protected int getBetsPlaced() {
        return this.betsPlaced;
    }

    protected int getBetsWon() {
        return this.betsWon;
    }

    protected BigDecimal getWinRate() {
        return this.winRate.setScale(2, RoundingMode.DOWN);
    }

    protected boolean getIsActive() {
        return this.isActive;
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
                PlayerAccount playerAccount = new PlayerAccount(playerId, 0, 0, 0, BigDecimal.ZERO, true);
                playerAccounts.add(playerAccount);
            }
        }
        Config.displayRunStatus("Player Accounts created");
        return playerAccounts;
    }


}
