import java.util.ArrayList;
import java.util.List;
class AllAdmins implements FileHandle{
    private static List adminList;

    public AllAdmins(){

    }

    public static List getList(){
        return adminList;
    }

    public static List readSerializedObject(String filename){
        return FileHandle.readSerializedObject(filename);
    }

    public static void writeSerializedObject(String filename,List list){
        FileHandle.writeSerializedObject(filename, list);
    }

    public static void deserializeFromFile(){
        AllAdmins.adminList = (ArrayList)(readSerializedObject("admin.dat"));
    }

    public static void serializeToFile(){
        writeSerializedObject("admin.dat", adminList);
    }


}