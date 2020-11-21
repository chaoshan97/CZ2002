import java.util.Scanner;

public class LoginBoundary {
    public static void main(String[] args) {
        LoginBoundary loginBoundary =  new LoginBoundary();
        Scanner scanner = new Scanner(System.in);
        //String domain=null;
        AllCourse ac = new AllCourse();
        AllStudent as = new AllStudent();
        AllLogin al = new AllLogin();
        AllAdmins ads = new AllAdmins();
        ac.deserializeFromFile();
        as.deserializeFromFile();
        al.deserializeFromFile();
        ads.deserializeFromFile();

        StudentCourseController studentCourseController = new StudentCourseController();
        StudentController studentController = new StudentController();
        AdminCourseController adminCourseController = new AdminCourseController();
        AdminStudentController adminStudentController = new AdminStudentController();
        LoginController loginController = new LoginController();
        Login newLogin = new Login();


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
                System.out.print("Password: ");
                String password = scanner.nextLine();
                loginController.checkData(userName, password, num);

            } catch(Exception e) {
                System.out.println("Invalid Inputs!Please enter either option 1 or 2 !");
                System.out.print("Select an option: ");
                num = scanner.nextInt();

            }
        }
    }


}
