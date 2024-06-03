package es.jfp;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class SerialMap implements Serializable {

    @Serial
    private static final long serialVersionUID = 12345L;

    private SerialFile rootFile;

    public SerialMap(SerialFile rootFile) {
        this.rootFile = rootFile;
    }

    public SerialFile getRootFile() {
        return rootFile;
    }

    public void setRootFile(SerialFile rootFile) {
        this.rootFile = rootFile;
    }



    public void forEachSerialFiles(Consumer<SerialFile> consumer) {
        walkDirectory(rootFile, consumer);
    }

    private void walkDirectory(SerialFile file, Consumer<SerialFile> consumer) {
        for (SerialFile child: file.getChildren()) {
            consumer.accept(child);
            if (child.isFolder()) {
                walkDirectory(child, consumer);
            }
        }
    }

    public List<SerialFile> filterSerialFiles(Function<SerialFile, Boolean> function) {
        return walkDirectory(rootFile, function);
    }

    private List<SerialFile> walkDirectory(SerialFile file, Function<SerialFile, Boolean> function) {
        List<SerialFile> files = new LinkedList<>();
        for (SerialFile child: file.getChildren()) {
            SerialFile newChild = child.clone();
            if (function.apply(newChild)) {
                files.add(newChild);
                if (newChild.isFolder()) {
                    List<SerialFile> newChildren = walkDirectory(newChild, function);
                    newChild.getChildren().clear();
                    newChild.getChildren().addAll(newChildren);
                }
            }
        }
        return files;
    }




}


