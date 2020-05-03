/**
 * Student.js is a class that consist of student information
 *
 */

class student {
    student(){
        this.major = "undeclared";
        this.majorGPA = 0.0;
        this.totalGPA = "";
        this.numOfClasses = 0;
        this.list = [];
        this.names = [];
        this.currentClasses = [];
    }


    getNames() {
        return this.names;
    }


    getMajor() {
        return this.major;
    }


    setMajor(major) {
        this.major = major;
    }

//TODO- gotta format output #.##

    getMajorGPA() {
        return this.majorGPA;
    }


    setMajorGPA(major) {
        this.majorGPA = major;
    }


    getTotalGPA() {
        return this.totalGPA;
    }


    setTotalGPA(totalGPA) {
        this.totalGPA = totalGPA;
    }


    getNumOfClasses() {
        return this.numOfClasses;
    }


    setNumOfClasses(numOfClasses) {
        this.numOfClasses = numOfClasses;
    }


    getList() {
        return this.list;
    }


    getCurrentClasses() {
        return this.currentClasses;
    }

    setCurrentClasses(currClasses) {
        this.currentClasses = currentClasses;
    }

//@param arrayList of taken classes

    setList(list) {
        this.list = list;
        //console.log('student.setList:'+this.list);
    }


    addNames() {
        var nlist = [];
        for(var element in this.list){
            nlist.push(this.list[element].name);
        }
        this.names = nlist;
    }
}

/**
 arraylist notes
 --------------------
 var array = [];
 array.push(val);
 array.pop(val)

 hashset/hashmap notes
 -------------------------
 var hash = {
  1:true,
  2:true,
  7:true
  //etc...
};

 var checkValue = function(value){
  return hash[value] === true;
};

 checkValue(7); // => true
 checkValue(3); // => false
 */