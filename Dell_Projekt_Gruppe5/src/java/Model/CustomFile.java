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
public class CustomFile {
    private String name;
    private String extension;
    
    public CustomFile(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }
    
    public String getFileName() {
        return name + "." + extension;
    }
}
