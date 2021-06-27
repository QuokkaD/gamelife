package UIPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sys.Cell;
import sys.GameMap;
import sys.GameProcessing;

/**
 * 
 * @author 86173
 *
 */


public class MainFrame extends JFrame {
	private static final int GRID_SIZE=16;//鏍煎瓙杈归暱澶у皬
	private static final int dis = 100;//鍦板浘缁樼敾璧风偣浣嶇疆
	//private JPanel contentPane;
	private boolean change = true;
    private boolean isonestep = false;
	private GameMap map;
	private OptionFrame of=new OptionFrame();
	private GameProcessing gamepro = new GameProcessing();
	
	
	private int formrow=10;
	private int formcol=10;
	
	//瀹氫箟JPanel鍐呴儴绫�,鐢ㄤ簬瀹屾垚鏄剧ず
	public class View extends JPanel{
		
		//缁樺埗
		public void paint(Graphics g) {
			super.paint(g);
			for(int r=0;r<map.getMapRow();r++) {
				for(int c=0;c<map.getMapCol();c++) {
					Cell cell=map.get(r, c);     //鑾峰緱璇诲彇鍦板浘鍐呭锛屽～鍏呮樉绀虹粏鑳�
					if(cell!=null && /*cell.isChangeStatus()*/cell.isAlive()) {
						cell.draw(g, r*GRID_SIZE + dis, c*GRID_SIZE + dis, GRID_SIZE);
					}
				}
			}		
		}	
        //鐢荤嚎
		public void paintGirdLines(Graphics g)
		{
			g.setColor(new Color(0,0,0));
			//鐢荤綉鏍� 妯悜
			for(int i=0;i<=map.getMapCol();i++)
			{
    			g.drawLine(dis, i*GRID_SIZE+ dis, map.getMapRow()*GRID_SIZE + dis,i*GRID_SIZE + dis);
			}
			//绾靛悜
			for(int i=0;i<=map.getMapRow();i++)
			{
    			g.drawLine(i*GRID_SIZE+ dis,dis,i*GRID_SIZE+ dis,map.getMapCol()*GRID_SIZE+ dis);
			}
		}
	}
	
	
    //婕斿寲绾跨▼绫�
	class ProcessThread extends Thread{
		
		public void run()
		{
			while(change) {
				//娓呯┖涓婁竴娆℃紨鍖栫暀涓嬬殑鐣岄潰
				//clearPaint(getGraphics(),40,40);
				clearPaint(getGraphics(),30,30);
				//clearPaint(getGraphics(),formrow,formcol);
				//婕斿寲
				evolve();
				try {
					int count  = of.getRatetextField();  //璁剧疆婕斿寲閫熷害
					Thread.sleep(count * 50);
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	private View view;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame mainframe = new MainFrame();
					
					mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainframe.setResizable(false);
					
					//mainframe.pack();
					mainframe.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("GameofLife");   //璁剧疆title锛堝浜巘est妫�娴嬪緢閲嶈锛�
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem JMenuItem_clear = new JMenuItem("          娓呯┖鐢婚潰");
		JMenuItem_clear.setHorizontalAlignment(SwingConstants.CENTER);
		JMenuItem_clear.setName("JMenuItem_clear");
		JMenuItem_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPaint(getGraphics(),map.getMapRow(),map.getMapCol());
			}
		});
		menuBar.add(JMenuItem_clear);
		
		JMenuItem JMenuItem_random = new JMenuItem("闅忔満鐢熸垚鏂扮敾闈�");
		JMenuItem_random.setName("JMenuItem_random");
		JMenuItem_random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int r = of.getRowtextField();
				int c = of.getColtextField();
				map = new GameMap(r,c);
				//clearPaint(getGraphics(),40,40);
				//clearPaint(getGraphics(),r,c);
				clearPaint(getGraphics(),30,30);
				
				//clearPaint(getGraphics(),formrow,formcol);
				
				int d = of.getDeathtextField();
				map.randomCellsStatus(d);
				view=new View();	
				//mainframe.add(view); 
				
				view.paintGirdLines(getGraphics());
				
				view.paint(getGraphics());
				getContentPane().add(view);
				getContentPane().setName("ContentPane");
				saverc(r,c);
				
