package com.tw.trains.test.control;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tw.trains.app.control.GetTripsNumWithExactStopsProcessor;
import com.tw.trains.app.control.Processor;
import com.tw.trains.app.control.UIControlCenter;
import com.tw.trains.app.view.IUserInteraction;
import com.tw.trains.test.view.StringBufferUserInteraction;

public class GetTripsNumWithExactStopsProcessorTest {
	
	private static Processor processor = null;
	private static UIControlCenter uiCenterInstance = null;
	private static IUserInteraction stringBufferUI = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stringBufferUI = new StringBufferUserInteraction();
		uiCenterInstance = UIControlCenter.getInstance();
		uiCenterInstance.setUI(stringBufferUI);
		
		processor = new GetTripsNumWithExactStopsProcessor(uiCenterInstance);
		uiCenterInstance.register(GetTripsNumWithExactStopsProcessor.KEY, processor);
	}

	@AfterClass
	public static void tearDownBeforeClass() throws Exception {
		processor = null;
		stringBufferUI = null;
		uiCenterInstance.unregister(GetTripsNumWithExactStopsProcessor.KEY);
	}

	@Test
	public void testProcess1() {
		String invalidKey = "anything";
		assertFalse(processor.process(invalidKey));
	}
	
	@Test
	public void testProcess2() {
		((StringBufferUserInteraction)stringBufferUI).setInputString(UIControlCenter.END);
		String validKey = GetTripsNumWithExactStopsProcessor.KEY;
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
		String alertMsg = "The number of trips is: 3.\n";
		String validCmd = "a-c-4";
		
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
		String alertMsg = "The number of trips is: 1.\n";
		String validCmd = "e-d-5";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd); 
	
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}


}
