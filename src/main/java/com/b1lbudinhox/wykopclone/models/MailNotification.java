package com.b1lbudinhox.wykopclone.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailNotification {
    private String subject;
    private String receiver;
    private String nickname;
    private String body;
}
