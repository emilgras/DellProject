
package Model;

import java.util.ArrayList;

public class Poe {
    private ArrayList<CustomFile> imageFiles;
    private ArrayList<CustomFile> videoFiles;
    private ArrayList<CustomFile> musicFiles;
    private ArrayList<CustomFile> textFiles;
    
    public Poe() {
        imageFiles = new ArrayList();
        videoFiles = new ArrayList();
        musicFiles = new ArrayList();
        textFiles = new ArrayList();
    }
    
    public void addImageFile(CustomFile file) {
        imageFiles.add(file);
    }
    
    public void addVideoFile(CustomFile file) {
        videoFiles.add(file);
    }
    
    public void addMusicFile(CustomFile file) {
        textFiles.add(file);
    }
    
    public void addTextFile(CustomFile file) {
        imageFiles.add(file);
    }

    public ArrayList<CustomFile> getImageFiles() {
        return imageFiles;
    }

    public ArrayList<CustomFile> getVideoFiles() {
        return videoFiles;
    }

    public ArrayList<CustomFile> getMusicFiles() {
        return musicFiles;
    }

    public ArrayList<CustomFile> getTextFiles() {
        return textFiles;
    }
}
