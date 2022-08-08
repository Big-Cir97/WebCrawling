import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 *  페이지의 가격 크롤링
 */
public class WebCrawling_Price {

    // 수집 대상 URL
    private final static String URL = "https://www.inflearn.com/courses/it-programming";

    public static void main(String[] args) {
        try {
            // Connection 생성
            Connection conn = Jsoup.connect(URL);

            // HTML 파싱.
            Document document = conn.get(); // conn.post();

            // select 메서드를 통해 데이터 수집
            Elements elementsByClass = document.select("div.card-content > div.course_title");

            for (Element element : elementsByClass) {                  // --> class="price" 들만 출력
                String price = element.getAllElements().text();
                System.out.println("가격 : " + price);
                System.out.println("할인 가격 : " + salePrice(price));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String salePrice(String price) {
        String[] priceArr = price.split(" ");
        return (priceArr.length == 1) ? price : priceArr[1];
    }
}
