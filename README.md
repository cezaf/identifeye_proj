# Identifeye Health Software Engineering Project - "identifeye_proj" 
  ## Overview:
  The primary file for this project is "HealthDataProcessor," written in Java and using standard Java imports. It contains three attributes, all of which are HashMaps: 
  
  
  - "patients": pairs patient IDs with patient names, used for storing patient records
  - "exams": pairs patient IDs with all of the exam IDs assigned to each patient, used for storing exam records per patient 
  - "examIdentifier": pairs exam IDs with associated patient IDs, used for quickly accessing what exam records exist in the system and what patient they are assigned to
  
  The file contains three methods: 
  
  - "addRecord": adds a patient or exam record into the system by manipulating the three class attributes
  - "deleteRecord": deletes a patient or exam record by manipulating the three class attributes 
  - "output": prints a statement to the terminal containing information about each patient in the system, including patient name, ID number, exam count, and a list of each patient's exams
  
  The file also contains a "main" function, which will run the overall program. There are two sample input documents for this program, "test_identifeye.txt" and "test_identifeye_2.txt", which are discussed below.
  ## Testing: 
 This program was tested with two input documents. One of these documents is the same example given in the project instructions, and the other is a more extensive iteration of that example that checks all edge cases and all methods under different circumstances (ex. it includes deleting a patient record that has no exams and a patient record that has two exams to ensure that the program can handle both cases). 
  With the first input document, the program is able to give the same output as listed in the instructions, showing that program can generally perform the desired functions. With the second input document, the 
  program is able to give an accurate output (I wrote the computed output by hand to compare with the program), proving that it can pass all described edge cases and run all methods properly. Additionally, 
  I printed all attributes after each line was read while running both files to ensure that the system was adding or deleting records correctly. 
  ## How To Run:  
  Open the Terminal and ensure that you are in the same directory as all the files featured in this repository (including the input text documents). As it is written in Java, the program will need to be compiled first. 
After, the file needs to be run with the specified input text document (there are two featured for this program). The two commands should generally look like this: 

    javac HealthDataProcessor.java
    java HealthDataProcessor test_identifeye.txt
  
  ## Test 1: "test_identifeye.txt"  
  - Description:  
  
    This input document features the same example given in the project instructions.
  - Expected Output: 
  
  
    Name: JOHN DOE, ID: 123, Exam Count: 0<br>
    Name: JANE CROW, ID: 789, Exam Count: 2, Exam List: [554, 445]
  ## Test 2: "test_identifeye_2.txt" 
  - Description: 
  
    This input document is an expansion of the given example in the project instructions. It checks for all edge cases in the program and more extensively tests the code.
  - Expected Output: 
  
    Name: JOE JOHN SCMOE II, ID: 321, Exam Count: 2, Exam List: [440, 444]<br>
    Name: JANE CROW, ID: 789, Exam Count: 1, Exam List: [445]<br>
    Name: JANE BOW JR, ID: 987, Exam Count: 0
