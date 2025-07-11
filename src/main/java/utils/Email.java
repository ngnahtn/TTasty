/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Date;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;

/**
 *
 * @author tung
 */
public class Email {
    // Email: ngnahtg123@gmail.com
    // password vuwv jbhf cgfp szng

    static final String from = "ngnahtg123@gmail.com";
    static final String password = "vuwv jbhf cgfp szng";

    public static int sendEmailOTP(String to) {
        // Properties : khai bao thuoc tinh
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        // phien lam viec
        Session session = Session.getInstance(props, auth);

        // tao 1 tin nhan 
        MimeMessage msg = new MimeMessage(session);

        // Tạo mã OTP ngẫu nhiên gồm 6 chữ số
        int randomSixDigits = 100000 + new Random().nextInt(900000);

        try {
            msg.addHeader("Content-type", "text/html; charset=UTF-8"); // Thiết lập định dạng HTML
            msg.setFrom(new InternetAddress(from, "T-Tasty Fruits")); // Tên người gửi hiển thị
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false)); // Địa chỉ người nhận
            msg.setSubject("🍓 Mã OTP xác thực từ T-Tasty!"); // Tiêu đề email
            msg.setSentDate(new Date()); // Ngày gửi

            // Nội dung HTML của email
            String htmlContent = "<div style='font-family:Arial, sans-serif; background:#f9f9f9; padding:40px;'>"
                    + "<div style='max-width:600px; margin:auto; background:white; padding:30px; border-radius:10px; box-shadow:0 0 10px rgba(0,0,0,0.05);'>"
                    + "<div style='text-align:center;'>"
                    + "<img src='https://img.redocn.com/sheji/20240414/yourencaofeijiandeniunaigaoqingtu_13282921.jpg.400.jpg' alt='T-Tasty Logo' style='width:300px; margin-bottom:20px;'/>"
                    + "</div>"
                    + "<h2 style='color:#E91E63; text-align:center;'>Xin chào từ T-Tasty Fruits! 🍇🍊</h2>"
                    + "<p style='font-size:16px; color:#555;'>Mã OTP của bạn để xác thực email là:</p>"
                    + "<div style='font-size:32px; font-weight:bold; color:#4CAF50; text-align:center; margin:20px 0;'>"
                    + randomSixDigits + "</div>"
                    + "<p style='font-size:14px; color:#999;'>Lưu ý: Mã này có hiệu lực trong <strong>5 phút</strong>.</p>"
                    + "<hr style='margin:30px 0;'>"
                    // Footer
                    + "<div style='font-size:14px; color:#777;'>"
                    + "<p style='margin-bottom:5px;'><strong>Về chúng tôi:</strong> T-Tasty là cửa hàng trái cây tươi ngon tại Hà Nội, nơi bạn luôn được trải nghiệm chất lượng và sự tận tâm.</p>"
                    + "<p style='margin-bottom:5px;'><strong>Liên hệ:</strong> +0344497444</p>"
                    + "<p style='margin-bottom:0;'>Địa chỉ: Long Biên, Hà Nội</p>"
                    + "</div>"
                    + "<p style='font-size:12px; color:#aaa; margin-top:30px;'>Nếu bạn không yêu cầu mã OTP này, vui lòng bỏ qua email này.</p>"
                    + "</div>"
                    + "</div>";

            msg.setContent(htmlContent, "text/html; charset=UTF-8"); // Gửi nội dung HTML

