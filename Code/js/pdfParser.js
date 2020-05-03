/*****
 * pdfParser.js parses through the user transcript and separates the classes
 * such as major classes, current classes and electives
 */

let electives = new Set();
let gradesList = new Set();
let reqClasses = new Set();
let majorClasses = new Set();
let currentClasses = [];
let takenClasses = [];
person = new student();
numOfClasses = 0;


function split(text) {
    let read = text.split("\n");
    for (let i = 0; i < read.length; ++i) {
        read[i] = read[i].trim();
    }
    fillElectives();
    fillGradesList();
    fillMajorClasses();
    parse(read)
}

function parse(line) {
    for (let i = 0; i < line.length; i++) {
        if (line[i].includes("COURSES IN PROGRESS ") || line[i].includes("TRANSFER CREDIT ") || line[i].includes("Major and Department:") || line[i] === "Overall:" || line[i].substring(line[i].length - 1) === "R" && line[i].includes("Subject")) {

            //grabs major
            if (line[i].includes("Major and Department:")) {
                let set = line[i].split(" ");
                let yet = "";
                for (let i = 3; i < set.length; i++) {
                    yet += set[i] + " ";
                    if (yet.includes(",")) {
                        yet = yet.substring(0, yet.length - 2);
                        break;
                    }
                }

                person.setMajor(yet.trim());
            }

            //grabs gpa
            if (line[i] === "Overall:") {
                i += 6;
                person.setTotalGPA(line[i].trim());
            }

            //loop to grab all classes
            if (line[i].substring(line[i].length - 1) === "R" && line[i].includes("Subject")) { //indicates when classes appear
                //ends with: Term Totals (Undergraduate)
                while (!line[i].includes("Term Totals")) {
                    i++;
                    line[i] = line[i].trim();
                    if (line[i].includes("Term Totals (Undergraduate)")) {
                        break;
                    }
                    if (line[i].length > 3) {
                        let items = line[i].split(" ");
                        i++;
                        let creditHours = line[i].trim();
                        i++;
                        let QPoints = line[i].trim();
                        fillClasses(items, creditHours, QPoints,3,items[2].trim());
                        numOfClasses++;
                    }
                }
            }

            //grabs transfer credits
            if (line[i].includes("TRANSFER CREDIT ACCEPTED BY IN")) {
                while (!line[i].includes("Subject")) {
                    i++;
                }
                i++;
                line[i] = line[i].trim();
                while (!line[i].includes("Attempt Hours")) {
                    let items = line[i].split(" ");
                    i++;
                    let creditHours = line[i].trim();
                    i++;
                    let QPoints = line[i].trim();
                    fillClasses(items, creditHours, QPoints,2,'T');
                    numOfClasses++;
                    i++;

                }
                while (!line[i].includes("INSTITUTION CREDIT")) {
                    i++;
                }
            }

            //grabs current classes
            if (line[i].includes("COURSES IN PROGRESS ") && line[i].includes("-Top-")) {
                i++;
                i++;
                i++;
                while (!line[i].includes("Unofficial Transcript")) {
                    let items = line[i].split(" ");
                    i++;
                    var subject = items[0].trim();
                    var course = items[1].trim();
                    currentClasses.push(subject+" "+course);
                    numOfClasses++;
                    i++;
                }
            }
        }
    }
    person.list = takenClasses;
    person.setNumOfClasses(numOfClasses);
    person.setMajorGPA(generateMajorGPA());
    person.setCurrentClasses(currentClasses);
}

/*toString() {
let separator = ",";
let personInfo = "major is: " + person.getMajor() + separator;
personInfo += "totalGPA is: " + person.getTotalGPA() + separator;
personInfo += "majorGPA is: " + person.getMajorGPA() + separator;
personInfo += "totalClasses is: " + person.getNumOfClasses() + separator;
if (!(person.getMajor() === "Computer Science"))
return personInfo + "This project only supports Computer Scientist" + separator;
personInfo += "class List:" + separator;
for (let personList in person.getList()) {
personInfo += personList.toString() + separator;
}
return personInfo;
}*/

function fillClasses(items, creditHours, QPoints, point, level) {
    try {
        var classNameStartPosition = point;
        var concat = "";
        //Fill in class object
        var temp = new Class();
        temp.subject = items[0].trim();
        temp.courseNum = items[1].trim();
        temp.level = level;
        temp.setName();
        /*while (!gradesList.contains(items[classNameStartPosition])) {
        concat += items[classNameStartPosition].trim() + " ";
        classNameStartPosition++;
     }*/
        temp.title = concat.trim();
        temp.grade = items[items.length - 1].trim();
        temp.creditHours = creditHours;
        temp.finalCredit = QPoints;
        temp.isMajorClass = majorClasses.has(items[0] + items[1]);
        temp.isTransferCredit = temp.grade.indexOf("T");
        takenClasses.push(temp);
    } catch (err) {
        console.log("couldn't fill class: " + "\n string: " + items[0] + "\n credit hours: " + creditHours + "\n quality points: " + QPoints);
    }
}



function fillGradesList() {
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
    gradesList.add("I");
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
    gradesList.add("TR");
}

function fillElectives() {
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

function fillMajorClasses() {
    majorClasses.add("GEEN111");
    majorClasses.add("COMP121");
    majorClasses.add("COMP180");
    majorClasses.add("COMP163");
    majorClasses.add("GEEN163");
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
}

function generateMajorGPA() {
    let list = person.getList();
    let QPoints = 0.0, creditHours = 0.0;
    for (var i =0; i< list.length;i++) {
        if (list[i].isMajorClass) {
            QPoints += parseFloat(list[i].finalCredit);
            creditHours += parseFloat(list[i].creditHours);
        }
    }
    let rawNum =(QPoints / creditHours);
    return (Math.round(rawNum * 100) / 100).toFixed(2);
}

function getPerson() {
    return person;
}

/**
 * Gets total GPA, major GPA, and Class Recommendations list from Student's person object and send it to
 * Selenium.java to be updated on front end.
 * @return
 */

function getUserInfo() {
    return person.getTotalGPA() + " " + person.getMajorGPA();
}