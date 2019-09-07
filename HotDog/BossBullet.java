package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BossBullet {
	//子弹坐标
	int bossbulletX = 0;
	int bossbulletY = 0;
	
	//子弹图片数组
	Image[] Img = new Image[4];
	
	//子弹图片id
	int bossbulletID = 0;
	
	//子弹状态，为真，表示子弹再设计状态，假表示已经击中敌机或者飞出界外
	boolean bossbulletState = false;
	
	//子弹构造函数，读入子弹图片
	public BossBullet() {
		for(int i = 0; i < Img.length; i++) {
			Img[i] = Toolkit.getDefaultToolkit().getImage("HOTDOGJPG\\bullet.png");
		}
	}
	
	//子弹坐标初始化
	public void init(int x, int y) {
		bossbulletX = x;
		bossbulletY = y;
		bossbulletState = true;
		bossbulletID = 0;
	}
	
	//绘制子弹
	public void DrawBullet(Graphics g, JPanel i) {
		if(bossbulletState == true) {//子弹活着的时候才要绘图
			g.drawImage(Img[bossbulletID], bossbulletX, bossbulletY, i);
			bossbulletID++;//子弹编号，让子弹画的更生动形象
			if(bossbulletID == 4) {
				bossbulletID = 0;
			}
		}
	}
	
	//更新子弹
	public void updateBullet() {
		if(bossbulletState) {//只有子弹射击状态才需要更新坐标
			bossbulletY += 15;
			if(bossbulletY > 480) {//子弹出界
				bossbulletState = false;
			}
		}
	}
}
