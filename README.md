# Hotel Reservation

This Java program provides a simple hotel reservation system with the following features:

Methods

1. viewReservation(Connection conn)
    - Displays all existing reservations in the database.
    - Prints reservation ID, guest name, room number, and contact number.
2. reserveRoom(Connection conn, Scanner sc)
    - Allows users to reserve a room by entering guest name, room number, and contact number.
    - Inserts a new reservation into the database.
3. getRoom(Connection conn, Scanner sc)
    - Retrieves the room number associated with a given reservation ID.
    - Prints the room number.
4. updateReserve(Connection conn, Scanner sc)
    - Updates an existing reservation with new guest name, room number, and contact number.
    - Requires the old reservation ID.
5. deleteReservation(Connection conn, Scanner sc)
    - Deletes a reservation by entering the reservation ID.

Usage

1. Establish a database connection using the Connection object.
2. Create a Scanner object to read user input.
3. Call the desired method, passing the Connection and Scanner objects as arguments.

Note

- These methods assume a database table named "reservation" with columns:
    - reservation_ID (int)
    - guest_Name (varchar)
    - room_No (int)
    - contact_No (varchar)
- The program uses JDBC to interact with the database.
- Error handling is implemented using try-catch blocks to catch SQLExceptions.