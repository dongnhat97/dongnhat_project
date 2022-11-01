package com.java.common.mail;

import com.java.common.entity.VerifyMail;
import com.java.common.repository.UserRepository;
import com.java.common.repository.VerifyMailRepository;
import com.java.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MailService {
    @Autowired
    private VerifyMailRepository verifyMailRepository;
    @Autowired
    private UserRepository userRepository;

    public void confirmMail(String token) throws NotFoundException {
        Optional<VerifyMail> verifyMailOptional = verifyMailRepository.findByToken(token);
        if (!verifyMailOptional.isPresent()) {
            throw new NotFoundException();
        }
        LocalDateTime expireAt = verifyMailOptional.get().getExpireAt();
        if (expireAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Expire verify token");
        }
        expireAt = LocalDateTime.now();
        verifyMailOptional.get().setConfirmAt(expireAt);
        userRepository.enableActiveAccount(verifyMailOptional.get().getEmail());

    }

}
