function displayInfo(){
    var major = 3.75, overall = 3.90;
    var list = ["Take MATH 341 Next", "Take a Proper Science Next", "Take SPCH 250 Next"];

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