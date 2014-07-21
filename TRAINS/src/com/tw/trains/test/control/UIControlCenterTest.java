package com.tw.trains.test.control;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tw.trains.app.control.GetDistanceProcessor;
import com.tw.trains.app.control.Processor;
import com.tw.trains.app.control.UIControlCenter;
import com.tw.trains.app.view.IUserInteraction;
import com.tw.trains.test.view.StringBufferUserInteraction;

public class UIControlCenterTest {
	
	private static UIControlCenter uiCenterInstance = null;
	private static IUserInteraction stringBufferUI = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stringBufferUI = new StringBufferUserInteraction();
		uiCenterInstance = UIControlCenter.getInstance();
		uiCenterInstance.setUI(stringBufferUI);
	}

	@AfterClass
	public static void tearDownBeforeClass() throws Exception {
		stringBufferUI = null;
		uiCenterInstance.unregister(GetDistanceProcessor.KEY);
	}

	@Test
	public void testShowInfoToUser1() {
		uiCenterInstance.showInfoToUser("anything");
		String oldOutput = ((StringBufferUserInteraction)stringBufferUI).getOutputString();
		String showUser = oldOutput + "somethingelse";
		uiCenterInstance.showInfoToUser(showUser);
		assertTrue(!oldOutput.equals(((StringBufferUserInteraction)stringBufferUI).getOutputString()));
	}
	
	@Test
	public void testShowInfoToUser2() {
		String oldOutput = ((StringBufferUserInteraction)stringBufferUI).getOutputString();
		String showUser = oldOutput + "somethingelse";
		uiCenterInstance.setUI(null);
		uiCenterInstance.showInfoToUser(showUser);
		assertEquals(oldOutput, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
		uiCenterInstance.setUI(stringBufferUI);
	}
	
	@Test
	public void testGetInfoFromUser() {
		String inputStr = "input from user\n";
		((StringBufferUserInteraction)stringBufferUI).setInputString(inputStr);
		assertEquals(uiCenterInstance.getInfoFromUser(), inputStr);
	}

	@Test
	public void testRegister1() {
		String key = "123";
		Processor processor = new GetDistanceProcessor(uiCenterInstance);
		assertTrue(uiCenterInstance.register(key, processor));
	}
	
	@Test
	public void testRegister2() {
		String key = "123";
		Processor processor = new GetDistanceProcessor(uiCenterInstance);
		assertFalse(uiCenterInstance.register(key, processor));
	}

	@Test
	public void testGetProcessorByKey1(){
		String key = "123";
		assertNotNull(uiCenterInstance.getProcessorByKey(key));
	}
	
	@Test
	public void testUnregister1() {
		String key = "123";
		assertTrue(uiCenterInstance.unregister(key));
	}
	
	@Test
	public void testUnregister2() {
		String key = "123";
		assertFalse(uiCenterInstance.unregister(key));
	}
	
	@Test
	public void testGetProcessorByKey2(){
		String key = "123";
		assertNull(uiCenterInstance.getProcessorByKey(key));
	}
	
}