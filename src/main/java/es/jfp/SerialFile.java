package es.jfp;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serial;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class SerialFile implements Serializable {

    private String fileName;
    private String directory;
    private boolean isFolder;
    private String owner;
    private List<SerialFile> children;

    public SerialFile(String fileName, String directory, boolean isFolder) {
        this.fileName = fileName;
        this.directory = directory;
        this.isFolder = isFolder;
        if (this.isFolder) {
            this.children = new LinkedList<>();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<SerialFile> getChildren() {
        return children;
    }

    public void setChildren(List<SerialFile> children) {
        this.children = children;
    }

    public void appendChildren(List<SerialFile> children) {
        if (this.isFolder) {
            this.children.addAll(children);
        }
    }

    @Override
    public String toString() {
        return "SerialFile{" +
                "fileName='" + fileName + '\'' +
                ", directory=" + directory +
                ", isFolder=" + isFolder +
                ", owner='" + owner + '\'' +
                ", children=" + children +
                '}';
    }

    public SerialFile clone() {
        return SerializationUtils.clone(this);
    }


}
