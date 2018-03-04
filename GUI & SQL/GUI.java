import java.util.HashMap;
//import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI {
	private static JTextField sumText;
	private static Redistribute z;
	private static JTextField [] text;
	
	public static void main(String[] args) {
		JFrame f = new JFrame("原料分配");
		
		//set icon 
		/*
		try {
			f.setIconImage(ImageIO.read(new File("res/icon.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		HashMap<String,Double> map = new HashMap<String, Double>();
		
		map.put("#4", 293.16);
		map.put("439", 78.91);
		map.put("466", 206.24);
		map.put("492", 10.9);
		map.put("425", 41.31);
		map.put("#4327", 5.52);
		
		z = new Redistribute(map);
		
		f.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel [] lb = new JLabel[map.size()];
		text = new JTextField[map.size()];
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,0,10);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		int c = 0;
		
		for(String key : map.keySet()) {
			gbc.gridy = c;
			lb[c] = new JLabel(key);
			lb[c].setFont(new Font("Comic Sans MS",Font.PLAIN,20));
			f.add(lb[c],gbc);
			c++;
		}
		
		c = 0;
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		
		
		
		for(String key : map.keySet()) {
			gbc.gridy = c;
			text[c] = new JTextField(String.format("%.2f",map.get(key)));
			text[c].setFont(new Font("Comic Sans MS",Font.PLAIN,20));
			text[c].setPreferredSize(new Dimension(130,30));
			text[c].setEditable(false);
			f.add(text[c],gbc);
			c++;
		}
		
		sumText = new JTextField(String.format("%.2f", z.getSum()));
		sumText.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
		sumText.setPreferredSize(new Dimension(130,30));
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = map.size();
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.NONE;
		f.add(sumText, gbc);
		
		JPanel p = new JPanel();
		
		JButton cal = new JButton("調整原料比例");
		JButton send = new JButton("確認送出");
		
		cal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double a = Double.parseDouble(sumText.getText());
					z.setTotal(a);
					z.print();
					
					int i = 0;
					for(String key : z.getMap().keySet()) {
						//text[i].setText(String.format("%f", z.getMap().get(key)));
						text[i].setText(z.getMap().get(key).toString());
						i++;
					}
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(f, "你輸入的不是數字","錯誤",JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("請輸入新品號");
				if(name !=null && !name.isEmpty()) {
					
					int result = JOptionPane.showConfirmDialog(f,"確認送出資料?","提醒視窗",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
					if(result==JOptionPane.YES_OPTION) {
						Conn c = new Conn(name,Double.parseDouble(sumText.getText()),z.getMap());
						c.print();
						//c.print();
						f.dispose();
					}
				}
			}
		});
		
		p.setLayout(new FlowLayout());
		p.add(cal);
		//p.setBackground(Color.CYAN);
		p.add(send);
		gbc.gridx = 0;
		gbc.gridy = map.size() + 1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20,0,0,0);
		f.add(p, gbc);
		
		f.setSize(500,500);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		Thread [] a = new Thread[3];
		
		for(Thread i : a) {
			i = new Thread(new Runnable() {
				@Override
				public void run() {
					int s = new Random().nextInt(5) + 1;
					while(true) {
						System.out.println("我每" + s + "秒會輸出一次");
						
						try {
							Thread.sleep(s*1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				;
			});
			
			i.start();
		}
		*/
	}
}
