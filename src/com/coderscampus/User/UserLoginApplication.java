package com.coderscampus.User;

public class UserLoginApplication {

	public static void main(String[] args) {
		UserServices user = new UserServices();
		User[] list = user.userList("data.txt");
		System.out.println(user.loginValidation(list));
		
	}

}
