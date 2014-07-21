package com.tw.trains.app.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserInteraction implements IUserInteraction {

	private final static String START_GREETING = "\n\n******Welcome To Try The Routes Query System******\n\n";
	
	@Override
	public void showUI() {
		System.out.println(ConsoleUserInteraction.START_GREETING);
	}
	
	@Override
	public String getInfoFromUser() {
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		try {
			line = bufferReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return line;
	}

	@Override
	public void showInfoToUser(String info) {
		System.out.print(info);
	}
	
}
