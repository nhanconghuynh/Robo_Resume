package com.company;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class ResumeApp {

    //driver to test functionality
    public static void main(String[] args) {


/*      Robo_Resume a = new Robo_Resume();
        Robo_Resume b = new Robo_Resume();
        Robo_Resume c = new Robo_Resume();
        Robo_Resume d = new Robo_Resume();
        Robo_Resume e = new Robo_Resume();

       Resume_DB z = new Resume_DB();
       z.AddResume(a, b, c, d, e);
       System.out.println(a.displayResume());

*/

        ArrayList<Robo_Resume> resume_db = new ArrayList<>();
        //       Robo_Resume resume = new Robo_Resume();
        int resume_counter = 0;

        boolean main_menu = true;


        int selection = 0;

        LinkedHashMap<String, Robo_Resume> resume_map = new LinkedHashMap<>();
        Scanner keybd = new Scanner(System.in);

        while (main_menu) {

            System.out.println((char)27 + "[34m" + "\nWelcome to Beginner's Resume Application" + "\n=============================================" + (char)27 + "[30m");
            System.out.println("\nPlease select one of the numerical option below:");
            System.out.println("1. Input new resume data");
            System.out.println("2. View resume of specific candidate");
            System.out.println("3. Change resume data for specific candidate");
            System.out.println("4. Search skill from Resume Database");
            System.out.println("5. Display all resumes. Exit program  \n\nSelection: ");
            selection = keybd.nextInt();
            switch (selection) {
                case 1:
                    resume_db.add(inputResume());
                    break;

                case 2:
                    viewResume(resume_db);

                    break;

                case 3:
                    changeResume(resume_db);

                    break;
                case 4:
                    break;
                case 5:
                    for (int i = 0; i < resume_db.size(); i++) {
                        System.out.println(resume_db.get(i).displayResume());
                        System.out.println("------------------------------------------");
                    }
                    main_menu = false;

                    break;
                default:
                    System.out.println("Invalid selection.");
                    break;
            }

        }
    }


    public static void viewResume(ArrayList<Robo_Resume> resume_db) {
        boolean loop = true, candidate = false;
        boolean name_loop = true;
        Scanner keybd = new Scanner(System.in);
        String name = "";


        while (name_loop) {
            System.out.println("\nEnter name of candidate on resume you want to view: \n(First Name <space> Last Name.  Capitalize first letters.)");
            name = keybd.nextLine();

            if (name.matches( "[A-Z][a-z]+( [A-Z][a-z]+)?"))
                name_loop = false;
            else
                System.out.println("Invalid input. Please enter name in format (First Name <space> Last Name with Capitalize first letters.");

        }

            for (int i = 0; i < resume_db.size(); i++) {
                if (resume_db.get(i).getName().equalsIgnoreCase(name)) {
                    System.out.println(resume_db.get(i).displayResume());
                    System.out.println("-------------------------------------------");
                    candidate = true;
                } else candidate = false;

            }

            if (candidate == false) {
                System.out.println("Resume not found.");

            }


    }

    public static void changeResume(ArrayList<Robo_Resume> resume_db) {

        boolean loop = true, candidate = false;
        boolean name_loop = true;
        Scanner keybd = new Scanner(System.in);
        String name = "";


        while (name_loop) {
            System.out.println("\nEnter name of candidate on resume you want to change: \n(First Name <space> Last Name.  Capitalize first letters.)");
            name = keybd.nextLine();

            if (name.matches( "[A-Z][a-z]+( [A-Z][a-z]+)?"))
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

        if (!candidate)   System.out.println("Resume not found.");


    }


    public static void changeResumeData_Menu(ArrayList<Robo_Resume> resume_db, int index) {

        Scanner keybd = new Scanner(System.in);
        boolean change_data_menu = true;
        int selection = 0;

        while (change_data_menu) {

            System.out.println("\nWhat data on the resume you would like to change?");
            System.out.println("1. Name");
            System.out.println("2. Phone Number");
            System.out.println("3. Email address");
            System.out.println("4. Education (Not Activated)");
            System.out.println("5. Experience (Not Activated)");
            System.out.println("6. Skillset (Not Activated)");
            System.out.println("7. Exit to Main Menu \n\nSelection:");
            selection = keybd.nextInt();
            switch (selection) {
                case 1:
                    changeResumeData_Name(resume_db, index);
                    break;

                case 2:
                    changeResumeData_Phone(resume_db, index);
                    break;

                case 3:
                    changeResumeData_Email(resume_db, index);
                    break;
                case 4:
                    System.out.println((char)27 +"[31m" + "This feature has not been activated yet." + (char)27 + "[30m");
                    break;
                case 5:
                    System.out.println((char)27 +"[31m" + "This feature has not been activated yet." + (char)27 + "[30m");
                    break;
                case 6:
                    System.out.println((char)27 +"[31m" + "This feature has not been activated yet." + (char)27 + "[30m");
                    break;
                case 7:
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
            System.out.println("Enter the new name you want for this resume:");
            new_name = keybd.nextLine();
            if (resume_db.get(index).validateName(new_name)) {
                resume_db.get(index).setName(new_name);
                System.out.println("Name successfully changed.");
                name_loop = false;
            }
            else System.out.println("Invalid input. Please enter name in format (First Name <space> Last Name with Capitalized first letters).");
        }
    }

    public static void changeResumeData_Phone(ArrayList<Robo_Resume> resume_db, int index) {
        Scanner keybd = new Scanner(System.in);
        String new_phone = "";
        boolean phone_loop = true;

        while (phone_loop) {
            System.out.println("Current phone number on this resume is: " + (char) 27 + "[31m" + resume_db.get(index).getPhone() + (char) 27 + "[30m");
            System.out.println("Enter the new contact phone number you want for this resume: \n(Digits only or digits with dashes)");
            new_phone = keybd.nextLine();
            if (resume_db.get(index).isPhoneValid(new_phone)) {
                resume_db.get(index).setPhone(new_phone);
                System.out.println("Phone number successfully changed.");
                phone_loop = false;
            }
            else System.out.println("Invalid input. Please enter phone number with digits only or digits with dashes.");
        }
    }

    public static void changeResumeData_Email(ArrayList<Robo_Resume> resume_db, int index) {
        Scanner keybd = new Scanner(System.in);
        String new_email = "";
        boolean email_loop = true;

        while (email_loop) {
            System.out.println("Current email on the resume you want to change is: " + (char) 27 + "[31m" + resume_db.get(index).getEmail() + (char) 27 + "[30m");
            System.out.println("Enter the new email you want for this resume: \n(Format string@company.com)");
            new_email = keybd.nextLine();
            if (resume_db.get(index).isEmailValid(new_email)) {
                resume_db.get(index).setName(new_email);
                System.out.println("Email successfully changed.");
                email_loop = false;
            }
            else System.out.println("Invalid input. Please enter email in the format string@company.com.");
        }
    }

    public static Robo_Resume inputResume() {
        Robo_Resume input_resume = new Robo_Resume();
        Scanner keybd = new Scanner(System.in);

        String choice_ed = "";
        String graduate_ed = "";
        String additional_ed = "";

        String name  = "";
        String phone = "";
        String email = "";

        boolean loop_ed = false;

        boolean name_loop = true;
        boolean phone_loop = true;
        boolean email_loop = true;

        int counter_ed = 0;


        // Section: Input Basic details
        System.out.println("\n--------------------------------------------- ");
        while (name_loop) {
            System.out.println("\nEnter Name of candidate to display on resume: \n(First Name <space> Last Name.  Capitalized first letters.)");
            name = keybd.nextLine();
            if (input_resume.validateName(name)) {
                input_resume.setName(name);
                name_loop = false;
            } else System.out.println("Invalid input. Please enter name in format (First Name <space> Last Name with Capitalized first letters).");
        }

        while (phone_loop) {
            System.out.println("\nEnter contact phone number of candidate: \n(Digits only or digits with dashes)");
            phone = keybd.nextLine();
            if (input_resume.isPhoneValid(phone)) {
                input_resume.setPhone(phone);
                phone_loop = false;
            } else System.out.println("Invalid input. Please enter phone number with digits only or digits with dashes.");
        }

        while (email_loop) {
            System.out.println("\nEnter contact email of candidate: \n(Format string@company.com)");
            email = keybd.nextLine();
            if (input_resume.isEmailValid(email)) {
                input_resume.setEmail(email);
                email_loop = false;
            } else System.out.println("Invalid input. Please enter email in the format string@company.com.");
        }


        // Section: Input Education

        System.out.println("\nIs there any education attainment you would like to input? y/yes or n/no");
        choice_ed = keybd.nextLine();

        if (choice_ed.equalsIgnoreCase("y") || choice_ed.equalsIgnoreCase("yes"))
            loop_ed = true;

        while (loop_ed) {
            input_resume.getEducation().add(new Education());
            System.out.println("\nEnter name of school/college/university attended: ");
            input_resume.getEducation().get(counter_ed).setSchool(keybd.nextLine());
            System.out.println("Enter year last attended: ");
            input_resume.getEducation().get(counter_ed).setYear(keybd.nextLine());
            System.out.println("Did you graduate? y/yes or n/no");
            graduate_ed = keybd.nextLine();
            if (graduate_ed.equalsIgnoreCase("y") || graduate_ed.equalsIgnoreCase("yes")) {
                System.out.println("Enter highest degree earned from this school/college/university: ");
                input_resume.getEducation().get(counter_ed).setDegree(keybd.nextLine());
                System.out.println("Enter degree's major: ");
                input_resume.getEducation().get(counter_ed).setMajor(keybd.nextLine());
            } else if (graduate_ed.equalsIgnoreCase("n") || graduate_ed.equalsIgnoreCase("no")) {
                input_resume.getEducation().get(counter_ed).setDegree("Did not graduate");
                System.out.println("Enter major of program you were studying: ");
                input_resume.getEducation().get(counter_ed).setMajor(keybd.nextLine());
            }

            System.out.println("\nIs there any additional education you would like to input? y/yes or n/no ");
            additional_ed = keybd.nextLine();

            counter_ed += 1;
            loop_ed = true;

            if (additional_ed.equalsIgnoreCase("n") || additional_ed.equalsIgnoreCase("no"))
                loop_ed = false;

        } //end of while loop education


        //  Section: Input Experience

        String choice_exp = "";
        String additional_exp = "";
        String additional_duty = "";
        boolean loop_exp = false;
        boolean loop_duty = true;
        int counter_exp = 0;


        System.out.println("\nDo you have work experience you would like to input? y/yes or n/no ");
        choice_exp = keybd.nextLine();
        if (choice_exp.equalsIgnoreCase("y") || choice_exp.equalsIgnoreCase("yes"))
            loop_exp = true;

        while (loop_exp) {
            input_resume.getExperience().add(new Experience());
            System.out.println("\nEnter title of position: ");
            input_resume.getExperience().get(counter_exp).setTitle(keybd.nextLine());
            System.out.println("Enter name of company: ");
            input_resume.getExperience().get(counter_exp).setCompany(keybd.nextLine());
            System.out.println("Enter start date:");
            input_resume.getExperience().get(counter_exp).setStart_date(keybd.nextLine());
            System.out.println("Enter end date or 'Present' if you still work here:");
            input_resume.getExperience().get(counter_exp).setEnd_date(keybd.nextLine());

            while (loop_duty) {
                System.out.println("Enter duty description:");
                input_resume.getExperience().get(counter_exp).getDuty().add(keybd.nextLine());
                System.out.println("\nDid you have any additional duty: y/yes or n/no");
                additional_duty = keybd.nextLine();
                if (additional_duty.equalsIgnoreCase("y") || additional_duty.equalsIgnoreCase("yes"))
                    loop_duty = true;
                else if (additional_duty.equalsIgnoreCase("n") || additional_duty.equalsIgnoreCase("no"))
                    loop_duty = false;
            }


            System.out.println("\nIs there any additional experience you would like to input? y/yes or n/no ");
            additional_exp = keybd.nextLine();

            counter_exp += 1;
            loop_exp = true;

            if (additional_exp.equalsIgnoreCase("n") || additional_exp.equalsIgnoreCase("no"))
                loop_exp = false;

        } //end of while loop experience

        //  Section: Input Skills
        String choice_skills = "";
        String additional_skills = "";
        String skill_descriptor = "";
        String proficiency = "";
        boolean loop_skills = false;


        System.out.println("\nDo you have any skill(s) you would like to input? y/yes or n/no ");
        choice_skills = keybd.nextLine();
        if (choice_skills.equalsIgnoreCase("y") || choice_skills.equalsIgnoreCase("yes"))
            loop_skills = true;

        while (loop_skills) {
            System.out.println("\nEnter skill descriptor:");
            skill_descriptor = keybd.nextLine();
            System.out.println("\nEnter proficiency of skill: \n_ Fundamental_ Novice_ Intermediate_ Advanced_ Expert");
            proficiency = keybd.nextLine();
            input_resume.getSkillset().getSkills().put(skill_descriptor, proficiency);
            System.out.println("\nAre there any additional skill(s) you would like to input? y/yes or n/no");
            additional_skills = keybd.nextLine();
            if (additional_skills.equalsIgnoreCase("n") || additional_skills.equalsIgnoreCase("no"))
                loop_skills = false;
        } //end of while loop skills

        return input_resume;
    }

}
