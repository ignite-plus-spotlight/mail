package com.mail.Mail;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class CertGenerator {

    public String certGenerate() throws Exception {

        VelocityEngine ve = new VelocityEngine();
        ve.init();

        VelocityContext context = new VelocityContext();
        context.put("name", "Rithika");
        context.put("description", "for being someone we can always count on! I'm so grateful for all of your hard work. " +
                "The entire team thanks you for everything you do. It's rare to come across people who are so dedicated and trustworthy");


        Template t = ve.getTemplate("src/main/resources/certificate.vm");

        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }

}