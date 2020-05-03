import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CourseRecommendations {

    private HashSet<String> takenClassNames;
    private HashSet<String> classToTakeNames;
    private HashSet<classRequirements> requiredClasses;
    private HashMap<String, HashSet<String>> classEquivalents;
    private ArrayList<String> compElectives;
    private ArrayList<String> sciElectives;
    private ArrayList<String> mathElectives;
    private ArrayList<String> statElectives;
    private ArrayList<String> busElectives;
    private ArrayList<String> socialElectives;
    private ArrayList<String> histElectives;
    private ArrayList<String> globalElectives;
    private HashSet<classRequirements> classestoTake;
    private ArrayList<String> finalList;
    private String elevtivesResponse = "";
    private Student student;
    private int counter;
    private String k = "\n";

    public CourseRecommendations(Student s) {
        s.addNames();
        student = s;
        takenClassNames = s.getNames();
        takenClassNames.addAll(s.getCurrentClasses());
        requiredClasses = new HashSet<>();
        classToTakeNames = new HashSet<>();
        classEquivalents = new HashMap<>();
        socialElectives = new ArrayList<>();
        globalElectives = new ArrayList<>();
        mathElectives = new ArrayList<>();
        compElectives = new ArrayList<>();
        histElectives = new ArrayList<>();
        statElectives = new ArrayList<>();
        sciElectives = new ArrayList<>();
        busElectives = new ArrayList<>();
        classestoTake = new HashSet<>();
        finalList = new ArrayList<>();
        start();
    }

    private void start() {
        //order matters
        fillClassEquivalents();
        fillClassList();
        fillClassesToTake();
        createFinalList();
        checkElectives();
    }

    private void checkElectives() {
        elevtivesResponse = ""
                + compElectives()
                + scienceElectives()
                + mathElectives()
                + statElectives()
                + busElectives()
                + histElectives()
                + globalElectives()
                + socialSciElectives();
    }

    //create recommendations list
    private void createFinalList() {
        // classestoTake has required classes not yet taken
        for (classRequirements a : classestoTake) {
            if (checkPreReqs(a)) {
                finalList.add(a.name);
            }
        }
        Collections.sort(finalList);
    }

    private boolean checkPreReqs(classRequirements c) {
        for (String name : c.preRequisites) {
            name = name.toUpperCase();
            if (classToTakeNames.contains(name)) {
                return false;
            }
        }
        return true;
    }

    //check electives
    public String compElectives() {
        ArrayList<String> electives =
                new ArrayList(Arrays.asList(
                        "mgmt 335", "bued 342", "ecen 427", "isen 415", "math 360", "math 365", "cst 340",
                        "comp 340", "comp 397", "comp 420", "comp 440", "comp 494", "comp 495", "comp 620",
                        "comp 710", "comp 725", "comp 726", "comp 755", "comp 765", "comp 790", "comp 320"
                ));
        compElectives.addAll(electives);
        int eCount = 3;
        for (String name : electives) {
            name = name.toUpperCase();
            if (takenClassNames.contains(name)) {
                compElectives.remove(name.toLowerCase());
                eCount--;
            }
            if (eCount < 1) {
                return "";
            }
        }
        finalList.add("Comp Electives needed: " + Math.max(0, eCount));

        return "Comp Electives needed: " + Math.max(0, eCount) + k + k;

    }

    public String scienceElectives() {
        ArrayList<String> scienceElectives = new ArrayList(Arrays.asList("phys 241", "phys 242", "chem 106", "chem 107", "biol 100", "biol 101", "slmg 200"));
        sciElectives.addAll(scienceElectives);
        int eCount = 3;
        for (String name : scienceElectives) {
            name = name.toUpperCase();
            if (takenClassNames.contains(name)) {
                sciElectives.remove(name.toLowerCase());
                eCount--;
            }
        }
        if (!takenClassNames.contains("CHEM 106")) {
            sciElectives.remove("chem 107");
        } else if (!takenClassNames.contains("PHYS 241")) {
            sciElectives.remove("phys 242");
        }
        if (eCount < 1) {
            return "";
        }
        finalList.add("Science Electives needed: " + Math.max(0, eCount));

        return "Science Electives needed: " + Math.max(0, eCount) + k + k;

    }

    public String mathElectives() {
        ArrayList<String> electives = new ArrayList(Arrays.asList("math 340", "math 351"));
        mathElectives.addAll(electives);
        int eCount = 1;
        for (String name : electives) {
            name = name.toUpperCase();
            if (takenClassNames.contains(name)) {
                mathElectives.remove(name.toLowerCase());
                eCount--;
            }
            if (eCount < 1) {
                return "";
            }
        }
        finalList.add("Math Electives needed: " + Math.max(0, eCount));
        return "Math Electives needed: " + Math.max(0, eCount) + k + k;


    }

    public String statElectives() {
        ArrayList<String> electives = new ArrayList(Arrays.asList("math 224", "isen 370", "ecen 356"));
        statElectives.addAll(electives);
        int eCount = 1;
        for (String name : electives) {
            name = name.toUpperCase();
            if (takenClassNames.contains(name)) {
                statElectives.remove(name.toLowerCase());
                eCount--;
            }
            if (eCount < 1) {
                return "";
            }
        }
        finalList.add("Stat Electives needed: " + Math.max(0, eCount));
        return "Stat Electives needed: " + Math.max(0, eCount) + k + k;
    }

    public String busElectives() {
        ArrayList<String> electives = new ArrayList(Arrays.asList("mgmt 110", "mgmt 132", "mktg 230", "econ 200", "econ 201"));
        busElectives.addAll(electives);
        int eCount = 1;
        for (String name : electives) {
            name = name.toUpperCase();
            if (takenClassNames.contains(name)) {
                busElectives.remove(name.toLowerCase());
                eCount--;
            }
            if (eCount < 1) {
                return "";
            }
        }
        finalList.add("Business Electives needed: " + Math.max(0, eCount));
        return "Business Electives needed: " + Math.max(0, eCount) + k + k;
    }

    public String socialSciElectives() {
        ArrayList<String> electives = new ArrayList(Arrays.asList("bued 279", "econ 200", "econ 201", "fcs 135", "fcs 181",
                "fcs 260", "hist 103", "hist 104", "hist 105", "hist 106", "hist 107", "hist 130", "hist 206", "hist 207", "hist 216",
                "hist 231", "jomc 240", "poli 110", "psyc 101", "soci 100", "soci 200", "ssfm 226"));
        socialElectives.addAll(electives);
        int counter = 0;
        for (String name : electives) {
            name = name.toUpperCase();
            if (takenClassNames.contains(name)) {
                socialElectives.remove(name.toLowerCase());
                return "";
            }
        }

        finalList.add("Social/ behavior Science Electives needed: " + 1);
        return "Social/ behavior Science Electives needed: " + 1 + k + k;
    }

    public String histElectives() {
        ArrayList<String> electives = new ArrayList(Arrays.asList("engl 333", "engl 334", "hist 103", "hist 106", "hist 107", "libs 202", "musi 220"));
        histElectives.addAll(electives);
        int eCount = 1;
        for (String name : electives) {
            name = name.toUpperCase();
            if (takenClassNames.contains(name)) {
                histElectives.remove(name.toLowerCase());
                eCount--;
            }
            if (eCount < 1) {
                return "";
            }
        }
        finalList.add("History Electives needed: " + Math.max(0, eCount));
        return "History Electives needed: " + Math.max(0, eCount) + k + k;
    }

    public String globalElectives() {
        ArrayList<String> electives = new ArrayList(Arrays.asList("hist 130", "hist 206", "hist 207", "hist 216", "hist 231", "mgmt 221", "phil 103", "phil 201"));
        globalElectives.addAll(electives);
        int eCount = 1;
        for (String name : electives) {
            name = name.toUpperCase();
            if (takenClassNames.contains(name)) {
                globalElectives.remove(name.toLowerCase());
                eCount--;
                return "";
            }
        }
        finalList.add("Global Awareness Electives needed: " + Math.max(0, eCount));
        return "Global Awareness Electives needed: " + Math.max(0, eCount) + k + k;
    }

    //loads required classes into objs with all pre-recs
    private void fillClassList() {
        try {
            Scanner file = new Scanner(new File("requiredClassList.txt"));
            String[] line;
            while (file.hasNext()) {
                line = file.nextLine().split(",");
                classRequirements obj = new classRequirements();
                obj.name = line[0].toUpperCase().trim();
                for (int i = 1; i < line.length; i++) {
                    obj.preRequisites.add(line[i].toUpperCase().trim());
                }
                obj.classEquivalents.addAll(classEquivalents.getOrDefault(obj.name, new HashSet<>()));
                requiredClasses.add(obj);
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("error in fillClassList()");
        }
    }

    //fill each classes class Equivalents list
    private void fillClassEquivalents() {
        //text has classes that were renamed and transfer credits that transfer over
        try {
            Scanner file = new Scanner(new File("classEquivalents.txt"));
            String[] line;
            while (file.hasNext()) {
                line = file.nextLine().split(",");
                HashSet obj = new HashSet();
                String name = line[0].toUpperCase().trim();
                for (int i = 1; i < line.length; i++) {
                    obj.add(line[i].toUpperCase().trim());
                }
                classEquivalents.put(name, obj);

            }
        } catch (FileNotFoundException e) {
            System.out.println("class Equivalents error");
            e.printStackTrace();
        }
    }

    //parse taken classes against required classes
    private void fillClassesToTake() {
        for (classRequirements one : requiredClasses) {
            if (!takenClassNames.contains(one.name)) {
                classestoTake.add(one);
                classToTakeNames.add(one.name);
            }
        }
        try {
            //removes class offsets from the list ex: geen163 and comp163
            //also binary class options ex: Math 340 and Math 351
            HashSet<classRequirements> listTemp = new HashSet<>();
            listTemp.addAll(classestoTake);
            for (classRequirements one : listTemp) {
                if (one.classEquivalents != null) {
                    HashSet temp = one.classEquivalents;
                    temp.retainAll(takenClassNames);
                    if (temp.size() > 0) {
                        classestoTake.remove(one);
                        classToTakeNames.remove(one.name);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("\nError in fillClassesToTake(): " + ex.toString());
        }
    }

    //class holds prerequisites and classEquivalents
    private class classRequirements {
        String name;
        HashSet<String> preRequisites = new HashSet<>();
        HashSet<String> classEquivalents = new HashSet<>();


    }

    //Return methods
    public String toString() {
        String k = System.lineSeparator();
        String key ="major: "+student.getMajor() +"\n" + "Required classes to take: " + classestoTake.size() + k;
        return key + getRequiredClasses() + k + elevtivesResponse + "\n"
                +"Taken Classes: \n"+ getTakenClasses() + "\n"
                +"Current Classes: \n"+ getCurrentClasses() + "\n"
                + "Recommended Classes List: \n"+ getFinalList() + "\n";
    }

    public String getRequiredClasses(){
        String list = "";
        ArrayList<String>names = new ArrayList<>();
        for (classRequirements clss: classestoTake) {
            names.add(clss.name);
        }
        Collections.sort(names);
        for (String clss : names) {
            list += clss + "\n";
        }
        return list;
    }

    public String getFinalList(){
        String k = "";
        for(String a: finalList){
            k+= a+"\n";
        }
        return k;
    }

    public String getTakenClasses() {
        String all = "";
        HashSet<String> takenClasses = takenClassNames;
        for(String name:student.getCurrentClasses()){
            takenClasses.remove(name);
        }
        takenClasses.remove("NONE");
        Object[] c = takenClasses.toArray();
        Arrays.sort(c);
        for (Object f : c) {
            all += f + "\n";
        }
        return all;
    }

    public String getCurrentClasses(){
        String currClasses ="";
        HashSet<String> r = student.getCurrentClasses();
        Object[] c = r.toArray();
        Arrays.sort(c);
        for (Object f : c) {
            currClasses += f + "\n";
        }
        return currClasses;
    }

    public String takeThese() {
        String key = "<ul>";
        for (String clss : finalList) {
            key += "<li>" + clss + "</li>";
        }
        return key + "</ul>";
    }
}