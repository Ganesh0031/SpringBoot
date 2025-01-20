package com.example.emailTemplate.Service;

import com.example.emailTemplate.Entity.EmailTemplate;
import com.example.emailTemplate.Reposistory.EmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class EmailTemplateServices {
    @Autowired
    private EmailRepo emailRepo;
    public void saveEntry(EmailTemplate emailTemplate){
        emailRepo.save(emailTemplate);
    }
    public List<EmailTemplate> getAll(){
        return emailRepo.findAll();
    }

}
