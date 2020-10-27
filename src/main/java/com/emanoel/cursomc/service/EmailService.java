package com.emanoel.cursomc.service;

import org.springframework.mail.SimpleMailMessage;

import com.emanoel.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage msg);
}
