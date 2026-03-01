public class Circle { // Save as "Circle.java"
    // private instance variable
    private double radius;
    private String color; // Modification: add color color attribute

    // 1st (default) constructor
    public Circle() {
        radius = 1.0;
        color = "red";
    }

    // 2nd constructor
    public Circle(double r) {
        radius = r;
        color = "red";
    }

    // Modification: 3rd constructor using parameter radius dan color
    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    // Getter for radius
    public double getRadius() {
        return radius;
    }

    // Modification: Getter for color
    public String getColor() {
        return color;
    }

    // Modification: Setter for color
    public void setColor(String color) {
        this.color = color;
    }

    // Returns the area of this Circle instance
    public double getArea() {
        return radius * radius * Math.PI;
    }

    // Return a self-descriptive string of this instance
    public String toString() {
        return "Circle[radius=" + radius + ",color=" + color + "]";
    }
}