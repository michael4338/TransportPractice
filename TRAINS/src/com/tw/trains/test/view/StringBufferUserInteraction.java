package com.tw.trains.test.view;

import com.tw.trains.app.view.IUserInteraction;

/*
 * another IUserInteraction implementation, for unit testing usage
 */
public class StringBufferUserInteraction implements IUserInteraction {

	private String inputString = null;
	private String outputString = null;
	
	public String getOutputString(){
		return outputString;
	}
	
	public void setInputString(String input){
		inputString = input;
	}
	
	
	@Override
	public String getInfoFromUser() {
		return inputString;
	}

	@Override
	public void showInfoToUser(String info) {
		outputString = info;
	}

	@Override
	public void showUI() {
	}

}
