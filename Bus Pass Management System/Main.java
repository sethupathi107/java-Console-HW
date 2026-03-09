import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static Map<String, User> users = new HashMap<>();
    static List<Passenger> passengers = new ArrayList<>();
    static List<BusPass> passes = new ArrayList<>();

    static int passengerIdCounter = 100;
    static int passIdCounter = 500;

    public static void main(String[] args) {

        users.put("admin", new User("admin","admin123","ADMIN"));

        while(true){

            System.out.println("\n===== BUS PASS SYSTEM =====");
            System.out.println("1 Login");
            System.out.println("2 Register Passenger");
            System.out.println("3 Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    login();
                    break;

                case 2:
                    registerPassenger();
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
            passengerMenu(username);
    }

    static void registerPassenger(){

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Passenger p = new Passenger(passengerIdCounter++,username);

        passengers.add(p);
        users.put(username,new User(username,password,"PASSENGER"));

        System.out.println("Passenger registered successfully.");
    }

    // ADMIN MENU
    static void adminMenu(){

        while(true){

            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1 View Passengers");
            System.out.println("2 View All Bus Passes");
            System.out.println("3 Cancel Pass");
            System.out.println("4 Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    viewPassengers();
                    break;

                case 2:
                    viewPasses();
                    break;

                case 3:
                    cancelPass();
                    break;

                case 4:
                    return;
            }
        }
    }

    static void viewPassengers(){

        for(Passenger p : passengers){

            System.out.println("PassengerID:"+p.id+" Username:"+p.username);
        }
    }

    static void viewPasses(){

        for(BusPass bp : passes)
            System.out.println(bp);
    }

    static void cancelPass(){

        System.out.print("Enter Pass ID: ");
        int id = sc.nextInt();

        for(BusPass bp : passes){

            if(bp.passId == id){
                bp.status = "CANCELLED";
                System.out.println("Pass cancelled.");
                return;
            }
        }

        System.out.println("Pass not found.");
    }

    // PASSENGER MENU
    static void passengerMenu(String username){

        while(true){

            System.out.println("\n--- PASSENGER MENU ---");
            System.out.println("1 Apply Bus Pass");
            System.out.println("2 Renew Bus Pass");
            System.out.println("3 View My Passes");
            System.out.println("4 Cancel My Pass");
            System.out.println("5 Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    applyPass(username);
                    break;

                case 2:
                    renewPass(username);
                    break;

                case 3:
                    myPasses(username);
                    break;

                case 4:
                    cancelMyPass(username);
                    break;

                case 5:
                    return;
            }
        }
    }

    static boolean hasActivePass(String username){

        for(BusPass bp : passes){

            if(bp.passengerUsername.equals(username) && bp.status.equals("ACTIVE"))
                return true;
        }

        return false;
    }

    static void applyPass(String username){

        if(hasActivePass(username)){
            System.out.println("You already have an active pass.");
            return;
        }

        System.out.print("Enter Route: ");
        String route = sc.nextLine();

        System.out.println("Pass Type:");
        System.out.println("1 Student");
        System.out.println("2 Monthly");
        System.out.println("3 Senior Citizen");

        int typeChoice = sc.nextInt();
        sc.nextLine();

        String passType = "";

        if(typeChoice == 1) passType = "STUDENT";
        if(typeChoice == 2) passType = "MONTHLY";
        if(typeChoice == 3) passType = "SENIOR";

        System.out.print("Enter Issue Date: ");
        String issue = sc.nextLine();

        System.out.print("Enter Expiry Date: ");
        String expiry = sc.nextLine();

        BusPass bp = new BusPass(
                passIdCounter++,
                username,
                route,
                passType,
                issue,
                expiry,
                "ACTIVE"
        );

        passes.add(bp);

        System.out.println("Bus pass issued successfully.");
    }

    static void renewPass(String username){

        for(BusPass bp : passes){

            if(bp.passengerUsername.equals(username) && bp.status.equals("EXPIRED")){

                System.out.print("Enter new expiry date: ");
                String newExpiry = sc.nextLine();

                bp.expiryDate = newExpiry;
                bp.status = "ACTIVE";

                System.out.println("Pass renewed.");
                return;
            }
        }

        System.out.println("No expired pass found.");
    }

    static void myPasses(String username){

        for(BusPass bp : passes){

            if(bp.passengerUsername.equals(username))
                System.out.println(bp);
        }
    }

    static void cancelMyPass(String username){

        System.out.print("Enter Pass ID: ");
        int id = sc.nextInt();

        for(BusPass bp : passes){

            if(bp.passId == id && bp.passengerUsername.equals(username)){
                bp.status = "CANCELLED";
                System.out.println("Pass cancelled.");
                return;
            }
        }

        System.out.println("Pass not found.");
    }
}