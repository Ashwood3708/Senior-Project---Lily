var major, gpa, rec;
function displayInfo(){
    var major = getMajor(), overall = getGPA();
    var list = getList();

    var x = document.getElementById("table").rows[1].cells;
    x[0].innerHTML = major;
    x[1].innerHTML = overall;
    var y = document.getElementById("table").rows[5].cells;
    var start = "<ul>", end = "</ul>";
    for(i = 0; i < list.length; i++){
        start += "<li>" + list[i] + "</li>";
    }
    y[0].innerHTML = start + end;
}

function getInfo(){
    var banner = document.getElementById("bannerID").value;
    var pin = document.getElementById("userPIN").value;
    // send to java fail
    // get info
    // send to info funtion
    if((banner == '') && (pin == '')){
        //send to java file
        setInfo(3.75, 3,90, "Take MATH 341 Next", "Take a Proper Science Next", "Take SPCH 250 Next");
        document.location.href = "home.html";
    }else{
        document.write("Password Invalid");
    }
}

function setInfo(g1, g2, list){
    major = g1;
    gpa = g2;
    rec = list;
}

function getMajor() {
    return major;
}

function getGPA() {
    return gpa;
}

function getList() {
    return list;
}