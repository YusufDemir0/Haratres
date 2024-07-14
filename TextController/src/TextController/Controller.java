package TextController;

import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);
    static String sentence = "";
    static int maxLength = 0;
    static boolean caseSensitive = false; // Büyük/küçük harf duyarlılığı

    public static void main(String[] args) {
        boolean validInput = false;
        while (!validInput) {
            try {
                // 1. Karakter sayısını iste
                System.out.println("Maksimum karakter sayısını giriniz:");
                maxLength = scanner.nextInt();
                scanner.nextLine(); // Buffer temizleme

                if (maxLength <= 0) {
                    System.out.println("Geçerli bir sayı giriniz (0'dan büyük bir sayı).");
                } else {
                    validInput = true; // Geçerli giriş
                }
            } catch (Exception e) {
                System.out.println("Geçerli bir sayı giriniz.");
                scanner.nextLine(); // Buffer temizleme
            }
        }

        // Sonraki adımları başlat
        soru1();
    }

    public static void soru1() {
        // 2. Kullanıcıdan cümle iste
        System.out.println("Bir cümle giriniz:");
        sentence = scanner.nextLine();

        // 3. Karakter sayısını kontrol et
        if (sentence.length() > maxLength) {
            System.out.println("Girilen cümle maksimum karakter sayısını aşıyor! Lütfen tekrar giriniz.");
            soru1();  // Adımı tekrar çalıştır
        } else {
            // Sonraki adıma geç
            soru2();
        }
    }

    public static void soru2() {
        // 4. Büyük/küçük harf analizi sor
        System.out.println("Büyük/küçük harf duyarlılığı aktif olsun mu? (evet/hayır)");
        String analyzeChoice = scanner.nextLine();

        if (analyzeChoice.equalsIgnoreCase("evet")) {
            caseSensitive = true;
        } else if (analyzeChoice.equalsIgnoreCase("hayır")) {
            caseSensitive = false;
        } else {
            System.out.println("Geçersiz cevap! Lütfen 'evet' veya 'hayır' yazınız.");
            soru2();  // Adımı tekrar çalıştır
        }

        // Sonraki adıma geç
        soru3();
    }

    public static void soru3() {
        // 5. Harf iste
        System.out.println("Bir harf giriniz:");
        char character = scanner.next().charAt(0);
        scanner.nextLine(); // Buffer temizleme

        if (!Character.isLetter(character)) {
            System.out.println("Geçersiz harf! Lütfen tekrar giriniz.");
            soru3();  // Adımı tekrar çalıştır
        } else {
            int charCount = 0;
            for (char c : sentence.toCharArray()) {
                if (caseSensitive) {
                    if (c == character) {
                        charCount++;
                    }
                } else {
                    if (Character.toLowerCase(c) == Character.toLowerCase(character)) {
                        charCount++;
                    }
                }
            }

            System.out.println("'" + character + "' harfi sayısı: " + charCount);
        }
    }
}
