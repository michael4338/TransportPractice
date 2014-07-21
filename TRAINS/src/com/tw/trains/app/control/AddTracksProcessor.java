package com.tw.trains.app.control;

import com.tw.trains.app.model.DataCenterService;

public class AddTracksProcessor extends Processor {
	public final static String KEY = "1";
	private final static String ADDTRACKS_INSTRUCTION = 
		" *Please input command " + KEY + " for administrators to add new tracks\n";

	public AddTracksProcessor(UIControlCenter uiCenter){
		super(uiCenter);
	}
	
	
	@Override
	public boolean process(String cmd) {
		String errMsg = "logical error occurs in tracks adding processor\n";
		String instruction = "\nInput two towns' names and the instance between them separated by \'" 
			+ Processor.SEP 
			+ "\', for example A-B-5.  "
			+ "Input \'"+Processor.END+"\' to quit this command\n";
		String promptMsg = "Add the tracks: ";
		
		if (!super.validateInitialCmd(cmd, KEY, instruction, errMsg))
			return false;
		
		doProcess(promptMsg);
		
		return true;
	}
	
	@Override
	public String getInstruction(){
		return ADDTRACKS_INSTRUCTION;
	}
	
	@Override
	protected void callDataService(String cmd){
		String[] tracks = parseCmd(cmd);
		if(tracks != null){
			DataCenterService dataService = DataCenterService.getInstance();
			String result = dataService.addTrack(tracks[0], tracks[1], Integer.parseInt(tracks[2]))?"successfully":"failed";
			getUiCenter().showInfoToUser("new tracks added "+result+".\n");
		}
	}
	
	
	
	
	private String[] parseCmd(String cmd){
		// parse the command which is supposed to be like "A-B-5"
		String alertMsg = "Invalid routes, please input again\n";
		
		String[] towns = cmd.split(Processor.SEP);
		if (towns.length != 3 || !validatePositiveInteger(towns[towns.length-1])) {
			getUiCenter().showInfoToUser(alertMsg);
			return null;
		}
		
		return towns;
	}
		
}
