/**
 * 
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Sachini
 *
 */
public class Appointment {

	public int aID;
	int appointmentNo;
	LocalDate sessionDate;
	LocalTime sessionTime;
	boolean status;
	boolean paymentStatus;

	/**
	 * @param aID
	 * @param appointmentNo
	 * @param sessionDate
	 * @param sessionTime
	 * @param status
	 * @param paymentStatus
	 */
	public Appointment(int aID, int appointmentNo, LocalDate sessionDate, LocalTime sessionTime, boolean status,
			boolean paymentStatus) {

		this.aID = aID;
		this.appointmentNo = appointmentNo;
		this.sessionDate = sessionDate;
		this.sessionTime = sessionTime;
		this.status = status;
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @param appointmentNo
	 * @param sessionDate
	 * @param sessionTime
	 * @param status
	 * @param paymentStatus
	 */
	public Appointment(int appointmentNo, LocalDate sessionDate, LocalTime sessionTime, boolean status,
			boolean paymentStatus) {
		this.appointmentNo = appointmentNo;
		this.sessionDate = sessionDate;
		this.sessionTime = sessionTime;
		this.status = status;
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the aID
	 */
	public int getaID() {
		return aID;
	}

	/**
	 * @param aID the aID to set
	 */
	public void setaID(int aID) {
		this.aID = aID;
	}

	/**
	 * @return the appointmentNo
	 */
	public int getAppointmentNo() {
		return appointmentNo;
	}

	/**
	 * @param appointmentNo the appointmentNo to set
	 */
	public void setAppointmentNo(int appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	/**
	 * @return the sessionDate
	 */
	public LocalDate getSessionDate() {
		return sessionDate;
	}

	/**
	 * @param sessionDate the sessionDate to set
	 */
	public void setSessionDate(LocalDate sessionDate) {
		this.sessionDate = sessionDate;
	}

	/**
	 * @return the sessionTime
	 */
	public LocalTime getSessionTime() {
		return sessionTime;
	}

	/**
	 * @param sessionTime the sessionTime to set
	 */
	public void setSessionTime(LocalTime sessionTime) {
		this.sessionTime = sessionTime;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the paymentStatus
	 */
	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
