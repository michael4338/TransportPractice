package com.tw.trains.test.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tw.trains.test.control.ControlSuite;
import com.tw.trains.test.model.ModelSuite;

@RunWith(value=Suite.class)
@SuiteClasses(value={
	ControlSuite.class,
	ModelSuite.class
})

public class AllTests {

}
