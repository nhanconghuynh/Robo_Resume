package com.company;


import java.util.HashMap;
import java.util.Scanner;



public class Resume_DB extends Robo_Resume {


    public static void AddResume(Robo_Resume a, Robo_Resume b, Robo_Resume c, Robo_Resume d, Robo_Resume e) {
        a.setName("Nhan Huynh");
        a.setPhone("123456789");
        a.setEmail("cool_guy@gmail.com");

        b.setName("Dave");
        b.setPhone("Wolf");
        b.setEmail("987654321");

        c.setName("John Jingleheimer-Schmidt");
        c.setEmail("jjjschmidt@gmail.com");
        c.getEducation().add(new Education());
        c.getEducation().get(0).setSchool("University of Maryland");
        c.getEducation().get(0).setYear("2002");
        c.getEducation().get(0).setDegree("BS");
        c.getEducation().get(0).setMajor("Psychology");
        c.getEducation().add(new Education());
        c.getEducation().get(1).setSchool("Johns Hopkins U");
        c.getEducation().get(1).setYear("2005");
        c.getEducation().get(1).setDegree("MS");
        c.getEducation().get(1).setMajor("Software Engineering");
        c.getExperience().add(new Experience());
        c.getExperience().get(0).setTitle("Software Developer II");
        c.getExperience().get(0).setCompany("Amtrak");
        c.getExperience().get(0).setStart_date("June 2015");
        c.getExperience().get(0).setEnd_date("Present");
        c.getExperience().get(0).getDuty().add("Lorem ipsum");
        c.getExperience().get(0).getDuty().add("Gaudeamos Igitur Iuvenes Dum Somos");
        c.getExperience().add(new Experience());
        c.getExperience().get(1).setTitle("Software Developer I");
        c.getExperience().get(1).setCompany("Amtrak");
        c.getExperience().get(1).setStart_date("June 2014");
        c.getExperience().get(1).setEnd_date("June 2015");
        c.getExperience().get(1).getDuty().add("Lorem ipsum");
        c.getExperience().get(1).getDuty().add("Gaudeamos Igitur Iuvenes Dum Somos");
        c.getExperience().add(new Experience());
        c.getExperience().get(2).setTitle("Business Analyst");
        c.getExperience().get(2).setCompany("DCPS");
        c.getExperience().get(2).setStart_date("August 2005");
        c.getExperience().get(2).setEnd_date("May 2014");
        c.getExperience().get(2).getDuty().add("Lorem ipsum");
        c.getExperience().get(2).getDuty().add("Gaudeamos Igitur Iuvenes Dum Somos");
//        c.setSkillset(new Skills());
        c.getSkillset().getSkills().put("Java", "Advanced");
        c.getSkillset().getSkills().put("PHP", "Intermediate");
        c.getSkillset().getSkills().put("Ruby on Rails", "Novice");
        c.getSkillset().getSkills().put("Perl", "Fundamental");





    }

}
