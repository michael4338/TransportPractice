package com.tw.trains.test.control;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value=Suite.class)
@SuiteClasses(value={
	AddTracksProcessorTest.class,
	GetDistanceProcessorTest.class,
	GetShortestTripProcessorTest.class,
	GetTripsNumWithExactStopsProcessorTest.class,
	GetTripsNumWithMaximumStopsProcessorTest.class,
	GetTripsNumWithMaximumDistanceProcessorTest.class,
	UIControlCenterTest.class
})
public class ControlSuite {

}
