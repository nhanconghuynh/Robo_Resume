package com.company;

import java.util.ArrayList;

public class Experience {
    private String company;
    private String title;
    private String start_date;
    private String end_date;
    private ArrayList<String> duty = new ArrayList<>();

/*    public Experience () {
        super();
    }
*/

    public String displayExperience() {
        return  title + "\n" + company + ", " +
                start_date + " - " + end_date + "\n" +
                this.displayDuties();
    }

    public String displayDuties(){
        String display_duty = "";
        for (int i=0; i<duty.size(); i++ )
        display_duty = display_duty + "- Duty " + (i+1) + ", " + duty.get(i) + "\n";
        return display_duty;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public ArrayList<String> getDuty() {
        return duty;
    }

    public void setDuty(ArrayList<String> duty) {
        this.duty = duty;
    }


}
