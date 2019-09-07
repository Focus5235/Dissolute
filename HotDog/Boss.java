package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class Boss {
	//坐标
	int bossX = 0;
	int bossY = -200; 
	//状态
	boolean bossState = true;
	//子弹
	boolean bossBulletState = true;
	int bulletID = 0;
	Image[] bossbullet = new Image[4];
//	int bossBulletX = 0;
//	int bossBulletY = 0;
	
	Image bossImg = null;//图片对象
	Image[] bossbombImg = new Image[6];//爆炸图片对象
	int Dienum = 0;
	int bombID = 0; //爆炸图片编号
	
	//boss 来回参数
	int h = 0;
	
	public Boss() {
		try {
			bossImg = ImageIO.read(new File("HOTDOGJPG\\enemy2.png"));
			for(int i = 0; i < bossbombImg.length; i++) {
				bossbombImg[i] = ImageIO.read(new File("HOTDOGJPG\\enemy2_down" + i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
	}
	
	//初始化方法
	public void init() {
		bossX = 0;
		bossY = -200;
		bossState = true;//活着
		bombID = 0;//爆炸编号	
		bossBulletState = true;
		bulletID = 0;
//		bossBulletX = 0;
//		bossBulletY = 0;
	}
	
	//绘图方法
	public void DrawBoss(Graphics g, JPanel i) {//绘图
		if(bossState == true){
			g.drawImage(bossImg, bossX, bossY, i);
		}
	}
	
	public void DrawDie(Graphics g, JPanel i) {
		if(bossState == false && bombID < 6) {
			g.drawImage(bossbombImg[bombID], bossX, bossY, i);
			bombID++;
		}
	}
	public void updateBoss() {
		if(bossState == true && bossY < 0) {
			bossY += 5;
		}
		
		if(bossState == true && bossY == 0) {
			if(h == 0) {
				bossX +=2;
			}
			
			if(h == 1) {
				bossX -= 2;
			}
		}
		if(bossX > 140) {
			h = 1;
		}else if(bossX < 0) {
			h = 0;
		}
	}
	
	public void DrawBullet(Graphics g, JPanel i) {
		if(bossBulletState == true && bossState == true) {
			g.drawImage(bossbullet[bulletID], bossX, bossY, i);
			bulletID++;
		}
		if(bulletID == 4) {
			bulletID = 0;
		}
	}
}
