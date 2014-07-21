package com.tw.trains.app.control;

import com.tw.trains.app.model.DataCenterService;

public class GetTripsNumWithMaximumStopsProcessor extends Processor {
	public final static String KEY = "3";
	private final static String MAXIMUM_STOP_INSTRUCTION = 
		" *Please input command " + KEY + " to query the number of trips from one town to another by maximum number of stops\n";

	public GetTripsNumWithMaximumStopsProcessor(UIControlCenter uiCenter){
		super(uiCenter);
	}
	
	
	@Override
	public boolean process(String cmd) {
		String errMsg = "logical error occurs in GetTripsNum with maximum stops processor\n";
		String instruction = "\nInput two towns' names and the maximum stops between them separated by \'" 
			+ Processor.SEP 
			+ "\', for example A-B-5.  "
			+ "Input \'"+Processor.END+"\' to quit this command\n";
		String promptMsg = "Input names and number of stops: ";
		
		if (!super.validateInitialCmd(cmd, KEY, instruction, errMsg))
			return false;
		
		doProcess(promptMsg);
		
		return true;
	}
	
	@Override
	public String getInstruction(){
		return MAXIMUM_STOP_INSTRUCTION;
	}
	
	@Override
	protected void callDataService(String cmd){
		String[] values = parseCmd(cmd);
		if(values != null){
			DataCenterService dataService = DataCenterService.getInstance();
			int result = dataService.getTripsNumWithMaximumStops(values[0], values[1], Integer.parseInt(values[2]));
			getUiCenter().showInfoToUser("The number of trips is: "+result+".\n");
		}
	}
	
	
	
	
	private String[] parseCmd(String cmd){
		// parse the command which is supposed to be like "A-B-5"
		String alertMsg = "Invalid command, please input again\n";
		
		String[] values = cmd.split(Processor.SEP);
		if (values.length != 3 || !validatePositiveInteger(values[values.length-1])) {
			getUiCenter().showInfoToUser(alertMsg);
			return null;
		}
		
		return values;
	}
		
}
