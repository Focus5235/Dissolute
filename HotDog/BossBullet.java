package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BossBullet {
	//�ӵ�����
	int bossbulletX = 0;
	int bossbulletY = 0;
	
	//�ӵ�ͼƬ����
	Image[] Img = new Image[4];
	
	//�ӵ�ͼƬid
	int bossbulletID = 0;
	
	//�ӵ�״̬��Ϊ�棬��ʾ�ӵ������״̬���ٱ�ʾ�Ѿ����ел����߷ɳ�����
	boolean bossbulletState = false;
	
	//�ӵ����캯���������ӵ�ͼƬ
	public BossBullet() {
		for(int i = 0; i < Img.length; i++) {
			Img[i] = Toolkit.getDefaultToolkit().getImage("HOTDOGJPG\\bullet.png");
		}
	}
	
	//�ӵ������ʼ��
	public void init(int x, int y) {
		bossbulletX = x;
		bossbulletY = y;
		bossbulletState = true;
		bossbulletID = 0;
	}
	
	//�����ӵ�
	public void DrawBullet(Graphics g, JPanel i) {
		if(bossbulletState == true) {//�ӵ����ŵ�ʱ���Ҫ��ͼ
			g.drawImage(Img[bossbulletID], bossbulletX, bossbulletY, i);
			bossbulletID++;//�ӵ���ţ����ӵ����ĸ���������
			if(bossbulletID == 4) {
				bossbulletID = 0;
			}
		}
	}
	
	//�����ӵ�
	public void updateBullet() {
		if(bossbulletState) {//ֻ���ӵ����״̬����Ҫ��������
			bossbulletY += 15;
			if(bossbulletY > 480) {//�ӵ�����
				bossbulletState = false;
			}
		}
	}
}
