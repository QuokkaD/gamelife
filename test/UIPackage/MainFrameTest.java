package UIPackage;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.FrameFinder;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase { 
    private TestHelper helper = null;
    
    private Container ContentPane;
    public MainFrameTest(String name) {
		super(name);
		
    }
    
	@Before
	public void setUp() throws Exception {
		super.setUp();
		helper = new JFCTestHelper();
		this.setHelper(helper);
		
		MainFrame.main(new String[] {});
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test(timeout=1000)
	public void testMainFrame() throws AWTException, IOException {
		//设置frame的finder进行查找
		FrameFinder frameFinder = new FrameFinder("GameofLife");
		//查找所有frame窗体
		List frames =frameFinder.findAll();
		assertEquals("frames size wrong!",1,frames.size());
		//获取list中第一个窗体判断
		JFrame frame = (JFrame)frames.get(0);
		assertNotNull("frame not found!",frame);
		
		//设置component的finder进行查找
		 NamedComponentFinder finder = new NamedComponentFinder(
		 JComponent.class, "");
		 finder.setName("JMenuItem_clear");
		 JMenuItem JMenuItemclear = (JMenuItem)finder.find(frame,0);
		 assertNotNull("Could not find the clear meunItem!", JMenuItemclear);
		 
		 //判断控件是否存在
		 finder.setName("JMenuItem_random");
		 JMenuItem JMenuItemrandom = (JMenuItem)finder.find(frame,0);
		 assertNotNull("Could not find the random menuItem!", JMenuItemrandom);
		 
		 finder.setName("JMenuItem_onestep");
		 JMenuItem JMenuItemonestep = (JMenuItem)finder.find(frame,0);
		 assertNotNull("Could not find the OneStep menuItem!", JMenuItemonestep);
		 
		 finder.setName("JMenuItem_stop");
		 JMenuItem JMenuItemstop = (JMenuItem)finder.find(frame,0);
		 assertNotNull("Could not find the stop menuItem!", JMenuItemstop);
		 
		 finder.setName("JMenuItem_start");
		 JMenuItem JMenuItemstart = (JMenuItem)finder.find(frame,0);
		 assertNotNull("Could not find the start menuItem!", JMenuItemstart);	 
		 
		 finder.setName("JMenuItem_option");
		 JMenuItem JMenuItemoption = (JMenuItem)finder.find(frame,0);
		 assertNotNull("Could not find the option menuItem!", JMenuItemoption);
		 
		 finder.setName("JMenuItem_view");
		 JMenuItem JMenuItemview = (JMenuItem)finder.find(frame,0);
		 assertNotNull("Could not find the view menuItem!", JMenuItemview);
		 
		 //判断界面是否被清除
		 helper.enterClickAndLeave(new MouseEventData(this,JMenuItemclear,1,false));
		 assertNull("ContentPane is null!",ContentPane);
		 
		 //测试clear功能是否正确
		 //点击后测试反馈是否正确
		 //查看整张地图，通过对每一个细胞的RGB返回值来判断是否为空状态
		 for(int x = 0; x < 10 * 16; x+= 16) {
			 for(int y = 0; y < 10*16;y+=16) {
				 try {
					 Color color =GetRGB(x,y);			
					 assertEquals(238,GetRGB(x,y).getRed());
					 assertEquals(238,GetRGB(x,y).getGreen());
					 assertEquals(238,GetRGB(x,y).getBlue());
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
				 
			 }
		 }
	
		 //测试随机random点击反应
    	 helper.enterClickAndLeave(new MouseEventData(this,JMenuItemrandom,1,false));		 		 
		 
		
    	 //先点击稀有掉落，通过对稀有掉落演化的过程判断是否演化正确
		 helper.enterClickAndLeave(new MouseEventData(this,JMenuItemview,1,false));
		  this.flushAWT();	
		  this.pause();
		//测试单步onestep点击反馈是否正确
		 helper.enterClickAndLeave(new MouseEventData(this,JMenuItemonestep,1,false));
		 
		//选取脉冲星周期第二步的状态进行检测；由于图形具有较高的对称性，因此只选取其中的一部分（即中心对称、轴对称的1/8图形）进行检测
		//共七个方格作为典型方格
		 //try {
			 assertEquals(0,GetRGB(88,20).getRed());
			 assertEquals(0,GetRGB(88,40).getRed());
			 assertEquals(0,GetRGB(88,60).getRed());
			 assertEquals(0,GetRGB(100,85).getRed());
			 assertEquals(0,GetRGB(120,85).getRed());
			 assertEquals(0,GetRGB(120,100).getRed());
			 assertEquals(0,GetRGB(100,55).getRed());
		 //}catch(Exception e) {
		//	 e.printStackTrace();
		// }	
		 
		 //测试选项option
		 helper.enterClickAndLeave(new MouseEventData(this,JMenuItemoption,1,false));
		 frameFinder = new FrameFinder("option");
		 frames =frameFinder.findAll();
		 //frames.add(frameFinder.findAll());
		 this.pause();
		 assertEquals("frames size wrong!",1,frames.size());
	     frame = (JFrame)frames.get(0);
		 assertNotNull("frame not found!",frame);
		 
		 
	}
	
	//获取指定坐标的RGB值
	public Color GetRGB(int x, int y) throws AWTException,IOException {
		Robot rb = new Robot();   //获取的截图是整个页面的截图
		
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();  //获取分辨率
		//System.out.println(di.width);
		//System.out.println(di.height);
		
		Rectangle rec = new Rectangle(200,200,17*16,17*16);
		//Rectangle rec = new Rectangle(134,126,di.width,di.height);
		BufferedImage bi = rb.createScreenCapture(rec);
		
		//测试
		//File file=new File("test.png");
		//ImageIO.write(bi, "png", file);
		
		int pixelColor = bi.getRGB(x, y);
		Color color = new Color(16777216 + pixelColor);
		return color;
	}

	/*@Test
	public void testEvolve() {
		fail("Not yet implemented");
	}*/


	
	
}
