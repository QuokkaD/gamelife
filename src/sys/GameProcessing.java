package sys;

public class GameProcessing {

//计算细胞地图的下一状态
public void NextStatus(int r,int co, GameMap map) {
		
		GameMap tempmap = new GameMap(r,co);
		tempmap.Getmap(map);
		for(int row=0;row<map.getMapRow();row++) {
			for(int col=0;col<map.getMapCol();col++) {
				Cell cell=map.get(row, col);
				//使用暂时地图保存原有地图
				Cell[] neighbor=tempmap.getNeighbor(row, col);
				int numOfLive=0;
				for(Cell c:neighbor) {
					if(c.isAlive()) {
						numOfLive++;
					}
				}
				if(cell.isAlive()) {
					if(numOfLive<2||numOfLive>3) {
						cell.die();
					}
				}
				else if(numOfLive==3) {
						cell.reborn();
					}	
			}
		}
		
	}
}
