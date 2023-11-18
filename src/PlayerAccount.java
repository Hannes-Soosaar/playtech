import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PlayerAccount {

    private String playerID;
    private int betsPlaced;
    private long playerBalance;
    //todo (wonGames/NumberOfBetsPlaced)
    private BigDecimal winRate;
    private boolean isActive;

    public PlayerAccount(String playerID, int betsPlaced, int playerBalance, BigDecimal winRate, boolean isActive) {
        this.playerID = playerID;
        this.betsPlaced = betsPlaced;
        this.playerBalance = playerBalance;
        this.winRate = winRate;
        this.isActive = isActive;
    }

    protected void updateBalance(int amount) {
        this.playerBalance += amount;
    }

    protected void setWinRate(BigDecimal updatedWinRate) {
        this.winRate = updatedWinRate;
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
                PlayerAccount playerAccount = new PlayerAccount(playerId, 0, 0, BigDecimal.ZERO, true);
                playerAccounts.add(playerAccount);
            }
        }
        Config.displayRunStatus("Player Accounts created");
        return playerAccounts;
    }
}
