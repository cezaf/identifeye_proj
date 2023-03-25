/* 
 * Identifeye Health Software Engineering Project
 * File: HealthDataProcessor.java
 * Author: Cynthia Zafiris 
 * Date: 
 * Description: processes text file displaying patient/exam data
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class HealthDataProcessor {
    
    private Map<String, String> patient; // Key-Value Pair: Patient ID - Name 
    private Map<String, HashSet<String>> exams; // Patient ID - Associated Exam IDs
    private Map<String, String> examIdentifier; // Exam ID - Patient ID; assumes that Exam IDs are unique

    public HealthDataProcessor(){
        this.patient = new HashMap<>();
        this.exams = new HashMap<>();
        this.examIdentifier = new HashMap<>();
    }
    
    // Input: String array, composed of different sections of one line in the text file (ex. "ADD", "PATIENT", "123", "JOHN", "DOE")
    // Output: void, only changes above attributes
    // Description: either adds a patient or exam file
    public void addRecord(String[] line){
        if(line[1].equals("PATIENT")){ // adding patient record
            // composition: ADD PATIENT ID NAME1 NAME2
            if(!this.patient.containsKey(line[2])){ // if patient with this ID does not exist
                int counter = 4;
                while(counter <= line.length - 1){ // compiles all following text (i.e. name of the patient) into line[3]
                    line[3] += " " + line[counter++];
                }
                this.patient.put(line[2],line[3]);
            }
        } else { // equals "EXAM" - adding exam record
            // composition: ADD EXAM PATIENT_ID EXAM_ID
            if(this.patient.containsKey(line[2])){ // if patient with this ID does exist
                if(!this.exams.containsKey(line[2])){
                    this.exams.put(line[2],new HashSet<>());
                } 
                if(!this.exams.get(line[2]).contains(line[3])){
                    this.exams.get(line[2]).add(line[3]);
                    this.examIdentifier.put(line[3],line[2]);
                }
            }
        }
    }

    // will delete either patient or exam record
    // public void delRecord(){
    // }

    // will print output of processor
    // public void output(){
    // }


    // main function
    public static void main(String[] args) throws Exception{
        HealthDataProcessor processor = new HealthDataProcessor();
        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        while ((str = br.readLine()) != null){
            String[] line = str.split("\\s+");
            if(line[0].equals("ADD")){
                processor.addRecord(line);
            } else { // equals "DEL"
                //processor.delRecord(line);
            }
        }
        br.close();
        System.out.println(processor.patient.entrySet());
        System.out.println(processor.exams.entrySet());        
    }
}
