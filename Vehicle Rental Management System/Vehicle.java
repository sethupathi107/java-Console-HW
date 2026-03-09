public class Vehicle {

    int id;
    String name;
    String type;
    double pricePerDay;
    boolean available;

    Vehicle(int id,String name,String type,double pricePerDay,boolean available){

        this.id=id;
        this.name=name;
        this.type=type;
        this.pricePerDay=pricePerDay;
        this.available=available;
    }
}