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






        /*        Robo_Resume a = new Robo_Resume();
        Robo_Resume b = new Robo_Resume();
        Robo_Resume c = new Robo_Resume();
        Robo_Resume d = new Robo_Resume();
        Robo_Resume e = new Robo_Resume();

 //       Resume_DB z = new Resume_DB();
//        z.AddResume(a, b, c, d, e);
 //       System.out.println(a.displayResume());

*/

        ArrayList<Robo_Resume> resume_db = new ArrayList<>();
        //       Robo_Resume resume = new Robo_Resume();
        int resume_counter = 0;

        boolean main_menu = true;
        String candidate_name = "";

        int selection = 0;

        LinkedHashMap<String, Robo_Resume> resume_map = new LinkedHashMap<>();
        Scanner keybd = new Scanner(System.in);

        while (main_menu) {

            System.out.println("\nWelcome to Beginner's Resume Application" + "\n=============================================");
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
                    System.out.println("\nEnter name of candidate on resume you want to view: ");
                    candidate_name = keybd.nextLine();
                    System.out.println(candidate_name);
                    main_menu = true;
                    //System.out.println(resume_db.get(0).displayResume());
                    /*
                    for (int i = 0; i < resume_db.size(); i++) {
                        //resume_map.put(resume_db.get(i).getName(), resume_db.get(i));
                        if (resume_db.get(i).getName().equalsIgnoreCase(candidate_name)) {
                            System.out.println(resume_db.get(i).displayResume());
                            System.out.println("------------------------------------------");
                        }
                    } */
                    break;

                case 3:
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
                default: System.out.println("Invalid selection");
                break;
            }

            //       System.out.println(c.displayResume());
        }
    }


  /*  public void main_menu() {
        Scanner keybd = new Scanner(System.in);
        boolean loop = true;

        do {

            System.out.println("\nWelcome to Beginner's Resume Application" + "\n=============================================");
            System.out.println("\nPlease select one of the numerical option below:");
            System.out.println("1. Input new resume data");
            System.out.println("2. View resume of specific candidate");
            System.out.println("3. Change resume data for specific candidate");
            System.out.println("4. Search skill from Resume Database");
            System.out.println("5. Display all resumes. Exit program  \n\nSelection: ");
            selection = keybd.nextInt();
            switch (selection) {
                case 1: {
                    resume_db.add(inputResume());
                }
                break;
                case 2: {
                    System.out.println("\nEnter name of candidate on resume you want to view: ");
                    candidate_name = keybd.nextLine();
                    System.out.println(candidate_name);
                    main_menu = true;
                    //System.out.println(resume_db.get(0).displayResume());
                    /*
                    for (int i = 0; i < resume_db.size(); i++) {
                        //resume_map.put(resume_db.get(i).getName(), resume_db.get(i));
                        if (resume_db.get(i).getName().equalsIgnoreCase(candidate_name)) {
                            System.out.println(resume_db.get(i).displayResume());
                            System.out.println("------------------------------------------");
                        }
                    }
                }
                break;
                case 3: {
                }
                break;
                case 4: {
                }
                break;
                case 5: {
                    for (int i = 0; i < resume_db.size(); i++) {
                        System.out.println(resume_db.get(i).displayResume());
                        System.out.println("------------------------------------------");
                    }
                    loop = false;
                }
                break;
                default:
                    break;
            }



            //       System.out.println(c.displayResume());
        } while (loop);
    }
*/
    public static Robo_Resume inputResume() {
        Robo_Resume input_resume = new Robo_Resume();
        Scanner keybd = new Scanner(System.in);

        String choice_ed = "";
        String graduate_ed = "";
        String additional_ed = "";
        boolean loop_ed = false;
        int counter_ed = 0;


        // Section: Input Basic details
        System.out.println("\n--------------------------------------------- ");
        System.out.println("\nEnter Name of candidate to display on resume: ");
        input_resume.setName(keybd.nextLine());
        System.out.println("Enter contact phone number of candidate: ");
        input_resume.setPhone(keybd.nextLine());
        System.out.println("Enter contact email of candidate: ");
        input_resume.setEmail(keybd.nextLine());


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
                System.out.println("\nDo you have any additional duty: y/yes or n/no");
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


        System.out.println("\nDo you have any skill(s) you would like to input? ");
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


    public static void viewResume() {
        boolean loop = true;
        Scanner keybd = new Scanner(System.in);
        String name = "";

        do {
            System.out.println("Enter name of candidate on resume to view:");
            name = keybd.next();

        } while (loop);


    }


}
