package HotDog;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Hotdog extends JPanel implements Runnable,KeyListener {
	//��������
	boolean gamewin = false;
	boolean gameover = false;//������Ϸ״̬��trueΪ��Ϸ����
	int score = 0;//�������
	int skillnum = 3;//���弼�ܴ���
	
	Image bgw = null;//�����˵�ͼƬ����
	int bgwX = 100;
	int bgwY = 300;
	
	Image bghd0 = null;//���屳��ͼƬ����
	Image bghd1 = null;
	int bghd0Y = -480;
	int bghd1Y = 0;
	
	//�����ȹ���Ϣ
	final int enemy_num = 1;//�����ȹ�����
	Enemy[] enemy = new Enemy[enemy_num];//�����ȹ�����
	
	//�����л���Ϣ
	final int plane_num = 5;//����л�����
	Plane[] plane = new Plane[plane_num];//����л���������
	
	//������л���Ϣ
	final int bplane_num = 1;//��������
	BigPlane[] bplane = new BigPlane[bplane_num];//�����л���������
	
	//����boss��Ϣ
	Boss boss = new Boss();	
	
	//�����ӵ���Ϣ
	final int BULLET_NUM = 20;//�ӵ�����
	Bullet[] bullet = new Bullet[BULLET_NUM];//�����ӵ�����
	int bulletID = 0;//�����ӵ����
	
	//����boss�ӵ���Ϣ
	final int bossBullet_NUM = 2;//�ӵ�����
	BossBullet[] bossbullet = new BossBullet[bossBullet_NUM]; 	
	int bossbulletID = 0;
	
	//����������Ϣ
	final int Award_num = 1;
	Award[] award = new Award[Award_num];
	
	//��������ͼ����Ϣ
	Image bombImg = null;
	
	//����ͼƬ����
	Image bj = null;
	//����ͼƬ��������
	int bjY = 0;
	
	//������Ϸ����ʱ��ͼƬ
	Image gameoverImg = null;
	Image gamewinImg = null;
	
	//������������
	Music bgMusic;//��������
	Music music10;//10������
	Music Gameover;//��Ϸ������Ч
	Music Planebg;//�ɻ���������
	//���췽��
	public Hotdog() {
		
		setFocusable(true);//�趨����
		addKeyListener(this);//���̼���
		
		init();//��ʼ����������
		
		Thread t = new Thread(this);//����һ���߳�
		t.start();//�����߳�
		bgMusic.playMusic();
	}
	
	//��ʼ������
	public void init() {
		bgw = getToolkit().getImage("HOTDOGJPG\\wsc.png");
		bghd0 = getToolkit().getImage("HOTDOGJPG\\background.png");
		bghd1 = getToolkit().getImage("HOTDOGJPG\\background.png");
		bombImg = getToolkit().getImage("HOTDOGJPG\\bomb.png");
		gameoverImg = getToolkit().getImage("HOTDOGJPG\\defeat.png");
		gamewinImg = getToolkit().getImage("HOTDOGJPG\\win.png");
		//�ȹ������ʼ��
		for(int i = 0; i < enemy.length; i++) {
			enemy[i] = new Enemy();

		}
		
		//�л������ʼ��
		for(int i = 0; i < plane.length; i++) {
			plane[i] = new Plane();
		}
			
		//�ӵ������ʼ��
		for(int i = 0; i < bullet.length; i++) {
			bullet[i] = new Bullet();
		}
		
		//boss�ӵ������ʼ��
		for(int i = 0; i < bossbullet.length; i++) {
			bossbullet[i] = new BossBullet();
		}

		//��л������ʼ��
		for(int i = 0; i < bplane.length; i++) {
			bplane[i] = new BigPlane(); 
		}

		//���������ʼ��
		for(int i = 0; i < award.length; i++) {
			award[i] = new Award();
		}
		
		//boss�����ʼ��
//		for(int i = 0; i < boss.length; i++) {
			boss = new Boss();
//		}
		
		//������Ч�ı�
		bgMusic = new Music("HOTDOGJPG/bgmusic.wav");
		music10 = new Music("HOTDOGJPG/music10.wav");
		Gameover = new Music("HOTDOGJPG/gameover.wav");
		Planebg = new Music("HOTDOGJPG/planebg.wav");
	}
	
	//��Ϸ���ݸ���
	public void UpdateData() {
		//����ͼƬ�������
		bghd0Y += 2;
		if(bghd0Y == 480) {
			bghd0Y = -480;
		}
		
		bghd1Y += 2;
		if(bghd1Y == 480) {
			bghd1Y = -480;
		}
		//�ȹ��������
		for(int i = 0; i < enemy.length; i++) {
			enemy[i].updateEnemy();
		}
		

		if(score >= 10) {
			//�л��������
			for(int i = 0; i < plane.length; i++) {
				if(boss.bossY <= -200) {
					plane[i].updatePlane();
				}
			}
		
			//�ӵ��������
			for(int i = 0; i < bullet.length; i++) {
				bullet[i].updateBullet();
			}
		}
		
		//��л��������
		for(int i = 0; i < bplane.length; i++) {
			if(score > 100 && score < 150 ) {
				bplane[i].updateBplane();
			}
		}
		
		//boss�������
		if(score > 500 ) {
			boss.updateBoss();
			for(int i = 0; i < plane.length; i++) {
				plane[i].planeState = false;
			}
		}

		//boss�ӵ��������
		if(boss.bossY == 0) {
			for(int i = 0; i < bossbullet.length; i++) {
				bossbullet[i].updateBullet();
			}
		}
			
		//���������������
		for(int i = 0; i < award.length; i++) {
			if(award[i].awardState == true && score > 100) {
				award[i].updateaward();
			}
		}
	}
	
	//�ӷַ���
	public void Score() {
		//��ֹ�����ﵽ10�ֺ󣬻���һ�ܵл�+11�ֵ�bug
		for(int i = 0; i < enemy.length; i++) {
			if(score > 9) {
				enemy[i].EhdState = true;
			}
		}
		
		//�Ե��ȹ��ļӷ�
		for(int i = 0; i < enemy.length; i++) {
			if(enemy[i].EhdState == false) {
				score += 1;
			}
		}
		
		//���ܵл��ӷ�
		for(int i = 0; i < plane.length; i++) {
			if(plane[i].planeState == false) {
				score += 10;
			}
		}
		//���ܴ�л��ӷ�
		for(int i = 0; i < bplane.length; i++) {
			if(bplane[i].BplaneState == false) {
				score += 50;
			}
		}
		
		//����boss�ӷ�
		if(boss.bossState == false) {
			score += 200;
		}
	}
	
	//��дpaint��������ʾͼƬ
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bghd0, 0, bghd0Y, this);
		g.drawImage(bghd1, 0, bghd1Y, this);
		g.drawImage(bgw, bgwX, bgwY, this);//��ʾ�˵�ͼƬ����

		//��ʾ�÷�
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString("�÷֣�" + String.valueOf(score), 10, 20);
		//��ʾ���ܴ���
		g.drawImage(bombImg, 0, 400, this);
		g.setFont(new Font("����",Font.BOLD,25));
		g.drawString("�� " + skillnum, 40, 425);

		//��һ�׶Σ�����С��ʮ�֣���ʾ�ȹ�
		if(score < 10) {
			for(int i = 0; i < enemy.length; i++) {
				enemy[i].DrawHotDog(g, this);
				}
		}else if(score >= 10) {
			//��ʾ�л�
			for(int j = 0; j < plane.length; j++) {
				plane[j].DrawPlane(g, this);
				}
			
			//��ʾ�ӵ�
			for(int i = 0; i < bullet.length; i++) {
				bullet[i].DrawBullet(g, this);
			}
		}
		
		//��ʾ��л�
		for(int i = 0; i < bplane.length; i++) {
			if(score > 100 && score < 150) {
				bplane[i].DrawBplane(g, this);
			}
			
			if(bplane[i].BplaneState == false) {
				bplane[i].DrawDie(g, this);
			}
		}
		
		//��ʾboss
		if(score > 500 && score < 700) {
			boss.DrawBoss(g, this);
		}
		
		if(boss.bossState == false) {
			boss.DrawDie(g, this);
		}
		
		//��ʾboss�ӵ�
		for(int i = 0; i < bossbullet.length; i++) {
			if(boss.bossY == 0) {
				bossbullet[i].DrawBullet(g, this);
			}
		}
		
		//��ʾ��������
		for(int i = 0; i < award.length; i++) {
			award[i].Drawaward(g, this);
		}
		
		if(gameover) {
			g.drawImage(gameoverImg, 50, 50, this);
		}
		
		if(gamewin) {
			g.drawImage(gamewinImg, 50, 50, this);
		}
	}

	//��ײ���
	public void Collision() {
		if(score < 10) {
			//�ȹ����Ե�����ײ���
			for(int i = 0; i < enemy.length; i++) {
				if(enemy[i].EhdState == true && gameover == false &&
						enemy[i].EhdX < bgwX + 30 &&
						enemy[i].EhdX+6 > bgwX &&
						enemy[i].EhdY < bgwY + 83 &&
						enemy[i].EhdY + 40 > bgwY ) {
					enemy[i].EhdState = false;//�ȹ����Ե�
//					score++;//������һ
					Score();
					//10����Ч
					if(score % 10 == 0 && score % 2 == 0) {
						music10.playMusic();
						bgMusic.stopMusic();
						Planebg.loopMusic();
					}
				}
			}
		
			//�ȹ������ױ���Ϸ����
			for(int i = 0; i < enemy.length; i++) {
				if(enemy[i].EhdState == true && enemy[i].EhdY > 400) { 
					gameover = true;
					enemy[i].EhdState = false;
//					bgMusic.stopMusic();
//					Gameover.playMusic();
				}
			}
		}
		
		for(int i = 0; i < plane.length; i++) {
			//�ӵ����ел�����ײ���
			for(int j = 0; j < bullet.length; j++) {
				if(bullet[j].bulletState == true && plane[i].planeState == true &&
						bullet[j].bulletX < plane[i].planeX +51 &&
						bullet[j].bulletX + 32 > plane[i].planeX &&
						bullet[j].bulletY < plane[i].planeY + 39 &&
						bullet[j].bulletY +50 > plane[i].planeY) {
					plane[i].planeState = false;
					bullet[j].bulletState = false;//�ӵ����ел������߶���ʧ
					Score();
				}
			}
			
			//�л���wsc����ײ���
			if(plane[i].planeState == true &&
					bgwX < plane[i].planeX +51 &&
					bgwX + 55 > plane[i].planeX &&
					bgwY < plane[i].planeY +39 &&
					bgwY + 59 > plane[i].planeY) {
				gameover = true;//wsc��������Ϸ����
				plane[i].planeState = false;//�л���ʧ
				}
			}
		
		for(int i = 0; i < bplane.length; i++) {
			//�ӵ����д�л�����ײ���
			for(int j = 0; j < bullet.length; j++) {
				if(bullet[j].bulletState == true && bplane[i].BplaneState == true &&
						bullet[j].bulletX < bplane[i].BplaneX + 69 &&
						bullet[j].bulletX + 32 > bplane[i].BplaneX &&
						bullet[j].bulletY < bplane[i].BplaneY + 89 &&
						bullet[j].bulletY +50 > bplane[i].BplaneY) {
					bplane[i].Dienum++;
					if(bplane[i].Dienum == 5) {
						bplane[i].BplaneState = false;
					}
					bullet[j].bulletState = false;//�ӵ����ел������߶���ʧ
					Score();
				}
			}
			
			//��л���wsc����ײ���
			if(bplane[i].BplaneState == true &&
					bgwX < bplane[i].BplaneX +69 &&
					bgwX + 55 > bplane[i].BplaneX &&
					bgwY < bplane[i].BplaneY + 89 &&
					bgwY + 59 > bplane[i].BplaneY) {
				gameover = true;//wsc��������Ϸ����
				bplane[i].BplaneState = false;//�л���ʧ
			}
		}
		
		//wsc�ͽ�������ײ���
		for(int i = 0; i < award.length; i++) {
			if(award[i].awardState == true &&
					bgwX < award[i].awardX + 60 &&
					bgwX + 55 > award[i].awardX &&
					bgwY < award[i].awardY + 103 &&
					bgwY + 59 > award[i].awardY) {
				award[i].awardState = false;
				skillnum++;
			}
		}
		
		//wsc��boss��ײ
		if(boss.bossBulletState == true &&
				bgwX < boss.bossX + 165 &&
				bgwX +55 > boss.bossX &&
				bgwY < boss.bossY + 246 &&
				bgwY + 59 > boss.bossY) {
			gameover = true;//wsc��������Ϸ����
			boss.bossState = false;
		}
		
		//boss�ӵ���wsc��ײ���
		for(int i = 0; i < bossbullet.length; i++) {
			if(bossbullet[i].bossbulletState == true &&
					bgwX < bossbullet[i].bossbulletX +5 &&
					bgwX + 55 > bossbullet[i].bossbulletX &&
					bgwY < bossbullet[i].bossbulletY + 5 &&
					bgwY + 59 > bossbullet[i].bossbulletY) {
				gameover = true;//wsc��������Ϸ����
				bossbullet[i].bossbulletState = false;
			}
		}
		
		//�ȹ��ӵ���boss��ײ
		for(int i = 0; i < bullet.length; i++) {
			if(boss.bossState == true && bullet[i].bulletState == true &&
					boss.bossY == 0 &&
					boss.bossX < bullet[i].bulletX + 32 &&
					boss.bossX +165 > bullet[i].bulletX &&
					boss.bossY < bullet[i].bulletY + 50 &&
					boss.bossY + 246 > bullet[i].bulletY) {
				boss.Dienum++;
				if(boss.Dienum == 15) {
					boss.bossState = false;
					Score();
					gamewin = true;
				}
				bullet[i].bulletState =false;
			}
		}
	}
	
	//run�����ع���������Ϸ����
	@Override
	public void run() {
		while(!gameover && !gamewin) {//��ѭ����ģ����Ϸһֱ����״̬
			UpdateData();//��Ϸ���ݸ���
			bossbullet();
			repaint();//�ػ���Ϸ����
			Collision();//��ײ

			try {
				Thread.sleep(50);//����50����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(gameover) {
				bgMusic.stopMusic();
				Planebg.stopMusic();
				Gameover.playMusic();
			}
		}	
	}
	
	//���̼���
	@Override
	public void keyPressed(KeyEvent e) {
		//w���ϼ����£����Ʒɻ������ƶ�
		 if(e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_W) {
			 if(bgwY > 0) {//δ����
				 bgwY -= 10;//�ɻ���������
			 }
		 }
		 //s���¼����£����Ʒɻ������ƶ�
		 if(e.getKeyCode() == KeyEvent.VK_DOWN ||e.getKeyCode() == KeyEvent.VK_S) {
			 if(bgwY < 385) {//Խ����
				 bgwY += 10;//�ɻ���������
			 }
		 }
		 //a��������£����Ʒɻ������ƶ�
		 if(e.getKeyCode() == KeyEvent.VK_LEFT ||e.getKeyCode() == KeyEvent.VK_A) {
			 if(bgwX > 0) {//Խ����
				 bgwX -= 10;//�ɻ���������
			 }
		 }
		 //d���Ҽ����£����Ʒɻ������ƶ�
		 if(e.getKeyCode() == KeyEvent.VK_RIGHT ||e.getKeyCode() == KeyEvent.VK_D) {
			 if(bgwX < 247) {//Խ����
				 bgwX += 10;//�ɻ���������
			 }
		 }
		 
		 //�ո�����£�������Ϸ���ڽ����У������ӵ�
		 if(e.getKeyCode() == 32 && gameover == false) {
			 //�����ӵ�
			 if(!bullet[bulletID].bulletState) {//�ӵ����ھ͸ı��ӵ����������ƶ�
				 bullet[bulletID].init(bgwX - 5, bgwY - 40);
				 bulletID++;//�ӵ���Ÿ���
				 if(bulletID == BULLET_NUM)//�ӵ����Խ��
					 bulletID = 0;//�ص�0
			 }
		 }
		 
		 //����B���������������ܣ����ел���ը
		 if(e.getKeyCode() == KeyEvent.VK_B) {
			 if(skillnum > 0) {
				 for(int i = 0; i < plane.length; i++) {
					 plane[i].allBomb();
				 }
				 skillnum--;
				 Score();
			 }
		 }
	}
	
	
	public void bossbullet() {
		//�����ӵ�
		if(!bossbullet[bossbulletID].bossbulletState && boss.bossState) {//�ӵ����ھ͸ı��ӵ����������ƶ�
			bossbullet[bossbulletID].init(boss.bossX + 85, boss.bossY + 246);
			bossbulletID++;//�ӵ���Ÿ���
			if(bossbulletID == bossBullet_NUM)//�ӵ����Խ��
				bossbulletID = 0;//�ص�0
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
