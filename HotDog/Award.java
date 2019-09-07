package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Award {
	//坐标
	int awardX = new Random().nextInt(100);
	int awardY = -new Random().nextInt(100)-103;
	//状态
	boolean awardState = true;
	
	Image awardImg = null;//图片骗对象
	
	public Award() {
		try {
			awardImg = ImageIO.read(new File("HOTDOGJPG\\award.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
	}
	
	//初始化方法
	public void init() {
		awardX = new Random().nextInt(100);
		awardY = -new Random().nextInt(100)-103;
		awardState = true;//活着
	}
	
	//绘图方法
	public void Drawaward(Graphics g, JPanel i) {//绘图
		if(awardState == true) {
			g.drawImage(awardImg, awardX, awardY, i);
		}
	}
	
	public void updateaward() {
		if(awardState == true) {
			awardY += 5;
		}
	}
}
