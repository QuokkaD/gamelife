package sys;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameProcessingTest {
	private GameMap gamemap1=new GameMap(4,4);
	private GameMap gamemap2=new GameMap(4,4);
	
	private GameProcessing gamepro=new GameProcessing();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//测试控制地图演化逻辑功能
	@Test
	public void testNextStatus() {
		//生成初始地图
		//行列生死间隔的地图
		boolean status = true;
		for(int r=0;r<gamemap1.getMapRow();r++) {
			for(int c=0;c<gamemap1.getMapCol();c++){
				gamemap1.setStatus(r, c, status);
				status = !status;
				
			}
		}  		 
		
		//演化结果地图
		status = true;
		gamemap2.setStatus(1, 0, status);
		gamemap2.setStatus(2, 0, status);
		gamemap2.setStatus(1, 2, status);
		gamemap2.setStatus(2, 2, status);
		gamemap2.setStatus(1, 3, status);
		gamemap2.setStatus(2, 3, status);
		
		//演化
		gamepro.NextStatus(gamemap1.getMapRow(), gamemap1.getMapCol(), gamemap1);	
		//判断
		for(int r=0;r<gamemap1.getMapRow();r++) {
			for(int c=0;c<gamemap1.getMapCol();c++) {
				assertEquals(gamemap1.getCellStatus(r, c),gamemap2.getCellStatus(r, c));
			}
	    }
	}
}
