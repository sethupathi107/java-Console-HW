import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static Map<String, User> users = new HashMap<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Vehicle> vehicles = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static List<Payment> payments = new ArrayList<>();

    static int vehicleIdCounter = 100;
    static int customerIdCounter = 200;
    static int bookingIdCounter = 300;
    static int paymentIdCounter = 400;

    public static void main(String[] args) {

        users.put("admin", new User("admin","admin123","ADMIN"));

        while(true){

            System.out.println("\n===== VEHICLE RENTAL SYSTEM =====");
            System.out.println("1 Login");
            System.out.println("2 Register Customer");
            System.out.println("3 Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    login();
                    break;

                case 2:
                    registerCustomer();
                    break;

                case 3:
                    return;
            }
        }
    }

    static void login(){

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = users.get(username);

        if(user == null || !user.password.equals(password)){
            System.out.println("Invalid credentials");
            return;
        }

        if(user.role.equals("ADMIN"))
            adminMenu();
        else
            customerMenu(username);
    }

    static void registerCustomer(){

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Customer c = new Customer(customerIdCounter++,username);

        customers.add(c);
        users.put(username,new User(username,password,"CUSTOMER"));

        System.out.println("Customer registered successfully.");
    }

    // ADMIN MENU
    static void adminMenu(){

        while(true){

            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1 Add Vehicle");
            System.out.println("2 View Vehicles");
            System.out.println("3 View Bookings");
            System.out.println("4 View Payments");
            System.out.println("5 Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    addVehicle();
                    break;

                case 2:
                    viewVehicles();
                    break;

                case 3:
                    viewBookings();
                    break;

                case 4:
                    viewPayments();
                    break;

                case 5:
                    return;
            }
        }
    }

    static void addVehicle(){

        System.out.print("Vehicle Name: ");
        String name = sc.nextLine();

        System.out.print("Type (Car/Bike): ");
        String type = sc.nextLine();

        System.out.print("Price Per Day: ");
        double price = sc.nextDouble();
        sc.nextLine();

        Vehicle v = new Vehicle(vehicleIdCounter++,name,type,price,true);
        vehicles.add(v);

        System.out.println("Vehicle added successfully.");
    }

    static void viewVehicles(){

        for(Vehicle v : vehicles){

            System.out.println("ID:"+v.id+" Name:"+v.name+
                    " Type:"+v.type+
                    " Price:"+v.pricePerDay+
                    " Available:"+v.available);
        }
    }

    static void viewBookings(){

        for(Booking b:bookings)
            System.out.println(b);
    }

    static void viewPayments(){

        for(Payment p:payments)
            System.out.println(p);
    }

    // CUSTOMER MENU
    static void customerMenu(String username){

        while(true){

            System.out.println("\n--- CUSTOMER MENU ---");
            System.out.println("1 View Vehicles");
            System.out.println("2 Search Available Vehicles");
            System.out.println("3 Book Vehicle");
            System.out.println("4 View My Bookings");
            System.out.println("5 Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    viewVehicles();
                    break;

                case 2:
                    searchAvailable();
                    break;

                case 3:
                    bookVehicle(username);
                    break;

                case 4:
                    myBookings(username);
                    break;

                case 5:
                    return;
            }
        }
    }

    static void searchAvailable(){

        for(Vehicle v:vehicles)
            if(v.available)
                System.out.println(v.id+" "+v.name+" "+v.type+" "+v.pricePerDay);
    }

    static void bookVehicle(String username){

        searchAvailable();

        System.out.print("Enter Vehicle ID: ");
        int id = sc.nextInt();

        System.out.print("Rental Days: ");
        int days = sc.nextInt();
        sc.nextLine();

        Vehicle vehicle = null;

        for(Vehicle v:vehicles)
            if(v.id == id)
                vehicle = v;

        if(vehicle == null || !vehicle.available){
            System.out.println("Vehicle not available");
            return;
        }

        double total = vehicle.pricePerDay * days;

        Booking booking = new Booking(
                bookingIdCounter++,
                username,
                id,
                days,
                "ACTIVE"
        );

        bookings.add(booking);
        vehicle.available = false;

        Payment payment = new Payment(
                paymentIdCounter++,
                booking.id,
                total
        );

        payments.add(payment);

        System.out.println("Booking successful. Payment:"+total);
    }

    static void myBookings(String username){

        for(Booking b:bookings)
            if(b.customerUsername.equals(username))
                System.out.println(b);
    }
}