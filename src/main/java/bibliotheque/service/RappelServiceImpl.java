package bibliotheque.service;


import bibliotheque.proxies.BibliothequeProxy;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Service("RappelService")
public class RappelServiceImpl implements RappelService{
    private final BibliothequeProxy bibliothequeProxy;
    private final JavaMailSender javaMailSender;

    @Autowired
    public RappelServiceImpl(BibliothequeProxy bibliocapiProxy, JavaMailSender javaMailSender) {
        this.bibliothequeProxy = bibliocapiProxy;
        this.javaMailSender = javaMailSender;
    }
    @Override
    public void envoyerRappelMails() {
        List listeEmail =  new ArrayList<>();
        listeEmail = bibliothequeProxy.listerMail();
        Enumeration enumeration = Collections.enumeration(listeEmail);
        while (enumeration.hasMoreElements()){
            System.out.println("boucle dans le retour de la liste");
            envoyerMailAUsager((String) enumeration.nextElement());
        }

    }

    private void envoyerMailAUsager(String email){
        System.out.println(email);
        SimpleMailMessage simpleMailMessage = creerEmail(email);
        javaMailSender.send(simpleMailMessage);
    }

    private SimpleMailMessage creerEmail(String adresseMail){
        System.out.println("création du mail a envoyer");
        StringBuilder body = new StringBuilder("Cher Membre,\r\nLa date de retour d'emprunt(s) a été dépassée(s).\r\n");
        body.append("\r\nLe(s) livre(s) concerné(s): \r\n");
        body.append("\r\nMerci de le(s) rapporter dès que possible.\r\nD'avance merci.\r\nLe gestionnaire de BILIOC");
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("gestionnaire@biblioc.fr");
        email.setTo(adresseMail);
        email.setSubject("[BILIOC] - Retard de retour d'emprunt");
        email.setText(body.toString());

        return email;
    }
}
