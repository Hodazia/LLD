package DESIGN_PATTERNS.AbstractFactory;


/*
2 product interfaces like UI, 



*/
// Product Family A: UI Theme
interface UITheme {
    void applyTheme();
}

class WindowsTheme implements UITheme {
    public void applyTheme() { System.out.println("Applying Fluent Design (Windows) UI Theme."); }
}

class MacTheme implements UITheme {
    public void applyTheme() { System.out.println("Applying Aqua (macOS) UI Theme."); }
}

class LinuxTheme implements UITheme {
    public void applyTheme() { System.out.println("Applying Adwaita/GNOME (Linux) UI Theme."); }
}

// Product Family B: File System
interface FileSystem {
    void formatDrive();
}

class NTFS implements FileSystem {
    public void formatDrive() { System.out.println("Formatting drive using NTFS file system."); }
}

class APFS implements FileSystem {
    public void formatDrive() { System.out.println("Formatting drive using APFS file system."); }
}

class EXT4 implements FileSystem {
    public void formatDrive() { System.out.println("Formatting drive using EXT4 file system."); }
}


interface OSAbstractFactory {
    UITheme createUITheme();
    FileSystem createFileSystem();
}

class WindowsFactory implements OSAbstractFactory {
    public UITheme createUITheme() { return new WindowsTheme(); }
    public FileSystem createFileSystem() { return new NTFS(); }
}

class MacFactory implements OSAbstractFactory {
    public UITheme createUITheme() { return new MacTheme(); }
    public FileSystem createFileSystem() { return new APFS(); }
}

class LinuxFactory implements OSAbstractFactory {
    public UITheme createUITheme() { return new LinuxTheme(); }
    public FileSystem createFileSystem() { return new EXT4(); }
}

class OSClient {
    private UITheme theme;
    private FileSystem fileSystem;

    // The client works purely with the abstract types
    public OSClient(OSAbstractFactory factory) {
        this.theme = factory.createUITheme();
        this.fileSystem = factory.createFileSystem();
    }

    public void initializeSystem() {
        theme.applyTheme();
        fileSystem.formatDrive();
        System.out.println("Operating System environment is fully ready!\n");
    }
}

// ==========================================
// 5. MAIN METHOD
// ==========================================
public class main {
    public static void main(String[] args) {
        // Setup a Windows Environment
        System.out.println("--- Setting up Windows ---");
        OSAbstractFactory winFactory = new WindowsFactory();
        OSClient windowsEnv = new OSClient(winFactory);
        windowsEnv.initializeSystem();

        // Setup a macOS Environment
        System.out.println("--- Setting up macOS ---");
        OSAbstractFactory macFactory = new MacFactory();
        OSClient macEnv = new OSClient(macFactory);
        macEnv.initializeSystem();

        // Setup a Linux Environment
        System.out.println("--- Setting up Linux ---");
        OSAbstractFactory linuxFactory = new LinuxFactory();
        OSClient linuxEnv = new OSClient(linuxFactory);
        linuxEnv.initializeSystem();
    }
}

