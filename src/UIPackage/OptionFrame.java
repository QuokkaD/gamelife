package UIPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField RowtextField;
	private JTextField ColtextField;
	private JTextField DeathtextField;
	private JTextField RatetextField;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionFrame frame = new OptionFrame();
					frame.setName("option");
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OptionFrame() {
		setTitle("option");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(0, 0, 776, 109);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JSlider Rowslider = new JSlider();
		Rowslider.setPaintLabels(true);
		
		Rowslider.setMinorTickSpacing(1);
		Rowslider.setMajorTickSpacing(5);
		Rowslider.setValue(10);
		Rowslider.setPaintTicks(true);
		Rowslider.setMaximum(30);
		Rowslider.setBounds(10, 66, 725, 33);
		panel_1.add(Rowslider);
		
		JLabel JLabelRow = new JLabel("    列数");
		JLabelRow.setFont(new Font("宋体", Font.PLAIN, 15));
		JLabelRow.setBounds(10, 23, 85, 39);
		panel_1.add(JLabelRow);
		
		RowtextField = new JTextField();
		RowtextField.setBounds(87, 26, 99, 33);
		panel_1.add(RowtextField);
		RowtextField.setColumns(10);
		RowtextField.setText(10 + "");
		Rowslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int v = Rowslider.getValue();
				//System.out.println(String.valueOf(v));
				RowtextField.setText(String.valueOf(Rowslider.getValue()));
			}
		});
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_1.setBounds(0, 106, 776, 109);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JSlider Colslider = new JSlider();
		
		Colslider.setPaintLabels(true);
		Colslider.setMinorTickSpacing(1);
		Colslider.setMajorTickSpacing(5);
		Colslider.setValue(10);
		Colslider.setPaintTicks(true);
		Colslider.setMaximum(30);
		Colslider.setBounds(10, 66, 725, 33);
		panel_1_1.add(Colslider);
		
		ColtextField = new JTextField();
		ColtextField.setColumns(10);
		ColtextField.setBounds(87, 23, 99, 33);
		ColtextField.setText(10 + "");
		panel_1_1.add(ColtextField);
		Colslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ColtextField.setText(Colslider.getValue() + "");
			}
		});
		
		JLabel JLabelCol = new JLabel("    行数");
		JLabelCol.setFont(new Font("宋体", Font.PLAIN, 15));
		JLabelCol.setBounds(10, 23, 85, 39);
		panel_1_1.add(JLabelCol);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_2.setBounds(0, 216, 776, 109);
		panel.add(panel_1_2);
		panel_1_2.setLayout(null);
		
		JSlider Deathslider = new JSlider();
		Deathslider.setPaintLabels(true);
		Deathslider.setMinorTickSpacing(2);
		Deathslider.setMajorTickSpacing(10);
		
		Deathslider.setValue(60);
		Deathslider.setPaintTicks(true);
		Deathslider.setBounds(10, 66, 725, 33);
		panel_1_2.add(Deathslider);
		
		DeathtextField = new JTextField();
		DeathtextField.setColumns(10);
		DeathtextField.setBounds(87, 23, 99, 33);
		panel_1_2.add(DeathtextField);
		DeathtextField.setText(50 + "");
		Deathslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				DeathtextField.setText(Deathslider.getValue() + "");
			}
		});
		
		JLabel JLabelDeath = new JLabel("   死亡率");
		JLabelDeath.setFont(new Font("宋体", Font.PLAIN, 15));
		JLabelDeath.setBounds(10, 23, 85, 39);
		panel_1_2.add(JLabelDeath);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1_3.setBounds(0, 324, 776, 109);
		panel.add(panel_1_3);
		panel_1_3.setLayout(null);
		
		JSlider Rateslider = new JSlider();
		
		Rateslider.setPaintLabels(true);
		Rateslider.setMinorTickSpacing(2);
		Rateslider.setMajorTickSpacing(10);
		Rateslider.setValue(60);
		Rateslider.setPaintTicks(true);
		Rateslider.setMinimum(1);
		Rateslider.setBounds(10, 66, 725, 33);
		panel_1_3.add(Rateslider);
		
		RatetextField = new JTextField();
		RatetextField.setColumns(10);
		RatetextField.setBounds(87, 23, 99, 33);
		RatetextField.setText(60 + "");
		panel_1_3.add(RatetextField);
		Rateslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				RatetextField.setText(Rateslider.getValue() + "");
			}
		});
		
		JLabel JLabelRate = new JLabel(" 休眠时间");
		JLabelRate.setFont(new Font("宋体", Font.PLAIN, 15));
		JLabelRate.setBounds(10, 23, 85, 39);
		panel_1_3.add(JLabelRate);
		
		JButton JButton_confirm = new JButton("确认    ");
		JButton_confirm.setName("JButton_confirm");
		JButton_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JButton_confirm.setHorizontalAlignment(SwingConstants.RIGHT);
		JButton_confirm.setFont(new Font("宋体", Font.PLAIN, 14));
		JButton_confirm.setBounds(190, 484, 118, 46);
		panel.add(JButton_confirm);
		
		JButton JButton_reset = new JButton("恢复默认");
		JButton_reset.setName("JButton_reset");
		JButton_reset.setFont(new Font("宋体", Font.PLAIN, 14));
		JButton_reset.setBounds(504, 484, 118, 46);
		panel.add(JButton_reset);
	}
	
	public int getRowtextField()
	{
		if(RowtextField.getText() != null)
		return Integer.parseInt(RowtextField.getText());
		else
			return 10;
	}
	public int getColtextField()
	{
		if(ColtextField.getText() != null)
		return Integer.parseInt(ColtextField.getText());
		else return 10;
	}
	public void setRowtextField(int row)
	{
		RowtextField.setText(row+"");
	}
	public void setColtextField(int col)
	{
		ColtextField.setText(col+"");
	}
	
	
	public int getDeathtextField()
	{
		if(DeathtextField.getText() != null)
		return Integer.parseInt(DeathtextField.getText());
		else return 50;
	}
	public int getRatetextField()
	{
		if(RatetextField.getText() != null)
		return Integer.parseInt(RatetextField.getText());
		else
			return 60;
	}
	
	
	
}
