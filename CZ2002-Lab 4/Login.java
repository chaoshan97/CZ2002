import java.io.*;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Login{
    public static void main(String[] args) {
        Login login =  new Login();
        Allpasswords allpasswords =  new Allpasswords();
        Scanner scanner = new Scanner(System.in);
        String domain=null;

        System.out.println("Select your domain: ");
        System.out.println("1. Student");
        System.out.println("2. Staff");
        System.out.print("Select an option: ");

        if(!scanner.hasNextInt()){
            System.out.println("Invalid Inputs!Please enter a number!");
        }
        else{
        int num= scanner.nextInt();
            try {
                while (num < 1 || num > 2) {
                    System.out.println("Invalid input");
                    System.out.print("Select an option: ");
                    num = scanner.nextInt();

                }
                System.out.print("Network username or new username: ");
                String userName = scanner.nextLine();
                String userName1 = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                PasswordController passwordController = new PasswordController();
                passwordController.checkData(userName1, password,login, num);

            } catch(Exception e) {
                System.out.println("Invalid Inputs!Please enter either option 1 or 2 !");
                System.out.print("Select an option: ");
                num = scanner.nextInt();

            }
        }
    }

    public boolean checkAllowedPeriod(){
        String allowedSTime = "2020-11-19-01-25-39".replace("-",""); //enter allowed start time in "yyyy-MM-dd-HH-mm-ss" format
        String allowedETime = "2020-12-19-01-25-39".replace("-",""); //enter allowed end time in "yyyy-MM-dd-HH-mm-ss" format
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String time = sdf.format(date).replace("-","");

        if(Long.parseLong(time)<Long.parseLong(allowedSTime) && Long.parseLong(time)>Long.parseLong(allowedETime)){
            return false;
        }else{
            return true;
        }

    }
}
