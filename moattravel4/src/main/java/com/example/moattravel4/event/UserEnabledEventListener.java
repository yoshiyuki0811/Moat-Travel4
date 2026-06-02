package com.example.moattravel4.event;

import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.moattravel4.Entity.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserEnabledEventListener {

	private final JavaMailSender javaMailSender;

	@EventListener
	private void onUserEnabledEvent(UserEnabledEvent userEnebledEvent) {

		User user = userEnebledEvent.getUser();

		String recipientAddress = user.getEmail();

		String subject = "【moattravel】会員登録完了のお知らせ（10%OFFクーポンプレゼント！）";

		String message = "会員登録が完了し、アカウントが有効化されました！\n" +
				"moattravelへようこそ。\n\n" +
				"感謝の気持ちを込めて、初回限定の10%OFFクーポンをプレゼントします。\n" +
				"ご予約の際に、決済画面で以下のコードをご入力ください。\n\n" +
				"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
				" クーポンコード： WELCOME10\n" +
				" 割引内容： ご宿泊料金から 10% OFF\n" +
				"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
				"※お一人様1回限りのご利用となります。\n\n" +
				"素敵な旅の思い出作りに、ぜひお役立てください。\n" +
				"皆様のご予約を心よりお待ちしております。";

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(recipientAddress);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		
		javaMailSender.send(mailMessage);
		
	}

}
