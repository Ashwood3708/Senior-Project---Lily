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
    public void read() throws IOException {

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
                                //System.out.println(lines[i] + " ]]: " + lines[i].length());
                                String items[] = lines[i].split(" ");
                                String items2[] = lines[i + 1].split(" ");
                                int y = 3;
                                String concat = "";
                                //use enum to parse title
                                Class temp = new Class();
                                temp.subject = items[0].trim();
                                temp.courseNum = items[1].trim();
                                temp.level = items[2].trim();
                                while (!gradeList.contains(items[y])) {
                                    concat += items[y].trim()+" ";
                                    y++;
                                }
                                temp.title = concat.trim();
                                temp.grade = items[y++].trim();
                                temp.creditHours= Double.parseDouble( items2[0].trim());
                                temp.finalCredit= Double.parseDouble( items2[1].trim());
                                person.getList().add(temp);

                                numOfClasses++;
                            }

                            i++;
                        }
                    }
                }
            }

        }
        person.setNumOfClasses(numOfClasses);

    }

    //parses textFile
    //still need to complete
    public void read(File text) throws FileNotFoundException {
        Scanner scan = new Scanner(text);
        while(scan.hasNext()){

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
        gradeList.add("I");
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

}
