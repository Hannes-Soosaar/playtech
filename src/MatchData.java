public class MatchData {
    private String matchId; //the match Id – A random UUID.
    private String rateOfReturnSideA;  // the return rate for A side.
    private String rateOfReturnSideB; // return rate for B side
    private String matchResult; // result of the match

    // constructor for the Match class
    public MatchData(String matchId, String rateOfReturnSideA, String rateOfReturnSideB, String matchResult) {
        this.matchId = matchId;
        this.rateOfReturnSideA = rateOfReturnSideA;
        this.rateOfReturnSideB = rateOfReturnSideB;
        this.matchResult = matchResult;
    }

    //getters
    protected String getMatchId() {
        return matchId;
    }

    protected String getRateOfReturnSideA() {
        return rateOfReturnSideA;
    }

    protected String getRateOfReturnSideB() {
        return rateOfReturnSideB;
    }

    protected String getMatchResult() {
        return matchResult;
    }




}
