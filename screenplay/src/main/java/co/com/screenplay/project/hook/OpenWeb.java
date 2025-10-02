package co.com.screenplay.project.hook;

import net.serenitybdd.screenplay.Performable;
import net.thucydides.model.util.EnvironmentVariables;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;


import static co.com.screenplay.project.utils.Constants.WEB_URL;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenWeb implements Task {

    private EnvironmentVariables environmentVariables;

    /*public OpenWeb() {
        this.environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    }*/

    @Override
    @Step("{0} abre el navegador y navega a la URL configurada")
    public <T extends Actor> void performAs(T actor) {
        environmentVariables = SystemEnvironmentVariables.currentEnvironmentVariables();
        String pathWebUrl = environmentVariables.getProperty(WEB_URL);
        actor.attemptsTo(Open.url(pathWebUrl));
    }

    public static Performable browserURL() {
        return instrumented(OpenWeb.class);
    }
}
