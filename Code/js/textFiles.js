var requiredClassListTxt =(
    'engl 100, none \n'+
    'comp 163, none \n'+
    'geen 111, none \n'+
    'math 131, none \n'+
    'comp 121, none \n'+
    'engl 101, engl 100 \n'+
    'comp 180, math 131 \n'+
    'comp 167, comp163 \n'+
    'math 132, math 131 \n'+
    'spch 250, none \n'+
    'math 341, math 132, math 131 \n'+
    'comp 200, comp 163 \n'+
    'comp 280, comp 167,comp 163,comp 180, math 131 \n'+
    'comp 285, comp 280, comp 167,comp 163,comp 180, math 131 \n'+
    'comp 267, comp 280, comp 167,comp 163,comp 180, math 131 \n'+
    'comp 360, comp 280, comp 167,comp 163,comp 180, math 131 \n'+
    'comp 375, comp 280, comp 167,comp 163,comp 180, math 131 \n'+
    'engl 331, engl 101, engl 100 \n'+
    'comp 322, comp 280, comp 167,comp 163,comp 180, math 131 \n'+
    'comp 365, comp 360,comp 285,comp 280, comp 167,comp 163,comp 180, math 131 \n'+
    'comp 350, comp 375, comp 280, comp 167,comp 163,comp 180, math 131 \n'+
    'comp 390, comp 163, comp 180, comp 280, math 131 \n'+
    'comp 300, comp 167, comp163 \n'+
    'comp 385, comp 360,comp 285, comp 280, comp 167,comp 163, comp 180, math 131 \n'+
    'comp 476, comp 285, comp 280, comp 167,comp 163,comp 180, math 131 \n'+
    'comp 410, comp 285, comp 280, comp 167,comp 163,comp 180, math 131,comp 267 \n'+
    'comp 496, comp 410, comp 285, comp 280, comp 167,comp 163,comp 180, math 131, comp 267, spch 250, comp 121'
);



var classEquivalentsTxt = (
    'comp 163, geen 163' + '\n' +
    'comp 167, geen 165' + '\n' +
    'Math 340, Math 351' + '\n' +
    'Math 351, Math 340' + '\n' +
    'mgmt 110, mgmt 132, mktg 230, econ 200, econ 201' + '\n' +
    'mgmt 132, mgmt 110, mktg 230, econ 200, econ 201' + '\n' +
    'mktg 230, econ 200, econ 201,mgmt 110, mgmt 132'  + '\n' +
    'econ 200, econ 201,mgmt 110, mgmt 132, mktg 230'  + '\n' +
    'econ 201, econ 200,mgmt 110, mgmt 132, mktg 230  '+ '\n' +
    'math 224, isen 370, ecen 356' + '\n' +
    'isen 370, ecen 356,math 224'  + '\n' +
    'ecen 356, math 224, isen 370' + '\n' +
    'chem 106, phys 241' + '\n' +
    'chem 107, phys 242' + '\n' +
    'phys 241, chem106'  + '\n' +
    'phys 242, chem 107' + '\n' +
    'biol 100, biol 101, slmg 200' + '\n' +
    'biol 101, slmg 200, biol 100' + '\n' +
    'slmg 200, biol 100, biol 101');



