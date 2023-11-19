import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Comparator;
import java.util.List;

public class WriteToFile {
    public static void activePlayerAccounts(List<PlayerAccount> playerAccountList) {

        playerAccountList.sort(Comparator.comparing(PlayerAccount::getPlayerId));
        boolean hasActive = false;

        try (PrintWriter writer = new PrintWriter(new FileWriter(Config.REPORT_FILE_PATH, true))) {

            for (PlayerAccount playerAccount : playerAccountList) {

                if (playerAccount.getIsActive()) {
                    BigDecimal winRate = playerAccount.getWinRate();
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);
                    writer.printf("%s %d %s%n",
                            playerAccount.getPlayerId(),
                            playerAccount.getPlayerBalance(),
                            decimalFormat.format(winRate)
                    );
                    hasActive = true;
                }
            }
            if (!hasActive) {
                writer.println("");
            }
            writer.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Config.displayRunStatus("Active player stats written to file: OK");
    }


    public static void illegalPlayers(List<IllegitimatePlayers> illegitimatePlayersList) {

        illegitimatePlayersList.sort(Comparator.comparing(IllegitimatePlayers::getPlayerId));

        try (PrintWriter writer = new PrintWriter(new FileWriter(Config.REPORT_FILE_PATH, true))) {
            if (illegitimatePlayersList.isEmpty()) {
                writer.println("");
            }
            for (IllegitimatePlayers illegitimatePlayer : illegitimatePlayersList) {

                writer.printf("%s %s %s %s %s%n",
                        illegitimatePlayer.getPlayerId(),
                        illegitimatePlayer.getPlayerOperation(),
                        illegitimatePlayer.getMatchId(),
                        illegitimatePlayer.getTransactionSum(),
                        illegitimatePlayer.getBetPlacement());

            }
            writer.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Config.displayRunStatus("List of Illegitimate players written to file: OK");
    }
//

    public static void hostBalance(int hostBalance) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(Config.REPORT_FILE_PATH, true))) {

            writer.printf("%d", hostBalance);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Config.displayRunStatus("Balance report written to file: OK");
    }

}
