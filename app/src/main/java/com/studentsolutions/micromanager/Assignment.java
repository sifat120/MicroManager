package com.studentsolutions.micromanager;

public class Assignment implements Comparable<Assignment>{
    private String name;
    private String type;
    private int priority;
    private String due_date;

    public Assignment(String name, String type, String due_date){
        this.name = name;
        this.type = type;
        this.due_date = due_date;
        assignPriority(type);
    }

    public String getDueDate(){
        return due_date;
    }

    public int getMonth(){
        return Integer.parseInt(this.due_date.substring(0,2));
    }

    public int getDay(){
        return Integer.parseInt(this.due_date.substring(3,5));
    }

    public int getYr(){
        return Integer.parseInt(this.due_date.substring(6));
    }
    public String getName(){
        return name;
    }

    public int getPriority(){
        return priority;
    }

    private void assignPriority(String type){
      /*  if(type.equalsIgnoreCase("Homework")) {
            priority = 5;
        }else if(type.equalsIgnoreCase("Test")){
            priority = 4;
        }else if (type.equalsIgnoreCase("Midterm") || type.equalsIgnoreCase("Final"){
            priority = 3;
        }else if(type.equalsIgnoreCase("Extra Credit")){
            priority = 2;
        }else{
            priority = 0;
        }*/
    }

    public int compareTo(Assignment other){ //still needs implementation
        if(this.type.equalsIgnoreCase(other.type)){
        }
        return -1;
    }

    public String toString(){
        return name + ", " + due_date + ", "+ type;
    }

}
