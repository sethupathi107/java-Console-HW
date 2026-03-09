public class BusPass {

    int passId;
    String passengerUsername;
    String route;
    String passType;
    String issueDate;
    String expiryDate;
    String status;

    BusPass(int passId,String passengerUsername,String route,
            String passType,String issueDate,String expiryDate,String status){

        this.passId=passId;
        this.passengerUsername=passengerUsername;
        this.route=route;
        this.passType=passType;
        this.issueDate=issueDate;
        this.expiryDate=expiryDate;
        this.status=status;
    }

    public String toString(){

        return "PassID:"+passId+
                " Passenger:"+passengerUsername+
                " Route:"+route+
                " Type:"+passType+
                " Issue:"+issueDate+
                " Expiry:"+expiryDate+
                " Status:"+status;
    }
}