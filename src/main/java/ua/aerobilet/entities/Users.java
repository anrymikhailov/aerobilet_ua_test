package ua.aerobilet.entities;

public class Users {
	
		public static User userUA = new User() {
			{
			login = "anry.mikhailov@gmail.com";
			password = "diablo19818";
			userName = "1ANDRII MIKHAILOV";
			}
	};
	
		public static User userRU = new User(){
			{
			login = "andrey.mikhailov@iati.com";
			password = "diablo19818";
			userName = "ANDREY MIKHAILOV";
			}
	};
	
		public static User unsuccessUser = new User(){
			{
			login = "wrong@gmail.com";
			password = "wrongpass";
			userName = "Wrong Wrong";
			}
	};
}

