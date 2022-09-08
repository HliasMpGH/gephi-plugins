package org.gephi.plugins.neo4j.importer.wizard;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @author bsimard
 */
public class Neo4jDatabaseImporterImportPanel extends javax.swing.JPanel {

    /**
     * Creates new form Neo4jDatabaseImporterImportPanel
     */
    public Neo4jDatabaseImporterImportPanel() {
        initComponents();
        this.nodeActionText.setVisible(false);
        this.edgeActionText.setVisible(false);
    }

    @Override
    public String getName() {
        return "Import configuration";   //this will be the title of the panel in the wizard
    }

    /**
     * Check that the form is valid.
     */
    public void checkValidity() throws Exception {
        if (this.menu.getSelectedIndex() == 1) {
            // Check the node query
            if (Utils.isEmptyOrNull(this.nodeQuery.getText())) throw new Exception("Node query is mandatory");
            Utils.neo4jWizardCheckQuery(this.nodeQuery.getText(), Arrays.asList("id"));

            // Check the edge query
            if (Utils.isEmptyOrNull(this.edgeQuery.getText())) throw new Exception("Edge query is mandatory");
            Utils.neo4jWizardCheckQuery(this.edgeQuery.getText(), Arrays.asList("id", "sourceId", "targetId"));
        }
    }

    private void checkNodeQueryActionPerformed(java.awt.event.ActionEvent evt) {
        this.nodeActionText.setVisible(false);
        this.checkNodeQuery.setEnabled(false);
        try{
            if (Utils.isEmptyOrNull(this.nodeQuery.getText())) throw new Exception("Node query is empty");
            Utils.neo4jWizardCheckQuery(this.nodeQuery.getText(), Arrays.asList("id"));
            this.nodeActionText.setText("Query is valid");
            this.nodeActionText.setForeground(Color.GREEN);
        }
        catch(Exception e) {
            this.nodeActionText.setText(e.getMessage());
            this.nodeActionText.setForeground(Color.RED);
        }
        finally {
            this.checkNodeQuery.setEnabled(true);
            this.nodeActionText.setVisible(true);
        }
    }

