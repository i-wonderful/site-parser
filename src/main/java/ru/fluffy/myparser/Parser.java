package ru.fluffy.myparser;

import com.jaunt.HttpRequest;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.jaunt.component.Form;

import java.util.List;

public class Parser {

    private boolean log = true;
    private static final String URL = "https://internoxij.ru/";

    public void go() {

        try {
            UserAgent userAgent = new UserAgent();
            userAgent.visit(URL);

            log(userAgent);

            List<Form> forms = userAgent.doc.getForms();

            Form formPost = forms.stream()
                                 .filter(form -> form.getAction().contains("send.php"))
                                 .filter(form -> form.getMethod() == HttpRequest.TYPE_POST)
                                 .findFirst()
                                 .orElseThrow();
            log(formPost);

            formPost.apply("name", "123123");

            log(formPost);

        } catch (JauntException e) {
            System.err.println(e);
        }
    }

    private void log(UserAgent userAgent) {
        if (log) {
            System.out.println(">>> Response: " + userAgent.response);
            System.out.println(">>> Location: " + userAgent.getLocation());
            //System.out.println(">>> HTML: " +userAgent.doc.innerHTML());
        }
    }

    private void log(Form formPost) {
        if (log) {
            System.out.println(">>> Form HTML: " +formPost.element.innerHTML());
        }
    }
}
