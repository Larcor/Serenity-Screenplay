package co.com.screenplay.project.tasks;

import co.com.screenplay.project.ui.DatePickerPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;

import java.time.LocalDate;
import java.time.YearMonth;

import static co.com.screenplay.project.ui.DatePickerPage.IFRAME_DATE;
import static co.com.screenplay.project.ui.DatePickerPage.TXT_DATE;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class SelectDate implements Task {

    private final LocalDate targetDate;

    private SelectDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public static Performable on(LocalDate date) {
        return new SelectDate(date);
    }

    public static Performable on(int year, int month1Based, int day) {
        return on(LocalDate.of(year, month1Based, day));
    }

    @Override
    public <T extends Actor> void performAs(T actor)
    {
        actor.attemptsTo(
                WaitUntil.the(DatePickerPage.IFRAME_DATE, WebElementStateMatchers.isPresent()).forNoMoreThan(10).seconds(),
                Switch.toFrame(DatePickerPage.IFRAME_DATE.resolveFor(theActorInTheSpotlight()))
        );

        actor.attemptsTo(
                WaitUntil.the(DatePickerPage.INPUT_DATE, WebElementStateMatchers.isClickable()).forNoMoreThan(10).seconds(),
                Click.on(DatePickerPage.INPUT_DATE),
                WaitUntil.the(DatePickerPage.ANY_DAY_CELL, WebElementStateMatchers.isPresent()).forNoMoreThan(10).seconds()
        );

        YearMonth targetYM = YearMonth.of(targetDate.getYear(), targetDate.getMonthValue());
        while (true) {
            actor.attemptsTo(WaitUntil.the(DatePickerPage.ANY_DAY_CELL, WebElementStateMatchers.isPresent()).forNoMoreThan(5).seconds());
            String m0 = DatePickerPage.ANY_DAY_CELL.resolveFor(actor).getAttribute("data-month");
            String y  = DatePickerPage.ANY_DAY_CELL.resolveFor(actor).getAttribute("data-year");
            YearMonth visibleYM = YearMonth.of(Integer.parseInt(y), Integer.parseInt(m0) + 1);

            if (visibleYM.equals(targetYM)) break;
            if (visibleYM.isBefore(targetYM)) {
                actor.attemptsTo(Click.on(DatePickerPage.NEXT_BTN));
            } else {
                actor.attemptsTo(Click.on(DatePickerPage.PREV_BTN));
            }
        }

        int month0 = targetDate.getMonthValue() - 1;
        actor.attemptsTo(
                WaitUntil.the(DatePickerPage.DAY.of(
                        String.valueOf(targetDate.getYear()),
                        String.valueOf(month0),
                        String.valueOf(targetDate.getDayOfMonth())
                ), WebElementStateMatchers.isClickable()).forNoMoreThan(10).seconds(),
                Click.on(DatePickerPage.DAY.of(
                        String.valueOf(targetDate.getYear()),
                        String.valueOf(month0),
                        String.valueOf(targetDate.getDayOfMonth())
                )),
                Switch.toDefaultContext()
        );
    }
}