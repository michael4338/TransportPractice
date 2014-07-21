package com.tw.trains.test.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value=Suite.class)
@SuiteClasses(value={
	DataCenterServiceTest.class,
	DataCenterCoreGraphTest.class
})
public class ModelSuite {

}
