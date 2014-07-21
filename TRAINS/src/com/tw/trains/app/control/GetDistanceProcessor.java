package com.tw.trains.app.control;

import com.tw.trains.app.model.DataCenterService;

public class GetDistanceProcessor extends Processor {
	
	public final static String KEY = "2";
	private final String GETDISTANCE_INSTRUCTION = 
		" *Please input command " + KEY + " to query the distance of one route\n";
	
	private final String NO_SUCH_ROUTE = "NO SUCH ROUTE\n";
	
	public GetDistanceProcessor(UIControlCenter uiCenter){
		super(uiCenter);
	}
	
	@Override
	public boolean process(String cmd) {
		String errMsg = "logical error occurs in distance processor\n";
		String instruction = "\nInput routes with town names separated by \'" 
			+ Processor.SEP 
			+ "\', for example A-B-C.  "
			+ "Input \'"+Processor.END+"\' to quit this command\n";
		String promptMsg = "Input the routes: ";
		
		if (!super.validateInitialCmd(cmd, KEY, instruction, errMsg))
			return false;
		
		doProcess(promptMsg);
		
		return true;
	}
	
	@Override
	public String getInstruction(){
		return GETDISTANCE_INSTRUCTION;
	}
	
	@Override
	protected void callDataService(String cmd){
		String[] towns = parseCmd(cmd);
		if(towns != null){
			DataCenterService dataService = DataCenterService.getInstance();
			int distance = dataService.getDistance(towns);
			if(distance == -1) getUiCenter().showInfoToUser(NO_SUCH_ROUTE);
			else getUiCenter().showInfoToUser("the distance is: "+distance+"\n");
		}
	}
	
	
	
	private String[] parseCmd(String cmd){
		// parse the command which is supposed to be like "A-B-C"
		String alertMsg = "Invalid routes, please input again\n";
		
		String[] towns = cmd.split(Processor.SEP);
		if (towns.length < 2) {
			getUiCenter().showInfoToUser(alertMsg);
			return null;
		}
		
		return towns;
	}
	
}
