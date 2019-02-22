package com.company;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class Robo_Resume {
    private String name;
    private String phone;
    private String email;

    private ArrayList<Education> education = new ArrayList<>();
    private ArrayList<Experience> experience = new ArrayList<>();

    private Skills skillset = new Skills();

    Robo_Resume () {
        name = "";
        phone = "";
        email = "";
    }



    public String displayResume() {

            return "-------------------------------------------" + "\n" +
                        name + "\n" +
                        phone + "\n" +
                        email + "\n\n" +
                        this.displayEducation(education) + "\n" +
                        this.displayExperience(experience)+ "\n" +
                        this.skillset.displaySkills();

    }

    public String displayEducation(ArrayList<Education> education) {
        String display_education = "";
        for (int i=0; i < education.size(); i++)
            display_education = display_education + education.get(i).displayEducation() + "\n";

        return "Education" + "\n" + display_education;
    }


    public String displayExperience(ArrayList<Experience> experience) {
        String display_experience = "";
        for (int i=0; i < experience.size(); i++)
            display_experience = display_experience + experience.get(i).displayExperience() + "\n";

        return "Experience" + "\n" + display_experience;
    }





    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public static boolean validateName( String name)
    {

        if (name.matches("[A-Z][a-z]+( [A-Z][a-z]+)?")) return true;
        else if (name.matches("[A-Z][a-z]+( [A-Z])+[a-z]+[A-Z][a-z]+?")) return true;
        else if (name.matches("[A-Z][a-z]+( [A-Z][\\'\\-\\.][A-Z][a-z]+)?")) return true;
        else if (name.matches("[A-Z][a-z]+( [A-Z])+[a-z]+[\\'\\-\\.][A-Z][a-z]+?")) return true;
        else if (name == null) return false;
        else return false;
    }

    public String getPhone() {
//        if (this.isPhoneValid(phone))
        return phone;
//       else return "Invalid or No phone number exists for this resume.";

    }



    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static boolean isPhoneValid(String phone)
{

    if (phone.matches("\\d{10}")) return true;
        //validating phone number with -, . or spaces
    else if(phone.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
        //validating phone number with extension length from 3 to 5
    else if(phone.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
        //validating phone number where area code is in braces ()
    else if(phone.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
        //return false if nothing matches the input
    else if (phone == null) return false;
    else return false;

}

    public String getEmail() {

//        if (this.isEmailValid(email))
            return email;
//        else return "Invalid or No email exists for this resume.";

    }



    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean isEmailValid(String email)
    {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public ArrayList<Education> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<Education> education) {
        this.education =  education;
    }

    public ArrayList<Experience> getExperience() {
        return experience;
    }

    public void setExperience(ArrayList<Experience> experience) {
        this.experience = experience;
    }

    public Skills getSkillset() {
        return skillset;
    }

    public void setSkillset(Skills skillset) {
        this.skillset = skillset;
    }
}


