package com.tw.trains.app.control;

public class Processor {
	public final static String SEP = "-";
	public final static String END = "#";
	
	private UIControlCenter uiCenter = null;
	private boolean initialState = true;
	private final static String INSTRUCTIONS = "";
	
	public Processor(UIControlCenter uiCenter){
		setUiCenter(uiCenter);
	}

	public UIControlCenter getUiCenter() {
		return uiCenter;
	}

	public void setUiCenter(UIControlCenter uiCenter) {
		this.uiCenter = uiCenter;
	}
	
	public boolean getInitialState() {
		return initialState;
	}

	public void setInitialState(boolean initialState) {
		this.initialState = initialState;
	}

	public boolean process(String cmd){
		return false;
	} 
	
	public String getInstruction(){
		return INSTRUCTIONS;
	}
	
	
	
	/*
	 * Get data from model layer by calling data service;
	 * Each derived processor class has its own implementation.
	 */
	protected void callDataService(String cmd){
		return;
	}
	
	/*
	 * validate commands inputing from user when the current processor is just involved
	 */
	protected boolean validateInitialCmd(String cmd, String key, String instruction, String errMsg){
		if (initialState){
			if(cmd.equalsIgnoreCase(key)){
				initialState = false;
				getUiCenter().showInfoToUser(instruction);
			}else{
				getUiCenter().showInfoToUser(errMsg);
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Cooperate with user interaction and keep processing in current processor
	 */
	protected void doProcess(String promptMsg){
		do{
			getUiCenter().showInfoToUser(promptMsg);
			String cmd = getUiCenter().getInfoFromUser();
			
			if(cmd == null) 
				continue;
			else if (processingCompleted(cmd)) 
				break;
			
			callDataService(cmd);
			
		}while(true);
	}
	
	protected boolean processingCompleted(String cmds){
		return cmds.equalsIgnoreCase(END);
	}
	
	protected boolean validatePositiveInteger(String intStr){
		int positiveInt = 0;
		try{
			positiveInt = Integer.parseInt(intStr);
		}catch(NumberFormatException e){
			return false;
		}
		return positiveInt>0;
	}


}
