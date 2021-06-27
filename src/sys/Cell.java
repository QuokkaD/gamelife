package sys;

import java.awt.Graphics;
public class Cell {
     private boolean alive;//是否存活
     private boolean ChangeStatus;//更改状态
     public Cell()
     {
    	 alive = false;
    	 ChangeStatus = false;
     }
     public Cell(boolean alive, boolean ChangeStatus)
     {
    	 this.alive = alive;
    	 this.ChangeStatus = ChangeStatus;
     }
     
     //死亡
     public void die() {
    	 ChangeStatus = true;
    	 alive=false;
     }
     //重生
     public void reborn() {
    	 ChangeStatus = true;
    	 alive=true;
     }
     //判断
     public boolean isAlive() {
    	 return alive;
     }
     
     public void setAlive(boolean status)
     {
    	 this.alive = status;
     }
     //修改细胞状态
     public void setChangeStatus(boolean ChangeStatus)
     {
    	 this.ChangeStatus = ChangeStatus;
     }
     public boolean isChangeStatus()
     {
    	 return this.ChangeStatus;
     }
     
     
     //绘制黑色小方格
     //传入参数需要有图形、位置、小方形的大小
     public void draw(Graphics g,int x,int y,int size) {
    	// g.drawRect(x, y, size, size);
    	 if(alive) {
    		g.fillRect(x, y, size, size);
    	
    	 } 
     }
     
}
