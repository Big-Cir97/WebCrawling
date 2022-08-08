import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 *  페이지의 이미지 크롤링
 *
 *    1) Attribute 탐색
 *    - Element의 id, class, tag 등과 같은 속성값을 이용하여 요소를 탐색함.
 *    - getElementById, getElementsByClass, getElementsByTagName 등이 있음.
 *    - 한번의 하나의 속성값만 이용하여 탐색할 수 있기 때문에 깊이가 깊어질수록 복잡해짐.
 *
 *    2) CSS Selector 탐색
 *    - HTML에 스타일을 적용할 때 사용되는 CSS는 요소를 탐색하기위해 CSS Selector를 사용함.
 *    - 이 CSS Selector를 이용하여 Element를 탐색하는 기능.
 *    - select, not, next 등 많은 함수들에서 사용됨.
 *    - Attribute 탐색보다 자주사용되는 기능.
 */
public class WebCrawling_Images {

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

            // 복수형 데이터 이므로 Elements
            // getElementsByClassName() 은 컬렉션 객체를 반환한다.이 함수는 태그의 class="" 속성을 사용하여 접근한다.
            Elements elementsByClass = document.getElementsByClass("swiper-lazy");

            // HTML 출력
            // System.out.println(document.toString());                -->  수집 대상의 html 출력
            for (Element element : elementsByClass) {                  // --> class="swiper-lazy" 들만 출력
                // System.out.println(element);                        --> 이미지만 출력
                System.out.println(element.attr("src"));    // 불필요한 내용 제거, attr 메서드를 통해 src 부분만 가져올 수 있도록 가공
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
