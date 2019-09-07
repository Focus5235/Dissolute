package HotDog;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Music {
	File f;//�������ļ���·��
	URL url;
	URI uri;
	AudioClip clip;
	
	//���췽����nameд������·�����������ļ�
	public Music(String name) {
		f = new File(name);
		try {
			uri = f.toURI();
			url = uri.toURL();
			clip = Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("���Ŵ���");
		}
	}
	
	//ֹͣ����
	public void stopMusic() {
		clip.stop();
	}
	
	//����
	public void playMusic() {
		clip.play();
	}
	
	//ѭ������
	public void loopMusic() {
		clip.loop();
	}
}
