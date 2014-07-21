package com.tw.trains.app.control;

import com.tw.trains.app.model.DataCenterService;

public class GetShortestTripProcessor extends Processor {
	
	public final static String KEY = "6";
	private final String GETSHORTEST_TRIP_INSTRUCTION = 
		" *Please input command " + KEY + " to get the shortest trip (in terms of distance) between two towns\n";
	
	private final String NO_SUCH_ROUTE = "NO SUCH ROUTE\n";
	
	
	public GetShortestTripProcessor(UIControlCenter uiCenter){
		super(uiCenter);
	}
	
	@Override
	public boolean process(String cmd) {
		String errMsg = "logical error occurs in GetShortestTrip processor\n";
		String instruction = "\nInput two towns' name with town names separated by \'" 
			+ Processor.SEP 
			+ "\', for example A-B.  "
			+ "Input \'"+Processor.END+"\' to quit this command\n";
		String promptMsg = "Input the names: ";
		
		if (!super.validateInitialCmd(cmd, KEY, instruction, errMsg))
			return false;
		
		doProcess(promptMsg);
		
		return true;
	}
	
	@Override
	public String getInstruction(){
		return GETSHORTEST_TRIP_INSTRUCTION;
	}
	
	@Override
	protected void callDataService(String cmd){
		String[] towns = parseCmd(cmd);
		if(towns != null){
			DataCenterService dataService = DataCenterService.getInstance();
			int distance = dataService.getShortestTrip(towns[0], towns[1]);
			if(distance == -1) getUiCenter().showInfoToUser(NO_SUCH_ROUTE);
			else getUiCenter().showInfoToUser("the shortest distance is: "+distance+"\n");
		}
	}
	
	
	
	private String[] parseCmd(String cmd){
		// parse the command which is supposed to be like "A-B-C"
		String alertMsg = "Invalid towns, please input again\n";
		
		String[] towns = cmd.split(Processor.SEP);
		if (towns.length != 2) {
			getUiCenter().showInfoToUser(alertMsg);
			return null;
		}
		
		return towns;
	}
	
}
