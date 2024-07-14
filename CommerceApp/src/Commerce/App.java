/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 *//**
 *
 * @author ASUS
 */
package Commerce;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class App {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int size = 0;
        while (true) {
            System.out.print("Kaç farklı ürün gireceksiniz: ");
            size = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer
            if (size > 0) {
                break;
            } else {
                System.out.println("Ürün miktarı en az 1 olmalıdır.");
            }
        }

        ArrayList<String[]> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            System.out.println("Ürün " + (i + 1) + ":");

            String name;
            while (true) {
                System.out.print("Ürün Adı: ");
                name = scanner.nextLine();
                if (name.length() <= 20) {
                    break;
                } else {
                    System.out.println("Ürün adı en fazla 20 karakter olmalıdır.");
                }
            }

            String price;
            while (true) {
                System.out.print("Birim Fiyat: ");
                price = scanner.nextLine();
                float priceFloat = Float.parseFloat(price);
                if (priceFloat >= 1) {
                    break;
                } else {
                    System.out.println("Ürün birim fiyatı 1'den büyük olmalıdır.");
                }
            }

            String stock;
            while (true) {
                System.out.print("Stok Miktarı: ");
                stock = scanner.nextLine();
                if (Integer.parseInt(stock) >= 1) {
                    break;
                } else {
                    System.out.println("Ürün stok miktarı en az 1 olmalıdır.");
                }
            }

            String rate;
            while (true) {
                System.out.print("Değerlendirme Puanı (1.0 - 5.0): ");
                rate = scanner.nextLine();
                float rateFloat = Float.parseFloat(rate);
                if (rateFloat >= 1 && rateFloat <= 5) {
                    break;
                } else {
                    System.out.println("Ürün puanı 1.0 ile 5.0 arasında olmalıdır.");
                }
            }

            String[] item = {name, price, stock, rate};
            items.add(item);
        }

        System.out.print("Ürünleri hangi kritere göre sıralamak istersiniz? (name/stock/rating): ");
        String list = scanner.nextLine();

        System.out.print("Sıralama türü artan mı azalan mı olsun? (artan/azalan): ");
        String sortOrder = scanner.nextLine();

        Comparator<String[]> comparator = switch (list) {
            case "name" ->
                Comparator.comparing(o -> o[0]);
            case "stock" ->
                Comparator.comparingInt(o -> Integer.parseInt(o[2]));
            case "rating" ->
                Comparator.comparingDouble(o -> Double.parseDouble(o[3]));
            default ->
                null;
        };

        if (comparator != null) {
            if (sortOrder.equalsIgnoreCase("azalan")) {
                comparator = comparator.reversed();
            }
            items.sort(comparator);
        } else {
            System.out.println("Geçersiz kriter.");
        }

        System.out.println("Sıralanmış Ürünler:");
        for (String[] item : items) {
            System.out.println(item[0] + " - Fiyat: " + item[1] + ", Stok: " + item[2] + ", Değerlendirme: " + item[3]);
        }

        ArrayList<String[]> cart = new ArrayList<>();

        while (true) {
            System.out.print("Sepete ürün eklemek ister misiniz? (Evet/Hayır): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("Evet")) {
                break;
            }

            System.out.print("Eklemek istediğiniz ürünün adı: ");
            String productName = scanner.nextLine();
            String[] selectedItem = null;

            for (String[] item : items) {
                if (item[0].equalsIgnoreCase(productName)) {
                    selectedItem = item;
                    break;
                }
            }

            if (selectedItem == null) {
                System.out.println("Ürün bulunamadı.");
                continue;
            }

            int maxQuantity = Integer.parseInt(selectedItem[2]);
            int quantity = 0;
            while (true) {
                System.out.print("Eklemek istediğiniz adet: ");
                quantity = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                if (quantity > 0 && quantity <= maxQuantity) {
                    break;
                } else {
                    System.out.println("Geçersiz miktar. Stok miktarını aşmayacak şekilde bir değer giriniz.");
                }
            }

            boolean foundInCart = false;
            for (String[] cartItem : cart) {
                if (cartItem[0].equalsIgnoreCase(selectedItem[0])) {
                    int currentQuantity = Integer.parseInt(cartItem[1]);
                    cartItem[1] = String.valueOf(currentQuantity + quantity);
                    foundInCart = true;
                    break;
                }
            }

            if (!foundInCart) {
                String[] cartItem = {selectedItem[0], String.valueOf(quantity), selectedItem[1]};
                cart.add(cartItem);
            }

            System.out.println(selectedItem[0] + " sepetinize eklendi.");
        }

        System.out.println("\nSepetiniz:");
        double totalPrice = 0;
        for (String[] cartItem : cart) {
            int index = cart.indexOf(cartItem);
            index++;
            int quantity = Integer.parseInt(cartItem[1]);
            float unitPrice = Float.parseFloat(cartItem[2]);
            if (index < cart.size() && unitPrice > Float.parseFloat((cart.get(index)[2]))) {
                unitPrice -= Float.parseFloat((cart.get(index)[2]));
            }
            float totalItemPrice = unitPrice * quantity;
            System.out.println(cartItem[0] + " - Adet: " + quantity + ", Toplam Fiyat: " + totalItemPrice);
            totalPrice += totalItemPrice;
        }

        System.out.println("\nSepet Toplamı: " + totalPrice);
    }
}
