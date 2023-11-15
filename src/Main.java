import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Main {
    // used static as there will only be one instance of reading in the data per program cycle
    protected static List<Player> readInPlayerData() {

        List<Player> playerTransactions = new ArrayList<>();
        try (BufferedReader readInPlayerTransactions = new BufferedReader(new FileReader(Config.PLAYER_FILE_PATH))) {
            String transactionBuffer;
            while ((transactionBuffer = readInPlayerTransactions.readLine()) != null) {
                String[] transactionDetails = transactionBuffer.split(",");

                Player transaction;
                if (transactionDetails.length == 5) {
                    transaction = new Player(
                            transactionDetails[Config.PLAYER_ID],
                            transactionDetails[Config.PLAYER_OPERATION],
                            transactionDetails[Config.PLAYER_MATCH_ID],
                            transactionDetails[Config.PLAYER_TRANSACTION_SUM],
                            transactionDetails[Config.PLAYER_BET_PLACED]
                    );
                } else {
                    transaction = new Player(
                            transactionDetails[Config.PLAYER_ID],
                            transactionDetails[Config.PLAYER_OPERATION],
                            transactionDetails[Config.PLAYER_MATCH_ID],
                            transactionDetails[Config.PLAYER_TRANSACTION_SUM],
                            ""               // an empty string will be returned if no bet was placed
                    );
                }
                playerTransactions.add(transaction);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return playerTransactions;
    }
    protected static List<Match> readInMatchData() {
        List<Match> matchOutCome = new ArrayList<>();
        try (BufferedReader readInMatchOutComes = new BufferedReader(new FileReader(Config.MATCH_FILE_PATH))) {
            String matchOutComesBuffer;
            while ((matchOutComesBuffer = readInMatchOutComes.readLine()) != null) {
                String[] matchDetails = matchOutComesBuffer.split(",");
                Match outcome;
                outcome = new Match(
                        matchDetails[Config.MATCH_ID],
                        matchDetails[Config.MATCH_RETURN_ON_A],
                        matchDetails[Config.MATCH_RETURN_ON_B],
                        matchDetails[Config.MATCH_RESULT]
                );
                matchOutCome.add(outcome);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return matchOutCome;
    }
// for the initial test I will ust the Player class to print a dummy load as a resu
    // todo if time allowes remove the hardcode requirement
    protected static void writeResultsToFile(List<Player> firstResults) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(Config.REPORT_FILE_PATH,true))) {
            for (Player player : firstResults) {
                writer.printf("%s %s %s %s %s \n",
                        player.getPlayerId(),
                        player.getPlayerOperation(),
                        player.getMatchId(),
                        player.getTransactionSum(),
                        player.getBetPlacement());
            }
            writer.println("EMPTY LINE HERE NOTHING TO SEE"); // inputs an empty line when finished
        }catch (IOException e) {
            e.printStackTrace(); //todo read up on what this does exactly
        }


    }

    public static void main(String[] args) {

        List<Player> playerTransactions = readInPlayerData();
        List<Match> matchesOutcome = readInMatchData();

        // to test if  I have access to all data in the player_data.txt file
        for (Player player : playerTransactions) {
            System.out.print(player.getPlayerId() + " ");
            System.out.print(player.getPlayerOperation() + " ");
            System.out.print(player.getMatchId() + " ");
            System.out.print(player.getTransactionSum() + " ");
            System.out.println(player.getBetPlacement() + " ");
        }
// to test if I have access to all data in the match_data.txt file
        for (Match outcome : matchesOutcome) {
            System.out.print(outcome.getMatchId()+ " ");
            System.out.print(outcome.getRateOfReturnSideA()+ " ");
            System.out.print(outcome.getRateOfReturnSideB()+ " ");
            System.out.println(outcome.getMatchResult());

        }
        writeResultsToFile(playerTransactions);
    }
}
