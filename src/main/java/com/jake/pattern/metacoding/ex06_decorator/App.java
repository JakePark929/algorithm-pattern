package com.jake.pattern.metacoding.ex06_decorator;

import com.jake.pattern.metacoding.ex06_decorator.notification.BasicNotifier;
import com.jake.pattern.metacoding.ex06_decorator.notification.EmailNotifier;
import com.jake.pattern.metacoding.ex06_decorator.notification.Notifier;
import com.jake.pattern.metacoding.ex06_decorator.notification.SmsNotifier;

/**
 * 데코레이터 패턴 (장식하다)
 * (A) -> B(A) -> C(B(A))
 */
public class App {
    public static void main(String[] args) {
//        Notifier notifier = new BasicNotifier();
//        notifier.send();
//        System.out.println("__end");

//        Notifier emailNotifier = new EmailNotifier(new BasicNotifier());
//        emailNotifier.send();
//        System.out.println("__end");

//        Notifier smsNotifier = new SmsNotifier(new BasicNotifier());
//        smsNotifier.send();
//        System.out.println("__end");

        // 1. 전체알림(기본 -> 문자 -> 이메일)
        Notifier allNotifier = new EmailNotifier(new SmsNotifier(new BasicNotifier()));
        allNotifier.send();
        System.out.println("__end");

        // 2. 전체알림(기본 -> 이메일 -> 문자)
        Notifier allNotifier2 = new SmsNotifier(new EmailNotifier(new BasicNotifier()));
        allNotifier2.send();
        System.out.println("__end");

        // 3. 전체알림 (기본알림 -> ~~~~~~)
        Notifier allNotifier3 = new SmsNotifier(new EmailNotifier(new EmailNotifier(new BasicNotifier())));
        allNotifier3.send();
        System.out.println("__end");

        // 4. 기본알림
        Notifier basicNotifier = new BasicNotifier();
        basicNotifier.send();
        System.out.println("__end");

        // 5. 기본알림 + 문자알림
        Notifier smsNotifier = new SmsNotifier(new BasicNotifier());
        smsNotifier.send();
        System.out.println("__end");

        // 6. 기본알림 + 이메일알림
        Notifier emailNotifier = new EmailNotifier(new BasicNotifier());
        emailNotifier.send();
        System.out.println("__end");

        // 7. 이메일알림만? - default constructor 생성
        Notifier onlyEmailNotifier = new EmailNotifier();
        onlyEmailNotifier.send();
        System.out.println("__end");

        // 8. 문자알림만?
        Notifier onlySmsNotifier = new SmsNotifier();
        onlySmsNotifier.send();
        System.out.println("__end");

        // 9. 문자알림 + 이메일알림
        Notifier onlySmsAndEmailNotifier = new EmailNotifier(new SmsNotifier());
        onlySmsAndEmailNotifier.send();
        System.out.println("__end");
    }
}
