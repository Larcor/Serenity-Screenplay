package co.com.screenplay.project.stepdefinitions.hook;

import co.com.screenplay.project.hook.OpenWeb;
import co.com.screenplay.project.utils.Waits;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import org.hamcrest.Matchers;
import org.junit.Before;

import static co.com.screenplay.project.utils.Waits.waiting;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Hook {

    @Before
    public void setTheStage(){
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{string} abre el navegador")
    public void abreElNavegador(String actor) {
        OnStage.theActorCalled(actor).attemptsTo(
                OpenWeb.browserURL()
        );

        waiting(5);
        theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        TheWebPage.title(), Matchers.containsString("Datepicker | jQuery UI")
                )
        );

    }
}
