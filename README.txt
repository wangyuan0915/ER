Android Application - Phase III

Our design is very simple and easy-to-used.

log in:
You need enter the correct username and password which matches the status. The information 
have already stored in file "password.txt".


using the application
nurse:
- the nurse need to fill all of the blanks when update records. If any blank has not been filled,
the application will remind you to complete it.
- the application will calculate the new urgency score when update the vital sign
- please enter integers when record patients' dob, arrive time, heart rate, systolic and
diastolic blood pressure or the application won't show anything when you enter non-numeric 
character.
- However, temperature can be entered as a double.
- Patient's age is recorded as (2013 - year of birth)
- patients are allowed to visit the doctor once, if the system has found the certain patient
have already visited doctors, it will reject to record other visiting time.


physician:
physician need to fill all of the blanks when update prescription information.


all of the patients' record is written in "patient.txt" which is stored in 
/data/data/com.example.phase3/files/