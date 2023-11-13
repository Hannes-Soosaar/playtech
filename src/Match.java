public class Match {
    private String matchId; //the match Id – A random UUID.
    private String rateOfReturnSideA;  // the return rate for A side.
    private String rateOfReturnSideB; // return rate for B side
    private String matchResult; // result of the match

    // constructor for the Match class
    public Match(String matchId, String rateOfReturnSideA, String rateOfReturnSideB, String matchResult) {
        this.matchId = matchId;
        this.rateOfReturnSideA = rateOfReturnSideA;
        this.rateOfReturnSideB = rateOfReturnSideB;
        this.matchResult = matchResult;
    }

}
