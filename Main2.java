import java.io.IOException;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Введите текст любой песни");
        ItunesMusic itunesMusic =  new ItunesMusic();
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        try {
            itunesMusic.Playsong(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
