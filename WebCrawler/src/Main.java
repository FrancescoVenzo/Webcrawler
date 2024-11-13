import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Inserisci il sito: \n");
        String URL = in.next();
        if(!URL.contains("https://"))
            URL = "https://" + URL;

        ArrayList<String> URL_list = new ArrayList<>();
        Crawl(1, URL, URL_list);

    }

    public static void Crawl(int level, String URL, ArrayList<String> result){
        try {
            Document doc = Jsoup.connect(URL).get();

            Elements links = doc.select("a[href]");
            for(Element link : links) {
                if (level <= 3) {
                    String temp_link = link.absUrl("href");

                    if (!result.contains(link)) {
                        result.add(temp_link);
                    }
                    System.out.println("Titolo: " + doc.title());
                    System.out.println(temp_link);
                    System.out.println("Livello: " + level);
                    Crawl(level + 1, temp_link, result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
