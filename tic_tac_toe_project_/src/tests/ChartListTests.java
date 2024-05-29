package tests;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.ChartList;
import model.GameRecord;

class ChartListTests {
	
	ChartList<String> chartList;
	
	@BeforeEach
	void init() {
		chartList = new ChartList<>(5);
	}
	
	@Test
	@DisplayName("New ChartList must have correct capacity")
	void testInitialCapacity() {
		assertEquals(chartList.capacity(), 5,"Initial Capacity should be 5");
	}
	
	
	@Test
	@DisplayName("Chart list access should respects List boundaries")
	void testGetOutOfBoundsThrowsException() {
		Throwable ule = assertThrows(IndexOutOfBoundsException.class, ()->chartList.get(6));
		assertEquals(ule.getMessage(), "6 is not a valid index");
	}
	
	
	@Test
	@DisplayName("ChartList size must increment after each add")
	void testSizeIncrement() {
		chartList.add("Eirini");
		chartList.add("Christos");
		assertEquals(chartList.size(), 2,"Initial Capacity should be 2");
	}
	
	
	@Test
	@DisplayName("Full List should not accept more elements")
	void testAddRespectsLimits() {
		chartList.add("Nektarios");
		chartList.add("Yannis");
		chartList.add("Vasilis");
		chartList.add("Nikos");
		chartList.add("George");		
		assertEquals(chartList.size(), 5,"List size should be 5");
	}
	
	
	
	@Test
	@DisplayName("Chart List items should be ordered")
	void testAddPreservesOrdering() {
		chartList.add("Ann");
		chartList.add("Bill");
		chartList.add("Zoe");
		chartList.add("Christos");
		chartList.add("Eirini");		
		/*compareTo will place the strings in
		 *alphabetical order
		 */
		assertEquals(chartList.get(0), "Ann");
		assertEquals(chartList.get(1), "Bill");
	    assertEquals(chartList.get(2), "Christos");
	    assertEquals(chartList.get(3), "Eirini");
		assertEquals(chartList.get(4), "Zoe");
				
	}
	
	
	@Test
	@DisplayName("Adding empty List should not increment the list size")
	void testAddEmptyChartList() {
		chartList.add("Eirini");
		chartList.add("Christos");
		String[] second = new String[2];
		chartList.addAll(second);
		assertEquals(chartList.size(),2,"List size should be 2");		
	}
	
	
	@Test
	@DisplayName("Adding a non empty ChartList should increment the list size, and contain the elements")
	void testAddNonEmptyChartList() {
		chartList.add("Eirini");
		chartList.add("Christos");
		//size=2 and capacity()=5
		String[] second = {"Vasilis","Petros"};		
		chartList.addAll(second);
		assertEquals(chartList.size(),4,"List size should be 4");
				
	}	
	
	
	@Test
	@DisplayName("Testing if the list can be cleared")
	void testingClear() {
		String[] arr = {"Eirini", "Christos", "John", "Zoe"};
		chartList.addAll(arr);
		assertEquals("Christos", chartList.get(0));
		assertEquals("Eirini", chartList.get(1));
		assertEquals("John", chartList.get(2));
		assertEquals("Zoe", chartList.get(3));
		
		chartList.clear();
		assertEquals(null, chartList.get(0));
		assertEquals(null, chartList.get(1));
		assertEquals(null, chartList.get(2));
		assertEquals(null, chartList.get(3));

	}

	
	@Test 
	@DisplayName("Testing addExtend")
	void addExtendTest() {
		String[] arr = {"Eirini", "Christos", "John", "Ann", "Danae"};
		chartList.addAll(arr);	
		
		chartList.addExtend("Zoe");
		assertEquals("Zoe", chartList.get(5));
		assertEquals(6, chartList.capacity());
		assertEquals(6, chartList.size());
	}
}