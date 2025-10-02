package co.com.screenplay.project.questions;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.targets.Target;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateInput implements Question<LocalDate> {

    private final Target input;
    private final DateTimeFormatter fmt;

    private DateInput(Target input, DateTimeFormatter fmt) {
        this.input = input;
        this.fmt = fmt;
    }

    public static DateInput from(Target input) {
        return new DateInput(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public static DateInput from(Target input, DateTimeFormatter fmt) {
        return new DateInput(input, fmt);
    }

    @Override
    public LocalDate answeredBy(Actor actor) {
        String raw = Value.of(input).answeredBy(actor); // lee el atributo 'value'
        return LocalDate.parse(raw, fmt);
    }
}