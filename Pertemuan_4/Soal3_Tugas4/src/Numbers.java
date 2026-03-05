import java.util.Scanner;
public class Numbers {
    public static void main (String[] args) {
        Integer[] intList; // Solusi: Ubah int[] menjadi tipe referensi Integer[]
        int size;
        Scanner scan = new Scanner(System.in);
        System.out.print ("\nHow many integers do you want to sort? ");
        size = scan.nextInt();
        intList = new Integer[size]; // Inisialisasi menggunakan array Integer
        System.out.println ("\nEnter the numbers...");
        for (int i = 0; i < size; i++)
            intList[i] = scan.nextInt(); // Autoboxing terjadi di sini

        Sorting.insertionSort(intList); // Ubah pemanggilan menjadi insertionSort

        System.out.println ("\nYour numbers in sorted order...");
        for (int i = 0; i < size; i++)
            System.out.print(intList[i] + " ");
        System.out.println ();
    }
}