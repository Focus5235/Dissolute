package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Enemy {
	//热狗坐标
	int EhdX = new Random().nextInt(270);
	int EhdY = -new Random().nextInt(50)-50;
	
	//热狗状态，假表示不存在，被吃掉或者飞出界外，真表示存在
	boolean EhdState = true;
	
	Image Ehd = null;//定义热狗图片对象
	
	int hdID = 0;//定义热狗图片id
	//构造方法
	public Enemy() {
		try {
			Ehd = ImageIO.read(new File("HOTDOGJPG\\hotdog.png"));//载入热狗图片
			//载入敌机爆炸图片
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();//热狗初始化
	}
	
	//初始化方法
	public void init() {
		EhdX = new Random().nextInt(270);//热狗坐标随机生成
		EhdY = -new Random().nextInt(50)-50;
		EhdState = true;//热狗存在
	}
	
	//热狗绘制
	public void DrawHotDog(Graphics g, JPanel i) {
		if(EhdState == true) {//热狗存在，切编号...显示热狗图片
			g.drawImage(Ehd, EhdX, EhdY, i);//绘制热狗图片
		}else
			init();
	}
	
	//更新热狗信息
	public void updateEnemy() {
		if(EhdState == true ) {//热狗存在
			EhdY += 5;//热狗向下飞
			if(EhdY > 450 || EhdState == false) {//热狗飞出界外
				init();//产生新的热狗
			}
		}
	}
	
	//游戏结束balabala
}
