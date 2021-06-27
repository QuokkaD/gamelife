package UIPackage;

import java.awt.Component;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.FrameFinder;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class OptionFrameTest extends JFCTestCase{

	private TestHelper helper = null;
	
	public OptionFrameTest(String name) {
		super(name);
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();

	    helper = new JFCTestHelper();
		this.setHelper(helper);
		
		OptionFrame.main(new String[] {});  ///神奇
	}

	@After
	public void tearDown() throws Exception {		
		super.tearDown();
	}

	@Test
	public void testOptionFrame() {
		
		//找optionframe
		FrameFinder framefinder=new FrameFinder("option");
		List frames=framefinder.findAll();
		assertEquals("frames size wrong",1,frames.size());
		
		JFrame frame=(JFrame)frames.get(0);
		assertNotNull("frame is null!",frame);
		
        //找重置键控件
		NamedComponentFinder finder = new NamedComponentFinder(Component.class,"");
		finder.setName("JButton_reset");
		JButton btn=(JButton)finder.find(frame,0);
		assertNotNull("Could not find the reset button", btn
				);
		helper.enterClickAndLeave(new MouseEventData(this,btn,1,false));		
				
		//找确认键控件
		finder.setName("JButton_confirm");
		btn=(JButton)finder.find(frame,0);
		assertNotNull("Could not find the Confirm button", btn
				);
		helper.enterClickAndLeave(new MouseEventData(this,btn,1,false));

		//this.pause();
		FrameFinder frameFinder = new FrameFinder("option");
	    frames =frameFinder.findAll();
		assertEquals("frames size wrong!",0,frames.size());
		
		this.flushAWT();	
		this.pause();

	}

}
