public class Person {                 //Task 7: Creating person class
    private String fname;
    private String lname;
    private String email;

    public Person(String name,String surname,String email){             // Creating Person object to with reliable variables ; name,surname,email
        this.fname = name;
        this.lname = surname;
        this.email = email;
    }

    public String Name(){
        return fname;
    }

    public String Surname(){
        return lname;
    }

    public String Email(){
        return email;
    }

    public void printInfo(){                            //Printing ticket & person information
        System.out.println("Name: " + fname);
        System.out.println("Surname: " + lname);
        System.out.println("Email: " + email);
    }
}
