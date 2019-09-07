package HotDog;

import javax.swing.JFrame;


public class Frame extends JFrame {
	public Frame() {
		super("王思聪吃热狗");//设置窗体标题
		Hotdog hotdog = new Hotdog();
		add(hotdog);
		setSize(320, 480);//设置窗体大小
		setLocation(400, 150);//设置窗体显示初始位置
		setVisible(true);//设置窗体可见
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体默认退出方式
		
	}
	
	public static void main(String[] args) {
		new Frame();
	}

}
