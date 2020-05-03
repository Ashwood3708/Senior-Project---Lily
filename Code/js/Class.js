class Class {

    Class(){
        this.name = "NONE";
        this.subject = "comp";
        this.courseNum = "163";
        this.level = "";
        this.title = "";
        this.grade = "";
        this.creditHours = 0.0;
        this.finalCredit = 0.0;
        this.isMajorClass = false;
        this.isCompElective = false;
        this.isRequired = false;
        this.isTransferCredit = false;
    }

    setName() {
        this.name = this.subject + " " + this.courseNum;
    }
    getName() {
        return classObj.name;
    }
}