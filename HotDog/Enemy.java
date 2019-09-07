package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Enemy {
	//�ȹ�����
	int EhdX = new Random().nextInt(270);
	int EhdY = -new Random().nextInt(50)-50;
	
	//�ȹ�״̬���ٱ�ʾ�����ڣ����Ե����߷ɳ����⣬���ʾ����
	boolean EhdState = true;
	
	Image Ehd = null;//�����ȹ�ͼƬ����
	
	int hdID = 0;//�����ȹ�ͼƬid
	//���췽��
	public Enemy() {
		try {
			Ehd = ImageIO.read(new File("HOTDOGJPG\\hotdog.png"));//�����ȹ�ͼƬ
			//����л���ըͼƬ
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();//�ȹ���ʼ��
	}
	
	//��ʼ������
	public void init() {
		EhdX = new Random().nextInt(270);//�ȹ������������
		EhdY = -new Random().nextInt(50)-50;
		EhdState = true;//�ȹ�����
	}
	
	//�ȹ�����
	public void DrawHotDog(Graphics g, JPanel i) {
		if(EhdState == true) {//�ȹ����ڣ��б��...��ʾ�ȹ�ͼƬ
			g.drawImage(Ehd, EhdX, EhdY, i);//�����ȹ�ͼƬ
		}else
			init();
	}
	
	//�����ȹ���Ϣ
	public void updateEnemy() {
		if(EhdState == true ) {//�ȹ�����
			EhdY += 5;//�ȹ����·�
			if(EhdY > 450 || EhdState == false) {//�ȹ��ɳ�����
				init();//�����µ��ȹ�
			}
		}
	}
	
	//��Ϸ����balabala
}
