package validator;

/**DataValidator class.
 * @author Sachini
 *
 */
public class DataValidator {

	// validate NIC
	public boolean validateNICOld(String nic) {
		int length = nic.length();
		if (length != 10)
		{
			return false;
		}
		else {
			char lastChar = nic.charAt(length - 1);
			String lastCharacter = String.valueOf(lastChar);
			if (lastCharacter.equalsIgnoreCase("v") || lastCharacter.equalsIgnoreCase("x"))
			{
				String number = nic.substring(0, length - 1);
				// Log.e("NUmber", number);
				if (!number.trim().matches("/^[0-9]{9}/"))
				{
					return true;
				}
				else
				{
					return false;
				}
			} 
			else 
			{
				for (int i = 0; i < length - 2; i++)
				{
					char currentChar = nic.charAt(i);
					if (currentChar < '0' || '9' < currentChar) {
						return false;
					}
				}
			}
		}
		return false;
	}



	// validate email
	public boolean validateEmail(String email) {

		if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com")) {
			return false;
		} else {
			return true;
		}
	}


	//validate contactNo
	public boolean valPhone(String in)
	{
		return in.charAt(0) == '0' && in.length() == 10 && in.matches("[0-9]+");
	}


	//validate age
	public boolean valAge(String age) 
	{
		if(age.equalsIgnoreCase("0")){ 
			return false;
		}

		else
		{
			try 
			{
				Integer.parseInt(age);
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}

	}

}
