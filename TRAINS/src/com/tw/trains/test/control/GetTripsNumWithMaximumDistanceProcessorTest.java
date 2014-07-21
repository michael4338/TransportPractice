package com.tw.trains.test.control;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tw.trains.app.control.GetTripsNumWithMaximumDistanceProcessor;
import com.tw.trains.app.control.Processor;
import com.tw.trains.app.control.UIControlCenter;
import com.tw.trains.app.view.IUserInteraction;
import com.tw.trains.test.view.StringBufferUserInteraction;

public class GetTripsNumWithMaximumDistanceProcessorTest {
	
	private static Processor processor = null;
	private static UIControlCenter uiCenterInstance = null;
	private static IUserInteraction stringBufferUI = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stringBufferUI = new StringBufferUserInteraction();
		uiCenterInstance = UIControlCenter.getInstance();
		uiCenterInstance.setUI(stringBufferUI);
		
		processor = new GetTripsNumWithMaximumDistanceProcessor(uiCenterInstance);
		uiCenterInstance.register(GetTripsNumWithMaximumDistanceProcessor.KEY, processor);
	}

	@AfterClass
	public static void tearDownBeforeClass() throws Exception {
		processor = null;
		stringBufferUI = null;
		uiCenterInstance.unregister(GetTripsNumWithMaximumDistanceProcessor.KEY);
	}

	@Test
	public void testProcess1() {
		String invalidKey = "anything";
		assertFalse(processor.process(invalidKey));
	}
	
	@Test
	public void testProcess2() {
		((StringBufferUserInteraction)stringBufferUI).setInputString(UIControlCenter.END);
		String validKey = GetTripsNumWithMaximumDistanceProcessor.KEY;
		assertTrue(processor.process(validKey));
	}

	@Test
	public void testCallDataService1() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid command, please input again\n";
		String invalidCmd = "abcd";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, invalidCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService2() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid command, please input again\n";
		String invalidCmd = "a-b-";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, invalidCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}

	@Test
	public void testCallDataService4() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid command, please input again\n";
		String validCmd = "a-e-d";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService5() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid command, please input again\n";
		String validCmd = "a-e-0";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService6() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid command, please input again\n";
		String validCmd = "a-e--3";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService7() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "The number of trips is: 7.\n";
		String validCmd = "c-c-30";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd); 
	
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService8() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "The number of trips is: 0.\n";
		String validCmd = "e-a-2";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd); 
	
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService9() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "The number of trips is: 2.\n";
		String validCmd = "a-d-20";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd); 
	
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}


}
