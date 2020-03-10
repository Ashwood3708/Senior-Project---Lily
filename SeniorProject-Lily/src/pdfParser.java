import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


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

    //parses PDF
    public void readPdf() throws IOException {

        try (PDDocument document = PDDocument.load(new File("AcademicTranscript.pdf"))) {
            document.getClass();

            if (!document.isEncrypted()) {
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                String lines[] = pdfFileInText.split("\\s\\n");

                for (int i = 0; i < lines.length; i++) {

                    if (lines[i].contains("Major:")) { //grabs major
                        person.setMajor(lines[i].substring(6).trim());
                    }
                    if (lines[i].equals("Overall:")) {//grabs gpa
                        int end = lines[i + 1].length();
                        person.setTotalGPA(lines[i + 1].substring(end - 4, end).trim());
                    }
                    if (lines[i].equals("R")) {  //indicates when classes appear
                        //loop to grab all classses
                        //ends with: Term Totals (Undergraduate)
                        while (!lines[i].contains("Term Totals (Undergraduate)")) {
                            if (lines[i].length() > 2 && lines[i + 1].length() > 2) {
                                String items[] = lines[i].split(" ");
                                String items2[] = lines[i + 1].split(" ");
                                fillClass(items,items2[0].trim(),items2[1].trim());
                                numOfClasses++;
                            }
                            i++;
                        }
                    }
                }
            }

        }catch (Exception ex){
            System.out.println("couldnt find the PDF-File of incorrect format of File");
            System.exit(-1);

        }
        person.setNumOfClasses(numOfClasses);

    }

    //parses textFile
    /**
     * gotta implement transfer segment
     * */
    public void readTxt(String text) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(text));
        String line;
        while(scan.hasNext()){
            line = scan.nextLine().trim();

            //grabs transfer credits
            if(line.contains("TRANSFER CREDIT ACCEPTED BY INSTITUTION")){
                while (!line.contains("Subject")){
                    line = scan.nextLine();
                }
                while (!line.contains("Attempt Hours")) {
                    line = scan.nextLine().trim();
                    String creditHours = scan.nextLine().trim();
                    String QPoints = scan.nextLine().trim();
                    String items[] = line.split(" ");
                    fillClass(items, creditHours,QPoints);
                    numOfClasses++;
                    line= scan.nextLine();

                }
                person.setNumOfClasses(numOfClasses);
                person.setMajorGPA(getMajorGPA());
            }

            //grabs major
            if (line.contains("Major and Department:")) {
                String[] set = line.split(" ");
                String yet = "";
                for (int i=3;i<set.length;i++){
                yet+=set[i]+" ";
                if(yet.contains(",")){
                    yet= yet.substring(0,yet.length()-2);
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
            if (line.substring(line.length()-1).equals("R") && line.contains("Subject")) { //indicates when classes appear
                //ends with: Term Totals (Undergraduate)
                while (!line.contains("Term Totals")) {
                    line = scan.nextLine().trim();
                    if(line.contains("Term Totals (Undergraduate)")){
                        break;
                    }
                    String creditHours = scan.nextLine().trim();
                    String QPoints = scan.nextLine().trim();
                    String items[] = line.split(" ");
                    fillClassT(items, creditHours,QPoints);
                    numOfClasses++;

                }
                person.setNumOfClasses(numOfClasses);
                person.setMajorGPA(getMajorGPA());
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
        person.getList().add(temp);
        }catch(Exception e){
            System.out.println("incorrect File Format");
            System.exit(-1);
        }
    }

    public void fillClassT( String[] items, String creditHours, String QPoints){
        try{
            int classNameStartPosition = 3;
            String concat = "";
            //Fill in class object
            Class temp = new Class();
            temp.subject = items[0].trim();
            temp.courseNum = items[1].trim();
            temp.level = "T";
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
            person.getList().add(temp);
        }catch(Exception e){
            System.out.println("incorrect File Format");
            System.exit(-1);
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

}
