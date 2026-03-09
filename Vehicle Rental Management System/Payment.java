public class Payment {

    int id;
    int bookingId;
    double amount;

    Payment(int id,int bookingId,double amount){

        this.id=id;
        this.bookingId=bookingId;
        this.amount=amount;
    }

    public String toString(){

        return "PaymentID:"+id+
                " Booking:"+bookingId+
                " Amount:"+amount;
    }
}