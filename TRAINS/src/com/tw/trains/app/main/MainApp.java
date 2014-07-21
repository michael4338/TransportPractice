package com.tw.trains.app.main;

import com.tw.trains.app.view.*;
import com.tw.trains.app.control.AddTracksProcessor;
import com.tw.trains.app.control.GetDistanceProcessor;
import com.tw.trains.app.control.GetShortestTripProcessor;
import com.tw.trains.app.control.GetTripsNumWithExactStopsProcessor;
import com.tw.trains.app.control.GetTripsNumWithMaximumDistanceProcessor;
import com.tw.trains.app.control.GetTripsNumWithMaximumStopsProcessor;
import com.tw.trains.app.control.UIControlCenter;

public class MainApp {
	
	public static void registerCommandsToProcessors(UIControlCenter uiCenterInstance){
		uiCenterInstance.register(AddTracksProcessor.KEY, new AddTracksProcessor(uiCenterInstance));
		uiCenterInstance.register(GetDistanceProcessor.KEY, new GetDistanceProcessor(uiCenterInstance));
		uiCenterInstance.register(GetShortestTripProcessor.KEY, new GetShortestTripProcessor(uiCenterInstance));
		uiCenterInstance.register(GetTripsNumWithExactStopsProcessor.KEY, new GetTripsNumWithExactStopsProcessor(uiCenterInstance));
		uiCenterInstance.register(GetTripsNumWithMaximumDistanceProcessor.KEY, new GetTripsNumWithMaximumDistanceProcessor(uiCenterInstance));
		uiCenterInstance.register(GetTripsNumWithMaximumStopsProcessor.KEY, new GetTripsNumWithMaximumStopsProcessor(uiCenterInstance));
	}
	
	public static void main(String[] args) {
		IUserInteraction ui = new ConsoleUserInteraction();
		ui.showUI();

		UIControlCenter uiCenterInstance = UIControlCenter.getInstance();
		uiCenterInstance.setUI(ui);
		
		/*
		 * UIControlCenter links the UI and Processor objects
		 */
		registerCommandsToProcessors(uiCenterInstance);
	
		uiCenterInstance.run();
	}

}
