package com.example.emailTemplate.Reposistory;

import com.example.emailTemplate.Entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepo extends JpaRepository<EmailTemplate,Integer> {
}