            // Gửi email
            Transport.send(msg);
            System.out.println("✅ Gửi OTP thành công tới: " + to);
        } catch (Exception e) {
            System.err.println("❌ Gửi email thất bại:");
            e.printStackTrace();
        }
        return randomSixDigits;
    }

    public static void sendOrderConfirmation(String to, String buyerName) {
        // Properties : khai bao thuoc tinh
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            msg.setFrom(new InternetAddress(from, "T-Tasty Fruits"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("🛒 Đơn hàng mới của bạn tại T-Tasty!", "UTF-8");
            msg.setSentDate(new Date());

            String htmlContent = "<div style='font-family:Arial,sans-serif;background:#f9f9f9;padding:40px;'>"
                    + "<div style='max-width:600px;margin:auto;background:white;padding:30px;border-radius:10px;box-shadow:0 0 10px rgba(0,0,0,0.05);'>"
                    + "<div style='text-align:center;'>"
                    + "<img src='https://img.redocn.com/sheji/20240414/yourencaofeijiandeniunaigaoqingtu_13282921.jpg.400.jpg' alt='T-Tasty Logo' style='width:300px;margin-bottom:20px;'/>"
                    + "</div>"
                    + "<h2 style='color:#FF9800;text-align:center;'>Cảm ơn " + buyerName + " đã đặt hàng tại T-Tasty!</h2>"
                    + "<p style='font-size:16px;color:#555;'>Chúng tôi đã nhận được đơn hàng của bạn. Hãy chờ chúng tôi xác nhận và chuẩn bị đơn hàng trong thời gian sớm nhất.</p>"
                    + "<div style='font-size:16px;text-align:center;margin:20px 0;'>📦 Đơn hàng sẽ sớm được xử lý.</div>"
                    + "<p style='font-size:14px;color:#999;'>Mọi thông tin xác nhận sẽ được gửi qua email. Vui lòng kiểm tra thường xuyên.</p>"
                    + "<hr style='margin:30px 0;'>"
                    + "<div style='font-size:14px;color:#777;'>"
                    + "<p><strong>Liên hệ:</strong> +0344497444</p>"
                    + "<p><strong>Email:</strong> TTasty@gmail.com</p>"
                    + "<p><strong>Địa chỉ:</strong> Long Biên, Hà Nội</p>"
                    + "</div>"
                    + "<p style='font-size:12px;color:#aaa;margin-top:30px;'>Nếu bạn không thực hiện đơn hàng này, hãy liên hệ với chúng tôi để được hỗ trợ.</p>"
                    + "</div>"
                    + "</div>";

            msg.setContent(htmlContent, "text/html; charset=UTF-8");
            Transport.send(msg);
            System.out.println("✅ Gửi email xác nhận đơn hàng tới: " + to);
        } catch (Exception e) {
            System.err.println("❌ Gửi email thất bại:");
            e.printStackTrace();
        }
    }

    public static void sendOrderAcceptedEmail(String to, String buyerName) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            msg.setFrom(new InternetAddress(from, "T-Tasty Fruits"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("✅ Đơn hàng của bạn đã được xác nhận!", "UTF-8");
            msg.setSentDate(new Date());

            String htmlContent = "<div style='font-family:Arial,sans-serif;background:#f9f9f9;padding:40px;'>"
                    + "<div style='max-width:600px;margin:auto;background:white;padding:30px;border-radius:10px;box-shadow:0 0 10px rgba(0,0,0,0.05);'>"
                    + "<div style='text-align:center;'>"
                    + "<img src='https://img.redocn.com/sheji/20240414/yourencaofeijiandeniunaigaoqingtu_13282921.jpg.400.jpg' alt='T-Tasty Logo' style='width:300px;margin-bottom:20px;'/>"
                    + "</div>"
                    + "<h2 style='color:#28a745;text-align:center;'>Đơn hàng của bạn đã được xác nhận!</h2>"
                    + "<p style='font-size:16px;color:#555;'>Xin chào <strong>" + buyerName + "</strong>,</p>"
                    + "<p style='font-size:16px;color:#555;'>Chúng tôi vui mừng thông báo rằng đơn hàng của bạn đã được xác nhận thành công và đang được chuẩn bị để giao.</p>"
                    + "<div style='font-size:16px;text-align:center;margin:20px 0;'>🚚 Đơn hàng sẽ được vận chuyển trong thời gian sớm nhất.</div>"
                    + "<hr style='margin:30px 0;'>"
                    + "<div style='font-size:14px;color:#777;'>"
                    + "<p><strong>Liên hệ:</strong> +0344497444</p>"
                    + "<p><strong>Email:</strong> TTasty@gmail.com</p>"
                    + "<p><strong>Địa chỉ:</strong> Long Biên, Hà Nội</p>"
                    + "</div>"
                    + "<p style='font-size:12px;color:#aaa;margin-top:30px;'>Nếu bạn có bất kỳ câu hỏi nào, hãy liên hệ với chúng tôi để được hỗ trợ.</p>"
                    + "</div>"
                    + "</div>";

            msg.setContent(htmlContent, "text/html; charset=UTF-8");
            Transport.send(msg);
            System.out.println("✅ Đã gửi email xác nhận tới: " + to);
        } catch (Exception e) {
            System.err.println("❌ Lỗi gửi email xác nhận:");
            e.printStackTrace();
        }
    }

    public static void sendOrderRejectedEmail(String to, String buyerName) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            msg.setFrom(new InternetAddress(from, "T-Tasty Fruits"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("🛑 Xin lỗi, đơn hàng của bạn đã bị từ chối", "UTF-8");
            msg.setSentDate(new Date());

            String htmlContent = "<div style='font-family:Arial,sans-serif;background:#f9f9f9;padding:40px;'>"
                    + "<div style='max-width:600px;margin:auto;background:white;padding:30px;border-radius:10px;box-shadow:0 0 10px rgba(0,0,0,0.05);'>"
                    + "<div style='text-align:center;'>"
                    + "<img src='https://img.redocn.com/sheji/20240414/yourencaofeijiandeniunaigaoqingtu_13282921.jpg.400.jpg' alt='T-Tasty Logo' style='width:300px;margin-bottom:20px;'/>"
                    + "</div>"
                    + "<h2 style='color:#dc3545;text-align:center;'>Rất tiếc đơn hàng đã bị từ chối</h2>"
                    + "<p style='font-size:16px;color:#555;'>Xin chào <strong>" + buyerName + "</strong>,</p>"
                    + "<p style='font-size:16px;color:#555;'>Chúng tôi xin lỗi vì không thể thực hiện đơn hàng của bạn vào lúc này.</p>"
                    + "<p style='font-size:16px;color:#555;'>Vui lòng liên hệ với chúng tôi để biết thêm chi tiết hoặc hỗ trợ thêm.</p>"
                    + "<div style='font-size:16px;text-align:center;margin:20px 0;'>📩 Chúng tôi rất tiếc về sự bất tiện này.</div>"
                    + "<hr style='margin:30px 0;'>"
                    + "<div style='font-size:14px;color:#777;'>"
                    + "<p><strong>Liên hệ:</strong> +0344497444</p>"
                    + "<p><strong>Email:</strong> TTasty@gmail.com</p>"
                    + "<p><strong>Địa chỉ:</strong> Long Biên, Hà Nội</p>"
                    + "</div>"
                    + "<p style='font-size:12px;color:#aaa;margin-top:30px;'>Chúng tôi mong được phục vụ bạn vào lần tới.</p>"
                    + "</div>"
                    + "</div>";

            msg.setContent(htmlContent, "text/html; charset=UTF-8");
            Transport.send(msg);
            System.out.println("❌ Đã gửi email xin lỗi tới: " + to);
        } catch (Exception e) {
            System.err.println("❌ Lỗi gửi email xin lỗi:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //sendOrderConfirmation("ngnahtg@gmail.com", "tung");
        sendOrderAcceptedEmail("ngnahtg@gmail.com", "tung");
        sendOrderRejectedEmail("ngnahtg@gmail.com", "tung");
    }
}
