import java.io.*;                               // Importing FileWriter and IOException
import java.util.*;                             // Importing Scanner object

public class w2053224_PlaneManagement {         // Task 1: Create a class file named project title

    public static final int ROW_A =14;          // Defines the number of seats for Row A
    public static final int ROW_B =12;          // Defines the number of seats for Row B
    public static final int ROW_C =12;          // Defines the number of seats for Row C
    public static final int ROW_D =14;          // Defines the number of seats for Row D
    public static int  [][]seat = {             // The Array called seat representing the seats in every row (A/B/C/D)
            new int  [ROW_A],
            new int  [ROW_B],
            new int  [ROW_C],
            new int  [ROW_D]

    };

    public static Ticket[] soldTickets = new Ticket[ROW_A + ROW_B + ROW_C + ROW_D];      // Creating an array for maximus seat size
    public static int ticketCount = 0;                      //Tracking how many tickets sold

    public static boolean ValidSeat(int Row_No,int seatNum, int [][]seat){              // Tracks the seat what the user want is a valid seat
        return Row_No >= 0 && Row_No < seat.length && seatNum >= 0 && seatNum < seat[0].length && seat[Row_No][seatNum] == 0;
    }
    public static void MainMenu() {                                         //Task 2: Display the menu to enter user choice
        System.out.println("\n******************************************************");
        System.out.println("* \t\t     MENU OPTION\t\t     *");
        System.out.println("******************************************************");
        System.out.println("        1) Buy a seat");
        System.out.println("        2) Cancel a seat");
        System.out.println("        3) Find first available seat");
        System.out.println("        4) Show seating plan");
        System.out.println("        5) Print tickets information and total sales");
        System.out.println("        6) Search ticket");
        System.out.println("        0) Quit");
        System.out.println("******************************************************");
    }

    public static void buy_seat(Scanner SC_Obj) {               // Task 3: Buying a Seat
        while (true){
            System.out.print("Please enter your row letter only from (A/B/C/D) : ");
            char Row_Letter = Character.toUpperCase(SC_Obj.next().charAt(0));              // Getting the user input for Row Letter

            if (Row_Letter != 'A' && Row_Letter != 'B' && Row_Letter != 'C' && Row_Letter != 'D') {
                System.out.println("Invalid input for Row Letter");               // Re-enter the valid Row Letter when it is invalid
                continue;
            }

            System.out.print("Please enter your seat number (1-14) : ");
            int seatNum = SC_Obj.nextInt();                           // Getting the user input for Seat Number

            if (seatNum < 1 || seatNum > 14) {
                System.out.println("Invalid input for Seat Number");        // Re-enter the valid Seat Number when it is invalid
                continue;
            }

            int Row_No = Row_Letter - 'A';                  // Converts the Row Letter to array index
            seatNum --;                                   // Converts the Seat Number to array index
            int maxSeatNo_B_C = 12;

            if(Row_No < 0 || Row_No >= seat.length || seatNum < 0 || seatNum >= seat[Row_No].length) {     // Checking the Seat Number and Row Letters are valid or not
                System.out.println("Invalid Row or a Seat Number!!");
            }
            else if((Row_No == 1 || Row_No == 2) && seatNum >= maxSeatNo_B_C){
                System.out.println("Invalid Seat Number for Row " + Row_No);                // Checking the user inputs 13 or 14 or more for the Row B and Row C
            }
            else if (seat[Row_No][seatNum] == 1 ){              // Checking the seat is booked or not
                System.out.println("Seat already taken.");
            }
            else {
                seat[Row_No][seatNum] = 1;
                System.out.println("Seat purchased successfully!!");        // Displays the seat purchased

                System.out.print("Enter you Name : ");                  // Gathers the Personal information to book the ticket
                String fName = SC_Obj.next();
                System.out.print("Enter you Surname : ");
                String lName = SC_Obj.next();
                System.out.print("Enter you Email : ");
                String email = SC_Obj.next();
                System.out.println("Your ticket booking has been booked successfully!!");

                Person person = new Person(fName, lName, email);            // Creating the new person object from given information

                int price = 0;
                if (Row_No == 0 || Row_No == 3) {                       // Checking the price of the ticket
                    if (seatNum >= 0 && seatNum <= 4) {
                        price = 200;
                    } else if (seatNum >= 5 && seatNum <= 8) {
                        price = 150;
                    } else if (seatNum >= 9 && seatNum <= 13) {
                        price = 180;
                    } else {
                        price = 0;                               // Reset price if seat num is out of range
                    }
                } else if (Row_No == 1 || Row_No == 2) {
                    if (seatNum >= 0 && seatNum <= 4) {
                        price = 200;
                    } else if (seatNum >= 5 && seatNum <= 8) {
                        price = 150;
                    } else if (seatNum >= 9 && seatNum <= 11) {
                        price = 180;
                    } else if (seatNum >= 12 && seatNum <= 13) {
                        price = 0;                              // Reset price if seat number is out of range
                    } else {
                        price = 0;                              // Reset price if seat num is out of range
                    }
                }

                Ticket ticket = new Ticket(Row_Letter, seatNum + 1, price, person);         // Crating the new ticket object from seat information
                soldTickets[ticketCount++] = ticket;                            // Add a purchased ticket to an array of sold ticket
                ticket.save();                                      // Save the information for the file
                break;
            }
        }
    }

