package co.com.screenplay.project.stepdefinitions;

/*import co.com.screenplay.project.questions.ValidateElementsTextBtn;
import co.com.screenplay.project.tasks.ChooseSubElementsRandomTask;
import co.com.screenplay.project.tasks.FuntionsElementsTask;*/
import co.com.screenplay.project.questions.DateInput;
import co.com.screenplay.project.tasks.SelectDate;
import co.com.screenplay.project.tasks.TypeDate;
import co.com.screenplay.project.ui.DatePickerPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*import static co.com.screenplay.project.utils.Constants.ACTOR;
import static co.com.screenplay.project.utils.Constants.REMEMBER_TEXT_BTN_SUB_ELEMENTS;
import static co.com.screenplay.project.utils.DataFaker.fakerNumberOneAndNine;*/
import java.time.Duration;

import static co.com.screenplay.project.ui.DatePickerPage.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static co.com.screenplay.project.utils.Waits.waiting;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class OpenWebStepDefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("Selecciona la fecha {int}-{int}-{int}")
    public void seleccionaLaFecha(int year, int month, int day) {
        theActorInTheSpotlight().attemptsTo(
                SelectDate.on(year, month, day)
        );
        waiting(5);



    }

    @Then("En el campo aparece la fecha {int}-{int}-{int}")
    public void enElCampoApareceLaFecha(int year, int month, int day) {
        var expected = java.time.LocalDate.of(year, month, day);

        theActorInTheSpotlight().attemptsTo(
                Switch.toFrame(DatePickerPage.IFRAME_DATE.resolveFor(theActorInTheSpotlight()))
        );

        theActorInTheSpotlight().should(
                seeThat(DateInput.from(DatePickerPage.INPUT_DATE), equalTo(expected))
        );

        theActorInTheSpotlight().attemptsTo(Switch.toDefaultContext());
    }

    @When("Ingresa la fecha {int}-{int}-{int} en el campo")
    public void ingresaLaFecha(int y, int m, int d) {
        theActorInTheSpotlight().attemptsTo(
                TypeDate.with(java.time.LocalDate.of(y, m, d))
        );
    }

}