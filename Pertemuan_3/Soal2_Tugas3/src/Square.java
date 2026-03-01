public class Square extends Rectangle {

    // Konstruktor memanggil super(side, side) milik Rectangle
    public Square() {
        super(1.0, 1.0);
    }

    public Square(double side) {
        super(side, side);
    }

    public Square(double side, String color, boolean filled) {
        super(side, side, color, filled);
    }

    public double getSide() {
        return getWidth(); // atau bisa juga getLength() karena nilainya sama
    }

    public void setSide(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    // Overriding setter dari Rectangle untuk menjaga geometri persegi
    @Override
    public void setWidth(double side) {
        this.setSide(side);
    }

    @Override
    public void setLength(double side) {
        this.setSide(side);
    }

    @Override
    public String toString() {
        return "A Square with side=" + getSide() + ", which is a subclass of " + super.toString();
    }
}