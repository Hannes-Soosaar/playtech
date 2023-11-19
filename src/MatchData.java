public class MatchData {
    private String matchId; 
    private String rateOfReturnSideA;  
    private String rateOfReturnSideB; 
    private String matchResult; 

    public MatchData(String matchId, String rateOfReturnSideA, String rateOfReturnSideB, String matchResult) {
        this.matchId = matchId;
        this.rateOfReturnSideA = rateOfReturnSideA;
        this.rateOfReturnSideB = rateOfReturnSideB;
        this.matchResult = matchResult;
    }

    protected String getMatchId() {
        return matchId;
    }

    protected double getRateOfReturnSideA() {
        return Double.parseDouble(rateOfReturnSideA);
    }

    protected double getRateOfReturnSideB() {
        return Double.parseDouble(rateOfReturnSideB);
    }

    protected String getMatchResult() {
        return matchResult;
    }
}
