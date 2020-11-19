import java.util.*;
import java.io.Serializable;

public class Course implements Serializable{
	private String courseName;
	private String courseCode;
	private String ofSchool;
	private int numOfIndex;
	private List<CourseType> availableType;
	private Map<Integer, Index> indexInfo; // the class schedule and venue for each index, vacancy for each 							//index can be stored in Index class?
	private int totalVacancy; // total vacancy for this course
	private static int numOfCourses = 0;
	private int AU;

	// constructor
	public Course(String cName, String cCode, String ofSchool, List<CourseType> cAvailableType, int au) {
		courseName = cName;
		courseCode = cCode;
		this.ofSchool = ofSchool;
		numOfIndex = 0;
		indexInfo = new HashMap<>();
		totalVacancy = 0;
		numOfCourses++;
		availableType = cAvailableType;
		AU = au;
	}

	//-------------------------------------to String method----------------------------------
	public String toString(){
		return courseName + ", " + courseCode + ", " + ofSchool;
	}

	// ---------------------------------------get method--------------------------------------
	public String getCourseName(){
		return courseName;
	}

	public int getAU(){
		return AU;
	}

	public String getCourseCode(){
		return courseCode;
	}

	public String getCourseSchool(){
		return ofSchool;
	}

	public List<Index>  getAllIndex(){
		return new ArrayList<>(indexInfo.values());
	}

	public int getNumOfIndex(){
		return numOfIndex;
	}

	public int getTotalVac(){
		return totalVacancy;
	}

	public List<CourseType> getTypes(){
		return new ArrayList<>(availableType);
	}

	public static int getNumOfCourses(){
		return numOfCourses;
	}


	public Index getIndex(int indexNumber) {
		return indexInfo.get(indexNumber);
	}


	//-------------------------------------set method----------------------------------------
	public void setCourseName(String nowCname){
		courseName = nowCname;
	}

	public void setAU(int au){
		AU = au;
	}

	public void setCourseCode(String nowCcode){
		courseCode = nowCcode;
	}

	public void setCourseSchool(String nowSchool){
		ofSchool = nowSchool;
	}

	public void setTotalVac(int nowVac){
		totalVacancy = nowVac;
	}

	public void setAvailableType(List<CourseType> availableTypes) {
		for (CourseType type:availableTypes){
			this.availableType.add(type);
		}
	}

	//-------------------------------- changing index --------------------------------------
	public boolean addIndex(int indexNo, int vac, Timetable timeSlot){
		if (indexInfo.containsKey(indexNo)) return false;
		Index newIndex = new Index(indexNo, vac, this, timeSlot);
		indexInfo.put(indexNo, newIndex);
		numOfIndex++;
		totalVacancy+=newIndex.getCapacity();
		return true;
	}

	public boolean deleteIndex(int indexNo) {
		if (!indexInfo.containsKey(indexNo)) return false;
		Index toDeleteIndex = indexInfo.get(indexNo);
		if (toDeleteIndex.getNumRegistered() > 0) return false;
		indexInfo.remove(indexNo);
		numOfIndex--;
		totalVacancy-=toDeleteIndex.getCapacity();
		return true;
	}

	//return true if update is successful
	public boolean updateIndex(int indexNo, int capacity) {
		if (!indexInfo.containsKey(indexNo)) {
			System.out.println("The index does not exist.");
			return false;
		}
		Index toUpdate = indexInfo.get(indexNo);
		int oldCapacity = toUpdate.getCapacity();
		if (capacity < toUpdate.getNumRegistered()) return false;
		toUpdate.setCapacity(capacity);
		totalVacancy += (capacity - oldCapacity);
		return true;

	}

	/*public void checkAllVacancies(){
		for (Index i : indexInfo.values()){
			System.out.println(i.toString());
		}
	}*/

	public int getIndexVacancy(int indexNumber){
		if (!containIndex(indexNumber)) return -1;
		return indexInfo.get(indexNumber).getVacancies();
	}

	//---------------Stud and Index---------------------------------------
	public boolean addStudentToIndex(Student stud, int indexNumber) {
		Index index = indexInfo.get(indexNumber);
		return (index.addStudent(stud)); //return true if stud is successfully added to the class
	}

	public boolean deleteStudentFromIndex(Student stud, int indexNumber) {
		Index index = indexInfo.get(indexNumber);
		return (index.deleteStudent(stud)); //return true if stud is successfully deleted from the class
	}

	public boolean swapIndex (Student stud, int indexNumber1, int indexNumber2) {
		if (!containIndex(indexNumber1)) return false;
		if (!containIndex(indexNumber2)) return false;
		boolean success = false;
		Index index1 = indexInfo.get(indexNumber1);
		Index index2 = indexInfo.get(indexNumber2);
		if (index2.getVacancies() > 0) {
			success = index1.deleteStudent(stud);
			if (success) index2.addStudent(stud);
		}
		return success;
	}

	public boolean swapIndexWithAnotherOne(int indexNumber1, int indexNumber2, Student stud1, Student stud2){
		if (!containIndex(indexNumber1)) return false;
		if (!containIndex(indexNumber2)) return false;

		boolean successful = false;
		Index index1 = indexInfo.get(indexNumber1);
		Index index2 = indexInfo.get(indexNumber2);

		if (index1.deleteStudent(stud1))
		{
			if(index2.deleteStudent(stud2)) {
				index1.addStudent(stud2);
				index2.addStudent(stud1);
				successful = true;
			}
			else
				index1.addStudent(stud1);
		}

		return successful;
	}

	private boolean containIndex(int indexNumber) {
		return indexInfo.containsKey(indexNumber);
	}


	// -----------------------------for admin to modify CourseType -----------------------------
	public boolean addCourseType(CourseType courseType) {
		if (availableType.contains(courseType)) return false;
		availableType.add(courseType);
		System.out.println("Course Type added successfully.");
		return true;
	}

	public boolean deleteCourseType(CourseType courseType) {
		if (!availableType.contains(courseType)) return false;
		availableType.remove(courseType);
		System.out.println("Course Type deleted successfully.");
		return true;
	}
}
