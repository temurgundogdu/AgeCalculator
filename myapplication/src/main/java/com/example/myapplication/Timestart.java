package com.example.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestart {
	private SimpleDateFormat dateFormat;
	private Date datefield;

	public Timestart () {
		datefield = new Date(); //Default date
	    //Your prefered date format
	    dateFormat = new SimpleDateFormat("dd.MM.yyyy"); 

	} 
	//... other Code ...


	//Getter method of your Date
	public String getdateFormat(){
	return dateFormat.format(datefield);
	}
}
