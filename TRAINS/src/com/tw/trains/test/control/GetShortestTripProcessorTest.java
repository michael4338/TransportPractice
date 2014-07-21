package com.tw.trains.test.control;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tw.trains.app.control.GetShortestTripProcessor;
import com.tw.trains.app.control.Processor;
import com.tw.trains.app.control.UIControlCenter;
import com.tw.trains.app.view.IUserInteraction;
import com.tw.trains.test.view.StringBufferUserInteraction;

public class GetShortestTripProcessorTest {
	
	private static Processor processor = null;
	private static UIControlCenter uiCenterInstance = null;
	private static IUserInteraction stringBufferUI = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stringBufferUI = new StringBufferUserInteraction();
		uiCenterInstance = UIControlCenter.getInstance();
		uiCenterInstance.setUI(stringBufferUI);
		
		processor = new GetShortestTripProcessor(uiCenterInstance);
		uiCenterInstance.register(GetShortestTripProcessor.KEY, processor);
	}

	@AfterClass
	public static void tearDownBeforeClass() throws Exception {
		processor = null;
		stringBufferUI = null;
		uiCenterInstance.unregister(GetShortestTripProcessor.KEY);
	}

	@Test
	public void testProcess1() {
		String invalidKey = "anything";
		assertFalse(processor.process(invalidKey));
	}
	
	@Test
	public void testProcess2() {
		((StringBufferUserInteraction)stringBufferUI).setInputString(UIControlCenter.END);
		String validKey = GetShortestTripProcessor.KEY;
		assertTrue(processor.process(validKey));
	}

	@Test
	public void testGetInstruction() {
		String GETSHORTEST_TRIP_INSTRUCTION = 
			" *Please input command " + GetShortestTripProcessor.KEY + " to get the shortest trip (in terms of distance) between two towns\n";
		assertEquals(processor.getInstruction(), GETSHORTEST_TRIP_INSTRUCTION);
	}

	@Test
	public void testCallDataService1() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid towns, please input again\n";
		String invalidCmd = "abcd";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, invalidCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService2() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid towns, please input again\n";
		String invalidCmd = "ab-";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, invalidCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}

	@Test
	public void testCallDataService3() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid towns, please input again\n";
		String invalidCmd = "a-b-c";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, invalidCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService4() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "NO SUCH ROUTE\n";
		String validCmd = "e-a";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService5() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "the shortest distance is: 9\n";
		String validCmd = "a-c";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService6() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "the shortest distance is: 9\n";
		String validCmd = "b-b";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}

}
