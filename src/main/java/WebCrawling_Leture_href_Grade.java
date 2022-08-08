import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;

/**
 *  페이지의 강의 링크, 평점
 */
public class WebCrawling_Leture_href_Grade {

    // 수집 대상 URL
    private final static String URL = "https://www.inflearn.com/courses/it-programming";

    public static void main(String[] args) {
        try {
            // 개발 강의 모든 페이징 순회
            //for (int i = FIRST_PAGE_INDEX; i <= LAST_PAGE_INDEX; i++) {
            //    final String inflearnUrl = URL + "?order=seq&page=" + i;


            // Connection 생성
            Connection conn = Jsoup.connect(URL);

            // HTML 파싱.
            Document document = conn.get(); // conn.post();

            // select 메서드를 통해 데이터 수집
            Elements elementsByClass = document.select("a.course_card_front");

            for (Element element : elementsByClass) {
                String hrefUrl = element.getAllElements().attr("abs:href");
                System.out.println(hrefUrl);

                // 평점은 강의 내부로 한번 더 들어가야 함
                Connection innerConn = Jsoup.connect(hrefUrl);
                Document innerDocument = innerConn.get();

                // select 메서드를 통해 데이터 수집, 복수형 데이터가 아니므로 Element
                Element element1 = innerDocument.selectFirst("div.dashboard-star__num");
                float rating = Objects.isNull(element1) ? Float.parseFloat("0") : Float.parseFloat(element1.text());
                System.out.println("평점 : " + rating);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
