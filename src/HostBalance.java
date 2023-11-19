public class HostBalance {

    /*
     host balance can be negative
     host balance start at 0
     any bet made by a player set to inactive does not change the balance
     */
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
