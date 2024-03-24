package com.b1lbudinhox.wykopclone.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificaitonMail {
    private String subject;
    private String recipient;
    private String username;
    private String mailBody;
}
