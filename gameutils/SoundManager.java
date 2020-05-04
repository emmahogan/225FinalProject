package gameutils;

import java.util.ArrayList;

public class SoundManager {
    ArrayList<Sound> sounds;
    ArrayList<Sound> rmSounds;

    public SoundManager() {
        sounds = new ArrayList<>();
    }

    public void update() {
        for (Sound s : sounds) {
            if (s.done) {
                rmSounds.add(s);
            }
        }
        sounds.removeAll(rmSounds);
    }

    public void play(String filePath) {
        for (Sound s : sounds) {
            if (s.filePath.equals(filePath)) {
                s.setIsLoop(true);
                s.start();
            }
        }
    }

    public void loop(String filePath) {
        for (Sound s : sounds) {
            if (s.filePath.equals(filePath)) {
                s.setIsLoop(false);
                s.start();
            }
        }
    }

    public void addSound(String filePath) {
        sounds.add(new Sound(filePath));
    }

    public void addSound(Sound sound) {
        sounds.add(sound);
    }

    public void killAll() {
        for (Sound s : sounds) {
            if (!s.done) {
                s.done = true;
                rmSounds.add(s);
            }
        }
        sounds.removeAll(rmSounds);
    }
}
