package com.mail.Mail;

public class App {
    public static void main(String[] args) {
        try {

            CertGenerator certGenerator = new CertGenerator();
            String htmlData = certGenerator.certGenerate();
            VelToPDF.velocityToPdf(htmlData);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}