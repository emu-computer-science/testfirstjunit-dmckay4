package testingDates;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DateSetDateTest {
	
	Date date;
	@Before
	public void before() {
		date = new Date (2,2,2222);
	}
	
	@Test
	public void testSetsDateCorrectlyWhenYearMonthAndDayAreValid() throws Exception {
		date.setDate("March", 1, 1999);
		
		assertEquals(3, date.getMonth());
		assertEquals(1, date.getDay());
		assertEquals(1999, date.getYear());
	}
	
	@Test
	public void testDoesNotSetDateWhenMonthIsInvalid() throws Exception {
		date.setDate("Moose", 1, 1999);
		
		assertEquals(2, date.getMonth());
		assertEquals(2, date.getDay());
		assertEquals(2222, date.getYear());
	}
	
	@Test
	public void testDoesNotSetDateWhenDayIsInvalid() throws Exception {
		date.setDate("March", 0, 1999);
		
		assertEquals(2, date.getMonth());
		assertEquals(2, date.getDay());
		assertEquals(2222, date.getYear());
	}
	
	@Test
	public void testDoesnotSetDateWhenDayDoesNotFitInMonth() throws Exception {
		date.setDate("February", 29, 1999);
		
		assertEquals(2, date.getMonth());
		assertEquals(2, date.getDay());
		assertEquals(2222, date.getYear());
	}
	
	@Test
	public void testDoesNotSetDateWhenIsInvalid() throws Exception {
		date.setDate("March", 1, 10000);
		
		assertEquals(2, date.getMonth());
		assertEquals(2, date.getDay());
		assertEquals(2222, date.getYear());
	}

}
