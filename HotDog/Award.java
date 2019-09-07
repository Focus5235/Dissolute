package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Award {
	//����
	int awardX = new Random().nextInt(100);
	int awardY = -new Random().nextInt(100)-103;
	//״̬
	boolean awardState = true;
	
	Image awardImg = null;//ͼƬƭ����
	
	public Award() {
		try {
			awardImg = ImageIO.read(new File("HOTDOGJPG\\award.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
	}
	
	//��ʼ������
	public void init() {
		awardX = new Random().nextInt(100);
		awardY = -new Random().nextInt(100)-103;
		awardState = true;//����
	}
	
	//��ͼ����
	public void Drawaward(Graphics g, JPanel i) {//��ͼ
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
