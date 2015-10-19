package nextgenpos;

import javax.swing.JFrame;

public class PricingStrategyForm extends javax.swing.JFrame {

    private boolean percent_str = false;
    private boolean discount_str = false;
    private boolean shop_str = false;
    private boolean cust_str = false;
    private double p = 0;
    private double t = 0;
    private double d = 0;
    
    public PricingStrategyForm() {
        initComponents();
    }
    
    public double getPercent(){
        return this.p;
    }
    
    public double getThreshold(){
        return this.t;
    }
    
    public double getDiscount(){
        return this.d;
    }
    
    public void clearFlags(){
        this.percent_str = false;
        this.discount_str = false;
        this.shop_str = false;
        this.cust_str = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        percent_strategy = new javax.swing.JRadioButton();
        threshold_strategy = new javax.swing.JRadioButton();
        best_shop = new javax.swing.JRadioButton();
        best_customer = new javax.swing.JRadioButton();
        set_strategy = new javax.swing.JButton();
        percent = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        threshold = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        discount = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        no_strategy = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Set Pricing Strategy");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Choose Your Pricing Strategy");

        buttonGroup1.add(percent_strategy);
        percent_strategy.setText("Percent Discount");

        buttonGroup1.add(threshold_strategy);
        threshold_strategy.setText("Absolute Discount");

        buttonGroup1.add(best_shop);
        best_shop.setText("Best for Shop");

        buttonGroup1.add(best_customer);
        best_customer.setText("Best for Customer");

        set_strategy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nextgenpos/checked.gif"))); // NOI18N
        set_strategy.setText("Set");
        set_strategy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                set_strategyActionPerformed(evt);
            }
        });

        jLabel2.setText("Percentage");

        jLabel3.setText("Threshold");

        jLabel4.setText("Discount");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Note: You must start a new sale in order for the pricing strategy to be activated");

        buttonGroup1.add(no_strategy);
        no_strategy.setSelected(true);
        no_strategy.setText("No Strategy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(threshold_strategy)
                    .addComponent(percent_strategy)
                    .addComponent(jLabel5)
                    .addComponent(set_strategy, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(best_customer)
                    .addComponent(best_shop)
                    .addComponent(no_strategy)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(percent, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(24, 24, 24)
                                .addComponent(discount))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(threshold, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(no_strategy)
                .addGap(18, 18, 18)
                .addComponent(percent_strategy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(percent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(threshold_strategy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(threshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(best_shop)
                .addGap(18, 18, 18)
                .addComponent(best_customer)
                .addGap(37, 37, 37)
                .addComponent(set_strategy)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void set_strategyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_set_strategyActionPerformed
        if(percent_strategy.isSelected()){
            this.p = Double.parseDouble(percent.getValue().toString());
            UI.active_strategy = 1;
        }
        
        else if(no_strategy.isSelected()){
            UI.active_strategy = 0;
        }
        
        else if(threshold_strategy.isSelected()){
            this.t = Double.parseDouble(threshold.getValue().toString());
            this.d = Double.parseDouble(discount.getValue().toString());
            UI.active_strategy = 2;
        }
        
        else if(best_shop.isSelected()){
            this.p = Double.parseDouble(percent.getValue().toString());
            this.t = Double.parseDouble(threshold.getValue().toString());
            this.d = Double.parseDouble(discount.getValue().toString());
            UI.active_strategy = 3;
        }
        
        else if(best_customer.isSelected()){
            this.p = Double.parseDouble(percent.getValue().toString());
            this.t = Double.parseDouble(threshold.getValue().toString());
            this.d = Double.parseDouble(discount.getValue().toString());
            UI.active_strategy = 4;
        }
        
        else{
            UI.active_strategy = 0;
        }
        
        this.setVisible(false);
    }//GEN-LAST:event_set_strategyActionPerformed

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
            java.util.logging.Logger.getLogger(PricingStrategyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PricingStrategyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PricingStrategyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PricingStrategyForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PricingStrategyForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton best_customer;
    private javax.swing.JRadioButton best_shop;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JSpinner discount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton no_strategy;
    private javax.swing.JSpinner percent;
    private javax.swing.JRadioButton percent_strategy;
    private javax.swing.JButton set_strategy;
    private javax.swing.JSpinner threshold;
    private javax.swing.JRadioButton threshold_strategy;
    // End of variables declaration//GEN-END:variables
}