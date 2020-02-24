package S1101_S1200;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * https://leetcode.com/problems/invalid-transactions/
 * Create by haifei on 24/2/2020 3:38 PM.
 */
public class S1169_InvalidTransactions {

    private static final int AMOUNT_EXCEED_LIMIT = 1000;

    /**
     * Class Represents the Transaction
     */
    public class Transaction {
        private String name;
        private Long time;
        private Long amount;
        private String city;

        public Transaction(String name, long time, long amount, String city) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
        }

        @Override
        public String toString() {
            return name + "," + time + "," + amount + "," + city;
        }

        public boolean isSameTransaction(List<Transaction> transactions) {
            for(Transaction transaction : transactions){
                if(!transaction.equals(this)
                           && this.name.equals(transaction.name)
                           && ((Math.abs(this.time - transaction.time)) <= 60)
                           && (!this.city.equals(transaction.city))) {
                    return true;
                }
            }

            return false;
        }
    }

    public List<String> invalidTransactions(String[] transactions) {
        return findInvalid(generateTransactionsList(transactions));
    }

    /**
     * Find the invalid transactions for both amount exceeded and different
     * city less than 60 minutes.
     */
    private List<String> findInvalid(List<Transaction> transactions) {
        List<Transaction> invalidTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.isSameTransaction(transactions)) {
                invalidTransactions.add(transaction);
            } else if (transaction.amount > AMOUNT_EXCEED_LIMIT) {
                invalidTransactions.add(transaction);
            }
        }

        return invalidTransactions.stream()
                       .map(Transaction::toString)
                       .collect(Collectors.toList());
    }

    /**
     * Create our Transactions based on the array of strings passed in.
     */
    private List<Transaction> generateTransactionsList(String[] transactions) {
        List<Transaction> transactionList = new ArrayList<>();

        for (String tx : transactions) {
            String[] splitTx = tx.split(",");

            String name = splitTx[0];
            long time = Long.parseLong(splitTx[1]);
            long amount = Long.parseLong(splitTx[2]);
            String city = splitTx[3];

            transactionList.add(new Transaction(name, time, amount, city));
        }

        return transactionList;
    }

    @Test
    public void test2() {
        String[] arr = {"alice,20,800,mtv","bob,50,1200,mtv"};

        List<String> result = invalidTransactions(arr);

        System.out.println(result);
    }

    @Test
    public void test1() {
        String[] arr = {"alice,20,800,mtv","alice,50,1200,mtv"};

        List<String> result = invalidTransactions(arr);

        System.out.println(result);
    }

    @Test
    public void test() {
        String[] arr = {"alice,20,800,mtv","alice,50,100,beijing"};

        List<String> result = invalidTransactions(arr);

        System.out.println(result);
    }

}
