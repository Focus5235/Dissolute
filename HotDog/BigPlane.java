package HotDog;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BigPlane {
	//����
	int BplaneX = 0;
	int BplaneY = -89;
	//״̬
	boolean BplaneState = true;
	
	Image Bplane = null;//ͼƬ����
	Image[] bpbombImg = new Image[4];//��ըͼƬ����
	int bombID = 0;//��ըͼƬ���
	int Dienum = 0;//��������
	public BigPlane() {
		try {
			Bplane = ImageIO.read(new File("HOTDOGJPG\\enemy1.png"));//�����ز�ͼƬ
			//����л���ըͼƬ
			for(int i = 0; i < bpbombImg.length; i++) {
				bpbombImg[i] = ImageIO.read(new File("HOTDOGJPG\\enemy1_down" + i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();//��ʼ��
	}
	
	//��ʼ������
	public void init() {
		BplaneX = 0;
		BplaneY = -89;
		BplaneState = true;//����
		bombID = 0;//��ը���
	}
	
	//��ͼ����
	public void DrawBplane(Graphics g, JPanel i) {//��ͼ
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
	
	//�������ܶԴ�ɻ�������Ч
	public void allBomb() {
		BplaneState = false;
	}
}
