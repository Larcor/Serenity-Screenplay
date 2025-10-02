package co.com.screenplay.project.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DatePickerPage extends PageObject {

    public static final Target TXT_DATE = Target.the("Campo de texto calendario").
            located(By.id("datepicker"));

    public static final Target IFRAME_DATE = Target.the("Iframe calendario").
            located(By.cssSelector("iframe.demo-frame"));

    public static final Target ANY_DAY_CELL =
            Target.the("cualquier día visible")
                    .locatedBy("#ui-datepicker-div td[data-handler='selectDay']");

    public static final Target INPUT_DATE =
            Target.the("input datepicker").locatedBy("#datepicker");

    public static final Target NEXT_BTN =
            Target.the("botón siguiente mes")
                    .locatedBy("#ui-datepicker-div .ui-datepicker-next");

    public static final Target PREV_BTN =
            Target.the("botón mes anterior")
                    .locatedBy("#ui-datepicker-div .ui-datepicker-prev");

    public static final Target DAY =
            Target.the("día específico del calendario")
                    .locatedBy("#ui-datepicker-div td[data-handler='selectDay'][data-year='{0}'][data-month='{1}'] a[data-date='{2}']");

    public static final By FRAME_DATE = By.cssSelector("iframe.demo-frame");

    public static final Target BTN_DATE_FIFTEEN = Target.the("Boton fecha quince").
            located(By.xpath("//div[@id='ui-datepicker-div']//td[@data-handler='selectDay' and @data-year='2025' and @data-month='9']//a[@data-date='15']"));





}
