package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioFormat;

 
public class Sound {

    public static Sound point;
    Clip clip;
    URL soundUrl[] = new URL[30];

    public Sound() {
        soundUrl[0] = getClass().getResource("/rcs/flap.wav");
        setFile(0);
        soundUrl[1] = getClass().getResource("/rcs/die.wav");
        setFile(1);
        soundUrl[2] = getClass().getResource("/rcs/point.wav");
        setFile(2);
        soundUrl[3] = getClass().getResource("/rcs/hit.wav"); // Update the path here
        setFile(3);
    }
    

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodedFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void playSound(int i) {
        setFile(i);
        play();
    }
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() { 
        if (clip != null) {
            clip.stop();
        }
    }
}

