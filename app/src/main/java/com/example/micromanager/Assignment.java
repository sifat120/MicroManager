package com.example.micromanager;

public class Assignment {
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

    public String getName(){
        return name;
    }

    public int getPriority(){
        return priority;
    }

    private void assignPriority(String type){
        if(type.equalsIgnoreCase("Midterm") || type.equalsIgnoreCase("Final")){
            priority = 4;
        }else if(type.equalsIgnoreCase("Homework")){
            priority = 3;
        }else if(type.equalsIgnoreCase("Extra Credit")){
            priority = 2;
        }else
            priority = 0;
    }

    public String toString(){
        return name + ", " + due_date + ", "+ type;
    }

}
