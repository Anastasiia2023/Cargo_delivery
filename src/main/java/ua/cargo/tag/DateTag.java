package ua.cargo.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateTag extends SimpleTagSupport {
    private String terms;

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public void doTag()  throws IOException {
        if (terms != null) {
            JspWriter out = getJspContext().getOut();
            LocalDate date = LocalDate.now(ZoneId.of("Europe/Paris"));
            out.println( date.plusDays(Integer.parseInt(terms)));
        }
    }
}