    public static void cancel_ticket(Scanner SC_Obj) {             // Task 4: Cancel a ticket
        while (true) {
            System.out.print("Please enter your row letter only from (A/B/C/D) : ");
            char Row_Letter = Character.toUpperCase(SC_Obj.next().charAt(0));           // Getting the user input for Row Letter

            if (Row_Letter != 'A' && Row_Letter != 'B' && Row_Letter != 'C' && Row_Letter != 'D') {
                System.out.println("Invalid input for Row Letter");                   // Re-enter the valid Row Letter when it is invalid
                return;
            }

            System.out.print("Please enter your seat number (1-14) : ");
            int seatNum = SC_Obj.nextInt();                     // Getting the user input for Seat Number

            if (seatNum < 1 || seatNum > 14) {
                System.out.println("Invalid input for Seat Number");            // Re-enter the valid Seat Number when it is invalid
                return;
            }

            int Row_No = Row_Letter - 'A';                  // Converts the Row Letter to array index
            seatNum--;                                  // Converts the Seat Number to array index
            int maxSeatNo_B_C = 12;

            if (Row_No < 0 || Row_No >= seat.length || seatNum < 0 || seatNum >= seat[Row_No].length) {
                if (Row_No == 1 || Row_No == 2) {
                    if (seatNum >= maxSeatNo_B_C) {
                        System.out.println("Invalid Seat Number for Row " + Row_No);
                        return;
                    }
                } else {
                    System.out.println("Invalid Row or a Seat Number!!");
                    return;
                }
            }
            if (seat[Row_No][seatNum] == 0) {                       // Checking the seat is available or not
                System.out.println("Seat already available");
                return;
            } else {
                boolean ticketFound = false;
                for (int i = 0; i <= ticketCount; i++) {             //  If the current ticket matches the given row letter and seat number
                    if (soldTickets[i] != null && soldTickets[i].Seat_Letter() == Row_Letter && soldTickets[i].Seat_Number() == seatNum + 1) {
                        for (int j = i; j < ticketCount - 1; j++) {
                            soldTickets[j] = soldTickets[j + 1];
                        }
                        soldTickets[ticketCount - 1] = null;
                        ticketCount--;                          // Reduces a ticket from ticket count
                        seat[Row_No][seatNum] = 0;              // Marks that seat is now available
                        ticketFound = true;
                        System.out.println("Seat cancellation successfully!!");       // Displays the ticket is successfully canceled

                        String fileName = Row_Letter + String.valueOf(seatNum + 1) +".txt";    // Identify the file name
                        File file = new File(fileName);       // Creates a file object
                        if (file.delete()) {                       // Checks the file can delete or not
                            System.out.println(fileName+ ": file deleted successfully!!");    // Displays the file is deleted
                        }
                        else{
                            System.out.println("Failed to delete the file!!");         // Displays failed to delete the file
                        }
                        break;
                    }
                }
                if (!ticketFound) {
                    System.out.println("Ticket not found for cancellation");         // Displays when the ticket is not found
                    break;
                }
            }
        break;
        }

    }

    public static void first_find_available() {            // Task 5: Finds the first available seat
        int maxSeatNo = 0;
        for(int Row_No = 0; Row_No < seat.length; Row_No++) {       // Checking maximum seats for each row
            if(Row_No == 0) {
                maxSeatNo = ROW_A;
            } else if (Row_No == 1) {
                maxSeatNo = ROW_B;
            } else if (Row_No == 3) {
                maxSeatNo = ROW_C;
            } else {
                maxSeatNo = ROW_D;
            }

            for(int Seat = 0; Seat < maxSeatNo ; Seat++) {             // Iterate through each seat from the current row
                if (seat[Row_No][Seat] == 0) {                          // Checks if the seat is available or not
                    char Row_Letter = (char) ('A' + Row_No);
                    int Seat_Num = Seat + 1;
                    System.out.println("First available seat: Row "+Row_Letter+ " Seat Number: "+Seat_Num);         // Displays the first available seat
                    return;
                }

            }
        }
        System.out.println("No available seats.");              // Displays no available seat are there
    }

