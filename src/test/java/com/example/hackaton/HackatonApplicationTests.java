package com.example.hackaton;

import com.example.hackaton.Reserva.Domain.Reserva;
import com.example.hackaton.Reserva.Domain.ReservaService;
import com.example.hackaton.Salon.Domain.Salon;
import com.example.hackaton.Usuario.Domain.Usuario;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@SpringBootTest
@Transactional
public class HackatonApplicationTests {

    @Autowired
    private ReservaService reservaService;

    private List<Reserva> reservas;

    @BeforeEach
    public void setUp() {
        // Configura las reservas antes de cada prueba
        reservas = Arrays.asList(
                new Reserva(LocalDate.of(2024, 6, 1), LocalTime.of(10, 0), LocalTime.of(12, 0), new Usuario(), new Salon()),
                new Reserva(LocalDate.of(2024, 6, 2), LocalTime.of(14, 0), LocalTime.of(16, 0), new Usuario(), new Salon()),
                new Reserva(LocalDate.of(2024, 6, 3), LocalTime.of(9, 0), LocalTime.of(11, 0), new Usuario(), new Salon())
        );
        // Guarda las reservas en la base de datos
        for (Reserva reserva : reservas) {
            reservaService.createReserva(reserva);
        }
    }


    @Test
    public void PDFTest() {
        // Crea un flujo de bytes para almacenar el PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // Crea un escritor para el PDF
            PdfWriter writer = new PdfWriter(outputStream);

            // Crea un documento PDF
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Agrega un título
            document.add(new Paragraph("Lista de Reservas"));

            // Agrega las reservas al PDF
            for (Reserva reserva : reservas) {
                document.add(new Paragraph("ID: " + reserva.getId()));
                document.add(new Paragraph("Fecha: " + reserva.getFecha()));
                document.add(new Paragraph("Hora de Inicio: " + reserva.getHoraInicio()));
                document.add(new Paragraph("Hora de Fin: " + reserva.getHoraFin()));
                document.add(new Paragraph(" "));
            }

            // Cierra el documento
            document.close();

            // Envia el PDF como un correo electrónico
            enviarCorreoConPDF(outputStream.toByteArray());

            System.out.println("PDF creado y correo electrónico enviado con éxito!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void enviarCorreoConPDF(byte[] pdfBytes) throws MessagingException, IOException {
        // Configura las propiedades para la conexión con el servidor SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.example.com"); // Reemplaza "smtp.example.com" con tu servidor SMTP
        properties.put("mail.smtp.port", "587"); // Reemplaza "587" con el puerto SMTP de tu servidor
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Crea una sesión de correo
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tu_email@example.com", "tu_contraseña"); // Reemplaza con tus credenciales de correo electrónico
            }
        });

        // Crea un mensaje de correo electrónico
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("tu_email@example.com")); // Reemplaza con tu dirección de correo electrónico
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("destinatario@example.com")); // Reemplaza con la dirección del destinatario
        message.setSubject("PDF de Reservas");

        // Crea la parte del cuerpo del mensaje
        MimeBodyPart mensajeParte = new MimeBodyPart();
        mensajeParte.setText("Adjunto encontrarás el PDF de las reservas.");

        // Crea la parte del PDF adjunto
        MimeBodyPart pdfParte = new MimeBodyPart();
        pdfParte.attachFile(Arrays.toString(pdfBytes), "application/pdf", "reservas.pdf");

        // Crea un multipart para agregar ambas partes
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mensajeParte);
        multipart.addBodyPart(pdfParte);

        // Establece el multipart como el contenido del mensaje
        message.setContent(multipart);

        // Envía el mensaje
        Transport.send(message);
    }
}
