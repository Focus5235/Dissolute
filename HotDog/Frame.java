package HotDog;

import javax.swing.JFrame;


public class Frame extends JFrame {
	public Frame() {
		super("��˼�ϳ��ȹ�");//���ô������
		Hotdog hotdog = new Hotdog();
		add(hotdog);
		setSize(320, 480);//���ô����С
		setLocation(400, 150);//���ô�����ʾ��ʼλ��
		setVisible(true);//���ô���ɼ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���Ĭ���˳���ʽ
		
	}
	
	public static void main(String[] args) {
		new Frame();
	}

}
