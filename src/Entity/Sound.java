package Entity;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	public URL url[] = new URL[30];
	public Sound() {
		url[0] = getClass().getResource("/sound/1.wav");
		url[1] = getClass().getResource("/sound/2.wav");
		url[2] = getClass().getResource("/sound/flash.wav");
		url[3] = getClass().getResource("/sound/naruto.wav");
		url[4] = getClass().getResource("/sound/ultiNaruto.wav");
		url[5] = getClass().getResource("/sound/sasuke.wav");
		url[6] = getClass().getResource("/sound/ultiSasuke.wav");
		url[7] = getClass().getResource("/sound/sakura.wav");
		
	}
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(url[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}
