package testingDates;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.experimental.runners.Enclosed;

@RunWith(Enclosed.class)
public class DateAddDaysTest {

	
	@RunWith(Parameterized.class)
	public static class TestAddsADayInSameMonthCorrectly {
		@Parameters
		public static Integer[] expectedDays() {
			return new Integer[] {2, 10, 30};
		}
		
		@Parameter
		public int expectedDay;
	
		
		@Test
		public void testReturnsCorrectDayWhenAddingADayInSameMonth_CaseOne() throws Exception {
			int expectedMonth = 2;
			int expectedYear = 2025;
			Date originalDate = new Date(expectedMonth, expectedDay - 1, expectedYear);
			Date expectedDate = new Date(expectedMonth, expectedDay, expectedYear);
			
			Date newDate = originalDate.addOneDay();
			
			assertTrue(expectedDate.equals(newDate));
		}
	}
	
	@Test
	public void testAddsDayCorrectlyWhenCrossingMonthBoundary_case31Days() throws Exception {
		Date originalDate = new Date(1,31, 2025);
		Date expectedDate = new Date(2,1,2025);
		
		Date actualDate = originalDate.addOneDay();
		
		assertTrue(expectedDate.equals(actualDate));
	}
	
	@Test
	public void testAddsDayCorrectlyWhenCrossingMonthBoundary_case28Days() throws Exception {
		Date originalDate = new Date(2,28, 2025);
		Date expectedDate = new Date(3,1,2025);
		
		Date actualDate = originalDate.addOneDay();
		
		assertTrue(expectedDate.equals(actualDate));
	}
	
	@Test
	public void testAddsDayCorrectlyWhenCrossingMonthBoundary_case30Days() throws Exception {
		Date originalDate = new Date(6,30, 2025);
		Date expectedDate = new Date(7,1,2025);
		
		Date actualDate = originalDate.addOneDay();
		
		assertTrue(expectedDate.equals(actualDate));
	}
	
	@Test
	public void testAddsDayCorrectlyWhenCrossingYearBoundary() throws Exception {
		Date originalDate = new Date(12,31, 2025);
		Date expectedDate = new Date(1,1,2026);
		
		Date actualDate = originalDate.addOneDay();
		
		assertTrue(expectedDate.equals(actualDate));
	}
}
