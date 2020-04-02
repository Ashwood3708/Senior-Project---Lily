import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;

public class Student {

    private String major;
    private double majorGPA;
    private String totalGPA;
    private int numOfClasses;
    private ArrayList<Class> list;
    private HashSet<String> names;

    public Student(){
         major = "undeclared";
         majorGPA =0.0;
         totalGPA = "";
         numOfClasses = 0;
         list = new ArrayList();
         names = new HashSet<>();
    }

    public void addNames(){
        for (Class one:list) {
            names.add(one.name);
        }
        names.add("NONE");
    }

    public  HashSet<String> getNames(){
        return names;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajorGPA() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        return df2.format(majorGPA);
    }

    public void setMajorGPA(double major) { majorGPA = major; }

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
