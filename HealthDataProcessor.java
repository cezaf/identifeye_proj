/* 
 * Identifeye Health Software Engineering Project
 * File: HealthDataProcessor.java
 * Author: Cynthia Zafiris 
 * Date: 3/27/23
 * Description: processes text file displaying patient/exam data
 */

import java.util.*;
import java.io.*;

public class HealthDataProcessor {
    
    private Map<String, String> patients; // Key-Value Pair: Patient ID - Name 
    private Map<String, HashSet<String>> exams; // Patient ID - Associated Exam IDs
    private Map<String, String> examIdentifier; // Exam ID - Patient ID; assumes that Exam IDs are unique

    public HealthDataProcessor(){
        this.patients = new HashMap<>();
        this.exams = new HashMap<>();
        this.examIdentifier = new HashMap<>();
    }
    
    // Input: String array, composed of different sections of one line in the text file (ex. "ADD", "PATIENT", "123", "JOHN", "DOE")
    // Output: none, only changes class attributes
    // Description: either adds a patient or exam file
    public void addRecord(String[] line){
        if(line[1].equals("PATIENT")){ 
            // line composition: ADD PATIENT PATIENT_ID NAME1 NAME2
            if(!this.patients.containsKey(line[2])){ 
                int counter = 4;
                while(counter <= line.length - 1){ // compiles all following text (i.e. name of the patient) into line[3]
                    line[3] += " " + line[counter++];
                }
                this.patients.put(line[2],line[3]);
            }
        } else { 
            // line composition: ADD EXAM PATIENT_ID EXAM_ID
            if(this.patients.containsKey(line[2])){  
                if(!this.examIdentifier.containsKey(line[3])){ 
                    if(!this.exams.containsKey(line[2])){
                        this.exams.put(line[2],new HashSet<>()); // initializes hashset if patient had no previous exams
                    }
                    this.exams.get(line[2]).add(line[3]);
                    this.examIdentifier.put(line[3],line[2]);
                }
            }
        }
    }

    // Input: String array, composed of different sections of one line in the text file (ex. "DEL", "PATIENT", "123")
    // Output: none, only changes class attributes
    // Description: will delete either patient or exam record
    public void deleteRecord(String[] line){
        if(line[1].equals("PATIENT")){ 
            // line composition: DEL PATIENT PATIENT_ID
            if(this.patients.containsKey(line[2])){ 
                this.patients.remove(line[2]);
                if(this.exams.get(line[2]) != null){ // if the patient had exams
                    for(String exam : this.exams.get(line[2])){ 
                        this.examIdentifier.remove(exam);
                    }
                    this.exams.remove(line[2]);
                }
            } 
        } else {  
            // line composition: DEL EXAM EXAM_ID
            if(this.examIdentifier.containsKey(line[2])){ 
                String patientID = this.examIdentifier.get(line[2]); 
                this.exams.get(patientID).remove(line[2]);
                this.examIdentifier.remove(line[2]);
            }
        }
    }

    // Input: none
    // Output: none, only prints statements to terminal
    // Description: will print output statement to the terminal containing the names, IDs, exam counts, and exam lists of all patients
    public void output(){
        for (Map.Entry<String,String> entry : this.patients.entrySet()){ 
            if(this.exams.get(entry.getKey()) == null){ // if the patient had no exams
                System.out.println("Name: " + entry.getValue() + ", ID: " + entry.getKey() + ", Exam Count: 0");
            } else { 
                System.out.println("Name: " + entry.getValue() + ", ID: " + entry.getKey() + ", Exam Count: " + this.exams.get(entry.getKey()).size() + ", Exam List: " + Arrays.toString(this.exams.get(entry.getKey()).toArray()));
            }
        }
    }

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
            } else { 
                processor.deleteRecord(line);
            }
        }
        br.close();    
        processor.output();    
    }
}
