package clients;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.io.IOException;
import java.net.InetAddress;
import javax.swing.JOptionPane;

public class Client extends javax.swing.JFrame {

    private javax.swing.JTextArea chatArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel status;


    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message="";
    private String serverIP;
    private Socket clientSocket;
    private int port = 6789;
    
    
    public Client(String s) {
        
        initComponents();
        this.setTitle("Client");
        this.setVisible(true);
        status.setVisible(true);
        serverIP = s;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(null);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(30, 50, 270, 30);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(310, 50, 80, 30);

        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 110, 360, 270);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Write your text here");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 30, 150, 20);

        status.setText("...");
        jPanel1.add(status);
        status.setBounds(30, 80, 300, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server/bg7.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 420, 410);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(414, 428));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        sendMessage(jTextField1.getText());
	    jTextField1.setText("");
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       sendMessage(jTextField1.getText());
	    jTextField1.setText("");
    }   //GEN-LAST:event_jButton1ActionPerformed

    public void startRunning() {

       try {

            status.setText("Attempting Connection ...");

            try {
                clientSocket = new Socket(InetAddress.getByName(serverIP),port);

            }catch(IOException ioe) {
                    JOptionPane.showMessageDialog(null,"Server Might Be Down!","Warning",JOptionPane.WARNING_MESSAGE);
            }

            status.setText("Connected to: " + clientSocket.getInetAddress().getHostName());

            output = new ObjectOutputStream(clientSocket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(clientSocket.getInputStream());

            whileChatting();

       } catch(IOException ioException) {

            ioException.printStackTrace();
       }
    }
    
    private void whileChatting() throws IOException {

      jTextField1.setEditable(true);

      do{
          try {
              message = (String) input.readObject();
              chatArea.append("\n"+message);

          } catch(ClassNotFoundException classNotFoundException) {
          }

      } while(!message.equals("Client - END")); }

    private void sendMessage(String message) {

        try {

            output.writeObject("Client - " + message);
            output.flush();
            chatArea.append("\nClient - "+message);
        }

        catch(IOException ioException) {

            chatArea.append("\n Unable to Send Message");
        }
    }
}
