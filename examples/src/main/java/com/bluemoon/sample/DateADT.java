package com.bluemoon.sample;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateADT implements Serializable {
	private static final long serialVersionUID = 367803699023827305L;
	private Date date;
	private long timeStamp;
	private boolean fault;

	public DateADT() {
	}

	public DateADT(Date date) {
		if(null != date) {
			this.date = date;
			this.timeStamp = date.getTime();
		}

	}

	public DateADT(Date date, long timeStamp) {
		if(null != date) {
			this.date = date;
		}

		this.timeStamp = timeStamp;
	}

	public static DateADT fromDateTime(DateTime dateTime) {
		return dateTime == null?null:new DateADT(dateTime.toDate());
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return null != this.date?this.date:new Date(this.timeStamp);
	}

	public long getTimeStamp() {
		if(0L == this.timeStamp && null != this.date) {
			this.timeStamp = this.date.getTime();
		}

		return this.timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public long getMillisTime() {
		return null != this.date?this.date.getTime():this.timeStamp;
	}

	public java.sql.Date toSqlDate() {
		long time = this.getMillisTime();
		return new java.sql.Date(time);
	}

	public String toFormattedDate() {
		Date date = this.date;
		String formattedDate = null;

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			formattedDate = simpleDateFormat.format(date);
		} catch (Exception var4) {
			;
		}

		return formattedDate;
	}

	public String toString() {
		return String.valueOf(this.getMillisTime());
	}

	public void readData(ResultSet rs, int columnIndex) throws SQLException {
		this.date = rs.getTimestamp(columnIndex);
		if(null != this.date) {
			this.timeStamp = this.date.getTime();
		} else {
			this.fault = true;
		}

	}

	public static DateADT getDate(String str) {
		DateADT date = null;

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
			date = new DateADT(simpleDateFormat.parse(str));
		} catch (ParseException var3) {
			;
		}

		return date;
	}

	public boolean fault() {
		return this.fault;
	}

	public boolean equals(Object o) {
		if(this == o) {
			return true;
		} else if(o != null && this.getClass() == o.getClass()) {
			DateADT dateADT = (DateADT)o;
			if(this.fault != dateADT.fault) {
				return false;
			} else if(this.timeStamp != dateADT.timeStamp) {
				return false;
			} else {
				if(this.date != null) {
					if(!this.date.equals(dateADT.date)) {
						return false;
					}
				} else if(dateADT.date != null) {
					return false;
				}

				return true;
			}
		} else {
			return false;
		}
	}

	public int hashCode() {
		int result = this.date != null?this.date.hashCode():0;
		result = 31 * result + (int)(this.timeStamp ^ this.timeStamp >>> 32);
		result = 31 * result + (this.fault?1:0);
		return result;
	}
}

