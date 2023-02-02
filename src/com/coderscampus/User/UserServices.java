package com.coderscampus.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserServices {

	public User[] userList(String fileName) {
		BufferedReader lineReader = null;
		BufferedReader fileReader = null;
		int totalLines = 0;
		int usersIndex = 0;
		User[] usersList = null;
		
		try {
			lineReader = new BufferedReader(new FileReader(fileName));		
			
			while(lineReader.readLine() != null) {
				totalLines++;
			}
			
			//Dynamically get the number of user to size array
			usersList = new User[totalLines];
			
			fileReader = new BufferedReader(new FileReader(fileName));
			String line;
			
			while((line = fileReader.readLine()) != null) {
				
				String[] credentials = line.split(",");
				User user = new User();
				user.setUserName(credentials[0]);
				user.setPassword(credentials[1]);
				user.setName(credentials[2]);
				usersList[usersIndex] = user;
				usersIndex++;
			}
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			
		} catch (IOException e) {			
			e.printStackTrace();
			
		} finally {
			
			try {
				lineReader.close();
				fileReader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return usersList;
	}

	public String loginValidation(User[] userList) {
			/*
			 * Note the input.close() closes scanner and the input stream System.in
			 * once System.in is close you cannot reopen it. Only close when completely done.
			 * */
			int loginAttempts = 0;
				
			Scanner input =  new Scanner(System.in);
			
			while(loginAttempts < 5) {

				System.out.println("Enter your username:");
				String userNameInput = input.nextLine();				
				System.out.println("Enter your password:");
				String passwordInput = input.nextLine();
				
				for (User user : userList) {
					if(user.getUserName().toLowerCase().equals(userNameInput.toLowerCase()) && passwordInput.equals(user.getPassword())) {
						return "\nWelcome " + user.getUserName();
					}
				}
				System.out.println("Invalid login, please try again.\n");
				loginAttempts++;
			}
			
			input.close();
			return "Too many failed login attempts, you are now locked out.";
	}
}

	
