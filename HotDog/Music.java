package HotDog;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Music {
	File f;//放音乐文件的路径
	URL url;
	URI uri;
	AudioClip clip;
	
	//构造方法，name写入音乐路径，打开声音文件
	public Music(String name) {
		f = new File(name);
		try {
			uri = f.toURI();
			url = uri.toURL();
			clip = Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("播放错误");
		}
	}
	
	//停止播放
	public void stopMusic() {
		clip.stop();
	}
	
	//播放
	public void playMusic() {
		clip.play();
	}
	
	//循环播放
	public void loopMusic() {
		clip.loop();
	}
}
