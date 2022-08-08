import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 *  페이지의 강의자, 부가설명, 기술스택
 */
public class WebCrawling_Instructor_Description_Skills {

    // 수집 대상 URL
    private final static String URL = "https://www.inflearn.com/courses/it-programming";

    public static void main(String[] args) {
        try {
            // Connection 생성
            Connection conn = Jsoup.connect(URL);

            // HTML 파싱.
            Document document = conn.get(); // conn.post();

            // select 메서드를 통해 데이터 수집
            Elements instructorElements = document.getElementsByClass("instructor");
            Elements descriptionElements = document.select("p.course_description");
            Elements skillElements = document.select("div.course_skills > span");

            for (int i = 0; i < instructorElements.size(); i++) {
                final String instructor = instructorElements.get(i).text();
                final String description = descriptionElements.get(i).text();
                final String skills = skillElements.get(i).text();

                System.out.println("강의자: " + instructor);
                System.out.println("강의 부가설명: " + description);
                System.out.println("기술 스택: " + skills);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