    private void checkEdgeQueryActionPerformed(java.awt.event.ActionEvent evt) {
        this.edgeActionText.setVisible(false);
        this.checkEdgeQuery.setEnabled(false);
        try{
            if (Utils.isEmptyOrNull(this.edgeQuery.getText())) throw new Exception("Node query is empty");
            Utils.neo4jWizardCheckQuery(this.edgeQuery.getText(), Arrays.asList("id", "sourceId", "targetId"));
            this.edgeActionText.setText("Query is valid");
            this.edgeActionText.setForeground(Color.GREEN);
        }
        catch(Exception e) {
            this.edgeActionText.setText(e.getMessage());
            this.edgeActionText.setForeground(Color.RED);
        }
        finally {
            this.checkEdgeQuery.setEnabled(true);
            this.edgeActionText.setVisible(true);
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        menu = new javax.swing.JTabbedPane();
        typesPanel = new javax.swing.JPanel();
        labelsScroll = new javax.swing.JScrollPane();
        labels = new javax.swing.JList<>();
        typesScroll = new javax.swing.JScrollPane();
        types = new javax.swing.JList<>();
        labelsLabel = new javax.swing.JLabel();
        typesLabels = new javax.swing.JLabel();
        help = new javax.swing.JLabel();
        queriesPanel = new javax.swing.JPanel();
        nodeQueryLabel = new javax.swing.JLabel();
        nodeQueryScroll = new javax.swing.JScrollPane();
        nodeQuery = new javax.swing.JTextArea();
        edgeQueryLabel = new javax.swing.JLabel();
        edgeQueryScroll = new javax.swing.JScrollPane();
        edgeQuery = new javax.swing.JTextArea();
        nodeActionTextScroll = new javax.swing.JScrollPane();
        nodeActionText = new javax.swing.JTextArea();
        checkNodeQuery = new javax.swing.JButton();
        checkEdgeQuery = new javax.swing.JButton();
        edgeActionTextScroll = new javax.swing.JScrollPane();
        edgeActionText = new javax.swing.JTextArea();
        nodeQueryHelp = new javax.swing.JLabel();
        edgeQueryHelp = new javax.swing.JLabel();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        menu.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        menu.setMaximumSize(null);
        menu.setMinimumSize(new java.awt.Dimension(500, 500));
        menu.setPreferredSize(new java.awt.Dimension(500, 500));

        typesPanel.setName("Labels & Relationship types"); // NOI18N
        typesPanel.setOpaque(false);
        typesPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        java.awt.GridBagLayout typesPanelLayout = new java.awt.GridBagLayout();
        typesPanelLayout.columnWidths = new int[] {0, 5, 0, 5, 0};
        typesPanelLayout.rowHeights = new int[] {0, 5, 0, 5, 0};
        typesPanel.setLayout(typesPanelLayout);

        labelsScroll.setMaximumSize(null);
        labelsScroll.setMinimumSize(null);
        labelsScroll.setPreferredSize(new java.awt.Dimension(200, 380));

        labels.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        labels.setPreferredSize(new java.awt.Dimension(200, 450));
        labelsScroll.setViewportView(labels);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        typesPanel.add(labelsScroll, gridBagConstraints);

        typesScroll.setMaximumSize(null);
        typesScroll.setMinimumSize(null);
        typesScroll.setPreferredSize(new java.awt.Dimension(200, 380));

        types.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        types.setPreferredSize(new java.awt.Dimension(200, 500));
        typesScroll.setViewportView(types);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        typesPanel.add(typesScroll, gridBagConstraints);

        labelsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(labelsLabel, org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.labelsLabel.text")); // NOI18N
        labelsLabel.setMaximumSize(null);
        labelsLabel.setMinimumSize(new java.awt.Dimension(200, 24));
        labelsLabel.setPreferredSize(new java.awt.Dimension(245, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        typesPanel.add(labelsLabel, gridBagConstraints);

        typesLabels.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(typesLabels, org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.typesLabels.text")); // NOI18N
        typesLabels.setMinimumSize(new java.awt.Dimension(200, 24));
        typesLabels.setPreferredSize(new java.awt.Dimension(245, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        typesPanel.add(typesLabels, gridBagConstraints);

        help.setFont(new java.awt.Font("sansserif", 2, 13)); // NOI18N
        help.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(help, org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.help.text")); // NOI18N
        help.setPreferredSize(new java.awt.Dimension(500, 48));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        typesPanel.add(help, gridBagConstraints);

        menu.addTab(org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.Labels & Relationship types.TabConstraints.tabTitle"), typesPanel); // NOI18N

        queriesPanel.setName("Nodes & Edges queries"); // NOI18N
        queriesPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        queriesPanel.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(nodeQueryLabel, org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.nodesQueryLabel.text")); // NOI18N
        nodeQueryLabel.setToolTipText(org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.nodeQueryLabel.toolTipText")); // NOI18N
        nodeQueryLabel.setName("nodesQueryLabel"); // NOI18N
        nodeQueryLabel.setPreferredSize(new java.awt.Dimension(500, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        queriesPanel.add(nodeQueryLabel, gridBagConstraints);

        nodeQueryScroll.setPreferredSize(new java.awt.Dimension(400, 120));

        nodeQuery.setColumns(20);
        nodeQuery.setRows(5);
        nodeQuery.setPreferredSize(null);
        nodeQueryScroll.setViewportView(nodeQuery);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        queriesPanel.add(nodeQueryScroll, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(edgeQueryLabel, org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.edgeQueryLabel.text")); // NOI18N
        edgeQueryLabel.setPreferredSize(new java.awt.Dimension(500, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        queriesPanel.add(edgeQueryLabel, gridBagConstraints);

        edgeQueryScroll.setPreferredSize(new java.awt.Dimension(400, 120));

        edgeQuery.setColumns(20);
        edgeQuery.setRows(5);
        edgeQueryScroll.setViewportView(edgeQuery);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        queriesPanel.add(edgeQueryScroll, gridBagConstraints);

        nodeActionTextScroll.setBorder(null);
        nodeActionTextScroll.setPreferredSize(new java.awt.Dimension(390, 60));

        nodeActionText.setEditable(false);
        nodeActionText.setColumns(20);
        nodeActionText.setLineWrap(true);
        nodeActionText.setRows(2);
        nodeActionText.setText(org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.nodeActionText.text")); // NOI18N
        nodeActionText.setBorder(null);
        nodeActionText.setMinimumSize(new java.awt.Dimension(200, 48));
        nodeActionText.setPreferredSize(new java.awt.Dimension(400, 60));
        nodeActionTextScroll.setViewportView(nodeActionText);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        queriesPanel.add(nodeActionTextScroll, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(checkNodeQuery, org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.checkNodeQuery.text")); // NOI18N
        checkNodeQuery.setActionCommand(org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.checkNodeQuery.actionCommand")); // NOI18N
        checkNodeQuery.setPreferredSize(new java.awt.Dimension(110, 25));
        checkNodeQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNodeQueryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        queriesPanel.add(checkNodeQuery, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(checkEdgeQuery, org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.checkEdgeQuery.text")); // NOI18N
        checkEdgeQuery.setPreferredSize(new java.awt.Dimension(110, 25));
        checkEdgeQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEdgeQueryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        queriesPanel.add(checkEdgeQuery, gridBagConstraints);

        edgeActionTextScroll.setBorder(null);
        edgeActionTextScroll.setPreferredSize(new java.awt.Dimension(390, 60));

        edgeActionText.setEditable(false);
        edgeActionText.setColumns(20);
        edgeActionText.setLineWrap(true);
        edgeActionText.setRows(2);
        edgeActionText.setBorder(null);
        edgeActionText.setPreferredSize(null);
        edgeActionTextScroll.setViewportView(edgeActionText);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        queriesPanel.add(edgeActionTextScroll, gridBagConstraints);

        nodeQueryHelp.setFont(new java.awt.Font("sansserif", 2, 13)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(nodeQueryHelp, org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.nodeQueryHelp.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        queriesPanel.add(nodeQueryHelp, gridBagConstraints);

        edgeQueryHelp.setFont(new java.awt.Font("sansserif", 2, 13)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(edgeQueryHelp, org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.edgeQueryHelp.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        queriesPanel.add(edgeQueryHelp, gridBagConstraints);

        menu.addTab(org.openide.util.NbBundle.getMessage(Neo4jDatabaseImporterImportPanel.class, "Neo4jDatabaseImporterImportPanel.Nodes & Edges queries.TabConstraints.tabTitle"), queriesPanel); // NOI18N

        menu.setSelectedComponent(typesPanel);

        add(menu);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkEdgeQuery;
    private javax.swing.JButton checkNodeQuery;
    private javax.swing.JTextArea edgeActionText;
    private javax.swing.JScrollPane edgeActionTextScroll;
    private javax.swing.JTextArea edgeQuery;
    private javax.swing.JLabel edgeQueryHelp;
    private javax.swing.JLabel edgeQueryLabel;
    private javax.swing.JScrollPane edgeQueryScroll;
    private javax.swing.JLabel help;
    private javax.swing.JList<String> labels;
    private javax.swing.JLabel labelsLabel;
    private javax.swing.JScrollPane labelsScroll;
    private javax.swing.JTabbedPane menu;
    private javax.swing.JTextArea nodeActionText;
    private javax.swing.JScrollPane nodeActionTextScroll;
    private javax.swing.JTextArea nodeQuery;
    private javax.swing.JLabel nodeQueryHelp;
    private javax.swing.JLabel nodeQueryLabel;
    private javax.swing.JScrollPane nodeQueryScroll;
    private javax.swing.JPanel queriesPanel;
    private javax.swing.JList<String> types;
    private javax.swing.JLabel typesLabels;
    private javax.swing.JPanel typesPanel;
    private javax.swing.JScrollPane typesScroll;
    // End of variables declaration//GEN-END:variables


    public JButton getCheckEdgeQuery() {
        return checkEdgeQuery;
    }

    public JTextArea getEdgeQuery() {
        return edgeQuery;
    }

    public JList<String> getLabels() {
        return labels;
    }

    public JTabbedPane getMenu() {
        return menu;
    }

    public JTextArea getNodeQuery() {
        return nodeQuery;
    }

    public JList<String> getTypes() {
        return types;
    }
}
