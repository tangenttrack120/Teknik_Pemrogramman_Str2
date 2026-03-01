public class TestCylinder { // save as "TestCylinder.java"
    public static void main(String[] args) {

        // test constructor default
        Cylinder c1 = new Cylinder();
        System.out.println("--- Cylinder 1 ---");
        System.out.println("Radius: " + c1.getRadius());
        System.out.println("Height: " + c1.getHeight());
        System.out.println("Color: " + c1.getColor());
        System.out.println("Base Area: " + (Math.PI * c1.getRadius() * c1.getRadius())); // Manual check
        System.out.println("Surface Area: " + c1.getArea());
        System.out.println("Volume: " + c1.getVolume());
        System.out.println(c1.toString());

        // test constructor (radius, height, color)
        Cylinder c2 = new Cylinder(5.0, 10.0, "blue");
        System.out.println("\n--- Cylinder 2 ---");
        System.out.println("Radius: " + c2.getRadius());
        System.out.println("Height: " + c2.getHeight());
        System.out.println("Color: " + c2.getColor());
        System.out.println("Surface Area: " + c2.getArea());
        System.out.println("Volume: " + c2.getVolume());
        System.out.println(c2.toString());
    }
}