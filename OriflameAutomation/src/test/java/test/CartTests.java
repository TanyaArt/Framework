package test;

import org.testng.annotations.Test;
import page.MainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CartTests extends CommonConditions {

    @Test
    public void addToCartTest(){
        int countOfElements =
                new MainPage(driver)
                .openPage()
                .search("помада")
                .clickOnTheResult(1)
                .addToCart()
                .toCart()
                .getCountElementsInCart();

        int expectedCountOfElements = 1;

        assertThat(countOfElements, equalTo(expectedCountOfElements));
    }

    @Test
    public void cartIsEmptyTest(){
        boolean expectedResult = true;
        boolean isEmpty = new MainPage(driver)
            .openPage()
            .toCart()
            .cartIsEmpty();

        assertThat(isEmpty, equalTo(expectedResult));
    }
}
