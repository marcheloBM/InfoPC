/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.InfoPC.GUI;

import Cl.Burgos.InfoPC.FUN.Actualizacion;
import static Cl.Burgos.InfoPC.FUN.Actualizacion.AbrirCarpeta;
import static Cl.Burgos.InfoPC.FUN.Actualizacion.obtenerContenidoURL;
import Cl.Burgos.InfoPC.Conf.Confi;
import Cl.Burgos.InfoPC.FUN.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author march
 */
public class FrUpdate extends javax.swing.JFrame {

    /**
     * Creates new form FrUpdate
     */
    public FrUpdate() {
        initComponents();
        setLocationRelativeTo(null);
        UpdateInfo();
        
    }
    public void UpdateInfo(){
        lblNombre.setText("Nombre del Archivo: "+Confi.nameArchivo);
        lblVersionActual.setText("Version Actual: "+Confi.Version);
        lblVersion.setText("Version Disponible: "+Actualizacion.obtenerVersion()+ " Diponible");
        
        String url=null;
        try {
            URL urlD = new URL(Confi.UrlDescarga);
            url=Actualizacion.obtenerContenidoURL(urlD);
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            tamano.setText("Tamaño: " + bytesToMeg(conn.getContentLength()) + " MB Aprox");
        } catch (IOException ex) {
            Log.log("Error en Clase FrUpdate: "+ex.getMessage());
            Logger.getLogger(FrUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo para descargar el archivo directo desde java
    public void descargarUpdate(){
        String url=null;
        try {
            //nombre del archivo destino
            String name = Confi.nameArchivo;
            
            //Directorio destino para las descargas
            String folder = Confi.CarpetaUpdate;
            
            //Crea el directorio de destino en caso de que no exista
            File dir = new File(folder);
            
            if (!dir.exists())
                if (!dir.mkdir())
                    // no se pudo crear la carpeta de destino
                    return;
            
            //Creamos el archivo destino, en caso de existir lo elimina:
            File file = new File(folder + name);
            
            //leer archivo url descargas
            URL urlD = new URL(Confi.UrlDescarga);
            url=obtenerContenidoURL(urlD);
            
            //Establece la conexion con la url
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
                                    
            //Abrimos los Stream
            InputStream in = conn.getInputStream();
            OutputStream out = new FileOutputStream(file);
            
            
            byte[] array = new byte[1024];
            int leido = in.read(array);
            while(leido > 0){
                out.write(array,0,leido);
                leido=in.read(array);
            }
            AbrirCarpeta();
            out.close();
            in.close();
        } catch (MalformedURLException e) {
            Log.log("Error en Clase FrUpdate: "+e.getMessage());
            JOptionPane.showMessageDialog(null, "La url: " + url + " no es valida!");
        } catch (IOException e) {
            Log.log("Error en Clase FrUpdate: "+e.getMessage());
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Archivo Descargado y listo para actualizar");
//        System.exit (0);
    }
    
    private static final long  MEGABYTE = 1024L * 1024L;

    public static long bytesToMeg(long bytes) {
      return bytes / MEGABYTE ;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        tamano = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblVersionActual = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Descargar Update");

        lblNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNombre.setText("nombre");

        lblVersion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblVersion.setText("Version");

        tamano.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tamano.setText("Tamaño");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Descargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblVersionActual.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblVersionActual.setText("Version Actual");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Atras");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(tamano)
                            .addComponent(lblVersion)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(119, 119, 119))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVersionActual)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersionActual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tamano)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        descargarUpdate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new FrHome().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrUpdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JLabel lblVersionActual;
    private javax.swing.JLabel tamano;
    // End of variables declaration//GEN-END:variables
}