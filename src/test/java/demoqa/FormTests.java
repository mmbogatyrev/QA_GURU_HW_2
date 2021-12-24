package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class FormTests {

    String site = "https://demoqa.com/automation-practice-form";
    String userName = "someName";
    String lastName = "someLastName";
    String userEmail = "some@e.mail";
    String sex = "Male";
    String somePhone = "9111122022";
    String currentAddress = "Some address";
    // date of birth
    String month = "February";
    String year = "1991";
    // If number < 10 => fisrt sign is '0' ~ "01"
    String dayNumber = "14";
    String subjects = "Maths";
    String hobbies = "Sports";
    String pathToFile = "src/test/resources/img/";
    String fileName = "0.jpg";
    String state = "Uttar Pradesh";
    String city = "Merrut";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

//    @Test
//    void cautionsTest() {
//        // TODO собрать селектор до стиля '.form-control:invalid'
//    }

    @Test
    void positiveScenario() {
        open(site);
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(sex)).click();
        $("#userNumber").setValue(somePhone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + dayNumber).click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        File pic = new File(pathToFile + fileName);
        $("#uploadPicture").uploadFile(pic);
        $("#currentAddress").setValue(currentAddress);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();

        $(byClassName("modal-content")).shouldBe(visible);
        $$("table tbody tr").shouldHave(size(10));

        $("table tbody").find((byText("Student Name"))).sibling(0).shouldHave(text(userName + " " + lastName));
        $("table tbody").find((byText("Student Email"))).sibling(0).shouldHave(text(userEmail));
        $("table tbody").find((byText("Gender"))).sibling(0).shouldHave(text(sex));
        $("table tbody").find((byText("Mobile"))).sibling(0).shouldHave(text(somePhone));
        $("table tbody").find((byText("Date of Birth"))).sibling(0).shouldHave(text(dayNumber + " " + month + "," + year));
        $("table tbody").find((byText("Subjects"))).sibling(0).shouldHave(text(subjects));
        $("table tbody").find((byText("Hobbies"))).sibling(0).shouldHave(text(hobbies));
        $("table tbody").find((byText("Picture"))).sibling(0).shouldHave(text(fileName));
        $("table tbody").find((byText("Address"))).sibling(0).shouldHave(text(currentAddress));
        $("table tbody").find((byText("State and City"))).sibling(0).shouldHave(text(state + " " + city));

        $("#closeLargeModal").click();
        $(byClassName("modal-content")).shouldBe(disappear);
    }

}
