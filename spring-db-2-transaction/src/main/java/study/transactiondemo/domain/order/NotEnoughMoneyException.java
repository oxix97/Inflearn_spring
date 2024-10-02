package study.transactiondemo.domain.order;

public class NotEnoughMoneyException extends Exception{

    // MEMO : 잔고가 부족한 경우에 대한 예외
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
