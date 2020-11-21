import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginController {

    private List loginList;

    public LoginController(){
        AllLogin.deserializeFromFile();
        loginList = AllLogin.getList();
    }

//-----------------for studentController and AdminController-----------------

//    public boolean checkPassword(Password tobeChecked){
//        for (Object pass: passwordList){
//            Password p = (Password) pass;
//            if (p.isEqual(tobeChecked))
//                return true;
//        }
//        return false;
//    }
public void checkData(String userName1 , String password,int num) throws IOException {
    boolean newAcc = true;

    FileReader reader = new FileReader("password.dat");
    BufferedReader bufferedReader = new BufferedReader(reader);

    String line;

    while ((line = bufferedReader.readLine()) != null) {
        if(line.trim().split(" ")[0].equals(userName1)){
            if(line.trim().split(" ")[1].equals(password)){
                if(num==1 && line.trim().split(" ")[2].equals("student")) {
                    if (loginBoundary.checkAllowedPeriod()) {
                        System.out.println("Successfully logged");
                        StudentController studentController = new StudentController();
                        studentController.getStudentBasicInfo(userName1);
                        studentController.showMenu(userName1);
//                        FileReader reader1 = new FileReader("studentInfo.txt");
//                        BufferedReader bufferedReader1 = new BufferedReader(reader1);
//                        if(line.contains(userName1)){
//                            String name = line.split("Name:")[0].trim();
//                            System.out.println(name);

                        }
                    else {
                        System.out.println("Restricted login in this time");
                    }

                       }
                else if(num==2 && line.trim().split(" ")[2].equals(admindomain) ){
                    //direct to admin menu
                    FileReader reader2 = new FileReader("admin.dat");
                    BufferedReader bufferedReader2 = new BufferedReader(reader2);

//                    AllAdmins allAdmins= new AllAdmins();
//                    allAdmins.readSerializedObject("");
//                    AdminBoundary adminBoundary = new AdminBoundary();
//                    adminBoundary.printMenu();
                }
//
//                        newAcc = false;

                    }
                }


            else{
                System.out.println("Invalid Password!");
            }

        }
    reader.close();
    }

}



