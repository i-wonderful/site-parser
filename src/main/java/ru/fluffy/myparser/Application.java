package ru.fluffy.myparser;

import com.jaunt.Element;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.jaunt.component.Form;

public class Application {
    public static void main(String[] args) {

        //exampleGoogle();
        //example15();

        new Parser().go();

    }

    /**
     * Example from https://patrick-meier.io/web-scraping-jaunt-vs-jsoup/#:~:text=To%20select%20elements%20Jsoup%20uses,Jaunt%20has%20it%27s%20own%20syntax.&text=Jaunt%20does%20not%20to%20support,something%20else%20for%20querying%20JSON.
     */
    private static void exampleGoogle() {
        try {
            final UserAgent userAgent = new UserAgent();
            userAgent.visit("http://google.com");
            userAgent.doc.apply("apple");
            userAgent.doc.submit();

            for (Element link : userAgent.doc.findEvery("<h3>").findEvery("<a>")) {
                System.out.println(link.getAt("href"));
            }
        } catch (JauntException e) {
            System.err.println(e);
        }
    }

    /**
     * Example from tutorial
     * https://jaunt-api.com/jaunt-tutorial.htm
     */
    private static void example15() {
        try {
            UserAgent userAgent = new UserAgent();
            userAgent.visit("http://jaunt-api.com/examples/signup2.htm");

            Form form = userAgent.doc.getForm(0);       //get the document's first Form
            form.setTextField("email", "tom@mail.com"); //or form.set("email", "tom@mail.com");
            form.setPassword("pw", "secret");           //or form.set("pw", "secret");
            form.setCheckBox("remember", true);         //or form.set("remember", "on");
            form.setSelect("account", "advanced");      //or form.set("account", "advanced");
            form.setTextArea("comment", "no comment");  //or form.set("comment", "no comment");
            form.setRadio("inform", "no");              //or form.set("inform", "no");
            form.submit("create trial account");        //click the submit button labelled 'create trial account'
            System.out.println(userAgent.getLocation());//print the current location (url)
        } catch (JauntException e) {
            System.err.println(e);
        }
    }
}
