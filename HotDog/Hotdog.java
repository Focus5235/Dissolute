package HotDog;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Hotdog extends JPanel implements Runnable,KeyListener {
	//变量声明
	boolean gamewin = false;
	boolean gameover = false;//定义游戏状态，true为游戏结束
	int score = 0;//定义分数
	int skillnum = 3;//定义技能次数
	
	Image bgw = null;//定义人的图片对象
	int bgwX = 100;
	int bgwY = 300;
	
	Image bghd0 = null;//定义背景图片对象
	Image bghd1 = null;
	int bghd0Y = -480;
	int bghd1Y = 0;
	
	//声明热狗信息
	final int enemy_num = 1;//定义热狗数量
	Enemy[] enemy = new Enemy[enemy_num];//声明热狗数组
	
	//声明敌机信息
	final int plane_num = 5;//定义敌机数量
	Plane[] plane = new Plane[plane_num];//定义敌机对象数组
	
	//声明大敌机信息
	final int bplane_num = 1;//定义数量
	BigPlane[] bplane = new BigPlane[bplane_num];//定义大敌机对象数组
	
	//声明boss信息
	Boss boss = new Boss();	
	
	//声明子弹信息
	final int BULLET_NUM = 20;//子弹个数
	Bullet[] bullet = new Bullet[BULLET_NUM];//声明子弹数组
	int bulletID = 0;//声明子弹编号
	
	//声明boss子弹信息
	final int bossBullet_NUM = 2;//子弹个数
	BossBullet[] bossbullet = new BossBullet[bossBullet_NUM]; 	
	int bossbulletID = 0;
	
	//声明奖励信息
	final int Award_num = 1;
	Award[] award = new Award[Award_num];
	
	//声明技能图标信息
	Image bombImg = null;
	
	//背景图片声明
	Image bj = null;
	//背景图片坐标声明
	int bjY = 0;
	
	//定义游戏结束时的图片
	Image gameoverImg = null;
	Image gamewinImg = null;
	
	//声明声音对象
	Music bgMusic;//背景音乐
	Music music10;//10分音乐
	Music Gameover;//游戏结束音效
	Music Planebg;//飞机背景音乐
	//构造方法
	public Hotdog() {
		
		setFocusable(true);//设定焦点
		addKeyListener(this);//键盘监听
		
		init();//初始化方法调用
		
		Thread t = new Thread(this);//创建一个线程
		t.start();//启动线程
		bgMusic.playMusic();
	}
	
	//初始化方法
	public void init() {
		bgw = getToolkit().getImage("HOTDOGJPG\\wsc.png");
		bghd0 = getToolkit().getImage("HOTDOGJPG\\background.png");
		bghd1 = getToolkit().getImage("HOTDOGJPG\\background.png");
		bombImg = getToolkit().getImage("HOTDOGJPG\\bomb.png");
		gameoverImg = getToolkit().getImage("HOTDOGJPG\\defeat.png");
		gamewinImg = getToolkit().getImage("HOTDOGJPG\\win.png");
		//热狗对象初始化
		for(int i = 0; i < enemy.length; i++) {
			enemy[i] = new Enemy();

		}
		
		//敌机对象初始化
		for(int i = 0; i < plane.length; i++) {
			plane[i] = new Plane();
		}
			
		//子弹对象初始化
		for(int i = 0; i < bullet.length; i++) {
			bullet[i] = new Bullet();
		}
		
		//boss子弹对象初始化
		for(int i = 0; i < bossbullet.length; i++) {
			bossbullet[i] = new BossBullet();
		}

		//大敌机对象初始化
		for(int i = 0; i < bplane.length; i++) {
			bplane[i] = new BigPlane(); 
		}

		//奖励对象初始化
		for(int i = 0; i < award.length; i++) {
			award[i] = new Award();
		}
		
		//boss对象初始化
//		for(int i = 0; i < boss.length; i++) {
			boss = new Boss();
//		}
		
		//导入音效文本
		bgMusic = new Music("HOTDOGJPG/bgmusic.wav");
		music10 = new Music("HOTDOGJPG/music10.wav");
		Gameover = new Music("HOTDOGJPG/gameover.wav");
		Planebg = new Music("HOTDOGJPG/planebg.wav");
	}
	
	//游戏数据更新
	public void UpdateData() {
		//背景图片坐标更新
		bghd0Y += 2;
		if(bghd0Y == 480) {
			bghd0Y = -480;
		}
		
		bghd1Y += 2;
		if(bghd1Y == 480) {
			bghd1Y = -480;
		}
		//热狗坐标更新
		for(int i = 0; i < enemy.length; i++) {
			enemy[i].updateEnemy();
		}
		

		if(score >= 10) {
			//敌机坐标更新
			for(int i = 0; i < plane.length; i++) {
				if(boss.bossY <= -200) {
					plane[i].updatePlane();
				}
			}
		
			//子弹坐标更新
			for(int i = 0; i < bullet.length; i++) {
				bullet[i].updateBullet();
			}
		}
		
		//大敌机坐标更新
		for(int i = 0; i < bplane.length; i++) {
			if(score > 100 && score < 150 ) {
				bplane[i].updateBplane();
			}
		}
		
		//boss坐标更新
		if(score > 500 ) {
			boss.updateBoss();
			for(int i = 0; i < plane.length; i++) {
				plane[i].planeState = false;
			}
		}

		//boss子弹坐标更新
		if(boss.bossY == 0) {
			for(int i = 0; i < bossbullet.length; i++) {
				bossbullet[i].updateBullet();
			}
		}
			
		//奖励对象坐标更新
		for(int i = 0; i < award.length; i++) {
			if(award[i].awardState == true && score > 100) {
				award[i].updateaward();
			}
		}
	}
	
	//加分方法
	public void Score() {
		//防止分数达到10分后，击败一架敌机+11分的bug
		for(int i = 0; i < enemy.length; i++) {
			if(score > 9) {
				enemy[i].EhdState = true;
			}
		}
		
		//吃到热狗的加分
		for(int i = 0; i < enemy.length; i++) {
			if(enemy[i].EhdState == false) {
				score += 1;
			}
		}
		
		//击败敌机加分
		for(int i = 0; i < plane.length; i++) {
			if(plane[i].planeState == false) {
				score += 10;
			}
		}
		//击败大敌机加分
		for(int i = 0; i < bplane.length; i++) {
			if(bplane[i].BplaneState == false) {
				score += 50;
			}
		}
		
		//击败boss加分
		if(boss.bossState == false) {
			score += 200;
		}
	}
	
	//重写paint方法，显示图片
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bghd0, 0, bghd0Y, this);
		g.drawImage(bghd1, 0, bghd1Y, this);
		g.drawImage(bgw, bgwX, bgwY, this);//显示人的图片对象

		//显示得分
		g.setFont(new Font("黑体",Font.BOLD,20));
		g.drawString("得分：" + String.valueOf(score), 10, 20);
		//显示技能次数
		g.drawImage(bombImg, 0, 400, this);
		g.setFont(new Font("黑体",Font.BOLD,25));
		g.drawString("× " + skillnum, 40, 425);

		//第一阶段，分数小于十分，显示热狗
		if(score < 10) {
			for(int i = 0; i < enemy.length; i++) {
				enemy[i].DrawHotDog(g, this);
				}
		}else if(score >= 10) {
			//显示敌机
			for(int j = 0; j < plane.length; j++) {
				plane[j].DrawPlane(g, this);
				}
			
			//显示子弹
			for(int i = 0; i < bullet.length; i++) {
				bullet[i].DrawBullet(g, this);
			}
		}
		
		//显示大敌机
		for(int i = 0; i < bplane.length; i++) {
			if(score > 100 && score < 150) {
				bplane[i].DrawBplane(g, this);
			}
			
			if(bplane[i].BplaneState == false) {
				bplane[i].DrawDie(g, this);
			}
		}
		
		//显示boss
		if(score > 500 && score < 700) {
			boss.DrawBoss(g, this);
		}
		
		if(boss.bossState == false) {
			boss.DrawDie(g, this);
		}
		
		//显示boss子弹
		for(int i = 0; i < bossbullet.length; i++) {
			if(boss.bossY == 0) {
				bossbullet[i].DrawBullet(g, this);
			}
		}
		
		//显示奖励对象
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

	//碰撞检测
	public void Collision() {
		if(score < 10) {
			//热狗被吃到的碰撞检测
			for(int i = 0; i < enemy.length; i++) {
				if(enemy[i].EhdState == true && gameover == false &&
						enemy[i].EhdX < bgwX + 30 &&
						enemy[i].EhdX+6 > bgwX &&
						enemy[i].EhdY < bgwY + 83 &&
						enemy[i].EhdY + 40 > bgwY ) {
					enemy[i].EhdState = false;//热狗被吃掉
//					score++;//分数加一
					Score();
					//10分音效
					if(score % 10 == 0 && score % 2 == 0) {
						music10.playMusic();
						bgMusic.stopMusic();
						Planebg.loopMusic();
					}
				}
			}
		
			//热狗碰到底边游戏结束
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
			//子弹击中敌机的碰撞检测
			for(int j = 0; j < bullet.length; j++) {
				if(bullet[j].bulletState == true && plane[i].planeState == true &&
						bullet[j].bulletX < plane[i].planeX +51 &&
						bullet[j].bulletX + 32 > plane[i].planeX &&
						bullet[j].bulletY < plane[i].planeY + 39 &&
						bullet[j].bulletY +50 > plane[i].planeY) {
					plane[i].planeState = false;
					bullet[j].bulletState = false;//子弹击中敌机，两者都消失
					Score();
				}
			}
			
			//敌机与wsc的碰撞检测
			if(plane[i].planeState == true &&
					bgwX < plane[i].planeX +51 &&
					bgwX + 55 > plane[i].planeX &&
					bgwY < plane[i].planeY +39 &&
					bgwY + 59 > plane[i].planeY) {
				gameover = true;//wsc阵亡，游戏结束
				plane[i].planeState = false;//敌机消失
				}
			}
		
		for(int i = 0; i < bplane.length; i++) {
			//子弹击中大敌机的碰撞检测
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
					bullet[j].bulletState = false;//子弹击中敌机，两者都消失
					Score();
				}
			}
			
			//大敌机与wsc的碰撞检测
			if(bplane[i].BplaneState == true &&
					bgwX < bplane[i].BplaneX +69 &&
					bgwX + 55 > bplane[i].BplaneX &&
					bgwY < bplane[i].BplaneY + 89 &&
					bgwY + 59 > bplane[i].BplaneY) {
				gameover = true;//wsc阵亡，游戏结束
				bplane[i].BplaneState = false;//敌机消失
			}
		}
		
		//wsc和奖励的碰撞检测
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
		
		//wsc和boss碰撞
		if(boss.bossBulletState == true &&
				bgwX < boss.bossX + 165 &&
				bgwX +55 > boss.bossX &&
				bgwY < boss.bossY + 246 &&
				bgwY + 59 > boss.bossY) {
			gameover = true;//wsc阵亡，游戏结束
			boss.bossState = false;
		}
		
		//boss子弹和wsc碰撞检测
		for(int i = 0; i < bossbullet.length; i++) {
			if(bossbullet[i].bossbulletState == true &&
					bgwX < bossbullet[i].bossbulletX +5 &&
					bgwX + 55 > bossbullet[i].bossbulletX &&
					bgwY < bossbullet[i].bossbulletY + 5 &&
					bgwY + 59 > bossbullet[i].bossbulletY) {
				gameover = true;//wsc阵亡，游戏结束
				bossbullet[i].bossbulletState = false;
			}
		}
		
		//热狗子弹和boss碰撞
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
	
	//run方法重构，控制游戏运行
	@Override
	public void run() {
		while(!gameover && !gamewin) {//死循环，模拟游戏一直进行状态
			UpdateData();//游戏数据更新
			bossbullet();
			repaint();//重绘游戏窗口
			Collision();//碰撞

			try {
				Thread.sleep(50);//休眠50毫秒
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
	
	//键盘监听
	@Override
	public void keyPressed(KeyEvent e) {
		//w和上键按下，控制飞机向上移动
		 if(e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_W) {
			 if(bgwY > 0) {//未到顶
				 bgwY -= 10;//飞机坐标向上
			 }
		 }
		 //s和下键按下，控制飞机向下移动
		 if(e.getKeyCode() == KeyEvent.VK_DOWN ||e.getKeyCode() == KeyEvent.VK_S) {
			 if(bgwY < 385) {//越界检测
				 bgwY += 10;//飞机坐标向下
			 }
		 }
		 //a和左键按下，控制飞机向左移动
		 if(e.getKeyCode() == KeyEvent.VK_LEFT ||e.getKeyCode() == KeyEvent.VK_A) {
			 if(bgwX > 0) {//越界检测
				 bgwX -= 10;//飞机坐标向左
			 }
		 }
		 //d和右键按下，控制飞机向右移动
		 if(e.getKeyCode() == KeyEvent.VK_RIGHT ||e.getKeyCode() == KeyEvent.VK_D) {
			 if(bgwX < 247) {//越界检测
				 bgwX += 10;//飞机坐标向左
			 }
		 }
		 
		 //空格键按下，并且游戏还在进行中，发射子弹
		 if(e.getKeyCode() == 32 && gameover == false) {
			 //发射子弹
			 if(!bullet[bulletID].bulletState) {//子弹还在就改变子弹坐标向上移动
				 bullet[bulletID].init(bgwX - 5, bgwY - 40);
				 bulletID++;//子弹编号更新
				 if(bulletID == BULLET_NUM)//子弹编号越界
					 bulletID = 0;//回到0
			 }
		 }
		 
		 //按下B键，发动清屏技能，所有敌机爆炸
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
		//发射子弹
		if(!bossbullet[bossbulletID].bossbulletState && boss.bossState) {//子弹还在就改变子弹坐标向上移动
			bossbullet[bossbulletID].init(boss.bossX + 85, boss.bossY + 246);
			bossbulletID++;//子弹编号更新
			if(bossbulletID == bossBullet_NUM)//子弹编号越界
				bossbulletID = 0;//回到0
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
