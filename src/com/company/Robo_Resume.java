package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class Robo_Resume {
    private String name;
    private String phone;
    private String email;

    private ArrayList<Education> education = new ArrayList<>();



    Robo_Resume () {
        name = "";
        phone = "";
        email = "";
    }



    public String displayResume() {

        if (phone.equals("") && email.equals(""))

                return "=============================" + "\n" +
                        name + "\n\n" + this.printEducation(education);

        else if (phone.equals(""))

                return "=============================" + "\n" +
                        name + "\n" + email + "\n\n" + this.printEducation(education);

        else if (email.equals(""))

                return "=============================" + "\n" +
                        name + "\n" + phone + "\n\n" + this.printEducation(education);

        else
            return "=============================" + "\n" +
                        name + "\n" +
                        phone + "\n" +
                        email + "\n\n" + this.printEducation(education);


    }

    public String printEducation(ArrayList<Education> education) {
        String display_education = "";
        for (int i=0; i < education.size(); i++)
            display_education = display_education + education.get(i).displayEducation() + "\n";

        return "Education" + "\n" + display_education;
    }



    public String getName() {

        if (this.validateName(name))
        return name;
        else return "Invalid name.";

    }


    public void setName(String name) {

        this.name = name;

    }

    public static boolean validateName( String name)
    {
        return name.matches( "[A-Z][a-z]+( [A-Z][a-z]+)?");
    }

    public String getPhone() {

        if (this.isPhoneValid(phone))
        return phone;
        else return "Invalid or No phone number exists for this resume.";

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
    else return false;

}

    public String getEmail() {

        if (this.isEmailValid(email))
            return email;
        else return "Invalid or No email exists for this resume.";

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

 /*   public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    } */
}


