package FileSystem;

public interface FileSystemItem {
    int getsize();
    void printStructure(String indent);
    void delete();
}


