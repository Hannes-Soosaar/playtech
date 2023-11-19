public class HostBalance {

    private int hostBalance;

    public HostBalance() {
        this.hostBalance = hostBalance;
    }

    public int getBalance() {
        return hostBalance;
    }

    protected void setBalance(int addToBalance, boolean subtract) {
        if (subtract) {
            hostBalance -= addToBalance;
        } else {
            hostBalance += addToBalance;
        }
    }
}