				map.setStatusDie();
			}
		});
		menuBar.add(JMenuItem_random);
		
		JMenuItem JMenuItem_onestep = new JMenuItem("杩涜鍗曟婕斿寲");
		JMenuItem_onestep.setName("JMenuItem_onestep");
		JMenuItem_onestep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isonestep = true;
				//clearPaint(getGraphics(),40,40);
				//clearPaint(getGraphics(),formrow,formcol);
				clearPaint(getGraphics(),30,30);
				
				//clearPaint(getGraphics(),of.getRowtextField(),of.getColtextField());
				
				evolve();
			}
		});
		menuBar.add(JMenuItem_onestep);
		
		
		JMenuItem JMenuItem_stop = new JMenuItem("缁撴潫涓嶉棿鏂紨鍖�");
		JMenuItem_stop.setName("JMenuItem_stop");
		JMenuItem_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				//璁惧畾鏍囪瘑绗︼紝浣挎紨鍖栫粨鏉�
				change = false;
			}
		});
		menuBar.add(JMenuItem_stop);
		
		JMenuItem JMenuItem_start = new JMenuItem("寮�濮嬩笉闂存柇婕斿寲   ");
		JMenuItem_start.setName("JMenuItem_start");
		JMenuItem_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				isonestep =false;
				change = true;
				//寮�鍚紨鍖栫粏鑳炵嚎绋�
				ProcessThread pth = new ProcessThread();
				pth.start();
			
				
			}
		});
		menuBar.add(JMenuItem_start);					
		
		JMenuItem JMenuItem_option = new JMenuItem("           閫夐」");
		JMenuItem_option.setName("JMenuItem_option");
		JMenuItem_option.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				//浣縊ptionFrame鍙
				of.setVisible(true);
			}
		});
		
		menuBar.add(JMenuItem_option);
		
		
		JMenuItem JMenuItem_view = new JMenuItem("绋�鏈夋帀钀�");
		JMenuItem_view.setName("JMenuItem_view");
		JMenuItem_view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				JOptionPane.showMessageDialog(null, "娉ㄦ剰锛氭鏃舵偍涓嶈兘璁剧疆琛屾暟鍒楁暟涓庢浜＄巼锛�", "绋�鏈夋帀钀�", JOptionPane.INFORMATION_MESSAGE);                     
				//璁惧畾鐗瑰畾鐨勮鍒楀苟鐢熸垚鍦板浘
				of.setRowtextField(17);
				of.setColtextField(17);
				int r = of.getRowtextField();
				int co = of.getColtextField();
				map=new GameMap(r,co);
				
				//璁剧疆寰幆鐢熸垚鍦板浘
			   for(int i=0;i<2;i++) {
				   for(int j=0;j<2;j++) {
					   map.setStatus(2+j*5+i*7, 4, true);
					   map.setStatus(2+j*5+i*7, 5, true);
					   map.setStatus(2+j*5+i*7, 6, true);
					   map.setStatus(2+j*5+i*7, 10, true);
					   map.setStatus(2+j*5+i*7, 11, true);
					   map.setStatus(2+j*5+i*7, 12, true);
				   }				   
				   
				   for(int k=0;k<3;k++) {
					   map.setStatus(4+k+i*6, 2, true);
					   map.setStatus(4+k+i*6, 7, true);
					   map.setStatus(4+k+i*6, 9, true);
					   map.setStatus(4+k+i*6, 14, true);
				   };
			   }
			   
			   //娓呯┖鍦板浘锛岄伩鍏嶅厛鍓嶆搷浣滅敓鎴愬湴鍥剧殑褰卞搷
			 //  clearPaint(getGraphics(),40,40);				
			 //  clearPaint(getGraphics(),formrow,formcol);
			   
			   //clearPaint(getGraphics(),of.getRowtextField(),of.getColtextField());
			   
			   clearPaint(getGraphics(),30,30);
				view=new View();	
				view.paintGirdLines(getGraphics());
				view.paint(getGraphics());
				getContentPane().add(view);			   
			   			   
		}
		});
		menuBar.add(JMenuItem_view);
		
	}
	
	
	//鍦板浘婕斿寲
	public void evolve() {		
		int r = of.getRowtextField();
		int co = of.getColtextField();
		gamepro.NextStatus(r, co, map);
		
		this.view.paintGirdLines(getGraphics());
		
		this.view.paint(getGraphics());
		if(isonestep) {
			change = false;
		}			
		
	}
	
	//娓呯┖鍦板浘
	public void clearPaint(Graphics g,int row,int col)
	{
		g.setColor(new Color(238,238,238));
		for(int i=0;i<=row;i++)
		{
			for(int j=0;j<=col;j++)
			{
			
					g.fillRect(j*GRID_SIZE+dis, i*GRID_SIZE+dis,GRID_SIZE,GRID_SIZE);
				
			}
		}
		
	}
	
	//淇濆瓨涔嬪墠鐨凴ow鍜孋ol鍊�
	public void saverc(int r, int c)
	{
		formrow = r;
		formcol = c;
	}
}






