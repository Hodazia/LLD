package DESIGN_PATTERNS.Factory;

/*
An interface OperatingSystem is defined it is the product,
- 3 concrete products are created which as windowsOs,MacOS, LinuxOS
- we have an interface factory = OSFactory whose implementation will create 3 products



*/
interface OperatingSystem{
    void boot();
}

class WindowsOS implements OperatingSystem {
    @Override 
    public void boot()
    {
        System.out.println("This is the windows OS");
    }
}

class MacOS implements OperatingSystem {
    @Override 
    public void boot()
    {
        System.out.println("This is the MAC OS");
    }
}

class LinuxOS implements OperatingSystem {
    @Override 
    public void boot()
    {
        System.out.println("This is the LINUX OS");
    }
}

interface OSFactory { 
    public OperatingSystem createOS();
}

class WindowsFactory implements  OSFactory {
    @Override 
    public OperatingSystem createOS()
    {
        return new WindowsOS();
    }
}

class MACFactory implements  OSFactory {
    @Override 
    public OperatingSystem createOS()
    {
        return new MacOS();
    }
}

class LinuxFactory implements  OSFactory {
    @Override 
    public OperatingSystem createOS()
    {
        return new LinuxOS();
    }
}



public class main {

    public static void main(String args[])
    {
        OSFactory windowsFactory = new WindowsFactory();
        OperatingSystem winOS = windowsFactory.createOS();
        winOS.boot();
    
        // Create macOS using Mac Factory
        OSFactory macFactory = new MACFactory();
        OperatingSystem macOS = macFactory.createOS();
        macOS.boot();
    
        // Create Linux using Linux Factory
        OSFactory linuxFactory = new LinuxFactory();
        OperatingSystem linuxOS = linuxFactory.createOS();
        linuxOS.boot();
    }

}
