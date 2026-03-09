public class Booking {

    int id;
    String customerUsername;
    int vehicleId;
    int days;
    String status;

    Booking(int id,String customerUsername,int vehicleId,int days,String status){

        this.id=id;
        this.customerUsername=customerUsername;
        this.vehicleId=vehicleId;
        this.days=days;
        this.status=status;
    }

    public String toString(){

        return "BookingID:"+id+
                " Customer:"+customerUsername+
                " Vehicle:"+vehicleId+
                " Days:"+days+
                " Status:"+status;
    }
}