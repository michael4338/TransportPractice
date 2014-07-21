package com.tw.trains.app.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

import com.tw.trains.app.view.IUserInteraction;

public class UIControlCenter {
	
	//map the command coming from user input and the processing object of this command 
	private Hashtable<String, Processor> hashTable = new Hashtable<String, Processor>();
	
	//relate to a specific user interaction object
	private IUserInteraction ui = null;	
	
	public final static String END = "#";
	private final static String END_INSTRUCTION = " *Please input command " + END +" to quit the system\n";
	private final static String END_GREETING = "Thank you for using Routes Query System, see you next time\n";
	
	//singleton, do not have a lock because we get an instance on single thread entrance
	private static UIControlCenter instance = null;
	private UIControlCenter(){
	}
	public static UIControlCenter getInstance(){
		if(instance == null){
			instance = new UIControlCenter();
		}
		return instance;	
	}
	
	
	public IUserInteraction getUI() {
		return ui;
	}

	public void setUI(IUserInteraction ui) {
		this.ui = ui;
	}
	
	public void showInfoToUser(String info){
		if(ui != null)
			ui.showInfoToUser(info);
	}
	
	public String getInfoFromUser(){
		if(ui != null)
			return ui.getInfoFromUser();
		return null;
	}
	
	public boolean register(String cmd, Processor processor){
		if (hashTable.get(cmd) != null) 
			return false;
		
		hashTable.put(cmd, processor);	
		return true;
	}
	
	public boolean unregister(String cmd){
		return hashTable.remove(cmd) == null ? false : true;
	}

	public Processor getProcessorByKey(String key){
		if(key == null) 
			return null;
		return hashTable.get(key);
	}
	
	public void showProcessorInstructions(){
		String instructions = "\n";
		
		ArrayList<String> keysList = new ArrayList<String>();
		for(Iterator<String> iterator = hashTable.keySet().iterator(); iterator.hasNext();)
			keysList.add(iterator.next());
		keysList = getOrderedKeys(keysList);
		
		for(int i=0; i<keysList.size(); i++)
			instructions += hashTable.get(keysList.get(i)).getInstruction();
		
		showInfoToUser(instructions+END_INSTRUCTION+"\n");
	}
	
	/*
	 * Get input commands from user,
	 * and dispatch the commands to their targeting processing object respectively
	 */
	public void run(){
		showProcessorInstructions();
		String alertMsg = "Invalid command, please input again\n";
		String prompMsg = "Input the command: ";
		
		do {
			showInfoToUser(prompMsg);
			String key = getInfoFromUser();
			Processor processor = getProcessorByKey(key);
			if (processor != null) {
				processor.process(key);
				showProcessorInstructions();
			}else{
				if(key.equalsIgnoreCase(END)){
					showInfoToUser(END_GREETING);
					break;
				}
				else
					showInfoToUser(alertMsg);
			}
		} while (true);
		
	}
	
	
	
	
	private ArrayList<String> getOrderedKeys(ArrayList<String> list){
		Collections.sort(list);
		return list;
	}
}
