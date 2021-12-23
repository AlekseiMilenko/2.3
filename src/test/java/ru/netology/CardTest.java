package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardTest {

    @Test
    void shouldTestCardDeliveryReorder() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] input").click();
        $("[data-test-id='date'] input").setValue(DataGenerator.generateDate(3));
        $("[data-test-id='name'] input").setValue(DataGenerator.generateName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.generatePhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Встреча успешно запланирована на")).shouldBe(Condition.visible, Duration.ofSeconds(15));

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataGenerator.generateDate(4));

        $$("button").find(exactText("Перепланировать")).shouldBe(Condition.visible).click();

//        $$("button").find(exactText("Перепланировать")).click();
        
        $(withText("Встреча упешно запланирована на")).shouldBe(Condition.visible);
    }
}
