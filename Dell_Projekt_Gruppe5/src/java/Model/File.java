/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author EmilGras
 */
public class File {
    private String name;
    private String extension;
    
    public File(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }
    
    public String getFileName() {
        return name + "." + extension;
    }
}
