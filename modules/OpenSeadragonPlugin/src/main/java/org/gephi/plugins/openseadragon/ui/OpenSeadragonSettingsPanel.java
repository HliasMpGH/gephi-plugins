/*
 * Gephi Seadragon Plugin
 *
 * Copyright 2010-2011 Gephi
 * Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
 * Website : http://www.gephi.org
 * Licensed under Apache 2 License (http://www.apache.org/licenses/LICENSE-2.0)
 */
package org.gephi.plugins.openseadragon.ui;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import org.gephi.lib.validation.Multiple4NumberValidator;
import org.gephi.lib.validation.PositiveNumberValidator;
import org.gephi.plugins.openseadragon.OpenSeadragonExporter;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.ValidationGroup;
import org.netbeans.validation.api.ui.swing.ValidationPanel;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

/**
 *
 * @author Mathieu Bastian
 */
public class OpenSeadragonSettingsPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 707L;
    final String LAST_PATH = "SeadragonExporterUI_Last_Path";
    private File path;
    private OpenSeadragonExporter exporter;

    public OpenSeadragonSettingsPanel() {
        initComponents();

        browseButton.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(pathTextField.getText());
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(WindowManager.getDefault().getMainWindow());
            if (result == JFileChooser.APPROVE_OPTION) {
                path = fileChooser.getSelectedFile();
                pathTextField.setText(path.getAbsolutePath());
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelDirectory = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        header = new org.jdesktop.swingx.JXHeader();
        labelTileSize = new javax.swing.JLabel();
        tileSizeTextField = new javax.swing.JTextField();
        labelPx3 = new javax.swing.JLabel();
        pathTextField = new javax.swing.JTextField();
        labelWidth = new javax.swing.JLabel();
        widthTextField = new javax.swing.JTextField();
        labelHeight = new javax.swing.JLabel();
        heightTextField = new javax.swing.JTextField();
        labelPx = new javax.swing.JLabel();
        labelPx2 = new javax.swing.JLabel();
        labelMargins = new javax.swing.JLabel();
        marginTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        labelDirectory.setText("Select export directory:");

        browseButton.setText("Browse");

        header.setDescription("Export the current network to the OpenSeadragon web technology, embeddable in any browser and allowing smooth network exploration");
        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gephi/plugins/openseadragon/ui/logo.png"))); // NOI18N
        header.setTitle("OpenSeadragon Web Export");

        labelTileSize.setText("Tile Size:");

        tileSizeTextField.setText(org.openide.util.NbBundle.getMessage(OpenSeadragonSettingsPanel.class, "SeadragonSettingsPanel.tile size.text")); // NOI18N
        tileSizeTextField.setName("tile size"); // NOI18N

        labelPx3.setText("px");

        pathTextField.setText(org.openide.util.NbBundle.getMessage(OpenSeadragonSettingsPanel.class, "SeadragonSettingsPanel.path.text")); // NOI18N
        pathTextField.setName("path"); // NOI18N

        labelWidth.setText("Width:");

        widthTextField.setText(org.openide.util.NbBundle.getMessage(OpenSeadragonSettingsPanel.class, "SeadragonSettingsPanel.width.text")); // NOI18N
        widthTextField.setName("width"); // NOI18N

        labelHeight.setText("Height:");

        heightTextField.setText(org.openide.util.NbBundle.getMessage(OpenSeadragonSettingsPanel.class, "SeadragonSettingsPanel.height.text")); // NOI18N
        heightTextField.setName("height"); // NOI18N

        labelPx.setText("px");

        labelPx2.setText("px");

        labelMargins.setText("Margins:");

        marginTextField.setText(org.openide.util.NbBundle.getMessage(OpenSeadragonSettingsPanel.class, "SeadragonSettingsPanel.margin.text")); // NOI18N
        marginTextField.setName("margin"); // NOI18N

        jLabel1.setText("% of size");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pathTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelWidth)
                            .addComponent(labelHeight))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(widthTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(heightTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelPx)
                                .addGap(47, 47, 47)
                                .addComponent(labelTileSize)
                                .addGap(18, 18, 18)
                                .addComponent(tileSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(labelPx3))
                            .addComponent(labelPx2)
                            .addComponent(jLabel1)))
                    .addComponent(labelDirectory)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelMargins)
                        .addGap(18, 18, 18)
                        .addComponent(marginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDirectory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelWidth)
                    .addComponent(widthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPx)
                    .addComponent(labelTileSize)
                    .addComponent(tileSizeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPx3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHeight)
                    .addComponent(heightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPx2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMargins)
                    .addComponent(marginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setup(OpenSeadragonExporter exporter) {
        this.exporter = exporter;
        tileSizeTextField.setText(String.valueOf(exporter.getTileSize()));
        path = new File(NbPreferences.forModule(OpenSeadragonExporterUI.class).get(LAST_PATH, System.getProperty("user.home")));
        pathTextField.setText(path.getAbsolutePath());

        //PDF
        widthTextField.setText(String.valueOf(exporter.getWidth()));
        heightTextField.setText(String.valueOf(exporter.getHeight()));
        marginTextField.setText(String.valueOf(exporter.getMargin()));
    }

    public void unsetup(boolean update) {
        if (update) {
            try {
                path = new File(pathTextField.getText());
            } catch (Exception e) {
            }
            NbPreferences.forModule(OpenSeadragonExporterUI.class).put(LAST_PATH, path.getAbsolutePath());
            exporter.setPath(path);
            exporter.setWidth(Integer.parseInt(widthTextField.getText()));
            exporter.setHeight(Integer.parseInt(heightTextField.getText()));
            exporter.setTileSize(Integer.parseInt(tileSizeTextField.getText()));
            exporter.setMargin(Integer.parseInt(marginTextField.getText()));
        }
    }

    public static ValidationPanel createValidationPanel(OpenSeadragonSettingsPanel innerPanel) {
        ValidationPanel validationPanel = new ValidationPanel();
        validationPanel.setInnerComponent(innerPanel);

        ValidationGroup group = validationPanel.getValidationGroup();
        group.add(innerPanel.tileSizeTextField, StringValidators.REQUIRE_NON_EMPTY_STRING,
                new Multiple4NumberValidator());

        group.add(innerPanel.widthTextField, StringValidators.REQUIRE_NON_EMPTY_STRING,
                new PositiveNumberValidator());
        group.add(innerPanel.heightTextField, StringValidators.REQUIRE_NON_EMPTY_STRING,
                new PositiveNumberValidator());

        group.add(innerPanel.pathTextField, StringValidators.FILE_MUST_BE_DIRECTORY);

        //Margins
        group.add(innerPanel.marginTextField, StringValidators.REQUIRE_NON_EMPTY_STRING,
                StringValidators.REQUIRE_VALID_NUMBER);

        return validationPanel;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private org.jdesktop.swingx.JXHeader header;
    private javax.swing.JTextField heightTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelDirectory;
    private javax.swing.JLabel labelHeight;
    private javax.swing.JLabel labelMargins;
    private javax.swing.JLabel labelPx;
    private javax.swing.JLabel labelPx2;
    private javax.swing.JLabel labelPx3;
    private javax.swing.JLabel labelTileSize;
    private javax.swing.JLabel labelWidth;
    private javax.swing.JTextField marginTextField;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JTextField tileSizeTextField;
    private javax.swing.JTextField widthTextField;
    // End of variables declaration//GEN-END:variables
}
