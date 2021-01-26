package test;

import page.MainPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class SearchTests extends CommonConditions {

    @Test
    public void canFindSomeElements()
    {
        String expectedSearchTitle = "ПОКАЗАТЬ РЕЗУЛЬТАТЫ ДЛЯ:";

        String searchTitle = new MainPage(driver)
                .openPage()
                .search("помада")
                .getSearchTitle();

        assertThat(searchTitle, equalTo(expectedSearchTitle));
    }

    @Test
    public void canFindNoOneElement()
    {
        String expectedSearchTitle = "НЕТ РЕЗУЛЬТАТОВ ПО ЗАПРОСУ:";

        String searchTitle = new MainPage(driver)
                .openPage()
                .search("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")
                .getBadSearchTitle();


        assertThat(searchTitle, equalTo(expectedSearchTitle));
    }
}
