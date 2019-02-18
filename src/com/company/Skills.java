package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Skills {
    private String skillset;
    private String rating;
    private LinkedHashMap<String, String> skills;

    public Skills () {
        skillset = "";
        rating   = "";
        this.skills = new LinkedHashMap<>();
    }


    public String displaySkills() {
        String display_skills = "";
        //String proficiency = "";

        //Set<Entry<String,String>> hashSet= skills.entrySet();
        for (Map.Entry<String,String> values:skills.entrySet()) {
 /*         if (values.getValue() == 1) proficiency = "Fundamental";
            if (values.getValue() == 2) proficiency = "Novice";
            if (values.getValue() == 3) proficiency = "Intermediate";
            if (values.getValue() == 4) proficiency = "Advanced";
            if (values.getValue() == 5) proficiency = "Expert";
*/
            display_skills = display_skills + values.getKey() + ", " +
                             values.getValue() + "\n";

        }

        return "Skills" + "\n" + display_skills;
    }

    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public LinkedHashMap<String, String> getSkills() {
        return skills;
    }

    public void setSkills(LinkedHashMap<String, String> skills) {
        this.skills = skills;
    }
}
