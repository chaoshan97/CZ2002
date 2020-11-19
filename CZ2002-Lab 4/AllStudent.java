import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllStudent implements FileHandle{
    private static Map<String, Student> studentMap;
    private static List studentList;
    public AllStudent(){}

    public static List readSerializedObject(String filename){
        return FileHandle.readSerializedObject(filename);
    }
    public static void deserializeFromFile() {
        AllStudent.studentList = (ArrayList)(readSerializedObject("studentInfo.dat"));
    }

    public static Map getStudentMap(){
        try {
            FileInputStream fileIn = new FileInputStream("studentInfo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            studentMap = (Map<String, Student>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            studentMap = new HashMap<String, Student>();
        } catch (ClassNotFoundException i) {
            i.printStackTrace();
        }
        return studentMap;
    }

}
