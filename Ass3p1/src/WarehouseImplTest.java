import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class WarehouseImplTest {

	Warehouse test;

	@Before
	public void setUp() throws Exception {
		test = new WarehouseImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test (expected = Exception.class)
	public void testaddException() throws Exception {
		test.add(true, "Hammer",-1);
		test.add(false,"Nail",-10);
		System.out.println("bdika");
	}

	@Test
	public void testAdd() throws Exception{
		/**
		 * testing add with adding a RepairTool (isTool == true).
		 */
		int qBefore = test.QuantityOf(true, "Hammer");
		test.add(true, "Hammer", 20);
		int qAfter = test.QuantityOf(true, "Hammer");
		assertEquals(qBefore+20,qAfter);
		assertFalse(test.isRepairToolsEmpty());
		assertTrue(test.isContain(true, "Hammer"));

		qBefore = test.QuantityOf(true, "Drill");
		test.add(true, "Drill", 5);
		qAfter = test.QuantityOf(true, "Drill");
		assertEquals(qBefore+5,qAfter);
		assertFalse(test.isRepairToolsEmpty());
		assertTrue(test.isContain(true, "Drill"));

		test.add(true, "Hammer", 30);
		assertEquals(test.QuantityOf(true,"Hammer"),50);
		assertTrue(test.isContain(true, "Hammer"));
		/**
		 * testing add with adding a RepairMaterial (isTool == false).
		 */		
		qBefore = test.QuantityOf(false, "Nail");
		test.add(false, "Nail", 100);
		qAfter = test.QuantityOf(false, "Nail");
		assertEquals(qBefore+100,qAfter);
		assertFalse(test.isRepairMaterialsEmpty());
		assertTrue(test.isContain(false, "Nail"));

		test.add(false, "Nail", 50);
		assertEquals(test.QuantityOf(false, "Nail"),150);
		assertFalse(test.isRepairMaterialsEmpty());
		assertTrue(test.isContain(false, "Nail"));

		test.add(false, "Screw", 230);
		assertEquals(test.QuantityOf(false,"Screw"),230);
		assertTrue(test.isContain(false, "Screw"));

	}

	@Test
	public void testIsRepairToolsEmpty() throws Exception{
		assertTrue(test.isRepairToolsEmpty());
		test.add(true, "Hammer", 20);
		assertFalse(test.isRepairToolsEmpty());
	}

	@Test
	public void testIsRepairMaterialsEmpty() throws Exception{
		assertTrue(test.isRepairMaterialsEmpty());
		test.add(false, "Nail", 100);
		assertFalse(test.isRepairMaterialsEmpty());
	}

	@Test
	public void testIsContain() throws Exception{
		assertFalse(test.isContain(true, "Hammer"));
		assertFalse(test.isContain(false, "Nail"));
		test.add(true, "Hammer", 20);
		assertTrue(test.isContain(true, "Hammer"));
		assertFalse(test.isContain(false, "Nail"));
		assertFalse(test.isContain(true, "Drill"));
		test.add(true, "Drill", 5);
		test.add(false, "Nail", 100);
		assertTrue(test.isContain(true, "Drill"));
		assertTrue(test.isContain(false, "Nail"));
		test.add(false, "Screw", 230);
		assertTrue(test.isContain(false, "Screw"));
	}

	@Test (expected = Exception.class)
	public void testQuantityOfException() throws Exception {
		test.QuantityOf(true, "Hammer");
		test.QuantityOf(false, "Nail");
	}

	@Test
	public void testQuantityOf() throws Exception{
		/**
		 * testing QuantityOf with adding a RepairTool (isTool == true).
		 */
		test.add(true, "Hammer", 20);
		assertEquals(test.QuantityOf(true, "Hammer"),20);
		test.add(true, "Hammer", 30);
		assertEquals(test.QuantityOf(true, "Hammer"),50);
		test.add(true, "Drill", 5);
		assertEquals(test.QuantityOf(true, "Drill"),5);
		test.add(true, "Drill", 5);
		assertEquals(test.QuantityOf(true, "Drill"),10);

		test.acquire(true, "Hammer", 30);
		assertEquals(test.QuantityOf(true, "Hammer"),20);
		test.acquire(true, "Hammer", 20);
		assertEquals(test.QuantityOf(true, "Hammer"),0);
		test.release(true, "Hammer", 50);
		assertEquals(test.QuantityOf(true, "Hammer"),50);
		/**
		 * testing QuantityOf with adding a RepairMaterial (isTool == false).
		 */		
		test.add(false, "Nail", 0);
		assertEquals(test.QuantityOf(false, "Nail"),0);
		test.add(false, "Nail", 100);
		assertEquals(test.QuantityOf(false, "Nail"),100);

		test.add(false, "Screw", 230);
		assertEquals(test.QuantityOf(false, "Screw"),230);
		test.add(false, "Screw", 170);
		assertEquals(test.QuantityOf(false, "Screw"),400);			
	}

	@Test (expected = Exception.class)
	public void testacquireException() throws Exception {
		test.acquire(true, "Hammer", 20);
		test.acquire(false, "Nail", 100);
	}

	@Test
	public void testacquire() throws Exception{
		test.add(true, "Hammer", 20);
		test.acquire(true, "Hammer", 10);
		assertEquals(test.QuantityOf(true, "Hammer"),10);
		test.acquire(true, "Hammer", 10);
		assertEquals(test.QuantityOf(true, "Hammer"),0);
		assertTrue(test.isContain(true, "Hammer"));

		test.add(false, "Nail", 100);
		test.acquire(false, "Nail", 20);
		assertEquals(test.QuantityOf(false, "Nail"),80);
		test.acquire(false, "Nail",70);
		assertEquals(test.QuantityOf(false, "Nail"),10);
	}

	@Test (expected = Exception.class)
	public void testreleaseException() throws Exception {
		test.release(true, "Hammer", 10);
		test.release(false, "Nail", 100);
	}

	@Test
	public void testRelease() throws Exception{
		test.add(true, "Hammer", 20);
		test.acquire(true, "Hammer", 10);
		test.release(true, "Hammer", 10);
		assertEquals(test.QuantityOf(true, "Hammer"),20);
		test.acquire(true, "Hammer", 15);
		test.release(true, "Hammer", 5);
		assertEquals(test.QuantityOf(true, "Hammer"),10);
		test.release(true, "Hammer", 10);
		assertEquals(test.QuantityOf(true, "Hammer"),20);
		test.acquire(true, "Hammer", 20);
		test.release(true, "Hammer", 2);
		assertEquals(test.QuantityOf(true, "Hammer"),2);
		test.release(true, "Hammer", 8);
		assertEquals(test.QuantityOf(true, "Hammer"),10);
		test.release(true, "Hammer", 5);
		assertEquals(test.QuantityOf(true, "Hammer"),15);

		test.add(false, "Nail", 20);
		test.acquire(false, "Nail", 10);
		test.release(false, "Nail", 10);
		assertEquals(test.QuantityOf(false, "Nail"),20);
		test.acquire(false, "Nail", 15);
		test.release(false, "Nail", 5);
		assertEquals(test.QuantityOf(false, "Nail"),10);
		test.release(true, "Hammer", 10);
		assertEquals(test.QuantityOf(false, "Nail"),20);
		test.acquire(false, "Nail", 20);
		test.release(false, "Nail", 2);
		assertEquals(test.QuantityOf(false, "Nail"),2);
		test.release(false, "Nail", 8);
		assertEquals(test.QuantityOf(false, "Nail"),10);
		test.release(false, "Nail", 5);
		assertEquals(test.QuantityOf(false, "Nail"),15);
	}

}
