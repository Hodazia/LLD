package DESIGN_PATTERNS.Builder;

/*
WHY WE NEED IT,
- suppose there are 50 instance variable , but a lot of them are optional, so we want to use only specified variables
for our use cases only, 
- so we can think why not have multiple constructors, but it is the same problem, what if we have 50+ instance variables
then the no. of constructors are gonna be huge,
- The object must be assembled through multiple steps, possibly in a specific order.
- TAKE A LOOK AT THE STRINGBUILDER CLASS, and see it is implemented via builder design pattern
- So we define parameters in both the product class as well as Builder class, which consumes memory and is wasteful, so 
that is a disadvantage,

DEFINITION - A creational pattern, it is a step by step object creation process, 


We are taking the example of product as Computer, 
- it has cpu, HW, RAM, cores, gpu, storage, powersupplywatts
- for the above we will also have a builder class to build it too

Singleton is a class for which we want to ensure:

Only one instance of the class exists throughout the application, and there is a globally accessible way to get that instance.

it needs 3 things
- private constructor,
- static instance,  private static Singleton instance;
- a public access method, 
public static Singleton getInstance() {
    return instance;
}

BASIC STRUCTURE
class Singleton {

    private static Singleton instance;
    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}



*/

public class Computer{
    private final String cpu;
    private final String motherboard;
    private final int ramGB;

    private  final String gpu;
    private final String storage;
    private final int powerSupplyWatts;
    private final String coolingSystem;
    private final boolean hasRGB;

    Computer(ComputerBuilder builder)
    {
        this.cpu = builder.cpu;
        this.motherboard = builder.motherboard;
        this.ramGB = builder.ramGB;
        this.gpu = builder.gpu;
        this.storage = builder.storage;
        this.powerSupplyWatts = builder.powerSupplyWatts;
        this.coolingSystem = builder.coolingSystem;
        this.hasRGB = builder.hasRGB;
    }

    // Getters only (Immutability)
    public String getCpu() { return cpu; }
    public String getMotherboard() { return motherboard; }
    public int getRamGB() { return ramGB; }
    public String getGpu() { return gpu; }
    public String getStorage() { return storage; }
    public int getPowerSupplyWatts() { return powerSupplyWatts; }
    public String getCoolingSystem() { return coolingSystem; }
    public boolean isHasRGB() { return hasRGB; }

    @Override
    public String toString() {
        return "Custom PC Specification:\n" +
               "  [Core] CPU: " + cpu + " | Motherboard: " + motherboard + " | RAM: " + ramGB + "GB\n" +
               "  [Graphics] GPU: " + (gpu != null ? gpu : "Integrated Graphics") + "\n" +
               "  [Storage] " + (storage != null ? storage : "No Drive Configured") + "\n" +
               "  [Power & Cooling] PSU: " + powerSupplyWatts + "W | Cooling: " + coolingSystem + "\n" +
               "  [Aesthetics] RGB Lighting: " + (hasRGB ? "Enabled" : "Disabled") + "\n";
    }
}

class ComputerBuilder{
    protected String cpu;
    protected String motherboard;
    protected int ramGB;
    protected String gpu;
    protected String storage;
    protected int powerSupplyWatts = 500; // Default baseline value
    protected String coolingSystem = "Stock Air Cooler"; // Default baseline value
    protected boolean hasRGB = false;

    // Constructor enforces mandatory parameters
    public ComputerBuilder(String cpu, String motherboard, int ramGB) {
        if (cpu == null || motherboard == null || ramGB <= 0) {
            throw new IllegalArgumentException("Core hardware (CPU, Motherboard, RAM) must be valid!");
        }
        this.cpu = cpu;
        this.motherboard = motherboard;
        this.ramGB = ramGB;
    }

    public ComputerBuilder setgpu(String gpu)
    {
        this.gpu = gpu;
        return this;
    }

    public ComputerBuilder setstorage(String storage)
    {
        this.storage = storage;
        return this;
    }
    public ComputerBuilder setPowerSupply(int watts)
    {
        this.powerSupplyWatts = watts;
        return this;
    }
    public ComputerBuilder setCoolingSystem(String coolingType) {
        this.coolingSystem = coolingType;
        return this;
    }

    public ComputerBuilder enableRGB() {
        this.hasRGB = true;
        return this;
    }
    public Computer build()
    {
        return new Computer(this);
    }
}



class main {
    public static void main(String[] args) {
        Computer gamingPC = new ComputerBuilder("AMD Ryzen","ASUS ROG X670E", 64).
        setgpu("NVIDIA RTY").setstorage("4TB NVMe M.2 SSD").setPowerSupply(1000).build();

        System.out.println(gamingPC);
        Computer officePC = new ComputerBuilder("Intel Core i3-13100", "MSI H610M", 8)
        .setstorage("500GB Budget SATA SSD")
        // GPU, RGB, custom fans, and peripherals are completely ignored
        .build();

        System.out.println(officePC);
        
    }
    
}
