public class Cylinder extends Circle { // Cylinder inherits Circle
    private double height;

    // Constructor default
    public Cylinder() {
        super(); // call default constructor from Circle
        height = 1.0;
    }

    // Constructor with height
    public Cylinder(double height) {
        super();
        this.height = height;
    }

    // Constructor with radius and height
    public Cylinder(double radius, double height) {
        super(radius); // Call Circle(double r) constructor
        this.height = height;
    }

    // Constructor (radius, height, color)
    public Cylinder(double radius, double height, String color) {
        super(radius, color); // call Circle(radius, color) constructor
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // Overriding getArea() to calculate surface area of the cylinder
    @Override
    public double getArea() {
        // Surface area = (2 * pi * r * t) + (2 * surface area)
        // Call super.getArea() to get surface area (circle area)
        return (2 * Math.PI * getRadius() * height) + (2 * super.getArea());
    }

    // getVolume()
    public double getVolume() {
        // using super.getArea() to calculate circle area,
        // not the getArea() Cylinder that already becomes surface area.
        return super.getArea() * height;
    }

    // Overriding toString()
    @Override
    public String toString() {
        return "Cylinder: subclass of " + super.toString() + " height=" + height;
    }
}