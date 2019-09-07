package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Plane {
	//敌机坐标
	int planeX = new Random().nextInt(270);
	int planeY = - new Random().nextInt(50) - 50;
	//敌机状态，假表示不存在，爆炸或者飞出界面，真表示存在
	boolean planeState = true;
	
	Image Plane = null;//定义敌机图片对象
	Image[] bombImg = new Image[4];//定义敌机爆炸状态图片数组
	
	int bombID = 0;//定义爆炸图片编号
	
	//构造方法
	public Plane() {
		try {
			Plane = ImageIO.read(new File("HOTDOGJPG\\enemy0.png"));//载入敌机图片
			//载入敌机爆炸图片
			for (int i = 0; i < bombImg.length; i++) {
				bombImg[i] = ImageIO.read(new File("HOTDOGJPG\\enemy0_down" + i +".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();//敌机初始化
	}
	
	//初始化方法
	public void init() {
		planeX = new Random().nextInt(270);
		planeY = - new Random().nextInt(50) - 50;
		planeState = true;//敌机活着
		bombID = 0;//敌机爆炸编号
	}
	
	//敌机绘图方法
	public void DrawPlane(Graphics g, JPanel i) {
		//敌机爆炸，且编号<4显示爆炸敌机
		if(planeState == false && bombID < 4) {
			g.drawImage(bombImg[bombID], planeX, planeY, i);//绘制爆炸敌机图片
			bombID++;//编号+1
			if(bombID == 4) {//编号越界，爆炸动画播放完毕
				init();//产生新的敌机
			}
		}else{//敌机活着
			g.drawImage(Plane, planeX, planeY, i);//显示敌机
		}
	}
	
	//游戏结束，显示爆炸飞机
	public void DrawGameOver(Graphics g, JPanel i, int x, int y) {
		g.drawImage(bombImg[0], x, y, i);//显示爆炸图片
		g.drawImage(bombImg[1], x, y, i);
	}
	
	//更新敌机信息
	public void updatePlane() {
		if(planeState == true) {//敌机活着
			planeY += 5;//敌机向下飞
			if(planeY > 480) {//敌机向下越界
				init();//产生新的敌机
			}
		}
	}
	
	//清屏技能发动
	public void allBomb() {
		planeState = false;
	}
	
}
