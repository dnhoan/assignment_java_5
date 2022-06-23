package com.example.demo.constants;

public class Constants {
	public final static String P_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	public final static String P_PASSWORD = "[0-9a-zA-Z]{6,225}";
	public final static String P_PHONE_NUMBER = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
	public final static int COLUMN_ERRORS_EXCEL = 6;
	public final static int SCHEDULE_FIXRATE_FOR_IMPORT_EXCEL = 20 * 1000;
	public static final int COLUMN_INDEX_NAME         = 0;
    public static final int COLUMN_INDEX_STOCK      = 1;
    public static final int COLUMN_INDEX_PRICE      = 2;
    public static final int COLUMN_INDEX_COLOR   = 3;
    public static final int COLUMN_INDEX_SIZE   = 4;
    public static final int COLUMN_INDEX_NOTE      = 5;
    public static final int COLUMN_INDEX_CATEGORY      = 6;
}
