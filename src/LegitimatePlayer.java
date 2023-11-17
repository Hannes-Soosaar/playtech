import java.math.BigDecimal;

public class LegitimatePlayer {

    private String playerID;
    private int playerBalance;
    //(wonGames/NumberOfBetsPlaced)
    private BigDecimal winRate;
    // a)In case of an illegitimate actions the account is deactivated all counts start
    // b) all counts start as active
    private boolean isActive;  // In case of an illegitimate actions the account is deactivated all counts start
                                // as active
    public LegitimatePlayer(String playerID, int playerBalance, BigDecimal winRate, boolean isActive){
        this.playerID = playerID;
        this.playerBalance = playerBalance;
        this.winRate = winRate;
        this.isActive =isActive;
    }

     // IDs followed with their final balance and their betting win rate (Win rate is calculated by number of won game
     // / number of placed bets)
     //	win rate is big decimal number with 2 decimal characters
     // ordered by player ID.
}
