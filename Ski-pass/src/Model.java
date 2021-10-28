import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;

public class Model {
    HashMap<String, SkiPass> fullBase = new HashMap<String, SkiPass>();
    HashMap<String, SkiPass> blockedPasses = new HashMap<String, SkiPass>();
    Model(){

    }
    Model(SkiPass[] fullBase){
        for (SkiPass temp : fullBase){
            this.fullBase.put(temp.identificator, temp);
        }
    }
    Model(ArrayList<SkiPass> fullBase){
        for (SkiPass temp : fullBase){
            this.fullBase.put(temp.identificator, temp);
        }
    }

    public void addNewSkiPass (SkiPass pass){
        if (!pass.blocked){
            fullBase.put(pass.identificator, pass);
        } else {
            blockedPasses.put(pass.identificator, pass);
        }
    }
    public void blockSkiPass (SkiPass pass, String reason){
        pass.blocked = true;
        pass.reason = reason;
        blockedPasses.put(pass.identificator, pass);
        fullBase.remove(pass.identificator);
    }
}

class Tourniquet {
    static int success;
    static int negative;
}

class SkiPass {
    String identificator;
    String validity;
    boolean blocked;
    String type;
    String reason;

    SkiPass(){

    }
    SkiPass(String type, boolean blocked){
        this.type = type;
        this.identificator = Controller.generateIdentify(type);
        this.blocked = blocked;
    }
    SkiPass(String type, String validity, boolean blocked){
        this.type = type;
        this.identificator = Controller.generateIdentify(type);
        this.validity = validity;
        this.blocked = blocked;
    }

    public int[] getValidity(){
        return Controller.transformDate(this.validity);
    }

    @Override
    public String toString (){
        return this.type + " " + this.validity + " " + this.identificator;
    }
}

class SkiWithoutNum extends SkiPass{
    int numOfDays;
    int startHour;
    int endHour;

    SkiWithoutNum(){

    }
    SkiWithoutNum(String type, String validity, boolean blocked, int startHour, int endHour){
        super(type, validity, blocked);
        this.startHour = startHour;
        this.endHour = endHour;
    }
    SkiWithoutNum(String type, String validity, int numOfDays, boolean blocked){
        super(type, validity, blocked);
        this.numOfDays = numOfDays;
    }
}

class SkiWithNum extends SkiPass{
    int numberOfLifts;

    SkiWithNum(){

    }
    SkiWithNum(String type, String validity, boolean blocked, int numberOfLifts){
        super(type, validity, blocked);
        this.numberOfLifts = numberOfLifts;
    }
}

class SkiSeasonPass extends SkiPass{
    SkiSeasonPass(){

    }
    SkiSeasonPass(String validity, boolean blocked){
        super("SP season pass", validity, blocked);
    }
}
