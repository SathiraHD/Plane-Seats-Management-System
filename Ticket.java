import java.io.FileWriter;              // Importing FileWriter
import java.io.IOException;             // Importing IOException

public class Ticket {                // Task 8: Create Ticket class
    private char Row_Letter;
    private int seatNum;
    private int price;
    private Person person;

    public Ticket(char Row, int Seat, int price, Person person ){          // Assigns variables to collect data
        this.Row_Letter = Row;
        this.seatNum = Seat;
        this.price = price;
        this.person = person;
    }

    public char Seat_Letter(){
        return Row_Letter;
    }

    public int Seat_Number(){
        return seatNum;
    }

    public int Ticket_Price(){
        return price;
    }

    public Person person(){
        return person;
    }

    public void printTicketInfo() {                         // Printing ticket and person information
        System.out.println("Ticket Information : ");
        System.out.println("Row : " + Row_Letter);
        System.out.println("Seat Number : " + seatNum);
        System.out.println("Price : £" + price);
        person.printInfo();
    }

    public void save(){                                                     // Task 12: Create a file and save the information
        String fileName = Row_Letter + String.valueOf(seatNum) +".txt";         // Create the file name
        try (FileWriter fileWriter = new FileWriter(fileName)){                     // Start to write information to the file by using FileWriter
            fileWriter.write("******************************************************\n");
            fileWriter.write("* \t     ### Tickets Information ###\t     *\n");
            fileWriter.write("******************************************************\n\n");
            fileWriter.write("\tRow: " + Row_Letter + "\n");
            fileWriter.write("\tSeat Number: " + seatNum + "\n");
            fileWriter.write("\tPrice: £" + price + "\n");
            fileWriter.write("\n\t***   Personal Information   ***\t\t\n\n");
            fileWriter.write("\tName: " + person.Name() + "\n");
            fileWriter.write("\tSurname: " + person.Surname() + "\n");
            fileWriter.write("\tEmail: " + person.Email() + "\n");
            fileWriter.write("\n******************************************************");
            fileWriter.close();
            System.out.println("Your ticket information saved to the file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can not save the text file!");              // Displays the error message
        }
    }
}
