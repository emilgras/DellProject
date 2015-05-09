package Entities;

/**
 *
 * @author EmilGras
 */
public class CustomFile {
    private String name;
    private String extension;
    
    public CustomFile(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }
    
    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }
    
    public String getFileName() {
        return name + "." + extension;
    }
}
