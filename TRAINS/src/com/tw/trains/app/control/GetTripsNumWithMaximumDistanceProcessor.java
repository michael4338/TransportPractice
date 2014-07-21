package com.tw.trains.app.control;

import com.tw.trains.app.model.DataCenterService;

public class GetTripsNumWithMaximumDistanceProcessor extends Processor {
	public final static String KEY = "5";
	private final static String MAXMUM_DISTANCE_INSTRUCTION = 
		" *Please input command " + KEY + " to query the number of trips from one town to another by NO more than specific distance\n";

	public GetTripsNumWithMaximumDistanceProcessor(UIControlCenter uiCenter){
		super(uiCenter);
	}
	
	
	@Override
	public boolean process(String cmd) {
		String errMsg = "logical error occurs in GetTripsNum with maximum distance processor\n";
		String instruction = "\nInput two towns' names and the maximum distance between them separated by \'" 
			+ Processor.SEP 
			+ "\', for example A-B-5.  "
			+ "Input \'"+Processor.END+"\' to quit this command\n";
		String promptMsg = "Input names and distance: ";
		
		if (!super.validateInitialCmd(cmd, KEY, instruction, errMsg))
			return false;
		
		doProcess(promptMsg);
		
		return true;
	}
	
	@Override
	public String getInstruction(){
		return MAXMUM_DISTANCE_INSTRUCTION;
	}
	
	@Override
	protected void callDataService(String cmd){
		String[] values = parseCmd(cmd);
		if(values != null){
			DataCenterService dataService = DataCenterService.getInstance();
			int result = dataService.getTripsNumWithMaximumDistance(values[0], values[1], Integer.parseInt(values[2]));
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
