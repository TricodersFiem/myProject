package com.example.dhritidhruve.app;

import android.widget.CheckBox;

public class attendancedesign {

    private String name, roll;
    private boolean present;

    attendancedesign(String Name, String Roll)
    {
        name = Name;
        roll = Roll;
    }



    public String getName(){ return name;}

    public String getRoll(){ return roll;}

    public void setAttendance(boolean present){

        this.present=present;
    }
    public boolean getAttendance(){return present;
    }

    public float getPercent(){return 0 ; }

}
