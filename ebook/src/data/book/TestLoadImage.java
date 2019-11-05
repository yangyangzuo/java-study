package data.book;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TestLoadImage extends JFrame {
	public static String content = "";
	    // 用户名
	    private JTextField code;
	    // 小容器
	    private JLabel picture;
	    // 小按钮
	    private JButton refresh;
	    private JButton confirm;
	    //
	    private JFrame f;
	    private Thread t;
	    public void shows(String url,JFrame f,Thread t) {
	        init(url);
	        this.f = f;
	        this.t = t;
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        // 设置布局方式为绝对定位
	        this.setLayout(null);
	        this.setBounds(0, 0, 355, 265);
	        // 窗体大小不能改变
	        this.setResizable(false);
	        // 居中显示
	        this.setLocationRelativeTo(null);
	        // 窗体可见
	        this.setVisible(true);
	    }
	 
	    /*
	     * 初始化方法
	     */
	    public void init(String url) {
		    System.out.println(url);
	        // 创建一个容器
	        Container con = this.getContentPane();
	        picture = new JLabel();
	        // 设置背景图片
	        Image image1 = new ImageIcon(url).getImage();
	        picture.setIcon(new ImageIcon(image1));
	        picture.setBounds(0, 0, 355, 265);
	        // 用户号码登录输入框
	        code = new JTextField();
	        code.setBounds(150, 100, 200, 70);
	 
	 
	 
	        // 按钮设定
	        refresh = new JButton("刷新");
	        refresh.setBounds(200, 200, 65, 20);
	        // 给按钮添加1个事件
	        refresh.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String str=e.getActionCommand();
	                if("刷新".equals(str)){
	                	//刷新验证码
	                }
	            }
	        });
	        confirm = new JButton("确定");
	        confirm.setBounds(280, 200, 65, 20);
	        confirm.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String str=e.getActionCommand();
	                if("确定".equals(str)){
	                    content = code.getText();
	                    t.interrupt();
	                    f.dispose();
	                }
	                 
	            }
	        }); 
	        // 所有组件用容器装载
	        picture.add(refresh);
	        picture.add(confirm);
	        con.add(picture);
	        con.add(code);
	    }
	    public static void main(String[] args) {
	        // 实例化对象
		    TestLoadImage qq = new TestLoadImage();
//		   qq.shows("/home/zuoyang/Desktop/processVerifyPng.png",qq);
	    }
	 
	}