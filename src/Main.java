
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
     private static void readInPlayerData(){
         List<Player> playerTransactions = new ArrayList<>();
         try (BufferedReader readInPlayerTransactions = new BufferedReader(new FileReader(Config.PLAYER_FILE_PATH))) {
             String transactionBuffer;
             while ((transactionBuffer = readInPlayerTransactions.readLine()) != null) {
                 String[] transactionDetails = transactionBuffer.split(",");

                 Player transaction;
                 if(transactionDetails.length == 5){
                     transaction = new Player(
                             transactionDetails[Config.PLAYER_ID],
                             transactionDetails[Config.PLAYER_OPERATION],
                             transactionDetails[Config.PLAYER_MATCH_ID],
                             transactionDetails[Config.PLAYER_TRANSACTION_SUM],
                             transactionDetails[Config.PLAYER_BET_PLACED]
                     );
                 }else{
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

    }
    public static void main(String[] args) {
         readInPlayerData(); // todo add a return for the function


    List<Match> playedMatches = new ArrayList<>();
    }
}
