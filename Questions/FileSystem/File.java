package FileSystem;

public class File implements FileSystemItem {
    private final String name;
    private final int size;

    public File(String name,int size)
    {
        this.name = name;
        this.size = size;
    }

    @Override
    public int getsize()
    {
        return size;
    }

    @Override
    public void printStructure(String indent)
    {
        System.out.println(indent + "- " + name + " (" + size + " KB)");
    } 

    @Override 
    public void delete()
    {
        System.out.println("Deleting file: " + name);
    }
}
