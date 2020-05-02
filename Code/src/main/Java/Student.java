/**
 * Student is a class that consist of student information
 *
 */

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
    private HashSet<String> currentClasses;

    public Student(){
        major = "undeclared";
        majorGPA =0.0;
        totalGPA = "";
        numOfClasses = 0;
        list = new ArrayList();
        names = new HashSet<>();
        currentClasses = new HashSet<>();
    }

    /**
     * add class names
     */
    public void addNames(){
        for (Class one:list) {
            names.add(one.name);
        }
        names.add("NONE");
    }

    /**
     * @return class names
     */
    public  HashSet<String> getNames(){
        return names;
    }

    /**
     * @return major
     */
    public String getMajor() {
        return major;
    }

    /***
     * set major
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * @return majorGPA
     */
    public String getMajorGPA() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        return df2.format(majorGPA);
    }

    /**
     * set major
     * @param major
     */
    public void setMajorGPA(double major) { majorGPA = major; }

    /**
     * @return overall GPA
     */
    public String getTotalGPA() {
        return totalGPA;
    }

    /**
     * set overall GPA
     * @param totalGPA
     */
    public void setTotalGPA(String totalGPA) {
        this.totalGPA = totalGPA;
    }

    /***
     * @return number of classes
     */
    public int getNumOfClasses() {
        return numOfClasses;
    }

    /**
     * set number of classes
     * @param numOfClasses
     */
    public void setNumOfClasses(int numOfClasses) {
        this.numOfClasses = numOfClasses;
    }

    /**
     * @return class list
     */
    public ArrayList<Class> getList() {
        return list;
    }

    /**
     * @return list of current classes
     */
    public HashSet<String> getCurrentClasses(){return currentClasses;}

    public void setList(ArrayList<Class> list) {
        this.list = list;
    }
}
