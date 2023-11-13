import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {

        System.out.println("Hello world!");
        List<Player> playerTransactions = new ArrayList<>();
        List<Match> playedMatches = new ArrayList<>();
        String testString = Config.PLAYER_FILE_PATH;

        try (BufferedReader readInPlayerTransactions = new BufferedReader(new FileReader(Config.PLAYER_FILE_PATH))) {
            String transaction;  // creates a string
            while ((transaction = readInPlayerTransactions.readLine()) != null {
                String[] transactionDetails = transaction.split(",");
                Player transaction = new Player(transactionDetails[0],transactionDetails[1],transactionDetails[2],
                        transactionDetails[3],transactionDetails[4]);
                playerTransactions.add(transaction);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        // read files
        // modify data
        // write data

//In the results.txt file there are 3 expected result groups separated by empty line between each group. Results.txt file should be written into the same directory as your Main class
//
//        o	Example: 163f23ed-e9a9-4e54-a5b1-4e1fc86f12f4"   "4321"           "0,80
//        o	           player ID                          balance number, win rate are separated by space.
//          o	win rate is big decimal number with 2 decimal characters.
//        o	The list is ordered by player ID.   // Order by player ID
//        o	Player is legitimate if they did not attempt any illegal action.  // need to check for illegal actions

    }
}