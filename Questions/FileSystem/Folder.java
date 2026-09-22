package FileSystem;


import java.util.ArrayList;
import java.util.List;
public class Folder implements  FileSystemItem{
    private final String name;
    private List<FileSystemItem> items;

    public Folder(String name,int size)
    {
        this.name = name;
        items = new ArrayList<>();
    }

    public void addItem(FileSystemItem item) {
        items.add(item);
    }

    public void removeItem(FileSystemItem item) {
        items.remove(item);
    }

    @Override
    public int getsize()
    {
        int size = 0;
        for(FileSystemItem item: items)
        {
            size = size + item.getsize();
        }
        return size;
    }

    @Override
    public void printStructure(String indent)
    {
        System.out.println(indent + "+ " + name + "/");
        for (FileSystemItem item : items) {
            item.printStructure(indent + "  ");
        }
    } 

    @Override 
    public void delete()
    {
        for (FileSystemItem item : items) {
            item.delete();
        }
        System.out.println("Deleting folder: " + name);
    }
}
