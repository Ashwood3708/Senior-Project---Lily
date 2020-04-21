
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * what I changed:
 * readTxt() was altered
 * fill current classes() was added
 * getUserInfo() was added
 */

public class pdfParser {
    private HashSet<String> electives;
    private HashSet<String> gradesList;
    private HashSet<String> reqClasses;
    private HashSet<String> majorClasses;
    private Student person;
    private int numOfClasses;

    public pdfParser(){
        electives = new HashSet();
        gradesList = new HashSet();
        reqClasses = new HashSet();
        majorClasses = new HashSet();
        person = new Student();
        numOfClasses = 0;
        fillElectives();
        fillGradesList();
        fillMajorClasses();
    }
    
    public void readTxt(String text) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(text));
        String line;
        while(scan.hasNext()) {
            line = scan.nextLine().trim();

            if (line.contains("COURSES IN PROGRESS ") || line.contains("TRANSFER CREDIT ") || line.contains("Major and Department:") || line.equals("Overall:") || line.substring(line.length() - 1).equals("R") && line.contains("Subject")) {

            //grabs transfer credits
            if (line.contains("TRANSFER CREDIT ACCEPTED BY IN")) {
                while (!line.contains("Subject")) {
                    line = scan.nextLine();
                }
                line = scan.nextLine().trim();
                while (!line.contains("Attempt Hours")) {
                    String creditHours = scan.nextLine().trim();
                    String QPoints = scan.nextLine().trim();
                    String items[] = line.split(" ");
                    fillClassT(items, creditHours, QPoints);
                    numOfClasses++;
                    line = scan.nextLine().trim();

                }
                while (!line.contains("INSTITUTION CREDIT")) {
                    line = scan.nextLine();
                }
                person.setNumOfClasses(numOfClasses);
                person.setMajorGPA(getMajorGPA());
            }

            //grabs major
            if (line.contains("Major and Department:")) {
                String[] set = line.split(" ");
                String yet = "";
                for (int i = 3; i < set.length; i++) {
                    yet += set[i] + " ";
                    if (yet.contains(",")) {
                        yet = yet.substring(0, yet.length() - 2);
                        break;
                    }
                }

                person.setMajor(yet.trim());
            }

            //grabs gpa
            if (line.equals("Overall:")) {
                scan.nextLine();
                scan.nextLine();
                scan.nextLine();
                scan.nextLine();
                scan.nextLine();
                line = scan.nextLine();
                person.setTotalGPA(line.trim());
            }

            //loop to grab all classses
            if (line.substring(line.length() - 1).equals("R") && line.contains("Subject")) { //indicates when classes appear
                //ends with: Term Totals (Undergraduate)
                while (!line.contains("Term Totals")) {
                    line = scan.nextLine().trim();
                    if (line.contains("Term Totals (Undergraduate)")) {
                        break;
                    }
                    if (line.length() > 3) {

                    String creditHours = scan.nextLine().trim();
                    String QPoints = scan.nextLine().trim();
                    String items[] = line.split(" ");
                    fillClass(items, creditHours, QPoints);
                    numOfClasses++;
                }
                }
                person.setNumOfClasses(numOfClasses);
                person.setMajorGPA(getMajorGPA());
            }

            //grabs current classes
                if (line.contains("COURSES IN PROGRESS ") && line.contains("-Top-")){
                    line = scan.nextLine();
                    line = scan.nextLine();
                    line = scan.nextLine();
                    while(!line.contains("Unofficial Transcript")){
                        String creditHours = scan.nextLine().trim();
                        String QPoints = "0.0";
                        String items[] = line.split(" ");
                        fillCurrentClasses(items, creditHours, QPoints);
                        numOfClasses++;
                        line = scan.nextLine();
                    }
                }


        }
        }
    }

    @Override
    public String toString(){
        String k = System.lineSeparator();
        String m = "major is: " + person.getMajor() +k;
        m+= "totalGPA is: " + person.getTotalGPA() +k;
        m+= "majorGPA is: " + person.getMajorGPA() +k;
        m+= "totalClasses is: " + person.getNumOfClasses() +k;
        if(!person.getMajor().equals("Computer Science"))return m + "This project only supports Computer Scientist"+k;
        m+= "class List:" +k;
        for(Class f: person.getList()){
            m+= f.toString() +k;
        }
        return m;
    }

    public void fillClass( String[] items, String creditHours, String QPoints){
        try{
        int classNameStartPosition = 3;
        String concat = "";
        //Fill in class object
        Class temp = new Class();
        temp.subject = items[0].trim();
        temp.courseNum = items[1].trim();
        temp.level = items[2].trim();
        temp.setName();
        while (!gradesList.contains(items[classNameStartPosition])) {
            concat += items[classNameStartPosition].trim()+" ";
            classNameStartPosition++;
        }
        temp.title = concat.trim();
        temp.grade = items[items.length-1].trim();
        temp.creditHours= Double.parseDouble(creditHours);
        temp.finalCredit= Double.parseDouble(QPoints);
        temp.isMajorClass = majorClasses.contains(items[0]+items[1]);
        temp.isCompElective = electives.contains(items[0]+items[1]); 
        temp.isRequired = reqClasses.contains(items[0]+items[1]);
        temp.isTransferCredit = temp.grade.contains("T");
        person.getList().add(temp);
        }catch(Exception e){
            System.out.println("couldn't fill class: "+ "\n string: "+ items[0] +"\n credit hours: " +creditHours +"\n quality points: "+ QPoints);
        }
    }

    public void fillClassT( String[] items, String creditHours, String QPoints){
        try{
            int classNameStartPosition = 2;
            String concat = "";
            //Fill in class object
            Class temp = new Class();
            temp.subject = items[0].trim();
            temp.courseNum = items[1].trim();
            temp.level = "T";
            temp.setName();
            while (!gradesList.contains(items[classNameStartPosition])) {
                concat += items[classNameStartPosition].trim()+" ";
                classNameStartPosition++;
            }
            temp.title = concat.trim();
            temp.grade = items[items.length-1].trim();
            temp.creditHours= Double.parseDouble(creditHours);
            temp.finalCredit= Double.parseDouble(QPoints);
            temp.isMajorClass = majorClasses.contains(items[0]+items[1]);
            temp.isCompElective = electives.contains(items[0]+items[1]);
            temp.isRequired = reqClasses.contains(items[0]+items[1]);
            temp.isTransferCredit = temp.grade.contains("T");
            person.getList().add(temp);
        }catch(Exception e){
            System.out.println("couldn't fill transfer class: "+ "\n string: "+ items[0] +"\n credit hours: " +creditHours +"\n quality points: "+ QPoints);
            System.exit(-1);
        }
    }

    public void fillCurrentClasses(String[] items, String creditHours, String QPoints){
        try{
            String concat = "";
            //Fill in class object
            Class temp = new Class();
            temp.subject = items[0].trim();
            temp.courseNum = items[1].trim();
            temp.level = items[2].trim();
            temp.setName();
            person.getCurrentClasses().add(temp.name);
        }catch(Exception e){
            System.out.println("couldn't fill-current-class: "+ "\n string: "+ items[0]+" "+items[1] +"\n credit hours: " +creditHours +"\n quality points: "+ QPoints);
            System.exit(-5);
        }
    }

    public void fillGradesList() {
        gradesList.add("A");
        gradesList.add("A-");
        gradesList.add("B+");
        gradesList.add("B");
        gradesList.add("B-");
        gradesList.add("C+");
        gradesList.add("C");
        gradesList.add("C-");
        gradesList.add("D+");
        gradesList.add("D");
        gradesList.add("D-");
        gradesList.add("F+");
        gradesList.add("F");
        gradesList.add("F-");
        gradesList.add("W");
        //gradeList.add("I");
//transfer credits
        gradesList.add("TA");
        gradesList.add("TA-");
        gradesList.add("TB+");
        gradesList.add("TB");
        gradesList.add("TB-");
        gradesList.add("TC+");
        gradesList.add("TC");
        gradesList.add("TC-");
        gradesList.add("TD+");
        gradesList.add("TD");
        gradesList.add("TD-");
        gradesList.add("TF+");
        gradesList.add("TF");
        gradesList.add("TF-");
        gradesList.add("TW");
        gradesList.add("TI");
        gradesList.add("TS");
    }

    public void fillElectives(){
        electives.add("COMP320");
        electives.add("COMP321");
        electives.add("COMP323");
        electives.add("COMP340");
        electives.add("COMP356");
        electives.add("COMP363");
        electives.add("COMP368");
        electives.add("COMP420");
        electives.add("COMP421");
        electives.add("COMP440");
        electives.add("");
        
    }

    public void fillMajorClasses() {
        majorClasses.add("GEEN111");
        majorClasses.add("COMP121");
        majorClasses.add("COMP163");
        majorClasses.add("COMP167");
        majorClasses.add("GEEN165");
        majorClasses.add("COMP267");
        majorClasses.add("COMP200");
        majorClasses.add("COMP280");
        majorClasses.add("COMP285");
        majorClasses.add("COMP300");
        majorClasses.add("COMP322");
        majorClasses.add("COMP350");
        majorClasses.add("COMP360");
        majorClasses.add("COMP365");
        majorClasses.add("COMP375");
        majorClasses.add("COMP385");
        majorClasses.add("COMP390");
        majorClasses.add("COMP410");
        majorClasses.add("COMP476");
        majorClasses.add("COMP496");
    }

    public double getMajorGPA(){
        ArrayList <Class> list = person.getList();
        double Qpoints=0.0, creditHours =0.0;
        for (Class c:list) {
            if(c.isMajorClass){
                Qpoints+= c.finalCredit;
                creditHours+=c.creditHours;
            }
        }
        return (Qpoints/creditHours);
    }

    public Student getPerson(){
        return person;
    }

    /**
     * Gets total GPA, major GPA, and Class Recommendations list from Student's person object and send it to
     * Selenium.java to be updated on front end.
     * @return
     */
    public String getUserInfo(){
        return person.getTotalGPA() + " " + person.getMajorGPA();
    }
}
