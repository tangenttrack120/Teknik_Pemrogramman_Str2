public class TestShape {
    public static void main(String[] args) {
        System.out.println("--- Menguji Class Shape ---");
        Shape s1 = new Shape();
        System.out.println(s1.toString());

        System.out.println("\n--- Menguji Class Circle ---");
        Circle c1 = new Circle(5.5, "blue", false);
        System.out.println(c1.toString());
        System.out.println("Area: " + c1.getArea());
        System.out.println("Perimeter: " + c1.getPerimeter());

        System.out.println("\n--- Menguji Class Square ---");
        Square sq1 = new Square(4.0, "yellow", true);
        System.out.println(sq1.toString());
        System.out.println("Area: " + sq1.getArea());
        System.out.println("Perimeter: " + sq1.getPerimeter());

        // Menguji mekanisme overriding geometri persegi
        sq1.setWidth(8.0);
        System.out.println("Setelah setWidth(8.0): " + sq1.toString());
    }
}