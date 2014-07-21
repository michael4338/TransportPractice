package com.tw.trains.test.control;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tw.trains.app.control.AddTracksProcessor;
import com.tw.trains.app.control.Processor;
import com.tw.trains.app.control.UIControlCenter;
import com.tw.trains.app.view.IUserInteraction;
import com.tw.trains.test.view.StringBufferUserInteraction;

public class AddTracksProcessorTest {
	
	private static Processor processor = null;
	private static UIControlCenter uiCenterInstance = null;
	private static IUserInteraction stringBufferUI = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stringBufferUI = new StringBufferUserInteraction();
		uiCenterInstance = UIControlCenter.getInstance();
		uiCenterInstance.setUI(stringBufferUI);
		
		processor = new AddTracksProcessor(uiCenterInstance);
		uiCenterInstance.register(AddTracksProcessor.KEY, processor);
	}

	@AfterClass
	public static void tearDownBeforeClass() throws Exception {
		processor = null;
		stringBufferUI = null;
		uiCenterInstance.unregister(AddTracksProcessor.KEY);
	}
	
	@Test
	public void addDataIntoDataStore() throws Exception{

		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);  

		String validCmd = "a-b-5";
		testMethod.invoke(processor, validCmd);  
		validCmd = "b-c-4";
		testMethod.invoke(processor, validCmd);  
		validCmd = "c-d-8";
		testMethod.invoke(processor, validCmd);  
		validCmd = "d-c-8";
		testMethod.invoke(processor, validCmd);  
		validCmd = "d-e-6";
		testMethod.invoke(processor, validCmd);  
		validCmd = "a-d-5";
		testMethod.invoke(processor, validCmd);  
		validCmd = "c-e-2";
		testMethod.invoke(processor, validCmd);  
		validCmd = "e-b-3";
		testMethod.invoke(processor, validCmd);    		
		
	}

	@Test
	public void testProcess1() {
		String invalidKey = "anything";
		assertFalse(processor.process(invalidKey));
	}
	
	@Test
	public void testProcess2() {
		((StringBufferUserInteraction)stringBufferUI).setInputString(UIControlCenter.END);
		String validKey = AddTracksProcessor.KEY;
		assertTrue(processor.process(validKey));
	}

	@Test
	public void testGetInstruction() {
		String ADDTRACKS_INSTRUCTION = 
			" *Please input command " + AddTracksProcessor.KEY + " for administrators to add new tracks\n";
		assertEquals(processor.getInstruction(), ADDTRACKS_INSTRUCTION);
	}

	@Test
	public void testCallDataService1() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid routes, please input again\n";
		String invalidCmd = "abcd";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, invalidCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService2() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid routes, please input again\n";
		String invalidCmd = "a-b";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, invalidCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService3() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid routes, please input again\n";
		String invalidCmd = "a-b-c";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, invalidCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}

	@Test
	public void testCallDataService4() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid routes, please input again\n";
		String invalidCmd = "a-b-0";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, invalidCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}

	@Test
	public void testCallDataService5() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "Invalid routes, please input again\n";
		String invalidCmd = "a-b--3";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, invalidCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService6() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "new tracks added successfully.\n";
		String validCmd = "a-e-7";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	
	@Test
	public void testCallDataService7() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String alertMsg = "new tracks added failed.\n";
		String validCmd = "a-b-6";
		
		Method testMethod = processor.getClass().getDeclaredMethod("callDataService",String.class);   
		testMethod.setAccessible(true);   
		testMethod.invoke(processor, validCmd);  
		
		assertEquals(alertMsg, ((StringBufferUserInteraction)stringBufferUI).getOutputString());
	}
	


}
