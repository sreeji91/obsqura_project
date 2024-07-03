package utility;

import java.util.Locale;

import com.github.javafaker.Faker;

public class randomDataUtility {
	static Faker objFake;
	public randomDataUtility() {
		objFake=new Faker();
		
	}
	public static String firstName() {
		return objFake.name().firstName();
	}
	public static void main(String args[])
	{
		randomDataUtility obj=new randomDataUtility();
		System.out.println(obj.firstName());
	}

}
