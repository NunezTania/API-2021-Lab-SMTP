package smtp;

import config.config.ConfigurationManager;
import model.model.Prank;

import java.io.*;
import java.net.Socket;

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
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

            // lis le 1er msg server
            String line = in.readLine();
            if( !line.startsWith("220")){
                throw new IOException(" Connection problems");
            }
            // ecrit EHLO
            out.write("EHLO hi\r\n");
            out.flush();

            // lis la rep server = liste des extension ...
            // continue de lire tant que la rep contient pas 250 sans tiret
            line = in.readLine();
            if( !line.startsWith("250")){
                throw new IOException(" Problem after EHLO");
            }
            while(line.startsWith("250 ")){
                line = in.readLine();
            }
            // ecrit seulement si a recu un 250 sans tiret
            // ecrit le vrai from
            out.write("MAIL FROM: tania.nunez@heig-vd.ch\r\n");
            out.flush();

            // lis ok
            line = in.readLine();
            if( !(line == "250 OK")){
                throw new IOException(" Problem with mail vrai from");
            }
            // ecrit le vrai dest = victime
            out.write("RCPT TO: " + prank.getVictim() + "\r\n");
            out.flush();

            // lis si ok
            line = in.readLine();
            if( !(line == "250 OK")){
                throw new IOException(" Problem with mail vrai to");
            }

            // ecrit le data
            out.write("DATA\r\n");
            out.flush();

            // lis si rep server ok
            line = in.readLine();
            if( !line.startsWith("354")){
                throw new IOException(" Problem after DATA");
            }

            // ecrit le mail
            out.write("From :" + prank.getSender() + "\r\n" );
            out.write("To :" + prank.getVictim() + "\r\n" );
            out.write( prank.getMessage() + "\r\n" );
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