    public static void show_seating_plan() {                      // Task 6: Showing Seating Plan
        System.out.println("\n******************************************************");
        System.out.println("* \t          ### Seating Plan ###\t             *");
        System.out.println("******************************************************\n");

        for (int Row_No = 0; Row_No < seat.length; Row_No ++){
            int maxSeatNo = 0;
            if(Row_No == 0){                              // Checking the maximus seat numbers for each rows
                maxSeatNo = ROW_A;
            } else if (Row_No == 1) {
                maxSeatNo = ROW_B;
            } else if (Row_No == 2) {
                maxSeatNo = ROW_C;
            }else {
                maxSeatNo = ROW_D;
            }

            for (int Seat = 0; Seat < maxSeatNo; Seat++){               // Iterate through each seat from the current row
                if (seat[Row_No][Seat] == 1){                   // Checks if the seat marked as 1
                    System.out.print("X ");                     // Displays 'X' to represents that seat is sold
                }else {
                    System.out.print("O ");                     // Displays 'O' to represents that seat is still available
                }
            }
            System.out.println();
        }
        System.out.println("\n******************************************************");
    }

    public static void print_ticket_info(){                 // Task 10: Printing ticket information
        int Total_Sales_Amount = 0;
        System.out.println("\n******************************************************");
        System.out.println("* \t     ### Tickets Information ###\t     *");
        System.out.println("******************************************************\n");

        for (int i = 0; i < ticketCount; i++){              // Iterate through each sold ticket
            Ticket ticket = soldTickets[i];                 //  Takes the ticket at index i from the soldTickets array
            if(ticket != null){                              // Check if the ticket exists
                System.out.println("Ticket" + (i+1) + ":");
                ticket.printTicketInfo();                       // Print ticket information from the method
                Total_Sales_Amount += ticket.Ticket_Price();       // Add the ticket price to the total sales amount
                System.out.println("******************************************************");
            }
        }
        System.out.println("\nTotal Amount : £" + Total_Sales_Amount);        // Displays the total sales amount
        System.out.println("******************************************************");
    }

    public static void search_ticket(Scanner SC_Obj){               // Task 11: Search a ticket
        while (true) {
            System.out.print("Please enter your row letter only from (A/B/C/D) : ");
            char Row_Letter = Character.toUpperCase(SC_Obj.next().charAt(0));               // Getting the user input for Row Letter

            if (Row_Letter != 'A' && Row_Letter != 'B' && Row_Letter != 'C' && Row_Letter != 'D') {
                System.out.println("Invalid input for Row Letter");                         // Re-enter the valid Row Letter when it is invalid
                continue;
            }

            System.out.print("Please enter your seat number (1-14) : ");
            int seatNum = SC_Obj.nextInt();                             // Getting the user input for Seat Number

            if (seatNum < 1 || seatNum > 14) {
                System.out.println("Invalid input for Seat Number");            // Re-enter the valid Seat Number when it is invalid
                continue;
            }

            int Row_No = Row_Letter - 'A';                 // Converts the Row Letter to array index
            seatNum--;                                   // Converts the Seat Number to array index

            if (Row_No < 0 || Row_No >= seat.length || seatNum < 0 || seatNum >= seat[Row_No].length) {         // Checking the Seat Number and Row Letters are valid or not
                System.out.println("Invalid Row or a Seat Number!!");
                return;
            }

            boolean found = false;
            for (int i = 0; i < ticketCount; i++) {               // Iterate through sold ticket
                Ticket ticket = soldTickets[i];                   //  Takes the ticket at index i from the soldTickets array
                if (ticket != null && ticket.Seat_Letter() == Row_Letter && ticket.Seat_Number() == seatNum + 1) {          // Checks the seat letter matches to row letter and seat number matches to the seat number
                    System.out.println("Ticket Found!!");      // Displays the ticket is found
                    ticket.printTicketInfo();                   // Print ticket information
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("This seat is available.");           // Displays the seat is available
            }
        break;
        }
    }


    public static void main(String[] args) {
        System.out.println("\n      Welcome to the Plane Management application");            //Printing the Welcome to the Plane Management application
        Scanner SC_Obj = new Scanner(System.in);                    //Inputs the user's choice from the menu
        int option = -1;

        while (option != 0) {                      // Run until user input 0 to end the program
            MainMenu();                             // Displays the Main Menu to select an option
            System.out.print("Please select an option: ");

            try{
                option = SC_Obj.nextInt();
                switch (option) {
                    case 1:
                        buy_seat(SC_Obj);              //Displays the Buy Seat option
                        break;
                    case 2:
                        cancel_ticket(SC_Obj);          //Displays the Cancel Seat option
                        break;
                    case 3:
                        first_find_available();              //Displays the First Seat option
                        break;
                    case 4:
                        show_seating_plan();                 //Displays the Seating Plan option
                        break;
                    case 5:
                        print_ticket_info();                    //Displays the Ticket Information option
                        break;
                    case 6:
                        search_ticket(SC_Obj);                  //Displays the Search Ticket option
                        break;
                    case 0:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid option. Please select a valid option!!");
                SC_Obj.next();                      // Reenter the valid option when the user input is invalid
            }

        }

    }

}
