package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Bullet {
	//�ӵ�����
	int bulletX = 0;
	int bulletY = 0;
	
	//�ӵ�ͼƬ����
	Image[] Img = new Image[4];
	
	//�ӵ�ͼƬid
	int bulletID = 0;
	
	//�ӵ�״̬��Ϊ�棬��ʾ�ӵ������״̬���ٱ�ʾ�Ѿ����ел����߷ɳ�����
	boolean bulletState = false;
	
	//�ӵ����캯���������ӵ�ͼƬ
	public Bullet() {
		for(int i = 0; i < Img.length; i++) {
			Img[i] = Toolkit.getDefaultToolkit().getImage("HOTDOGJPG\\hotdog.png");
		}
	}
	
	//�ӵ������ʼ��
	public void init(int x, int y) {
		bulletX = x;
		bulletY = y;
		bulletState = true;
		bulletID = 0;
	}
	
	//�����ӵ�
	public void DrawBullet(Graphics g, JPanel i) {
		if(bulletState == true) {//�ӵ����ŵ�ʱ���Ҫ��ͼ
			g.drawImage(Img[bulletID], bulletX, bulletY, i);
			bulletID++;//�ӵ���ţ����ӵ����ĸ���������
			if(bulletID == 4) {
				bulletID = 0;
			}
		}
	}
	
	//�����ӵ�
	public void updateBullet() {
		if(bulletState == true) {//ֻ���ӵ����״̬����Ҫ��������
			bulletY -= 15;
			if(bulletY < -30) {//�ӵ�����
				bulletState = false;
			}
		}
	}
}
