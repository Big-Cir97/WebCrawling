import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 *  페이지의 제목 크롤링
 */
public class WebCrawling_Title {

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

            for (Element element : elementsByClass) {
                String title = element.getAllElements().text();        // --> title 의 요소들을 String 으로 반환
                System.out.println("강의 제목 : " + title);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
