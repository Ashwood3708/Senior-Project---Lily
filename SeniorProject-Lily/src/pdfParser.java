import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class pdfParser {
    HashSet<String> gradeList = new HashSet();
    String major = "undeclared";
    String totalGPA = "";
    int numOfClasses = 0;
    ArrayList<Class> list = new ArrayList();

    public void read() throws IOException {
        fillHash();
        try (PDDocument document = PDDocument.load(new File("AcademicTranscript.pdf"))) {
            document.getClass();

            if (!document.isEncrypted()) {
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                String lines[] = pdfFileInText.split("\\s\\n");

                for (int i = 0; i < lines.length; i++) {

                    if (lines[i].contains("Major:")) { //grabs major
                        major = lines[i].substring(6).trim();
                    }
                    if (lines[i].equals("Overall:")) {//grabs gpa
                        int end = lines[i + 1].length();
                        totalGPA = lines[i + 1].substring(end - 4, end).trim();
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
                                list.add(temp);
                                numOfClasses++;
                            }

                            i++;
                        }
                    }
                }


//                System.out.println(pdfFileInText);
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
        gradeList.add("I");

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
        String m = "major is: " + major +k;
        m+= "totalGPA is: " + totalGPA  +k;
        m+= "totalClasses is: " + numOfClasses +k;
        m+= "class List:" +k;

        for(Class f: list){
            m+= f.toString() +k;
        }
        return m;
    }

}
