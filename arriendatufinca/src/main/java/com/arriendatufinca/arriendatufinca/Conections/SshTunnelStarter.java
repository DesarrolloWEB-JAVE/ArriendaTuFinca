/* package com.arriendatufinca.arriendatufinca.Conections;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SshTunnelStarter {

    private String url = "10.43.96.52";

    private String username = "estudiante";

    private String password = "1Rioblanco/7";

    private int port = 22;

    private Session session;

    @PostConstruct
    public void init() throws Exception {
       JSch jsch = new JSch();
       // Get SSH session
       session = jsch.getSession(username, url, port);
       session.setPassword(password);
       java.util.Properties config = new java.util.Properties();
       // Never automatically add new host keys to the host file
       config.put("StrictHostKeyChecking", "no");
       session.setConfig(config);
       // Connect to remote server
       session.connect();
       // Apply the port forwarding
       session.setPortForwardingL(3306, url, 3306);
    }

    @PreDestroy
    public void shutdown() throws Exception {
       if (session != null && session.isConnected()) {
           session.disconnect();
       }
    }
}
    */
package com.arriendatufinca.arriendatufinca.Conections;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SshTunnelStarter {

    private Session session;

    public void init() throws JSchException {
        String sshHost = "10.43.96.52";
        String sshUser = "estudiante";
        String sshPassword = "1Rioblanco/7";
        int sshPort = 22;
        int localPort = 3307; // Change this to a different port
        String remoteHost = "localhost";
        int remotePort = 3306;

        JSch jsch = new JSch();
        session = jsch.getSession(sshUser, sshHost, sshPort);
        session.setPassword(sshPassword);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        session.setPortForwardingL(localPort, remoteHost, remotePort);
    }

    public void shutdown() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    @Override
    public String toString() {
        return "SshTunnelStarter{" +
                "session=" + session +
                '}';
    }
}