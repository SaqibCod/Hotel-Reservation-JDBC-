import java.sql.*;
import java.util.Scanner;

import static java.lang.StringTemplate.STR;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/hotel_reservation";
    private static final String username = "root";
    private static final String password = "Saqib@12345";


    public static void viewReservation(Connection conn){

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery("SELECT * FROM reservation");
            while (rs.next()){
                int reserveID = rs.getInt("reservation_ID");
                String name = rs.getString("guest_Name");
                int roomNo = rs.getInt("room_No");
                String contactNo = rs.getString("contact_No");
//                int date = rs.getInt("reservation_Date");

                //Print
                System.out.println("================================");
                System.out.println("Reservation ID: "+ reserveID);
                System.out.println("Guest Name: "+ name);
                System.out.println("Room No: "+ roomNo);
                System.out.println("Contact No: "+ contactNo);
                System.out.println("================================");
                System.out.println();
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static void reserveRoom(Connection conn,Scanner sc){
        try {
            Statement stmt = conn.createStatement();

            System.out.println("Enter GuestName: ");
            String name = sc.next();
            System.out.println("Enter Room No: ");
            int roomNo  = sc.nextInt();
            System.out.println("Enter Contact No: ");
            String contNo = sc.next();


            int row  = stmt.executeUpdate(STR."INSERT INTO reservation(guest_Name, room_No, contact_No) VALUES ('\{name}',\{roomNo},'\{contNo}')");

            if (row > 0){
                System.out.println("Row affected: " + row);
            }
            else {
                System.out.println("Insertion Failed");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static void getRoom(Connection conn, Scanner sc){
        try {
            System.out.println("Enter Reservation ID: ");
            int reservation_id = sc.nextInt();
            Statement stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(STR."SELECT room_No FROM reservation WHERE reservation_ID = \{reservation_id}");

            while (rs.next()){

                int roomNo = rs.getInt("room_No");
                //Print
                System.out.println("================================");
                System.out.println("Room No: "+ roomNo);
                System.out.println("================================");
                System.out.println();
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateReserve(Connection conn, Scanner sc){
        try {

            Statement stmt = conn.createStatement();
            System.out.println("Enter reservation_ID(Old ID): ");
            int reserveID = sc.nextInt();
            System.out.println("Enter GuestName(New Name): ");
            String name = sc.next();
            System.out.println("Enter Room No(New Room): ");
            int roomNo  = sc.nextInt();
            System.out.println("Enter Contact No(New Contact): ");
            String contNo = sc.next();


            int row  = stmt.executeUpdate(STR."UPDATE reservation SET guest_Name = '\{name}', room_No = \{roomNo}, contact_No = '\{contNo}' WHERE reservation_ID = \{reserveID}");

            if (row > 0){
                System.out.println("Row affected: " + row);
            }
            else {
                System.out.println("Updation Failed");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteReservation(Connection conn, Scanner sc){
        try {

            Statement stmt = conn.createStatement();
            System.out.println("Enter reservation_ID: ");
            int reserveID = sc.nextInt();

            int row  = stmt.executeUpdate(STR."DELETE FROM reservation WHERE reservation_ID = \{reserveID}");

            if (row > 0){
                System.out.println("Row affected: " + row);
            }
            else {
                System.out.println("Deletion Failed");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 5;
        while (i-- > 0){
            System.out.print(".");
            Thread.sleep(400);
        }
        System.out.println();
        System.out.println("Thank You for Visiting");
    }

    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            while (true){
                System.out.println("------Hotel Reservation-------");

                Scanner sc = new Scanner(System.in);
                System.out.println("1. View Reservation");
                System.out.println("2. Reserve a Room");
                System.out.println("3. Update Reservation");
                System.out.println("4. Get Room Number");
                System.out.println("5. Delete Reservation");
                System.out.println("0. Exit");

                int choose = sc.nextInt();
                switch (choose){

                    case 1:
                        viewReservation(conn);
                        break;
                    case 2:
                        reserveRoom(conn, sc);
                        break;
                    case 3:
                        updateReserve(conn, sc);
                        break;
                    case 4:
                        getRoom(conn, sc);
                        break;
                    case 5:
                        deleteReservation(conn, sc);
                        break;
                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid Value Choose! Try again");
                }
            }

        } catch (SQLException | InterruptedException e) {
            System.out.println(e.getMessage());
        }





    }
}
