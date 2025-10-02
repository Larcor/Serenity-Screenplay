package co.com.screenplay.project.tasks;

import co.com.screenplay.project.ui.DatePickerPage;
import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class TypeDate implements Task {

    private final LocalDate date;
    private final String pattern;

    public TypeDate(LocalDate date, String pattern) {
        this.date = date;
        this.pattern = pattern;
    }

    public static Performable with(LocalDate date) {
        return Tasks.instrumented(TypeDate.class, date, "MM/dd/yyyy");
    }
    public static Performable with(LocalDate date, String pattern) {
        return Tasks.instrumented(TypeDate.class, date, pattern);
    }

    @Override public <T extends Actor> void performAs(T actor) {
        String value = date.format(DateTimeFormatter.ofPattern(pattern));
        actor.attemptsTo(
                WaitUntil.the(DatePickerPage.IFRAME_DATE, WebElementStateMatchers.isPresent()).forNoMoreThan(10).seconds(),
                Switch.toFrame(DatePickerPage.IFRAME_DATE.resolveFor(theActorInTheSpotlight())),

                WaitUntil.the(DatePickerPage.INPUT_DATE, WebElementStateMatchers.isClickable()).forNoMoreThan(10).seconds(),
                Clear.field(DatePickerPage.INPUT_DATE),
                Enter.theValue(value).into(DatePickerPage.INPUT_DATE),
                Hit.the(org.openqa.selenium.Keys.TAB).into(DatePickerPage.INPUT_DATE),

                Switch.toDefaultContext()
        );
    }
}