import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    protected static List<PlayerData> readInPlayerData() {
        List<PlayerData> playerDataTransactions = new ArrayList<>();
        try (BufferedReader readInPlayerTransactions = new BufferedReader(new FileReader(Config.PLAYER_FILE_PATH))) {
            String transactionBuffer;
            while ((transactionBuffer = readInPlayerTransactions.readLine()) != null) {
                String[] transactionDetails = transactionBuffer.split(",");
                PlayerData transaction;
                if (transactionDetails.length == 5) {
                    transaction = new PlayerData(
                            transactionDetails[Config.PLAYER_ID],
                            transactionDetails[Config.PLAYER_OPERATION],
                            transactionDetails[Config.PLAYER_MATCH_ID],
                            transactionDetails[Config.PLAYER_TRANSACTION_SUM],
                            transactionDetails[Config.PLAYER_BET_PLACED]
                    );
                } else {
                    transaction = new PlayerData(
                            transactionDetails[Config.PLAYER_ID],
                            transactionDetails[Config.PLAYER_OPERATION],
                            transactionDetails[Config.PLAYER_MATCH_ID],
                            transactionDetails[Config.PLAYER_TRANSACTION_SUM],
                            ""               // an empty string will be returned if no bet was placed
                    );
                }
                playerDataTransactions.add(transaction);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        Config.displayRunStatus("Read in of player transaction data from file: OK");
        return playerDataTransactions;
    }

    protected static List<MatchData> readInMatchData() {
        List<MatchData> matchOutcome = new ArrayList<>();
        try (BufferedReader readInMatchOutComes = new BufferedReader(new FileReader(Config.MATCH_FILE_PATH))) {
            String matchOutComesBuffer;
            while ((matchOutComesBuffer = readInMatchOutComes.readLine()) != null) {
                String[] matchDetails = matchOutComesBuffer.split(",");
                MatchData outcome;
                outcome = new MatchData(
                        matchDetails[Config.MATCH_ID],
                        matchDetails[Config.MATCH_RETURN_ON_A],
                        matchDetails[Config.MATCH_RETURN_ON_B],
                        matchDetails[Config.MATCH_RESULT]
                );
                matchOutcome.add(outcome);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Config.displayRunStatus("Data read of match data from file: OK");
        return matchOutcome;
    }

    // todo if time allows remove the hardcode requirement
    protected static void writeActivePlayerAccounts(List<PlayerAccount> playerAccountList) {

        playerAccountList.sort(Comparator.comparing(PlayerAccount::getPlayerId));

        try (PrintWriter writer = new PrintWriter(new FileWriter(Config.REPORT_FILE_PATH, true))) {
            for (PlayerAccount playerAccount : playerAccountList) {
                if (playerAccount.getIsActive()) {
                    BigDecimal winRate= playerAccount.getWinRate();
                    writer.printf("%s %d %.2f\n",
                            playerAccount.getPlayerId(),
                            playerAccount.getPlayerBalance(),
                            winRate.doubleValue()
                    );
                }
            }
            writer.println(""); // inputs an empty line when finished
        } catch (IOException e) {
            e.printStackTrace(); //todo read up on what this does exactly
        }
        Config.displayRunStatus("\nActive player stats written to file: OK");
    }

    protected static void writeIllegalPlayers(List<IllegitimatePlayers> illegitimatePlayersList) {

        illegitimatePlayersList.sort(Comparator.comparing(IllegitimatePlayers::getPlayerId));

        try (PrintWriter writer = new PrintWriter(new FileWriter(Config.REPORT_FILE_PATH, true))) {
            for (IllegitimatePlayers illegitimatePlayer : illegitimatePlayersList) {
                writer.printf("%s %s %s %s %s\n",
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
        Config.displayRunStatus("\nList of Illegitimate players written to file: OK");
    }

    protected static void writeHostBalance(int hostBalance) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(Config.REPORT_FILE_PATH, true))) {
                writer.printf("%d \n", hostBalance);
                writer.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Config.displayRunStatus("\nBalance report written to file: OK");
    }



    public static void main(String[] args) {

        HostBalance hostBalance;

        List<PlayerData> playerDataTransactions = readInPlayerData(); // todo move  to Extract class
        List<MatchData> matchOutcomes = readInMatchData();             // todo move to  Extract class
        List<PlayerAccount> playerAccounts =
                PlayerAccount.initializeAccounts(playerDataTransactions);
        Transform transform = new Transform();

        hostBalance = transform.processPlayerData(playerDataTransactions, playerAccounts, matchOutcomes);
        List<IllegitimatePlayers> illegitimatePlayers = IllegitimatePlayers.getIllegitimatePlayersList();

        writeActivePlayerAccounts(playerAccounts);
        writeIllegalPlayers(illegitimatePlayers);
        writeHostBalance(hostBalance.getBalance());
    }
}
