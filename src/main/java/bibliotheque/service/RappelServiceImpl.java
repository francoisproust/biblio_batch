package bibliotheque.service;


import bibliotheque.modele.Email;
import bibliotheque.proxies.BibliothequeProxy;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
        List<Email> listeEmail = bibliothequeProxy.listerMail();
        for(int i=0;i<listeEmail.size(); i++){
            envoyerMailAUsager(listeEmail.get(i));
        }
    }

    private void envoyerMailAUsager(Email email){
        System.out.println(email.getEmail());
        SimpleMailMessage simpleMailMessage = creerEmail(email);
        javaMailSender.send(simpleMailMessage);
    }

    private SimpleMailMessage creerEmail(Email adresseMail){
        StringBuilder body = new StringBuilder("Cher Membre,\r\nLa date de retour d'emprunt(s) a été dépassée(s).\r\n");
        body.append("\r\nLe(s) livre(s) concerné(s): \r\n");
        body.append("\r\nMerci de le(s) rapporter dès que possible.\r\nD'avance merci.\r\nLe gestionnaire de BILIOC");
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("gestionnaire@biblioc.fr");
        email.setTo(adresseMail.getEmail());
        email.setSubject("[BILIOC] - Retard de retour d'emprunt");
        email.setText(body.toString());

        return email;
    }
}
