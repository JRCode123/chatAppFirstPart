package chatappfirstpart;

import java.util.Scanner;
import java.util.regex.*;

public class LoginSystem {
    private final User1 user;
    private final Scanner scanner;
    
    public LoginSystem() {
        this.scanner = new Scanner(System.in);
        System.out.println("=== User Details ===");
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        this.user = new User1(firstName, lastName);
    }
    
    // Username validation
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    // Password validation
    public boolean checkPasswordComplexity(String password) {
        boolean lengthOK = password.length() >= 8;
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");
        
        return lengthOK && hasUpper && hasNumber && hasSpecial;
    }
    
    // Phone number validation
    public boolean checkCellPhoneNumber(String cellNumber) {
        Pattern pattern = Pattern.compile("^(?:\\+27)[6-8][0-9]{8}$");
        Matcher matcher = pattern.matcher(cellNumber);
        return matcher.matches();
    }
    
    // Registration process
    public void registerUser() {
        System.out.println("\n=== User credentials ===");
        
        // Username input
        String username;
        do {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            if (!checkUserName(username)) {
                System.out.println("Username must contain at least one underscore('_') and be no more than 5 characters long.");
            }
        } while (!checkUserName(username));
        user.setUsername(username);
        
        // Password input
        String password;
        do {
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            if (!checkPasswordComplexity(password)) {
                System.out.println("Password must be at least 8 characters long, contain at least one capital letter, one number, and one special character.");
            }
        } while (!checkPasswordComplexity(password));
        user.setPassword(password);
        
        // Phone number input
        String cellNumber;
        do {
            System.out.print("Enter a South African phone number: ");
            cellNumber = scanner.nextLine();
            if (!checkCellPhoneNumber(cellNumber)) {
                System.out.println("Phone number must start with '+' followed by country code and number, and be no more than 9 digits long after the code.");
            }
        } while (!checkCellPhoneNumber(cellNumber));
        user.setCellNumber(cellNumber);
        
        System.out.println("\nRegistration successful!");
        System.out.println("User credentials were successfully captured.");
        
    }
    
    // Login process
    public void loginUser() {
        System.out.println("\n=== User Login ===");
        System.out.print("Enter username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();
        
        if (inputUsername.equals(user.getUsername()) && inputPassword.equals(user.getPassword())) {
            System.out.println("Welcome " + user.getFirstName() +  user.getLastName() + " You are logged in successfully");
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
    }
    
    public static void main(String[] args) {
        LoginSystem system = new LoginSystem();
        system.registerUser();
        system.loginUser();
    }
}

 

