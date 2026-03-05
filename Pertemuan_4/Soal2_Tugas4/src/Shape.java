//*****************************************
// Shape.java
//
// Represents a shape.
//*****************************************

public abstract class Shape {
    protected String shapeName; // Atribut nama bangun ruang

    public Shape(String name) {
        this.shapeName = name;
    }

    public abstract double area(); // Method abstrak untuk menghitung luas

    @Override
    public String toString() {
        return shapeName;
    }
}