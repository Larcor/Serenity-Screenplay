package co.com.screenplay.project.tasks;

import co.com.screenplay.project.ui.DatePickerPage;
import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.abilities.BrowseTheWeb.as;

public class TypeDate implements Task {
    private final LocalDate date;
    private final DateTimeFormatter fmt;

    private TypeDate(LocalDate date, DateTimeFormatter fmt) {
        this.date = date; this.fmt = fmt;
    }

    // Usa MM/dd/yyyy (jQuery UI demo)
    public static Performable with(LocalDate date) {
        return Tasks.instrumented(TypeDate.class, date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    // Si tu app usa otro formato
    public static Performable with(LocalDate date, String pattern) {
        return Tasks.instrumented(TypeDate.class, date, DateTimeFormatter.ofPattern(pattern));
    }

    @Override public <T extends Actor> void performAs(T actor) {
        String value = date.format(fmt);

        if (DatePickerPage.IFRAME_DATE != null) {
            actor.attemptsTo(
                    WaitUntil.the(DatePickerPage.IFRAME_DATE, WebElementStateMatchers.isPresent()).forNoMoreThan(10).seconds(),
                    Switch.toFrame(DatePickerPage.IFRAME_DATE.resolveFor(theActorInTheSpotlight()))
            );
        }

        actor.attemptsTo(
                WaitUntil.the(DatePickerPage.INPUT_DATE, WebElementStateMatchers.isClickable()).forNoMoreThan(10).seconds(),
                Clear.field(DatePickerPage.INPUT_DATE),
                Enter.theValue(value).into(DatePickerPage.INPUT_DATE),
                Hit.the(org.openqa.selenium.Keys.TAB).into(DatePickerPage.INPUT_DATE)
        );

        actor.attemptsTo(Switch.toDefaultContext());
    }
}