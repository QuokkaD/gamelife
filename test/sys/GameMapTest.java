package sys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameMapTest {
	private static GameMap gamemap=new GameMap();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void testGameMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testGameMapIntInt() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testGetMapRow() {
		assertEquals(10,gamemap.getMapRow());
	}

	@Test
	public void testGetMapCol() {
		assertEquals(10,gamemap.getMapCol());
	}

	//在地图中设定某个细胞进行测试
	@Test
	public void testPlace() {
		Cell cell1=new Cell();
		Cell cell2=new Cell(true,false);
		Cell cell3=new Cell(false,false);
		assertEquals(cell1,gamemap.place(0, 0, cell1));
		assertEquals(cell2,gamemap.place(0, 1, cell2));
		assertEquals(cell3,gamemap.place(9, 9, cell3));
	}

	/*@Test
	public void testGet() {
		fail("Not yet implemented");
	}*/

	//对地图所有细胞设置状态功能进行测试
	@Test
	public void testSetStatus() {
		boolean status = true;
		for(int r=0;r<gamemap.getMapRow();r++) {
			for(int c=0;c<gamemap.getMapCol();c++){
				gamemap.setStatus(r, c, status);
				assertEquals(status,gamemap.getCellStatus(r, c));
				status = !status;
				
			}
		}   
	}

	//测试获取细胞邻居功能
	@Test
	public void testGetNeighbor() {
		//生成地图
		//这里使用的初始态地图都是具有一定特殊特征的，这里是行列间隔生死细胞
		boolean status = true;
		for(int r=0;r<gamemap.getMapRow();r++) {
			for(int c=0;c<gamemap.getMapCol();c++){
				gamemap.setStatus(r, c, status);
				status = !status;
				
			}
		}  
		//由于地图本身具有一定的对称性，因此演化细胞可共分为三类
		//选取三类细胞分别进行测试判断
		//第一类
		Cell[] neighbor1=gamemap.getNeighbor(0,0);
		int numOfLive=0;
		for(Cell c:neighbor1) {
			if(c.isAlive()) {
				numOfLive++;
			}
		}
		assertEquals(1,numOfLive);
		
		//第二类
		Cell[] neighbor2=gamemap.getNeighbor(9,1);
		numOfLive=0;
		for(Cell c:neighbor2) {
			if(c.isAlive()) {
				numOfLive++;
			}
		}
		assertEquals(4,numOfLive);    ///行列运算问题
		
		//第三类
		Cell[] neighbor3=gamemap.getNeighbor(5,4);
		numOfLive=0;
		for(Cell c:neighbor3) {
			if(c.isAlive()) {
				numOfLive++;
			}
		}
		assertEquals(2,numOfLive);
	}
   
	//测试设置随机状态功能
	@Test
	public void testRandomCellsStatus() {
		int ran;
		for(int i=0;i<10;i++) {
			ran=(int)(Math.random()*100);
			gamemap.randomCellsStatus(ran);
		     int rate=gamemap.getDieRate();
		     boolean flag=false;
		     
		     if(rate>=ran-10&&rate<=ran+10) {
		    	 flag=true;
		     }
	         assertTrue(flag==true);
		} 
		
	}

	//测试获取地图功能
	@Test
	public void testGetmap() {
		GameMap tMap=new GameMap();
		boolean status = true;
		for(int r=0;r<gamemap.getMapRow();r++) {
			for(int c=0;c<gamemap.getMapCol();c++){
				gamemap.setStatus(r, c, status);
				//gamemap.getCellStatus(r, c);
				status = !status;
				
			}
		}  
		tMap.Getmap(gamemap);
		
		status = true;
		for(int r=0;r<tMap.getMapRow();r++) {
			for(int c=0;c<tMap.getMapCol();c++){
				//status = !status;
				gamemap.getCellStatus(r, c);
				//assertEquals(status,tMap.getCellStatus(r, c));
				status = !status;
				
			}
		}  	
		
	}
}
