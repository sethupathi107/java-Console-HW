public class Appointment {

    int id;
    String patientUsername;
    int doctorId;
    String date;
    String time;
    String status;

    Appointment(int id,String patientUsername,int doctorId,String date,String time,String status){

        this.id=id;
        this.patientUsername=patientUsername;
        this.doctorId=doctorId;
        this.date=date;
        this.time=time;
        this.status=status;
    }

    public String toString(){

        return "AppointmentID:"+id+
                " Patient:"+patientUsername+
                " DoctorID:"+doctorId+
                " Date:"+date+
                " Time:"+time+
                " Status:"+status;
    }
}