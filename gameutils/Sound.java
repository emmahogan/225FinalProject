package gameutils;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * This is an object
 * @author Justin, Andrew, Emma, Tim, Nick
 *
 */
public class Sound extends Thread implements LineListener {
    private Clip audioClip;
    public String filePath;
    public boolean done;
    private boolean playCompleted;
    private boolean isLoop;

    public Sound(String filePath) {
        this.filePath = filePath;
        File audioFile = new File(filePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            this.audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
        } catch (Exception e) {
            System.out.println("File not found or file not supported.");
        }
    }

    @Override
    public void run() {
        if (isLoop && !done) {
            while (true) {
                audioClip.start();
                while (!playCompleted) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                audioClip.close();
            }
        } else if (!done) {
            audioClip.start();
            while (!playCompleted) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            audioClip.close();
            done = true;
        }
    }

    public void setIsLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }

    }
}
