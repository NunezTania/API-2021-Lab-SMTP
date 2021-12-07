package smtp;

import model.model.Person;
import model.model.Prank;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SMTPclient {
    Socket socket;
    String addressIP;
    int port;
    PrintWriter out;
    BufferedReader in;

    public SMTPclient(String IP, int port){
        this.addressIP = IP;
        this.port = port;
    }

    public void envoieMail(Prank prank){
        try {
            socket = new Socket(addressIP, port);
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            String line = in.readLine();
            if( !line.startsWith("220")){
                throw new IOException(" Connection problems");
            }
            out.write("EHLO hi\r\n");
            out.flush();


            line = in.readLine();
            if( !line.startsWith("250")){
                throw new IOException(" Problem after EHLO");
            }
            while(line.startsWith("250-")){
                line = in.readLine();
            }


            out.write("MAIL FROM:"+ prank.getSender().getEmail() +"\r\n");
            out.flush();

            line = in.readLine();
            if(!line.startsWith("250")){
                throw new IOException(" Problem with mail vrai from");
            }


            StringBuilder recepteur = new StringBuilder();
            for(Person s : prank.getVictim().getMembers()){
                recepteur.append(s.getEmail());
                recepteur.append(",");
                out.write("RCPT TO: " + s.getEmail() + "\r\n");
                out.flush();
                line = in.readLine();
                if(!line.startsWith("250")){
                    throw new IOException(" Problem with mail vrai to");
                }
            }


            out.write("DATA ");
            out.write("\r\n");
            out.flush();

            line = in.readLine();

            if(!line.startsWith("354")){
                throw new IOException(" Problem after DATA");
            }


            String content = prank.getMessage();
            StringBuilder message = new StringBuilder();
            String subject = content.split(":")[1].split("\n")[0];
            String body = content.substring(content. indexOf('\n')+1);
            message.append("Subject: " + "=?utf-8?B?").append(Base64.getEncoder()
                    .encodeToString(subject.getBytes(StandardCharsets.UTF_8))).append("?=").append("\r\n").append("\r\n");

            out.write("Content-Type: text/plain; charset=utf-8\r\n");
            out.write("From :" + prank.getSender().getEmail() + "\r\n" );

            out.write("To :" + recepteur + "\r\n" );
            out.write("Cc :" + prank.getWitness().getEmail() + "\r\n");
            out.write(message + "\r\n");
            out.write( body + "\r\n" );
            out.write("\r\n.\r\n");
            out.flush();

            line = in.readLine();
            if( !line.startsWith("250")){
                throw new IOException(" Problem in the message");
            }

            out.write("QUIT\r\n");
            out.flush();

            line = in.readLine();
            if( !line.startsWith("221")){
                throw new IOException(" Problem when quiting");
            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }



}
