import java.util.ArrayList;

public class Student {

    private String major;
    private String totalGPA;
    private int numOfClasses;
    private ArrayList<Class> list;

    public Student(){
         major = "undeclared";
         totalGPA = "";
        numOfClasses = 0;
         list = new ArrayList();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTotalGPA() {
        return totalGPA;
    }

    public void setTotalGPA(String totalGPA) {
        this.totalGPA = totalGPA;
    }

    public int getNumOfClasses() {
        return numOfClasses;
    }

    public void setNumOfClasses(int numOfClasses) {
        this.numOfClasses = numOfClasses;
    }

    public ArrayList<Class> getList() {
        return list;
    }

    public void setList(ArrayList<Class> list) {
        this.list = list;
    }
}
