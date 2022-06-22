package com.example.demo.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationExcel {
	volatile String message;
	volatile String name_file_Excel;
	volatile Date time;
	volatile String type;
	@Override
	public String toString() {
		return "NotificationExcel [message=" + message + ", name_file_Excel=" + name_file_Excel + ", time=" + time
				+ ", type=" + type + "]";
	}
}
