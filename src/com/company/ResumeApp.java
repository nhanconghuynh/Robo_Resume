package com.company;

import java.util.Scanner;

public class ResumeApp {

    //driver to test functionality
    public static void main(String[] args) {


/*        Scanner keybd = new Scanner(System.in);

        System.out.print("Enter the Book Code: ");

        String code = keybd.nextLine();
*/
        Robo_Resume a = new Robo_Resume();
        Robo_Resume b = new Robo_Resume();
        Robo_Resume c = new Robo_Resume();
        Robo_Resume d = new Robo_Resume();
        Robo_Resume e = new Robo_Resume();

        Resume_DB z = new Resume_DB();
        z.AddResume(a,b, c, d, e);
 //       System.out.println(a.displayResume());
        System.out.println(c.displayResume());

    }
}
