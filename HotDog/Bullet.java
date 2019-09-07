package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Bullet {
	//子弹坐标
	int bulletX = 0;
	int bulletY = 0;
	
	//子弹图片数组
	Image[] Img = new Image[4];
	
	//子弹图片id
	int bulletID = 0;
	
	//子弹状态，为真，表示子弹再设计状态，假表示已经击中敌机或者飞出界外
	boolean bulletState = false;
	
	//子弹构造函数，读入子弹图片
	public Bullet() {
		for(int i = 0; i < Img.length; i++) {
			Img[i] = Toolkit.getDefaultToolkit().getImage("HOTDOGJPG\\hotdog.png");
		}
	}
	
	//子弹坐标初始化
	public void init(int x, int y) {
		bulletX = x;
		bulletY = y;
		bulletState = true;
		bulletID = 0;
	}
	
	//绘制子弹
	public void DrawBullet(Graphics g, JPanel i) {
		if(bulletState == true) {//子弹活着的时候才要绘图
			g.drawImage(Img[bulletID], bulletX, bulletY, i);
			bulletID++;//子弹编号，让子弹画的更生动形象
			if(bulletID == 4) {
				bulletID = 0;
			}
		}
	}
	
	//更新子弹
	public void updateBullet() {
		if(bulletState == true) {//只有子弹射击状态才需要更新坐标
			bulletY -= 15;
			if(bulletY < -30) {//子弹出界
				bulletState = false;
			}
		}
	}
}
