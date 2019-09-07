package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BigPlane {
	//坐标
	int BplaneX = 0;
	int BplaneY = -89;
	//状态
	boolean BplaneState = true;
	
	Image Bplane = null;//图片对象
	Image[] bpbombImg = new Image[4];//爆炸图片对象
	int bombID = 0;//爆炸图片编号
	int Dienum = 0;//死亡变量
	public BigPlane() {
		try {
			Bplane = ImageIO.read(new File("HOTDOGJPG\\enemy1.png"));//载入素材图片
			//载入敌机爆炸图片
			for(int i = 0; i < bpbombImg.length; i++) {
				bpbombImg[i] = ImageIO.read(new File("HOTDOGJPG\\enemy1_down" + i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();//初始化
	}
	
	//初始化方法
	public void init() {
		BplaneX = 0;
		BplaneY = -89;
		BplaneState = true;//活着
		bombID = 0;//爆炸编号
	}
	
	//绘图方法
	public void DrawBplane(Graphics g, JPanel i) {//绘图
		if(BplaneState == true){
			g.drawImage(Bplane, BplaneX, BplaneY, i);
		}
	}
	public void DrawDie(Graphics g, JPanel i) {
		if(BplaneState == false && bombID < 4) {
			g.drawImage(bpbombImg[bombID], BplaneX, BplaneY, i);
			bombID++;
		}
	}
	public void updateBplane() {
		if(BplaneState = true) {
			BplaneY += 5;
		}
	}
	
	//清屏技能对大飞机依旧有效
	public void allBomb() {
		BplaneState = false;
	}
}
