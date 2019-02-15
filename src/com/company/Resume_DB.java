package com.company;


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





    }

}
