package com.example.emailTemplate.Controller;

import com.example.emailTemplate.Entity.EmailTemplate;
import com.example.emailTemplate.Service.EmailService;
import com.example.emailTemplate.Service.EmailTemplateServices;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/setTemplate")
public class EmailController {

    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    EmailTemplateServices emailTemplateServices;
   @Autowired
    EmailService emailService;
@GetMapping
public List<EmailTemplate> getAll(){
    return emailTemplateServices.getAll();
}

    @PostMapping("/register")
    public ResponseEntity<String> createEntry(@RequestBody EmailTemplate emailTemplate) {
        try {
            if (!isValidEmail(emailTemplate.getEmail())) {
                return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
            }


            Template template = freemarkerConfig.getTemplate("EmailTemplate.html");
            Map<String, Object> model = new HashMap<>();
            model.put("name", emailTemplate.getName());
            model.put("order_number", emailTemplate.getOrder_number());
            String emailBody = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);


            emailService.sendEmail(emailTemplate.getEmail(), emailTemplate.getSubject(), emailBody);

            return new ResponseEntity<>("Email sent successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to send mail", HttpStatus.BAD_REQUEST);
        }
    }


    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

}
