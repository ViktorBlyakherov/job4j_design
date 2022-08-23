package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String strIn = in.readLine();
                    if (strIn.contains("?msg=Hello")) {
                        out.write("Hello!".getBytes());
                    } else if (strIn.contains("?msg=Exit")) {
                        server.close();
                    } else {
                        out.write("What?".getBytes());
                    }
                    out.flush();
                } catch (Exception e) {
                    LOG.error("Exception in input/output block", e);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in server block", e);
        }
    }
}