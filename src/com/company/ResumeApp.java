package com.company;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import sun.plugin.javascript.navig.Array;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResumeApp {

    //driver to test functionality
    public static void main(String[] args) {


        ArrayList<Robo_Resume> resume_db = new ArrayList<>();
        //       Robo_Resume resume = new Robo_Resume();
        int resume_counter = 0;

        boolean main_menu = true;


        String selection = "";

        LinkedHashMap<String, Robo_Resume> resume_map = new LinkedHashMap<>();
        Scanner keybd = new Scanner(System.in);

        while (main_menu) {

            System.out.println((char) 27 + "[34m" + "\nWelcome to Beginner's Resume Application" + "\n=============================================" + (char) 27 + "[30m");
            System.out.println("\nPlease select one of the numerical option below:");
            System.out.println("1. Input new resume data");
            System.out.println("2. View resume of specific candidate");
            System.out.println("3. Change resume data for specific candidate");
            System.out.println("4. Search skill from Resume Database");
            System.out.println("5. Display all resumes. Exit program.  \n\nSelection: ");
            selection = keybd.nextLine();

            while (!selection.matches("[1-5]?")) {
                System.out.println("Invalid selection. Numbers 1-5 only.  Please re-enter your selection.");
                selection = keybd.nextLine();
            }

            switch (selection) {
                case "1":
                    resume_db.add(inputResume());
                    System.out.println("\nResume data added.  Press enter key to go back to main menu.");
                    keybd.nextLine();
                    break;
                case "2":
                    viewResume(resume_db);
                    break;
                case "3":
                    changeResume(resume_db);
                    break;
                case "4":
                    searchSkill(resume_db);
                    break;
                case "5":
                    listAllResumes(resume_db);
                    main_menu = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
                    break;
            }

        }
    }

    public static void searchSkill(ArrayList<Robo_Resume> resume_db) {
        Scanner keybd = new Scanner(System.in);
        Recruiter tech_recruiter = new Recruiter();
        ArrayList<String> skill_found_resumes = new ArrayList<>();

        String skill = "";

        if (resume_db.size() == 0) {
            System.out.println((char) 27 + "[31m" + "There is currently no resume in the database to view.  " + (char) 27 + "[30m" +
                    "Please input and create resume in the main menu.");
        } else {

            System.out.println("\nEnter the skill you want to search:");
            skill = keybd.nextLine();

            while (skill.equals("")) {
                System.out.println("Don't leave blank.  Please re-enter the skill you want to search");
                skill = keybd.nextLine();
            }


            skill_found_resumes = tech_recruiter.searchSkill(resume_db, skill);


            if (skill_found_resumes.size() == 0)
                System.out.println("There is currently no resume containing this skill.");

            else {
                System.out.println("\nHere are the resumes with the skill you are looking for:" + (char) 27 + "[34m");
                for (int i = 0; i < skill_found_resumes.size(); i++) {
                    System.out.println(skill_found_resumes.get(i));
                    System.out.print((char) 27 + "[30m");
                }
            }
        }

        System.out.println("Press enter key to continue.");
        keybd.nextLine();

    }

    public static void listAllResumes(ArrayList<Robo_Resume> resume_db) {
        for (int i = 0; i < resume_db.size(); i++) {
            System.out.println(resume_db.get(i).displayResume());
            System.out.println("------------------------------------------");
        }
    }

/*    public static boolean validateName (String name){
        if (name.matches("[A-Z][a-z]+( [A-Z][a-z]+)?")) return true;
        else if (name.matches("[A-Z][a-z]+( [A-Z])+[a-z]+[A-Z][a-z]+?")) return true;
        else if (name.matches("[A-Z][a-z]+( [A-Z][\\'\\-\\.][A-Z][a-z]+)?")) return true;
        else if (name.matches("[A-Z][a-z]+( [A-Z])+[a-z]+[\\'\\-\\.][A-Z][a-z]+?")) return true;
        else if (name == null) return false;
        else return false;
    }
*/

    public static void viewResume(ArrayList<Robo_Resume> resume_db) {
        boolean name_loop = true, candidate = false;
        Scanner keybd = new Scanner(System.in);
        String name = "";

        if (resume_db.size() == 0) {
            System.out.println((char) 27 + "[31m" + "There is currently no resume in the database to view.  " + (char) 27 + "[30m" +
                    "Please input and create resume in the main menu.  Press Enter key to continue.");
            keybd.nextLine();
            name_loop = false;
            candidate = true;

        } else {
            System.out.println("There are currently " + (char) 27 + "[34m" + resume_db.size() + (char) 27 + "[30m" + " resumes in the database.");
            System.out.println("Here are the first few resumes in the database: " + (char) 27 + "[34m");
            if (resume_db.size() < 11) {
                for (int i = 0; i < resume_db.size(); i++)
                    System.out.println(resume_db.get(i).getName());
            }

            System.out.print((char) 27 + "[30m");
        }

        while (name_loop) {
            System.out.println("\nEnter name of candidate on resume you want to view: \n(First Name <space> Last Name.  Capitalize first letters.)");
            name = keybd.nextLine();

            if (resume_db.get(0).validateName(name))
                name_loop = false;
            else
                System.out.println("Invalid input. Please enter name in format (First Name <space> Last Name with Capitalize first letters.");
        }

        for (int i = 0; i < resume_db.size(); i++) {
            if (resume_db.get(i).getName().equalsIgnoreCase(name)) {
                System.out.println(resume_db.get(i).displayResume());
                System.out.println("-------------------------------------------");
                System.out.print("Press enter key to continue.");
                keybd.nextLine();
                candidate = true;
            }
        }

            if (candidate == false) {
                System.out.println("Resume not found. Press enter key to continue.");
                keybd.nextLine();
            }


    }

    public static void changeResume(ArrayList<Robo_Resume> resume_db) {

        boolean loop = true, candidate = false;
        boolean name_loop = true;
        Scanner keybd = new Scanner(System.in);
        String name = "";


        if (resume_db.size() == 0) {
            System.out.println((char) 27 + "[31m" + "There is currently no resume in the database to change.  " + (char) 27 + "[30m" +
                    "Please input and create resume in the main menu.");
            name_loop = false;
            candidate = true;
            System.out.println("Press Enter key to continue.");
            keybd.nextLine();

        } else {
            System.out.println("There are currently " + (char) 27 + "[34m" + resume_db.size() + (char) 27 + "[30m" + " resumes in the database.");
            System.out.println("Here are the first few resumes in the database: " + (char) 27 + "[34m");
            if (resume_db.size() < 11) {
                for (int i = 0; i < resume_db.size(); i++)
                    System.out.println(resume_db.get(i).getName());
            }
            ;
            System.out.print((char) 27 + "[30m");
        }


        while (name_loop) {
            System.out.println("\nEnter name of candidate on resume you want to change: \n(First Name <space> Last Name.  Capitalize first letters.)");
            name = keybd.nextLine();

            if (name.matches("[A-Z][a-z]+( [A-Z][a-z]+)?"))
                name_loop = false;
            else
                System.out.println("Invalid input. Please enter name in format (First Name <space> Last Name with Capitalize first letters.");

        }

        for (int i = 0; i < resume_db.size(); i++) {
            if (resume_db.get(i).getName().equalsIgnoreCase(name)) {
                candidate = true;
                changeResumeData_Menu(resume_db, i);
            } else candidate = false;

        }

        if (!candidate) {
            System.out.println("Resume not found. Press enter key to continue.");
            keybd.nextLine();
        }

    }


    public static void changeResumeData_Menu(ArrayList<Robo_Resume> resume_db, int index) {

        Scanner keybd = new Scanner(System.in);
        boolean change_data_menu = true;
        String selection = "";

        while (change_data_menu) {

            System.out.println("\nWhat data on resume of " + (char)27 +"[34m" +
                    resume_db.get(index).getName() + (char)27 +"[30m" + " would you like to change?");
            System.out.println("1. Name");
            System.out.println("2. Phone Number");
            System.out.println("3. Email address");
            System.out.println("4. Education");
            System.out.println("5. Experience");
            System.out.println("6. Skillset");
            System.out.println("7. Exit to Main Menu \n\nSelection:");
            selection = keybd.nextLine();

            while (!selection.matches("[1-7]?")) {
                System.out.println("Invalid selection. Numbers 1-7 only.  Please re-enter your selection.");
                selection = keybd.nextLine();
            }

            switch (selection) {
                case "1":
                    changeResumeData_Name(resume_db, index);
                    break;

                case "2":
                    changeResumeData_Phone(resume_db, index);
                    break;

                case "3":
                    changeResumeData_Email(resume_db, index);
                    break;

                case "4":
                    changeResumeData_Education(resume_db, index);
                    //System.out.println((char)27 +"[31m" + "This feature has not been activated yet." + (char)27 + "[30m");
                    break;
                case "5":
                    changeResumeData_Experience(resume_db, index);
                    //System.out.println((char) 27 + "[31m" + "This feature has not been activated yet." + (char) 27 + "[30m");
                    break;
                case "6":
                    changeResumeData_Skills(resume_db, index);
                    //System.out.println((char) 27 + "[31m" + "This feature has not been activated yet." + (char) 27 + "[30m");
                    break;
                case "7":
                    change_data_menu = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
                    break;
            }

        }


    }

    public static void changeResumeData_Name(ArrayList<Robo_Resume> resume_db, int index) {
        Scanner keybd = new Scanner(System.in);
        String new_name = "";
        boolean name_loop = true;

        while (name_loop) {
            System.out.println("Current name on the resume you want to change is: " + (char) 27 + "[31m" + resume_db.get(index).getName() + (char) 27 + "[30m");
            System.out.println("Enter the new name you want for this resume:  (Type 'cancel' for no change)");
            new_name = keybd.nextLine();
            if (resume_db.get(index).validateName(new_name)) {
                resume_db.get(index).setName(new_name);
                System.out.println("Name successfully changed. Press enter key to continue.");
                name_loop = false;
                keybd.nextLine();
            }
            else if (new_name.equalsIgnoreCase("cancel")) name_loop = false;
            else System.out.println("Invalid input. Please enter name in format (First Name <space> Last Name with Capitalized first letters).");
        }
    }

    public static void changeResumeData_Phone(ArrayList<Robo_Resume> resume_db, int index) {
        Scanner keybd = new Scanner(System.in);
        String new_phone = "";
        boolean phone_loop = true;

        while (phone_loop) {
            System.out.println("Current phone number on this resume is: " + (char) 27 + "[31m" + resume_db.get(index).getPhone() + (char) 27 + "[30m");
            System.out.println("Enter the new contact phone number you want for this resume: \nDigits only or digits with dashes. (Type 'cancel' for no change).");
            new_phone = keybd.nextLine();
            if (resume_db.get(index).isPhoneValid(new_phone)) {
                resume_db.get(index).setPhone(new_phone);
                System.out.println("Phone number successfully changed. Press enter key to continue.");
                phone_loop = false;
                keybd.nextLine();
            }
            else if (new_phone.equalsIgnoreCase("cancel")) phone_loop = false;
            else System.out.println("Invalid input. Please enter phone number with digits only or digits with dashes.");
        }
    }

    public static void changeResumeData_Email(ArrayList<Robo_Resume> resume_db, int index) {
        Scanner keybd = new Scanner(System.in);
        String new_email = "";
        boolean email_loop = true;

        while (email_loop) {
            System.out.println("Current email on the resume you want to change is: " + (char) 27 + "[31m" + resume_db.get(index).getEmail() + (char) 27 + "[30m");
            System.out.println("Enter the new email you want for this resume: \nFormat string@company.com. (Type 'cancel' for no change).");
            new_email = keybd.nextLine();
            if (resume_db.get(index).isEmailValid(new_email)) {
                resume_db.get(index).setName(new_email);
                System.out.println("Email successfully changed. Press enter key to continue.");
                email_loop = false;
                keybd.nextLine();
            }
            else if (new_email.equalsIgnoreCase("cancel")) email_loop = false;
            else System.out.println("Invalid input. Please enter email in the format string@company.com.");
        }
    }


    public static void changeResumeData_Education(ArrayList<Robo_Resume> resume_db, int index) {
        Scanner keybd = new Scanner(System.in);
        String selection = "";
        String choice_input = "";

        if (resume_db.get(index).getEducation().size() == 0) {
            System.out.println((char) 27 + "[31m" + "This resume has no existing Education record. " + (char) 27 + "[30m" +
                    "Do you want to input new Education data? y/yes or n/no");
            choice_input = keybd.nextLine();
            while (!choice_input.equalsIgnoreCase("y") &&
                    !choice_input.equalsIgnoreCase("yes") &&
                    !choice_input.equalsIgnoreCase("n") &&
                    !choice_input.equalsIgnoreCase("no")
            ) {
                System.out.println("Invalid input.  Do you want to input new Education data? y/yes or n/no");
                choice_input = keybd.nextLine();
            }

            if (choice_input.equalsIgnoreCase("y") || choice_input.equalsIgnoreCase("yes")) {
                addEducation(resume_db.get(index).getEducation());
                System.out.print("Education data added successfully.  Press enter to return to previous menu.");
                keybd.nextLine();
            }
        }
        else {
            //while (ed_loop) {
            System.out.println("Current Education data on the resume you want to change is/are: " + (char) 27 + "[31m");
            System.out.print(resume_db.get(index).displayEducation(resume_db.get(index).getEducation()));
            System.out.print((char) 27 + "[30m");
            System.out.println("1. Add additional Education data.");
            System.out.println("2. Remove an existing Education data.");
            System.out.println("3. Return to previous menu. \n\nSelection:");
            selection = keybd.nextLine();
            while (!selection.matches("[1-3]?")) {
                System.out.println("Invalid selection. Numbers 1-3 only.  Please re-enter your selection.");
                selection = keybd.nextLine();
            }
        }

        if (selection.equals("1")) {
            addEducation(resume_db.get(index).getEducation());
            System.out.print("Additional Education data added successfully.  Press enter to return to previous menu.");
            keybd.nextLine();
        }
        else if (selection.equals("2")) {
            removeEducation(resume_db.get(index).getEducation());
            keybd.nextLine();
        }

    } //end changeResumeData_Education

   public static void addEducation(ArrayList<Education> education_data){

       Scanner keybd = new Scanner(System.in);

       int index = 0;

       String degree = "";
       String degree_abb = "";
       String graduate_ed = "";

        education_data.add(new Education());
        index = education_data.size()-1;
        System.out.println("\nEnter name of school/college/university attended: ");
        education_data.get(index).setSchool(keybd.nextLine());
        System.out.println("Enter year last attended: ");
        education_data.get(index).setYear(keybd.nextLine());
        System.out.println("Did you graduate? y/yes or n/no");
        graduate_ed = keybd.nextLine();
        while (!graduate_ed.equalsIgnoreCase("y") &&
                !graduate_ed.equalsIgnoreCase("yes") &&
                !graduate_ed.equalsIgnoreCase("n") &&
                !graduate_ed.equalsIgnoreCase("no")
        ) {
            System.out.println("Invalid input.  Did you graduate? y/yes or n/no");
            graduate_ed = keybd.nextLine();
        }

        if (graduate_ed.equalsIgnoreCase("y") || graduate_ed.equalsIgnoreCase("yes")) {
                System.out.println("Select highest degree earned from this school/college/university: (1-5 numbers only)");
                System.out.println("1_Associates 2_Bachelors 3_Masters 4_Doctorate 5_Post_Doctorate");
                degree = keybd.nextLine();

                while (!degree.matches("[1-5]?")) {
                    System.out.println("Invalid input.  Numbers 1-5 only.  Please enter number corresponding to highest degree earned at this school.");
                    degree = keybd.nextLine();
                }

                if (degree.equals("1")) degree_abb = "A.S. or A.A.";
                else if (degree.equals("2")) degree_abb = "B.S. or B.A.";
                else if (degree.equals("3")) degree_abb = "M.S. or M.A.";
                else if (degree.equals("4")) degree_abb = "Ph.D. or D.Sc.";
                else if (degree.equals("5")) degree_abb = "Fellows or Post-Doc";

                education_data.get(index).setDegree(degree_abb);

                System.out.println("Enter degree's major: ");
                education_data.get(index).setMajor(keybd.nextLine());
        }

        else if (graduate_ed.equalsIgnoreCase("n") || graduate_ed.equalsIgnoreCase("no")) {
                education_data.get(index).setDegree("Did not graduate");
                System.out.println("Enter major of program you were studying: ");
                education_data.get(index).setMajor(keybd.nextLine());
            }

   } //end addEducation

    public static void removeEducation(ArrayList<Education> education_data) {
        Scanner keybd = new Scanner(System.in);
        int remove_choice = 0;

        if (education_data.size() == 0) {
            System.out.println((char) 27 + "[31m" + "This resume has no existing Education record." + (char) 27 + "[30m" +
                    "Nothing to remove.");
            }
        else {
            System.out.println("Current Education data on the resume you want to change is/are: " + (char) 27 + "[31m");
            for (int i=0; i < education_data.size(); i++){
                System.out.println((i+1) +". " + education_data.get(i).displayEducation());

            }

            System.out.print((char) 27 + "[30m");
            System.out.println("Enter the number corresponding to the Education data entry you want to remove:");
            remove_choice = keybd.nextInt();
            education_data.remove(remove_choice-1);
            System.out.println("Education data entry successfully removed.  Press enter to return to previous menu.");
        }

    } //end removeEducation


    public static void changeResumeData_Experience(ArrayList<Robo_Resume> resume_db, int index) {
        Scanner keybd = new Scanner(System.in);
        String selection = "";
        String choice_input = "";

        if (resume_db.get(index).getExperience().size() == 0) {
            System.out.println((char) 27 + "[31m" + "This resume has no existing Experience data. " + (char) 27 + "[30m" +
                    "Do you want to input new Experience data? y/yes or n/no");
            choice_input = keybd.nextLine();
            while (!choice_input.equalsIgnoreCase("y") &&
                    !choice_input.equalsIgnoreCase("yes") &&
                    !choice_input.equalsIgnoreCase("n") &&
                    !choice_input.equalsIgnoreCase("no")
            ) {
                System.out.println("Invalid input.  Do you want to input new Experience data? y/yes or n/no");
                choice_input = keybd.nextLine();
            }

            if (choice_input.equalsIgnoreCase("y") || choice_input.equalsIgnoreCase("yes")) {
                addExperience(resume_db.get(index).getExperience());
            }
        }
        else {
            System.out.println("Current Experience data on the resume you want to change is/are: " + (char) 27 + "[31m");
            System.out.print(resume_db.get(index).displayExperience(resume_db.get(index).getExperience()));
            System.out.print((char) 27 + "[30m");
            System.out.println("1. Add additional Experience data.");
            System.out.println("2. Remove an existing Experience data.");
            System.out.println("3. Return to previous menu. \n\nSelection:");
            selection = keybd.nextLine();
            while (!selection.matches("[1-3]?")) {
                System.out.println("Invalid selection. Numbers 1-3 only.  Please re-enter your selection.");
                selection = keybd.nextLine();
            }
        }

        if (selection.equals("1")) {
            addExperience(resume_db.get(index).getExperience());
            System.out.print("Additional Experience data added successfully.  Press enter to return to previous menu.");
            keybd.nextLine();
        }
        else if (selection.equals("2")) {
            removeExperience(resume_db.get(index).getExperience());
            keybd.nextLine();
        }
    }

    public static void addExperience(ArrayList<Experience> experience_data) {

        Scanner keybd = new Scanner(System.in);
        int index = 0;

        String choice_exp = "";
        String additional_exp = "";
        String additional_duty = "";
        boolean loop_exp = false;
        boolean loop_duty = true;

            experience_data.add(new Experience());
            index = experience_data.size() - 1;
            System.out.println("\nEnter title of position: ");
            experience_data.get(index).setTitle(keybd.nextLine());
            System.out.println("Enter name of company: ");
            experience_data.get(index).setCompany(keybd.nextLine());
            System.out.println("Enter start date:");
            experience_data.get(index).setStart_date(keybd.nextLine());
            System.out.println("Enter end date or 'Present' if you still work here:");
            experience_data.get(index).setEnd_date(keybd.nextLine());

            while (loop_duty) {
                System.out.println("Enter duty description:");
                experience_data.get(index).getDuty().add(keybd.nextLine());
                System.out.println("\nDid you have any additional duty: y/yes or n/no");
                additional_duty = keybd.nextLine();

                while (!additional_duty.equalsIgnoreCase("y") &&
                        !additional_duty.equalsIgnoreCase("yes") &&
                        !additional_duty.equalsIgnoreCase("n") &&
                        !additional_duty .equalsIgnoreCase("no")
                ) {
                    System.out.println("Invalid input.  Please enter y/yes or n/no for any additional duty you would like to input.");
                    additional_duty = keybd.nextLine();
                }

                if (additional_duty.equalsIgnoreCase("y") || additional_duty.equalsIgnoreCase("yes"))
                    loop_duty = true;
                else if (additional_duty.equalsIgnoreCase("n") || additional_duty.equalsIgnoreCase("no"))
                    loop_duty = false;
            }
        }


    public static void removeExperience(ArrayList<Experience> experience_data) {
        Scanner keybd = new Scanner(System.in);
        int remove_choice = 0;

        if (experience_data.size() == 0) {
            System.out.println((char) 27 + "[31m" + "This resume has no existing Experience record." + (char) 27 + "[30m" +
                    "Nothing to remove.");
        }
        else {
            System.out.println("Current Experience data on the resume you want to change is/are: " + (char) 27 + "[31m");
            for (int i=0; i < experience_data.size(); i++){
                System.out.println((i+1) +". " + experience_data.get(i).displayExperience());

            }

            System.out.print((char) 27 + "[30m");
            System.out.println("Enter the number corresponding to the Experience data entry you want to remove:");
            remove_choice = keybd.nextInt();
            experience_data.remove(remove_choice-1);
            System.out.println("Experience data entry successfully removed.  Press enter to return to previous menu.");
        }

    } //end removeExperience

    public static void changeResumeData_Skills(ArrayList<Robo_Resume> resume_db, int index) {
        Scanner keybd = new Scanner(System.in);
        String selection = "";
        String choice_input = "";

        if (resume_db.get(index).getSkillset().getSkills().size() == 0) {
            System.out.println((char) 27 + "[31m" + "This resume has no existing Skills data. " + (char) 27 + "[30m" +
                    "Do you want to input new Skills data? y/yes or n/no");
            choice_input = keybd.nextLine();
            while (!choice_input.equalsIgnoreCase("y") &&
                    !choice_input.equalsIgnoreCase("yes") &&
                    !choice_input.equalsIgnoreCase("n") &&
                    !choice_input.equalsIgnoreCase("no")
            ) {
                System.out.println("Invalid input.  Do you want to input new Skills data? y/yes or n/no");
                choice_input = keybd.nextLine();
            }

            if (choice_input.equalsIgnoreCase("y") || choice_input.equalsIgnoreCase("yes")) {
                addSkills(resume_db.get(index).getSkillset());
            }
        }
        else {
            System.out.println("Current Skills data on the resume you want to change is/are: " + (char) 27 + "[31m");
            System.out.print(resume_db.get(index).getSkillset().displaySkills());
            System.out.print((char) 27 + "[30m");
            System.out.println("1. Add additional Skills data.");
            System.out.println("2. Remove an existing Skills data.");
            System.out.println("3. Return to previous menu. \n\nSelection:");
            selection = keybd.nextLine();
            while (!selection.matches("[1-3]?")) {
                System.out.println("Invalid selection. Numbers 1-3 only.  Please re-enter your selection.");
                selection = keybd.nextLine();
            }
        }

        if (selection.equals("1")) {
            addSkills(resume_db.get(index).getSkillset());
            System.out.print("Additional Skills data added successfully.  Press enter to return to previous menu.");
            keybd.nextLine();
        }
        else if (selection.equals("2")) {
            removeSkills(resume_db.get(index).getSkillset());
            keybd.nextLine();
        }
    }

    public static void addSkills(Skills skills_data) {
        Scanner keybd = new Scanner(System.in);
        String skill_descriptor = "";
        String proficiency_rating = "";
        String proficiency = "";


            System.out.println("\nEnter skill descriptor:");
            skill_descriptor = keybd.nextLine();
            System.out.println("\nSelect number correspond to described proficiency of skill (1-5 numbers only): \n1_ Fundamental  2_ Novice  3_Intermediate  4_Advanced  5_Expert");
            proficiency_rating = keybd.nextLine();

            while (!proficiency_rating.matches("[1-5]?")) {
                System.out.println("Invalid input.  Numbers 1-5 only.  Please enter number corresponding to described proficiency of skill.");
                proficiency_rating = keybd.nextLine();
            }

            if (proficiency_rating.equals("1")) proficiency = "Fundamental";
            else if (proficiency_rating.equals("2")) proficiency = "Novice";
            else if (proficiency_rating.equals("3")) proficiency = "Intermediate";
            else if (proficiency_rating.equals("4")) proficiency = "Advanced";
            else if (proficiency_rating.equals("5")) proficiency = "Expert";

            skills_data.getSkills().put(skill_descriptor, proficiency);
    }


    public static void removeSkills(Skills skills_data) {
        Scanner keybd = new Scanner(System.in);

        LinkedHashMap<Integer, String> map_pair = new LinkedHashMap<>();
        String remove_choice = "";
        int counter = 1;
        int remove_int = 0;


        if (skills_data.getSkills().size() == 0) {
            System.out.println((char) 27 + "[31m" + "This resume currently does not have any Skill(s) listed." + (char) 27 + "[30m" +
                    "Nothing to remove.");
        }
        else {
            System.out.println("Current Skills data on the resume you want to change is/are: " + (char) 27 + "[31m");
            for (Map.Entry<String,String> values:skills_data.getSkills().entrySet()) {
                System.out.println(counter +". " +  values.getKey() + ", " +
                        values.getValue() + "\n");
                map_pair.put(counter, values.getKey());
                counter += 1;
            }

            System.out.print((char) 27 + "[30m");
            System.out.println("Enter the number corresponding to the Skills data entry you want to remove:");
            remove_choice = keybd.nextLine();
            if (remove_choice.matches("[1-9]\\d*")) remove_int = Integer.parseInt(remove_choice);
            else remove_int = skills_data.getSkills().size()+1;
            while (!remove_choice.matches("[1-9]\\d*") || (remove_int > skills_data.getSkills().size()) ) {
                System.out.println("Invalid selection.  Numbers only and cannot be greater than the entries shown.  Please re-enter your selection.");
                remove_choice = keybd.nextLine();
                if (remove_choice.matches("[1-9]\\d*")) remove_int = Integer.parseInt(remove_choice);
            }

            for (Map.Entry<Integer, String> values2:map_pair.entrySet()) {
                if (remove_int == values2.getKey()) {
                    skills_data.getSkills().remove(values2.getValue());
                    System.out.println("Skills data entry successfully removed.  Press enter to return to previous menu.");
                }
            }

             //System.out.println("Current size of Skillset is: " + skills_data.getSkills().size());
             //keybd.nextLine();

        }

    } //end removeSkills

    //Method for input data into new resume
    public static Robo_Resume inputResume() {
        Robo_Resume input_resume = new Robo_Resume();
        Scanner keybd = new Scanner(System.in);

        String degree = "";
        String degree_abb = "";
        String choice_ed = "";
        String graduate_ed = "";
        String additional_ed = "";

        String name  = "";
        String phone = "";
        String email = "";

        boolean loop_ed = false;
        boolean loop_ed2 = true;

        boolean name_loop = true;
        boolean phone_loop = true;
        boolean email_loop = true;

        int counter_ed = 0;


        // Section: Input Basic details
        System.out.println("\n--------------------------------------------- ");
        while (name_loop) {
            System.out.println("\nEnter Name of candidate to display on resume (Resume must have a name): \n(First Name <space> Last Name.  Capitalized first letters.)");
            name = keybd.nextLine();
            if (input_resume.validateName(name)) {
                input_resume.setName(name);
                name_loop = false;
            }
            else {
                System.out.println("Invalid input. Please enter name in format (First Name <space> Last Name with Capitalized first letters).");
            }
        }

        while (phone_loop) {
            System.out.println("\nEnter contact phone number of candidate (Enter 'none' for no phone number): " +
                               "\n(Digits only or digits with dashes(-) and/or period(.))");
            phone = keybd.nextLine();
            if (phone.equalsIgnoreCase("none") || phone.equals("")) {
                input_resume.setPhone("No contact phone number exists.");
                phone_loop = false;
            }
            else if (input_resume.isPhoneValid(phone)) {
                input_resume.setPhone(phone);
                phone_loop = false;
            } else System.out.println("Invalid input. " +
                    "Please enter phone number with digits only or digits with dashes or digits with dashes(-) and/or period(.)" +
                    " or 'none' for no phone.");
        }

        while (email_loop) {
            System.out.println("\nEnter contact email of candidate (Enter 'none' for no email): \n(Format string@company.com)");
            email = keybd.nextLine();
            if (email.equalsIgnoreCase("none") || email.equals("")) {
                input_resume.setEmail("No email address exists.");
                email_loop = false;
            }
            else if (input_resume.isEmailValid(email)) {
                input_resume.setEmail(email);
                email_loop = false;
            } else System.out.println("Invalid input. Please enter email in the format string@company.com or 'none' for no email.");
        }


        // Section: Input Education

        System.out.println("\nIs there any Education attainment you would like to input? y/yes or n/no");
        choice_ed = keybd.nextLine();
        while (!choice_ed.equalsIgnoreCase("y") &&
                !choice_ed.equalsIgnoreCase("yes") &&
                !choice_ed.equalsIgnoreCase("n") &&
                !choice_ed.equalsIgnoreCase("no")
        ) {
            System.out.println("Invalid input.  Please enter y/yes or n/no for any Education attainment you would like to input.");
            choice_ed = keybd.nextLine();
        }

        if (choice_ed.equalsIgnoreCase("y") || choice_ed.equalsIgnoreCase("yes"))
            loop_ed = true;

        while (loop_ed) {
            addEducation(input_resume.getEducation());
            System.out.println("\nIs there any additional Education you would like to input? y/yes or n/no ");
            additional_ed = keybd.nextLine();

            while (!additional_ed.equalsIgnoreCase("y") &&
                    !additional_ed.equalsIgnoreCase("yes") &&
                    !additional_ed.equalsIgnoreCase("n") &&
                    !additional_ed.equalsIgnoreCase("no")
            ) {
                System.out.println("Invalid input.  Please enter y/yes or n/no for any additional Education you would like to input.");
                additional_ed = keybd.nextLine();
            }

            if (additional_ed.equalsIgnoreCase("n") || additional_ed.equalsIgnoreCase("no"))
                loop_ed = false;

        }

        //  Section: Input Experience

        String choice_exp = "";
        String additional_exp = "";
        boolean loop_exp = false;

        System.out.println("\nDo you have any Experience you would like to input? y/yes or n/no ");
        choice_exp = keybd.nextLine();

        while (!choice_exp.equalsIgnoreCase("y") &&
                !choice_exp.equalsIgnoreCase("yes") &&
                !choice_exp.equalsIgnoreCase("n") &&
                !choice_exp.equalsIgnoreCase("no")
        ) {
            System.out.println("Invalid input.  Please enter y/yes or n/no for any Experience you would like to input.");
            choice_exp = keybd.nextLine();
        }

        if (choice_exp.equalsIgnoreCase("y") || choice_exp.equalsIgnoreCase("yes"))
            loop_exp = true;

        while (loop_exp) {
            addExperience(input_resume.getExperience());
            System.out.println("\nIs there any additional Experience you would like to input? y/yes or n/no ");
            additional_exp = keybd.nextLine();

            while (!additional_exp.equalsIgnoreCase("y") &&
                    !additional_exp.equalsIgnoreCase("yes") &&
                    !additional_exp.equalsIgnoreCase("n") &&
                    !additional_exp.equalsIgnoreCase("no")
            ) {
                System.out.println("Invalid input.  Please enter y/yes or n/no for any additional Experience you would like to input.");
                additional_exp = keybd.nextLine();
            }

            if (additional_exp.equalsIgnoreCase("n") || additional_exp.equalsIgnoreCase("no"))
                loop_exp = false;

        } //end of while loop experience


        //  Section: Input Skills
        String choice_skills = "";
        String additional_skills = "";
        String skill_descriptor = "";
        String proficiency_rating = "";
        String proficiency = "";
        boolean loop_skills = false;


        System.out.println("\nDo you have any Skill(s) you would like to input? y/yes or n/no ");
        choice_skills = keybd.nextLine();

        while (!choice_skills.equalsIgnoreCase("y") &&
                !choice_skills.equalsIgnoreCase("yes") &&
                !choice_skills.equalsIgnoreCase("n") &&
                !choice_skills.equalsIgnoreCase("no")
        ) {
            System.out.println("Invalid input.  Please enter y/yes or n/no for any Skill(s) you would like to input.");
            choice_skills = keybd.nextLine();
        }
        if (choice_skills.equalsIgnoreCase("y") || choice_skills.equalsIgnoreCase("yes"))
            loop_skills = true;

        while (loop_skills) {
            System.out.println("\nEnter skill descriptor:");
            skill_descriptor = keybd.nextLine();
            System.out.println("\nSelect number correspond to described proficiency of skill (1-5 numbers only): \n1_ Fundamental  2_ Novice  3_Intermediate  4_Advanced  5_Expert");
            proficiency_rating = keybd.nextLine();

            while (!proficiency_rating.matches("[1-5]?")) {
                System.out.println("Invalid input.  Numbers 1-5 only.  Please enter number corresponding to described proficiency of skill.");
                proficiency_rating = keybd.nextLine();
            }

            if (proficiency_rating.equals("1")) proficiency = "Fundamental";
            else if (proficiency_rating.equals("2")) proficiency = "Novice";
            else if (proficiency_rating.equals("3")) proficiency = "Intermediate";
            else if (proficiency_rating.equals("4")) proficiency = "Advanced";
            else if (proficiency_rating.equals("5")) proficiency = "Expert";

            input_resume.getSkillset().getSkills().put(skill_descriptor, proficiency);

            System.out.println("\nAre there any additional Skill(s) you would like to input? y/yes or n/no");
            additional_skills = keybd.nextLine();

            while (!additional_skills.equalsIgnoreCase("y") &&
                    !additional_skills.equalsIgnoreCase("yes") &&
                    !additional_skills.equalsIgnoreCase("n") &&
                    !additional_skills.equalsIgnoreCase("no")
            ) {
                System.out.println("Invalid input.  Please enter y/yes or n/no for any additional Skill(s) you would like to input.");
                additional_skills = keybd.nextLine();
            }

            if (additional_skills.equalsIgnoreCase("n") || additional_skills.equalsIgnoreCase("no"))
                loop_skills = false;

        } //end of while loop skills

        return input_resume;
    }

}
