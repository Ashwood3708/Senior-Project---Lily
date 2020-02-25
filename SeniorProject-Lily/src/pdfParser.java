import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;


public class pdfParser {
    private HashSet<String> gradeList;
    private Student person;
    private int numOfClasses;

    public pdfParser(){
        gradeList = new HashSet();
         person = new Student();
         numOfClasses = 0;
        fillHash();
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
    public void readTxt(String text) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(text));
        while(scan.hasNext()){
            String line = scan.nextLine().trim();
            //grabs major
            if (line.contains("Major:")) {
                person.setMajor(line.substring(6).trim());
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
            if (line.substring(line.length()-1).equals("R")) { //indicates when classes appear
                //ends with: Term Totals (Undergraduate)
                while (!line.contains("Term Totals (Undergraduate)")) {
                    line = scan.nextLine().trim();
                    if(line.contains("Term Totals (Undergraduate)")){
                        break;
                    }
                    String creditHours = scan.nextLine().trim();
                    String QPoints = scan.nextLine().trim();
                    String items[] = line.split(" ");
                    fillClass(items, creditHours,QPoints);
                    numOfClasses++;

                }
                person.setNumOfClasses(numOfClasses);
            }
        }
    }

    public void fillHash() {
        gradeList.add("A");
        gradeList.add("A-");
        gradeList.add("B+");
        gradeList.add("B");
        gradeList.add("B-");
        gradeList.add("C+");
        gradeList.add("C");
        gradeList.add("C-");
        gradeList.add("D+");
        gradeList.add("D");
        gradeList.add("D-");
        gradeList.add("F+");
        gradeList.add("F");
        gradeList.add("F-");
        gradeList.add("W");
        //gradeList.add("I");
//transfer credits
        gradeList.add("TA");
        gradeList.add("TA-");
        gradeList.add("TB+");
        gradeList.add("TB");
        gradeList.add("TB-");
        gradeList.add("TC+");
        gradeList.add("TC");
        gradeList.add("TC-");
        gradeList.add("TD+");
        gradeList.add("TD");
        gradeList.add("TD-");
        gradeList.add("TF+");
        gradeList.add("TF");
        gradeList.add("TF-");
        gradeList.add("TW");
        gradeList.add("TI");
    }

    @Override
    public String toString(){
        String k = System.lineSeparator();
        String m = "major is: " + person.getMajor() +k;
        m+= "totalGPA is: " + person.getTotalGPA() +k;
        m+= "totalClasses is: " + person.getNumOfClasses() +k;
        m+= "class List:" +k;

        for(Class f: person.getList()){
            m+= f.toString() +k;
        }
        return m;
    }

    public void fillClass( String[] items, String creditHours, String QPoints){
        int classNameStartPosition = 3;
        String concat = "";
        //Fill in class object
        Class temp = new Class();
        temp.subject = items[0].trim();
        temp.courseNum = items[1].trim();
        temp.level = items[2].trim();
        while (!gradeList.contains(items[classNameStartPosition])) {
            concat += items[classNameStartPosition].trim()+" ";
            classNameStartPosition++;
        }
        temp.title = concat.trim();
        temp.grade = items[classNameStartPosition++].trim();
        temp.creditHours= Double.parseDouble(creditHours);
        temp.finalCredit= Double.parseDouble(QPoints);
        person.getList().add(temp);
    }

}
