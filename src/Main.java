import java.io.*;
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

    // for the initial test I will ust the Player class to print a dummy load as a resu
    // todo if time allows remove the hardcode requirement
    protected static void writeResultsToFile(List<PlayerData> firstResults) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(Config.REPORT_FILE_PATH, true))) {
            for (PlayerData playerData : firstResults) {
                writer.printf("%s %s %s %s %s \n",
                        playerData.getPlayerId(),
                        playerData.getPlayerOperation(),
                        playerData.getMatchId(),
                        playerData.getTransactionAmount(),
                        playerData.getBetPlacement());
            }
            writer.println(""); // inputs an empty line when finished
        } catch (IOException e) {
            e.printStackTrace(); //todo read up on what this does exactly
        }
        Config.displayRunStatus("\n Player Data written to file: OK");
    }

    protected static void writeActivePlayerAccounts(List<PlayerAccount> playerAccountList) {

        playerAccountList.sort(Comparator.comparing(PlayerAccount::getPlayerId));

        try (PrintWriter writer = new PrintWriter(new FileWriter(Config.REPORT_FILE_PATH, true))) {
            for (PlayerAccount playerAccount : playerAccountList) {
                if (playerAccount.getIsActive()) {
                    writer.printf("%s %d %s\n",
                            playerAccount.getPlayerId(),
                            playerAccount.getPlayerBalance(),
                            playerAccount.getBetsPlaced()
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
            e.printStackTrace(); //todo read up on what this does exactly
        }
        Config.displayRunStatus("\nList of Illegitimate players written to file: OK");
    }


    public static void main(String[] args) {

        List<PlayerData> playerDataTransactions = readInPlayerData();
        List<MatchData> matchOutcomes = readInMatchData();
        List<PlayerAccount> playerAccounts =
                PlayerAccount.initializeAccounts(playerDataTransactions); // Initialize accounts read
        Transform transform = new Transform();
        transform.processPlayerData(playerDataTransactions, playerAccounts, matchOutcomes);
        // to test if  I have access to all data in the player_data.txt file
//        for (PlayerData playerData : playerDataTransactions) {
//            System.out.print(playerData.getPlayerId() + " ");
//            System.out.print(playerData.getPlayerOperation() + " ");
//            System.out.print(playerData.getMatchId() + " ");
//            System.out.print(playerData.getTransactionAmount() + " ");
//            System.out.println(playerData.getBetPlacement() + " ");
//        }
// to test if I have access to all data in the match_data.txt file
//        for (MatchData outcome : matchOutcomes) {
//            System.out.print(outcome.getMatchId()+ " ");
//            System.out.print(outcome.getRateOfReturnSideA()+ " ");
//            System.out.print(outcome.getRateOfReturnSideB()+ " ");
//            System.out.println(outcome.getMatchResult());
//        }
        List<IllegitimatePlayers> illegitimatePlayers = IllegitimatePlayers.getIllegitimatePlayersList();
//        for (IllegitimatePlayers illegitimatePlayer : illegitimatePlayers) {
//            System.out.print(illegitimatePlayer.getPlayerId() + " ");
//            System.out.print(illegitimatePlayer.getPlayerOperation() + " ");
//            System.out.print(illegitimatePlayer.getMatchId() + " ");
//            System.out.print(illegitimatePlayer.getTransactionSum() + " ");
//            System.out.print(illegitimatePlayer.getBetPlacement() + " ");
//        }
        System.out.println("");
//        writeResultsToFile(playerDataTransactions);
        writeActivePlayerAccounts(playerAccounts);
        writeIllegalPlayers(illegitimatePlayers);

    }
}
