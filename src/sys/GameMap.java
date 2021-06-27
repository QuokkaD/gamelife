package sys;

import java.util.ArrayList;

public class GameMap {
	private int mapcol;  //地图行数
	private int maprow; 
	private Cell[][] map;  //细胞地图
	
	private static String string;
	
	//初始化GameMap，将map元素实例化
	public GameMap(){ 
    	 maprow=10;
    	 mapcol=10;
    	 map=new Cell[maprow][mapcol];
    	 for(int i = 0; i < maprow; i++)
    	 {
    		 for(int j = 0; j < mapcol; j++)
    		 {
    			 map[i][j] = new Cell();
    		 }
    	 }
      }
	public GameMap(int r,int c) {
		maprow=r;
		mapcol=c;
		map=new Cell[maprow][mapcol];
		for(int i = 0; i < maprow; i++)
   	 	{
   		 	for(int j = 0; j < mapcol; j++)
   		 	{
   		 		map[i][j] = new Cell();
   		 	}
   	 	}
	}
	
	//返回地图行数
	public int getMapRow() {
		/*int  a=0;
		if(a < 1 && a < 2)
			System.out.println("error");
		
		string=null;
		if(string.equals("0")) {
			System.out.println(string);
		}*/
		
		
		
		return maprow;
	}
	//返回地图列数
	public int getMapCol() {
		return mapcol;
	}
	
	
	//在细胞地图中直接放置Cell对象
	public Cell place(int row,int col,Cell c) {
		Cell ret=map[row][col];
		map[row][col]=c;
		
		//return ret;
		return map[row][col];
	}
	
	//从地图中直接获得Cell对象
	public Cell get(int row,int col) {
		return map[row][col];
	}
	
	//设置地图指定Cell对象状态̬
	public void setStatus(int row,int col,boolean status) {
		if(status==true) {
			map[row][col].reborn();
		}
		else {
			map[row][col].die();
		}
		
	}
	
	
	
	//得到地图中指定Cell对象邻居ָ
	public Cell[] getNeighbor(int row,int col){
		//保存邻居数组
		ArrayList<Cell> list=new ArrayList<Cell>();
				
		//获得对象的所有邻居（无论生死）
		for(int i=-1;i<2;i++) {
			for(int j=-1;j<2;j++) {
				int r=row+i;
				int c=col+j;
				if(r>-1&&r<maprow&&c>-1&&c<mapcol&&!(r==row&&c==col)) {
					list.add(map[r][c]);
				}
			}
		}
		//返回数组
		return list.toArray(new Cell[list.size()]);	
		
	}
	
	//״对地图中所有细胞状态进行随机设定
	public void randomCellsStatus(int deathpercent)
	{
		for(int i=0;i<maprow;i++)
		{
			for(int j=0;j<mapcol;j++)
			{
				//控制细胞的死亡率
				double canalive = 0.01 * deathpercent;
				double randomNum=Math.random();
				
				if(randomNum> canalive)
					map[i][j].reborn();
				else
					map[i][j].die();	
				
			}
		}
	}
	
	//获得整个细胞地图所有细胞的生死状态
	public void Getmap(GameMap tmap)
	{
		for(int i=0;i<maprow;i++)
		{
			for(int j=0;j<mapcol;j++)
			{
				boolean status = tmap.map[i][j].isAlive();
				this.map[i][j].setAlive(status);
				
			}
		}
	}
	
//------------------------------------------------------test
	//获取地图中的细胞死亡率
	public int getDieRate() {
		int dieNum=0;
		for(int i=0;i<maprow;i++)
		{
			for(int j=0;j<mapcol;j++)
			{
				if(!map[i][j].isAlive()) {
					dieNum++;
				}
				
			}
		}
		
		return (int)(1.0*dieNum/(mapcol*maprow)*100);
	}
	
	
     //获取指定位置细胞状态
	public boolean getCellStatus(int row,int col) {
		return map[row][col].isAlive();
		
	}
	
	
	
//-----------------------
	
	//设置细胞地图中所有下一状态为不变
	public void setStatusDie() {
		for(int i=0;i<maprow;i++) {
			for(int j=0;j<mapcol;j++) {
				map[i][j].setChangeStatus(false);
			}
		}
	}
	
	
	
	
	
}
