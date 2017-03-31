package edu.usf.cse.danielap.blabbnlogin;

/**
 * Created by quich on 3/30/2017.
 */

//public class Whale {
//
//    public String name;
//    public String x;
//    public String y;
//
//    public Whale(){
//
//    }
//
//    public Whale(String name, String x, String y) {
//        this.name = name;
//        this.x = x;
//        this.y = y;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getX(){
//        return x;
//    }
//
//    public String getY(){
//        return y;
//    }
//
//}

public class Whale{

    public String key;
    public Whale(){

    }

    public Whale(String key){
        this.key = key;
    }

    public String getName(){
        return key;
    }
}