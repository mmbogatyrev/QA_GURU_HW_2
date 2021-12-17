package demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByAttribute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;


public class FormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void cautionsTest() {
        // TODO собрать селектор до стиля '.form-control:invalid'
    }

    @Test
    void positiveScenario() {
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue("Велимир");
        $("#lastName").setValue("Хлебников");
        $("#userEmail").setValue("email@email.com");
        $("#genterWrapper").$(byText("Male")).click();
        //phone
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("1991");
        $(".react-datepicker__day--014").click();
        $("#subjectsInput").setValue("Some subjects").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        File pic = new File("src/test/resources/img/0.jpg");
        $("#uploadPicture").uploadFile(pic);
        $("#currentAddress").setValue("Some address");
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Merrut")).click();



//        $("#firstName").shouldHave(text("Велимир"));
//        $("#lastName").shouldHave(text("Хлебников"));
//        $("#userEmail").shouldHave(text("email@email.com"));
////        $$(By.id("genderWrapper")).filterBy(value("Male")).first().click();
//        $("#subjectsInput").shouldHave(text("Some subjects"));
////        $("#hobbies-checkbox-2").click();
//        $("#currentAddress").shouldHave(text("Some address"));


        $("#submit").click();
    }
}