var testTranscript = ("This is NOT an official transcript. Courses which are in progress may also be included on this transcript.\n" +
    "Institution Credit    Transcript Totals    Courses in Progress\n" +
    "Transcript Data\n" +
    "STUDENT INFORMATION\n" +
    "Student Type: Continuing\n" +
    "Curriculum Information\n" +
    "Current Program\n" +
    "Bachelor of Science\n" +
    "College: College of Engineering\n" +
    "Major and Department: Computer Science, Computer Science\n" +
    "  ***This is NOT an Official Transcript***\n" +
    "  DEGREE AWARDED\n" +
    "Pending: Bachelor of Science Degree Date:  \n" +
    "Curriculum Information\n" +
    "Primary Degree\n" +
    "College: College of Engineering\n" +
    "Major: Computer Science\n" +
    "    INSTITUTION CREDIT      -Top-\n" +
    "Term: Fall 2016\n" +
    "Additional Standing: Dean's List\n" +
    "Subject Course Level Title Grade Credit Hours Quality Points R\n" +
    "COMP 121 UG Computer Sci Freshman Colloqui A\n" +
    "1.000\n" +
    "4.00\n" +
    "    ENGL 100 UG Ideas & Their Expressions I A-\n" +
    "3.000\n" +
    "11.10\n" +
    "    GEEN 111 UG College of Engi Colloquium I A-\n" +
    "1.000\n" +
    "3.70\n" +
    "    GEEN 163 UG Intro Computer Programming A\n" +
    "3.000\n" +
    "12.00\n" +
    "    HIST 103 UG NC A&T SU:Leg Soc Act B+\n" +
    "3.000\n" +
    "9.90\n" +
    "    MATH 131 UG Calculus I A\n" +
    "4.000\n" +
    "16.00\n" +
    "    Term Totals (Undergraduate)\n" +
    "  Attempt Hours Passed Hours Earned Hours GPA Hours Quality Points GPA\n" +
    "Current Term:\n" +
    "15.000\n" +
    "15.000\n" +
    "15.000\n" +
    "15.000\n" +
    "56.70\n" +
    "3.78\n" +
    "Cumulative:\n" +
    "15.000\n" +
    "15.000\n" +
    "15.000\n" +
    "15.000\n" +
    "56.70\n" +
    "3.78\n" +
    "  Unofficial Transcript\n" +
    "Term: Spring 2017\n" +
    "Additional Standing: Dean's List\n" +
    "Subject Course Level Title Grade Credit Hours Quality Points R\n" +
    "COMP 180 UG Discrete Structures B+\n" +
    "3.000\n" +
    "9.90\n" +
    "    ENGL 101 UG Ideas & Their Expressions C\n" +
    "3.000\n" +
    "6.00\n" +
    "    GEEN 165 UG Computer Program Design B+\n" +
    "4.000\n" +
    "13.20\n" +
    "    MATH 132 UG Calculus II B\n" +
    "4.000\n" +
    "12.00\n" +
    "    SPCH 250 UG Speech Fundamentals A-\n" +
    "3.000\n" +
    "11.10\n" +
    "    Term Totals (Undergraduate)\n" +
    "  Attempt Hours Passed Hours Earned Hours GPA Hours Quality Points GPA\n" +
    "Current Term:\n" +
    "17.000\n" +
    "17.000\n" +
    "17.000\n" +
    "17.000\n" +
    "52.20\n" +
    "3.07\n" +
    "Cumulative:\n" +
    "32.000\n" +
    "32.000\n" +
    "32.000\n" +
    "32.000\n" +
    "108.90\n" +
    "3.40\n" +
    "  Unofficial Transcript\n" +
    "Term: Fall 2017\n" +
    "Additional Standing: Dean's List\n" +
    "Subject Course Level Title Grade Credit Hours Quality Points R\n" +
    "BUED 279 UG Personal Finance A-\n" +
    "3.000\n" +
    "11.10\n" +
    "    COMP 280 UG Data Structures A\n" +
    "3.000\n" +
    "12.00\n" +
    "    MATH 341 UG Intro Differential Equations B+\n" +
    "3.000\n" +
    "9.90\n" +
    "    MGMT 110 UG Business Environment A\n" +
    "3.000\n" +
    "12.00\n" +
    "    PHYS 241 UG General Physics I C+\n" +
    "3.000\n" +
    "6.90\n" +
    "    PHYS 251 UG General Physics I Lab A-\n" +
    "1.000\n" +
    "3.70\n" +
    "    Term Totals (Undergraduate)\n" +
    "  Attempt Hours Passed Hours Earned Hours GPA Hours Quality Points GPA\n" +
    "Current Term:\n" +
    "16.000\n" +
    "16.000\n" +
    "16.000\n" +
    "16.000\n" +
    "55.60\n" +
    "3.47\n" +
    "Cumulative:\n" +
    "48.000\n" +
    "48.000\n" +
    "48.000\n" +
    "48.000\n" +
    "164.50\n" +
    "3.42\n" +
    "  Unofficial Transcript\n" +
    "Term: Spring 2018\n" +
    "Additional Standing: Dean's List\n" +
    "Subject Course Level Title Grade Credit Hours Quality Points R\n" +
    "COMP 200 UG Computer Sci Sophomore Colloq A\n" +
    "1.000\n" +
    "4.00\n" +
    "    COMP 267 UG Data Base Design A-\n" +
    "3.000\n" +
    "11.10\n" +
    "    COMP 285 UG Analysis of Algorithms A\n" +
    "3.000\n" +
    "12.00\n" +
    "    MATH 351 UG Linear Algebra & Matrix Theory C+\n" +
    "3.000\n" +
    "6.90\n" +
    "    PHIL 201 UG Business Ethics A\n" +
    "3.000\n" +
    "12.00\n" +
    "    PHYS 242 UG General Physics II B+\n" +
    "3.000\n" +
    "9.90\n" +
    "    PHYS 252 UG General Physics II Lab C+\n" +
    "1.000\n" +
    "2.30\n" +
    "    Term Totals (Undergraduate)\n" +
    "  Attempt Hours Passed Hours Earned Hours GPA Hours Quality Points GPA\n" +
    "Current Term:\n" +
    "17.000\n" +
    "17.000\n" +
    "17.000\n" +
    "17.000\n" +
    "58.20\n" +
    "3.42\n" +
    "Cumulative:\n" +
    "65.000\n" +
    "65.000\n" +
    "65.000\n" +
    "65.000\n" +
    "222.70\n" +
    "3.42\n" +
    "  Unofficial Transcript\n" +
    "Term: Fall 2018\n" +
    "Additional Standing: Chancellor's List\n" +
    "Subject Course Level Title Grade Credit Hours Quality Points R\n" +
    "BIOL 100 UG Biological Science A\n" +
    "4.000\n" +
    "16.00\n" +
    "    COMP 360 UG Programming Languages B\n" +
    "3.000\n" +
    "9.00\n" +
    "    COMP 375 UG Comp Architecture & Org A-\n" +
    "3.000\n" +
    "11.10\n" +
    "    COMP 390 UG Social Implications Computing A\n" +
    "3.000\n" +
    "12.00\n" +
    "    ENGL 331 UG Writing Science & Technology A\n" +
    "3.000\n" +
    "12.00\n" +
    "    MATH 224 UG Intro Probability & Statistics A\n" +
    "3.000\n" +
    "12.00\n" +
    "    Term Totals (Undergraduate)\n" +
    "  Attempt Hours Passed Hours Earned Hours GPA Hours Quality Points GPA\n" +
    "Current Term:\n" +
    "19.000\n" +
    "19.000\n" +
    "19.000\n" +
    "19.000\n" +
    "72.10\n" +
    "3.79\n" +
    "Cumulative:\n" +
    "84.000\n" +
    "84.000\n" +
    "84.000\n" +
    "84.000\n" +
    "294.80\n" +
    "3.50\n" +
    "  Unofficial Transcript\n" +
    "Term: Spring 2019\n" +
    "Additional Standing: Dean's List\n" +
    "Subject Course Level Title Grade Credit Hours Quality Points R\n" +
    "COMP 300 UG Computer Science Jr Colloquium B\n" +
    "1.000\n" +
    "3.00\n" +
    "    COMP 350 UG Operating Systems B\n" +
    "3.000\n" +
    "9.00\n" +
    "    COMP 365 UG AI & Machine Learning A\n" +
    "3.000\n" +
    "12.00\n" +
    "    COMP 410 UG Software Engineering A-\n" +
    "3.000\n" +
    "11.10\n" +
    "    COMP 476 UG Networked Computer Systems A\n" +
    "3.000\n" +
    "12.00\n" +
    "    Term Totals (Undergraduate)\n" +
    "  Attempt Hours Passed Hours Earned Hours GPA Hours Quality Points GPA\n" +
    "Current Term:\n" +
    "13.000\n" +
    "13.000\n" +
    "13.000\n" +
    "13.000\n" +
    "47.10\n" +
    "3.62\n" +
    "Cumulative:\n" +
    "97.000\n" +
    "97.000\n" +
    "97.000\n" +
    "97.000\n" +
    "341.90\n" +
    "3.52\n" +
    "  Unofficial Transcript\n" +
    "Term: Fall 2019\n" +
    "Subject Course Level Title Grade Credit Hours Quality Points R\n" +
    "COMP 322 UG Internet Systems C+\n" +
    "3.000\n" +
    "6.90\n" +
    "    COMP 340 UG Game Intelligence B-\n" +
    "3.000\n" +
    "8.10\n" +
    "    COMP 385 UG Theory of Computing B+\n" +
    "3.000\n" +
    "9.90\n" +
    "    COMP 495 UG Senior Project I A\n" +
    "3.000\n" +
    "12.00\n" +
    "    Term Totals (Undergraduate)\n" +
    "  Attempt Hours Passed Hours Earned Hours GPA Hours Quality Points GPA\n" +
    "Current Term:\n" +
    "12.000\n" +
    "12.000\n" +
    "12.000\n" +
    "12.000\n" +
    "36.90\n" +
    "3.07\n" +
    "Cumulative:\n" +
    "109.000\n" +
    "109.000\n" +
    "109.000\n" +
    "109.000\n" +
    "378.80\n" +
    "3.47\n" +
    "  Unofficial Transcript\n" +
    "TRANSCRIPT TOTALS (UNDERGRADUATE)      -Top-\n" +
    "  Attempt Hours Passed Hours Earned Hours GPA Hours Quality Points GPA\n" +
    "Total Institution:\n" +
    "109.000\n" +
    "109.000\n" +
    "109.000\n" +
    "109.000\n" +
    "378.80\n" +
    "3.47\n" +
    "Total Transfer:\n" +
    "0.000\n" +
    "0.000\n" +
    "0.000\n" +
    "0.000\n" +
    "0.00\n" +
    "0.00\n" +
    "Overall:\n" +
    "109.000\n" +
    "109.000\n" +
    "109.000\n" +
    "109.000\n" +
    "378.80\n" +
    "3.47\n" +
    "  Unofficial Transcript\n" +
    "COURSES IN PROGRESS       -Top-\n" +
    "Term: Spring 2020\n" +
    "Subject Course Level Title Credit Hours\n" +
    "COMP 320 UG Fundamentals of Cyber Security\n" +
    "3.000\n" +
    "COMP 496 UG Senior Project II\n" +
    "3.000\n" +
    "FCS 135 UG Food and Man's Survival\n" +
    "3.000\n" +
    "FCS 245 UG Introduction to Food Science\n" +
    "3.000\n" +
    "  Unofficial Transcript");