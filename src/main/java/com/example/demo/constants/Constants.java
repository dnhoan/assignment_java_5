package com.example.demo.constants;

public class Constants {
	public final static String P_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	public final static String P_PASSWORD = "[0-9a-zA-Z]{6,225}";
	public final static String P_PHONE_NUMBER = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
	public final static int COLUMN_ERRORS_EXCEL = 6;
	public final static int SCHEDULE_FIXRATE_FOR_IMPORT_EXCEL = 20 * 1000;
}
