package sys;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CellTest {
    private static Cell cell=new Cell(); 
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testDie() {
		cell.die();
		assertEquals(false,cell.isAlive());
	}

	@Test
	public void testReborn() {
		cell.reborn();
		assertEquals(true,cell.isAlive());
		cell.die();
	}


	@Test
	public void testSetAlive() {
		cell.setAlive(true);
		assertEquals(true,cell.isAlive());
		cell.die();
	}


}
