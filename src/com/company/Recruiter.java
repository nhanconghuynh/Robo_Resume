package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.Map.Entry;

public class Recruiter {
    String search_skill;
    private ArrayList<Robo_Resume> resume_db = new ArrayList<>();


    public ArrayList<String> searchSkill (ArrayList<Robo_Resume> resume_db, String search_skill) {
    ArrayList<String> result_resume = new ArrayList<>();
      for (int i=0; i<resume_db.size(); i++)
        for (Map.Entry<String, String> values:resume_db.get(i).getSkillset().getSkills().entrySet()) {
           if (search_skill.equalsIgnoreCase(values.getKey())) {
               result_resume.add(resume_db.get(i).getName());
           }
        }


        return result_resume;
    }
}

