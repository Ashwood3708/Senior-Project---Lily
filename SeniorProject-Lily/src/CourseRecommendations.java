import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class CourseRecommendations {

    private HashSet<String> classNames;
    private HashSet<classRequirements> requiredClasses;
    private HashMap<String, HashSet<String>> classEquivalents;
    private HashSet<classRequirements> electiveClasses;
    private HashSet<classRequirements> classestoTake;
    private ArrayList<String> finalList;
    private int counter = 0;

    public CourseRecommendations(Student s) {
        s.addNames();
        classNames = s.getNames();
        requiredClasses = new HashSet<>();
        classEquivalents = new HashMap<>();
        electiveClasses = new HashSet<>();
        classestoTake = new HashSet<>();
        finalList = new ArrayList<>();

        //order matters
        fillClassEquivalents();
        fillClassList();
        fillClassestoTake();
    }

    public void compElectives(){
        ArrayList<String> electives = ["","",""] ;

    }

    public void fillClassestoTake() {
        for (classRequirements one : requiredClasses) {
            if (!classNames.contains(one.name)) {
                classestoTake.add(one);
            }
        }
        try {
            //removes class offsets from the list ex: geen163 and comp163
            //also binary class options ex: Math 340 and Math 351
            HashSet<classRequirements> listTemp = new HashSet<>();
            listTemp.addAll(classestoTake);
            for (classRequirements one : listTemp) {
                if (one.otherNames != null) {
                    HashSet temp = one.otherNames;
                    temp.retainAll(classNames);
                    if (temp.size() > 0) {
                        classestoTake.remove(one);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println( "\nError in fillClassesToTake(): " + ex.toString());
        }
    }

    public String classesToTakeToString() {
        String k = System.lineSeparator();
        String key = "classes to take: " + classestoTake.size() + k;
        for (classRequirements clss : classestoTake) {
            key += clss.name + k;
        }
        return key;
    }

    public String requiredClassesToString() {
        String k = System.lineSeparator();
        String key = "classes: " + counter + k;
        for (classRequirements clss : requiredClasses) {
            key += clss.name + k + clss.list.toString() + k;
        }
        return key;
    }

    public void fillClassList() {
        //loads required classes into obj with all pre-recs

        try {
            Scanner file = new Scanner(new File("requiredClassList.txt"));
            String[] line;
            while (file.hasNext()) {
                line = file.nextLine().split(",");
                classRequirements obj = new classRequirements();
                obj.name = line[0].toUpperCase().trim();
                for (int i = 1; i < line.length; i++) {
                    obj.list.add(line[i].toUpperCase().trim());
                }
                obj.otherNames.addAll(classEquivalents.getOrDefault(obj.name, new HashSet<>()));
                requiredClasses.add(obj);
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("");
            e.printStackTrace();
        }
    }

    public void fillClassEquivalents() {
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

    public class classRequirements {
        String name;
        HashSet<String> list = new HashSet<>();
        HashSet<String> otherNames = new HashSet<>();


    }


}
