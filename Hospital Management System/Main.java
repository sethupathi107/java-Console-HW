import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static Map<String, User> users = new HashMap<>();
    static List<Doctor> doctors = new ArrayList<>();
    static List<Patient> patients = new ArrayList<>();
    static List<Appointment> appointments = new ArrayList<>();

    static int doctorIdCounter = 100;
    static int patientIdCounter = 200;
    static int appointmentIdCounter = 300;

    public static void main(String[] args) {

        // default admin
        users.put("admin", new User("admin","admin123","ADMIN"));

        while(true){

            System.out.println("\n===== HOSPITAL SYSTEM =====");
            System.out.println("1. Login");
            System.out.println("2. Register Patient");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    login();
                    break;

                case 2:
                    registerPatient();
                    break;

                case 3:
                    System.out.println("Goodbye");
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

        switch(user.role){

            case "ADMIN":
                adminMenu();
                break;

            case "DOCTOR":
                doctorMenu(username);
                break;

            case "PATIENT":
                patientMenu(username);
                break;
        }
    }

    static void registerPatient(){

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        Patient p = new Patient(patientIdCounter++,username,password);

        users.put(username,new User(username,password,"PATIENT"));
        patients.add(p);

        System.out.println("Patient registered successfully.");
    }

    // ADMIN MENU
    static void adminMenu(){

        while(true){

            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1 Add Doctor");
            System.out.println("2 View Doctors");
            System.out.println("3 View Patients");
            System.out.println("4 View Appointments");
            System.out.println("5 Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    addDoctor();
                    break;

                case 2:
                    viewDoctors();
                    break;

                case 3:
                    viewPatients();
                    break;

                case 4:
                    viewAppointments();
                    break;

                case 5:
                    return;
            }
        }
    }

    static void addDoctor(){

        System.out.print("Doctor Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Specialization: ");
        String spec = sc.nextLine();

        Doctor d = new Doctor(doctorIdCounter++,name,spec,username);

        users.put(username,new User(username,password,"DOCTOR"));
        doctors.add(d);

        System.out.println("Doctor added successfully.");
    }

    static void viewDoctors(){

        System.out.println("\n--- Doctors ---");

        for(Doctor d : doctors){

            System.out.println("ID:"+d.id+" Name:"+d.name+" Specialization:"+d.specialization);
        }
    }

    static void viewPatients(){

        for(Patient p:patients){

            System.out.println("ID:"+p.id+" Username:"+p.username);
        }
    }

    static void viewAppointments(){

        for(Appointment a:appointments){

            System.out.println(a);
        }
    }

    // PATIENT MENU
    static void patientMenu(String username){

        while(true){

            System.out.println("\n--- PATIENT MENU ---");
            System.out.println("1 View Doctors");
            System.out.println("2 Book Appointment");
            System.out.println("3 View My Appointments");
            System.out.println("4 Cancel Appointment");
            System.out.println("5 Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    viewDoctors();
                    break;

                case 2:
                    bookAppointment(username);
                    break;

                case 3:
                    viewMyAppointments(username);
                    break;

                case 4:
                    cancelAppointment(username);
                    break;

                case 5:
                    return;
            }
        }
    }

    static void bookAppointment(String username){

        viewDoctors();

        System.out.print("Enter Doctor ID: ");
        int doctorId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Date: ");
        String date = sc.nextLine();

        System.out.print("Enter Time: ");
        String time = sc.nextLine();

        for(Appointment a:appointments){

            if(a.doctorId == doctorId && a.date.equals(date) && a.time.equals(time)){
                System.out.println("Slot already booked.");
                return;
            }
        }

        Appointment a = new Appointment(
                appointmentIdCounter++,
                username,
                doctorId,
                date,
                time,
                "BOOKED"
        );

        appointments.add(a);

        System.out.println("Appointment booked successfully.");
    }

    static void viewMyAppointments(String username){

        for(Appointment a:appointments){

            if(a.patientUsername.equals(username))
                System.out.println(a);
        }
    }

    static void cancelAppointment(String username){

        viewMyAppointments(username);

        System.out.print("Enter Appointment ID: ");
        int id = sc.nextInt();

        for(Appointment a:appointments){

            if(a.id == id && a.patientUsername.equals(username)){

                a.status = "CANCELLED";
                System.out.println("Appointment cancelled.");
                return;
            }
        }

        System.out.println("Appointment not found.");
    }

    // DOCTOR MENU
    static void doctorMenu(String username){

        while(true){

            System.out.println("\n--- DOCTOR MENU ---");
            System.out.println("1 View My Appointments");
            System.out.println("2 Complete Appointment");
            System.out.println("3 Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:
                    doctorAppointments(username);
                    break;

                case 2:
                    completeAppointment(username);
                    break;

                case 3:
                    return;
            }
        }
    }

    static void doctorAppointments(String username){

        Doctor doctor = null;

        for(Doctor d:doctors)
            if(d.username.equals(username))
                doctor = d;

        for(Appointment a:appointments){

            if(a.doctorId == doctor.id)
                System.out.println(a);
        }
    }

    static void completeAppointment(String username){

        System.out.print("Enter Appointment ID: ");
        int id = sc.nextInt();

        for(Appointment a:appointments){

            if(a.id == id){
                a.status = "COMPLETED";
                System.out.println("Appointment completed.");
                return;
            }
        }
    }
}