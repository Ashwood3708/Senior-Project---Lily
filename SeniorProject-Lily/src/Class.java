public class Class {

    String subject="";
    String courseNum ="";
    String level ="";
    String title = "";
    String grade = "";
    double creditHours = 0.0;
    double finalCredit = 0.0;
    boolean isMajorClass = false;
    boolean isCompElective = false;
    boolean isRequired = false;
    boolean isTransferCredit= false;


    @Override
    public String toString(){
        return subject +" "+courseNum+" "+ level+" "+title+" "+grade+ " "+ creditHours
                +" "+finalCredit+"\n "+"Major:"+isMajorClass+" Elective:"+isCompElective
                +" Required:"+isRequired+" Transfer Credit: "+isTransferCredit+"\n";
    }
}
