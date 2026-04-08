package com.fawry.common.api.service;

import com.fawry.common.model.dto.MailTemplateDTO;

public interface EmailService {
    void sendEmail(MailTemplateDTO mailTemplateDTO);
}