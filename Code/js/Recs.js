/**
 * CourseRecommendations looks at the students classes and see if the have their electives and core classes
 * this classes uses different functions to recommend what should and should not be taken within the next school year.
 * There are certain electives that needs to be taken more than one of so certain methods keep track of how many of
 * these electives are left to take.
 */

let takenClassNames = {};
let  classToTakeNames= [];
let requiredClasses= [];
let classEquivalents= new Map();
let compElectivesList = [];
let classestoTake = new Map();
let finalList = [];
let elevtivesResponse = "";
let targetStudent;
let counter;
let k = "\n";

//@param targetStudent
function generateRec(s) {
    s.addNames();
    targetStudent = s;
    takenClassNames = s.getNames().concat(s.getCurrentClasses());

    //order matters
    fillClassEquivalents();
    fillClassList();
    fillClassesToTake();
    createFinalList();
    checkElectives();
}

function  checkElectives() {
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
function createFinalList() {
    // classestoTake has required classes not yet taken
    for (let a = 0; a < classestoTake.size; a++) {
        if (checkPreReqs(classestoTake.get(a))) {
            finalList.push(classestoTake.get(a).name);
        }
    }
    finalList.sort();
}

//@param classRequirements
function checkPreReqs(classRequirements) {
    for (var name in classRequirements.preRequisites) {
        name = classRequirements.preRequisites[name].toUpperCase();
        if (classToTakeNames.contains(name)) {
            return false;
        }
    }
    return true;
}

//check electives functions
function compElectives() {
    let electives =[
        "mgmt 335", "bued 342", "ecen 427", "isen 415", "math 360", "math 365", "cst 340",
        "comp 340", "comp 397", "comp 420", "comp 440", "comp 494", "comp 495", "comp 620",
        "comp 710", "comp 725", "comp 726", "comp 755", "comp 765", "comp 790", "comp 320"
    ]
    compElectivesList.push(...electives);
    var eCount = 3;
    for (let name in electives) {
        var classname = electives[name].toUpperCase();
        if (takenClassNames.includes(classname)) {
            eCount--;
        }
        if (eCount < 1) {
            return "";
        }
    }
    finalList.push("Comp Electives needed: " + eCount);

    return "Comp Electives needed: " + eCount + k + k;

}

function scienceElectives() {
    let scienceElectives = ["phys 241", "phys 242", "chem 106", "chem 107", "biol 100", "biol 101", "slmg 200"];
    //sciElectives.push(...scienceElectives);
    let eCount = 3;
    for (let name in  scienceElectives) {
        var classname =  scienceElectives[name].toUpperCase();
        if (takenClassNames.includes(classname)) {
            eCount--;
        }
        if (eCount < 1) {
            return "";
        }
    }
    finalList.add("Science Electives needed: " + eCount);

    return "Science Electives needed: " + eCount + k + k;

}

function mathElectives() {
    let mElectives = ["math 340", "math 351"];
    //mathElectives.addAll(electives);
    let eCount = 1;
    for (let name in  mElectives) {
        var classname =  mElectives[name].toUpperCase();
        if (takenClassNames.includes(classname)) {
            eCount--;
        }
        if (eCount < 1) {
            return "";
        }
    }
    finalList.add("Math Electives needed: " + eCount);
    return "Math Electives needed: " + eCount + k + k;
}

function  statElectives() {
    let sElectives = ["math 224", "isen 370", "ecen 356"];
    //statElectives.addAll(electives);
    let eCount = 1;
    for (let point in  sElectives) {
        var classname =  sElectives[point].toUpperCase();
        if (takenClassNames.includes(classname)) {
            eCount--;
        }
        if (eCount < 1) {
            return "";
        }
    }
    finalList.add("Stat Electives needed: " + eCount);
    return "Stat Electives needed: " + eCount + k + k;
}

function  busElectives() {
    let bElectives = ["mgmt 110", "mgmt 132", "mktg 230", "econ 200", "econ 201"];
    //busElectives.addAll(electives);
    let eCount = 1;
    for (let point in  bElectives) {
        var classname =  bElectives[point].toUpperCase();
        if (takenClassNames.includes(classname)) {
            eCount--;
        }
        if (eCount < 1) {
            return "";
        }
    }
    finalList.add("Business Electives needed: " + eCount);
    return "Business Electives needed: " + eCount + k + k;
}

function  socialSciElectives() {
    let sElectives = ["bued 279", "econ 200", "econ 201", "fcs 135", "fcs 181",
        "fcs 260", "hist 103", "hist 104", "hist 105", "hist 106", "hist 107", "hist 130", "hist 206", "hist 207", "hist 216",
        "hist 231", "jomc 240", "poli 110", "psyc 101", "soci 100", "soci 200", "ssfm 226"];
    //socialElectives.addAll(electives);
    let eCount = 1;
    for (let point in  sElectives) {
        var classname =  sElectives[point].toUpperCase();
        if (takenClassNames.includes(classname)) {
            eCount--;
        }
        if (eCount < 1) {
            return "";
        }
    }
    finalList.add("Social/ behavior Science Electives needed: " + 1);
    return "Social/ behavior Science Electives needed: " + 1 + k + k;
}

function  histElectives() {
    let hElectives = ["engl 333", "engl 334", "hist 103", "hist 106", "hist 107", "libs 202", "musi 220"];
    //histElectives.addAll(electives);
    let eCount = 1;
    for (let point in  hElectives) {
        var classname =  hElectives[point].toUpperCase();
        if (takenClassNames.includes(classname)) {
            eCount--;
        }
        if (eCount < 1) {
            return "";
        }
    }
    finalList.add("History Electives needed: " + eCount);
    return "History Electives needed: " + eCount + k + k;
}

function  globalElectives() {
    let gElectives = ["hist 130", "hist 206", "hist 207", "hist 216", "hist 231", "mgmt 221", "phil 103", "phil 201"];
    //globalElectives.addAll(electives);
    let eCount = 1;
    for (let point in  gElectives) {
        let classname =  gElectives[point].toUpperCase();
        if (takenClassNames.includes(classname)) {
            eCount--;
        }
        if (eCount < 1) {
            return "";
        }
    }
    finalList.add("Global Awareness Electives needed: " + eCount);
    return "Global Awareness Electives needed: " + eCount + k + k;
}

//loads required classes leto objs with all pre-recs
function  fillClassList() {
    try{
        let read = requiredClassListTxt.split("\n");
        let line;
        for (let i = 0; i < read.length; ++i) {
            line = read[i].split(",");
            let obj = new classRequirements();
            obj.name = line[0].toUpperCase().trim();
            let preReqs = [];
            for (let i = 1; i < line.length; i++) {
                preReqs.push(line[i].toUpperCase().trim());
            }
            obj.prerequisites = preReqs;
            if(classEquivalents.has(obj.name)){
                obj.classEquivalents = classEquivalents.get(obj.name);
            }
            requiredClasses.push(obj);
            counter++;
        }
    } catch (err) {
        console.log("fillClassList()");
    }

}

//fill each classes class Equivalents list
function  fillClassEquivalents() {
    //classEquivalentsTxt has classes that were renamed and transfer credits that transfer over
    try{
        let read = classEquivalentsTxt.split("\n");
        let line;
        for (let i = 0; i < read.length; ++i) {
            line = read[i].split(",");
            let obj = [];
            let name = line[0].toUpperCase().trim();
            for (let i = 1; i < line.length; i++) {
                obj.push(line[i].toUpperCase().trim());
            }
            classEquivalents.set(name,obj);
        }
    } catch (err) {
        console.log("fillClassEquivalents()");
    }
    //console.log(classEquivalents.get('MGMT 110')[0]);
}

//parse taken classes against required classes
function fillClassesToTake() {
    let bool = false;
    let num = 0;
    for (let one in requiredClasses) {
        if (!takenClassNames.includes(requiredClasses[one].name)) {
            if (requiredClasses[one].classEquivalents != null) {
                let temp = requiredClasses[one].classEquivalents;
                for(let namess in temp){
                    if(takenClassNames.includes( temp[namess])){
                        bool = true;
                    }
                }
            }
            if(bool==false){
                classToTakeNames.push(requiredClasses[one].name)
                classestoTake.set(num,requiredClasses[one]);
                num++;
            }
            bool = false;
        }
    }
}

//class holds prerequisites and classEquivalents
class classRequirements {
    classRequirements(){
        this.name;
        this.preRequisites = [];
        this.classEquivalents = [];
    }
}

//Return methods
function toString() {
    k = '\n';
    key ="major: "+ targetStudent.getMajor() +"\n" + "Required classes to take: " + classestoTake.size + k;
    return key + getRequiredClasses() + k + elevtivesResponse + "\n"
        +"Taken Classes: \n"+ getTakenClasses() + "\n"
        +"Current Classes: \n"+ getCurrentClasses() + "\n"
        + "Recommended Classes List: \n"+ getFinalList() + "\n";
}

function getRequiredClasses(){
    let list = "";
    let names = [];
    for (let clss in classestoTake) {
        names.push(classestoTake[clss].name);
    }
    names.sort();
    for ( let c in names) {
        list += names[c] + "\n";
    }
    return list;
}

function getFinalList(){
    let k = "<ul>";
    for(let a in finalList){
        k += "<li>" + finalList[a] +"</li>";
    }
    return k + "</ul>";
}

function getTakenClasses() {
    let all = "<ul>";
    let takenClasses = takenClassNames;
    for( let name in targetStudent.getCurrentClasses()){
        takenClasses.pop(targetStudent[name]);
    }
    takenClasses.pop("NONE");
    takenClasses.sort();

    for (let f in takenClasses) {
        all += "<li>" + takenClasses[f] + "</li>";
    }
    return all + "</ul>";
}

function getCurrentClasses(){
    let currClasses = "<ul>";
    let r = targetStudent.getCurrentClasses();
    r.sort();
    for (let f in r) {
        currClasses += "<li>" + r[f] + "</li>";
    }
    return currClasses + "</ul>";
}
