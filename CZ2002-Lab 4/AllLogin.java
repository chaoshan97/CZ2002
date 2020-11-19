
import java.util.ArrayList;
import java.util.List;

class AllLogin implements FileHandle{
    private static List passwordList;

    public AllLogin(){

    }

    public static List getList(){
        return passwordList;
    }

    public static List readSerializedObject(String filename){
        return FileHandle.readSerializedObject(filename);
    }

    public static void writeSerializedObject(String filename,List list){
        FileHandle.writeSerializedObject(filename, list);
    }

    public static void deserializeFromFile(){
        AllLogin.passwordList = (ArrayList)(readSerializedObject("password.dat"));
    }

    public static void serializeToFile(){
        writeSerializedObject("password.dat", passwordList);
    }


}