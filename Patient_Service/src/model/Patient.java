/**
 * 
 */
package model;

/**
 * @author Sachini
 *
 */
public class Patient {

	String pFname;
	String pLname;
	int pAge;
	String pGender;
	String pAddress;
	String pContactNo;
	String pNIC;
	String pEmail;
	String pUsername;
	String pPassword;

	/**
	 * 
	 */
	public Patient() {

	}

	/**
	 * @param pFname
	 * @param pLname
	 * @param pAge
	 * @param pGender
	 * @param pAddress
	 * @param pContactNo
	 * @param pNIC
	 * @param pEmail
	 * @param pUsername
	 * @param pPassword
	 */
	public Patient(String pFname, String pLname, int pAge, String pGender, String pAddress, String pContactNo,
			String pNIC, String pEmail, String pUsername, String pPassword) {

		this.pFname = pFname;
		this.pLname = pLname;
		this.pAge = pAge;
		this.pGender = pGender;
		this.pAddress = pAddress;
		this.pContactNo = pContactNo;
		this.pNIC = pNIC;
		this.pEmail = pEmail;
		this.pUsername = pUsername;
		this.pPassword = pPassword;
	}

	/**
	 * @return the pFname
	 */
	public String getpFname() {
		return pFname;
	}

	/**
	 * @param pFname the pFname to set
	 */
	public void setpFname(String pFname) {
		this.pFname = pFname;
	}

	/**
	 * @return the pLname
	 */
	public String getpLname() {
		return pLname;
	}

	/**
	 * @param pLname the pLname to set
	 */
	public void setpLname(String pLname) {
		this.pLname = pLname;
	}

	/**
	 * @return the pAge
	 */
	public int getpAge() {
		return pAge;
	}

	/**
	 * @param pAge the pAge to set
	 */
	public void setpAge(int pAge) {
		this.pAge = pAge;
	}

	/**
	 * @return the pGender
	 */
	public String getpGender() {
		return pGender;
	}

	/**
	 * @param pGender the pGender to set
	 */
	public void setpGender(String pGender) {
		this.pGender = pGender;
	}

	/**
	 * @return the pAddress
	 */
	public String getpAddress() {
		return pAddress;
	}

	/**
	 * @param pAddress the pAddress to set
	 */
	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}

	/**
	 * @return the pContactNo
	 */
	public String getpContactNo() {
		return pContactNo;
	}

	/**
	 * @param pContactNo the pContactNo to set
	 */
	public void setpContactNo(String pContactNo) {
		this.pContactNo = pContactNo;
	}

	/**
	 * @return the pNIC
	 */
	public String getpNIC() {
		return pNIC;
	}

	/**
	 * @param pNIC the pNIC to set
	 */
	public void setpNIC(String pNIC) {
		this.pNIC = pNIC;
	}

	/**
	 * @return the pEmail
	 */
	public String getpEmail() {
		return pEmail;
	}

	/**
	 * @param pEmail the pEmail to set
	 */
	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}

	/**
	 * @return the pUsername
	 */
	public String getpUsername() {
		return pUsername;
	}

	/**
	 * @param pUsername the pUsername to set
	 */
	public void setpUsername(String pUsername) {
		this.pUsername = pUsername;
	}

	/**
	 * @return the pPassword
	 */
	public String getpPassword() {
		return pPassword;
	}

	/**
	 * @param pPassword the pPassword to set
	 */
	public void setpPassword(String pPassword) {
		this.pPassword = pPassword;
	}

}
