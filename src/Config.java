public class Config {
    // Toggle on-off the status log on the console
    public static boolean DISPLAY_RUN_STATUS_IN_CONSOLE = false;
    // The path for the files used, if the files need to be placed elsewhere the path should be changed to match
    public static final String PLAYER_FILE_PATH = "./src/player_data.txt";
    public static final String MATCH_FILE_PATH = "./src/match_data.txt";
    public static final String REPORT_FILE_PATH = "./src/report.txt";
    // columns in the csv file for player_data.txt
    public static final int PLAYER_ID = 0;
    public static final int PLAYER_OPERATION = 1;
    public static final int PLAYER_MATCH_ID = 2;
    public static final int PLAYER_TRANSACTION_SUM = 3;
    public static final int PLAYER_BET_PLACED = 4;
    // columns in the csv file for match_data.txt
    public static final int MATCH_ID = 0;
    public static final int MATCH_RETURN_ON_A = 1;
    public static final int MATCH_RETURN_ON_B = 2;
    public static final int MATCH_RESULT = 3;
    protected  static void  displayRunStatus(String messageToLog) {
        if (Config.DISPLAY_RUN_STATUS_IN_CONSOLE) {
            System.out.println(messageToLog);
        }
    }
}
