package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Plane {
	//�л�����
	int planeX = new Random().nextInt(270);
	int planeY = - new Random().nextInt(50) - 50;
	//�л�״̬���ٱ�ʾ�����ڣ���ը���߷ɳ����棬���ʾ����
	boolean planeState = true;
	
	Image Plane = null;//����л�ͼƬ����
	Image[] bombImg = new Image[4];//����л���ը״̬ͼƬ����
	
	int bombID = 0;//���屬ըͼƬ���
	
	//���췽��
	public Plane() {
		try {
			Plane = ImageIO.read(new File("HOTDOGJPG\\enemy0.png"));//����л�ͼƬ
			//����л���ըͼƬ
			for (int i = 0; i < bombImg.length; i++) {
				bombImg[i] = ImageIO.read(new File("HOTDOGJPG\\enemy0_down" + i +".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();//�л���ʼ��
	}
	
	//��ʼ������
	public void init() {
		planeX = new Random().nextInt(270);
		planeY = - new Random().nextInt(50) - 50;
		planeState = true;//�л�����
		bombID = 0;//�л���ը���
	}
	
	//�л���ͼ����
	public void DrawPlane(Graphics g, JPanel i) {
		//�л���ը���ұ��<4��ʾ��ը�л�
		if(planeState == false && bombID < 4) {
			g.drawImage(bombImg[bombID], planeX, planeY, i);//���Ʊ�ը�л�ͼƬ
			bombID++;//���+1
			if(bombID == 4) {//���Խ�磬��ը�����������
				init();//�����µĵл�
			}
		}else{//�л�����
			g.drawImage(Plane, planeX, planeY, i);//��ʾ�л�
		}
	}
	
	//��Ϸ��������ʾ��ը�ɻ�
	public void DrawGameOver(Graphics g, JPanel i, int x, int y) {
		g.drawImage(bombImg[0], x, y, i);//��ʾ��ըͼƬ
		g.drawImage(bombImg[1], x, y, i);
	}
	
	//���µл���Ϣ
	public void updatePlane() {
		if(planeState == true) {//�л�����
			planeY += 5;//�л����·�
			if(planeY > 480) {//�л�����Խ��
				init();//�����µĵл�
			}
		}
	}
	
	//�������ܷ���
	public void allBomb() {
		planeState = false;
	}
	
}
