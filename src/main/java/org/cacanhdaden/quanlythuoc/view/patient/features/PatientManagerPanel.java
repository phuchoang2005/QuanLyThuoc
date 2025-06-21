package org.cacanhdaden.quanlythuoc.view.patient.features;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Getter;
import org.cacanhdaden.quanlythuoc.control.patient.PatientManagerController;
import org.cacanhdaden.quanlythuoc.services.CheckUserInformationInvalid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@Getter
public class PatientManagerPanel extends JPanel {
    private final PatientManagerPanel mainPanel= this;
    private final JTextField idJTextField = new JTextField(20);
    private final JTextField full_nameField = new JTextField(20);
    private final JTextField phone_numberTextField = new JTextField(20);
    private final JTextField emailTextField = new JTextField(20);
    private final JTextField addressTextField = new JTextField(20);
    private final JTextField dobTextField = new JTextField(20);
    private final JComboBox<String> gengerComboBox = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
    private final JButton changePasswordButton = new JButton("Change Password");
    private final JButton updateButton = new JButton("Cập nhật");


    private final JLabel[] labels = {
            new JLabel("Mã số"),
            new JLabel("Họ và tên"),
            new JLabel("Email"),
            new JLabel("Địa chỉ"),
            new JLabel("Ngày tháng năm sinh"),
            new JLabel("Giới tính"),
    };

    private final JComponent[] components = {
            this.idJTextField,
            this.full_nameField,
            this.emailTextField,
            this.addressTextField,
            this.dobTextField,
            this.gengerComboBox
    };
    public PatientManagerPanel() {
        configLayout();
        initComponent();
        changePassword();
        checkEmailInvalid();
        checkDateInvalid();
        getInformation();
    }

    private void configLayout() {
        // Use BoxLayout with Y_AXIS for vertical stacking
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add padding using empty borders
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(30, 80, 30, 80),
                getBorder()
        ));
    }

    private void showTitle() {
        JLabel title = new JLabel("Thông tin cá nhân");
        title.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add title with spacing
        add(title);
        add(Box.createVerticalStrut(30)); // Replace wrap spacing
    }

    private void addComponent() {
        // Create form panel for field pairs
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add form fields in pairs
        for (int i = 0; i < labels.length; i++) {
            // Create horizontal panel for each label-component pair
            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
            fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Configure label
            labels[i].setHorizontalAlignment(SwingConstants.RIGHT);
            labels[i].setPreferredSize(new Dimension(120, labels[i].getPreferredSize().height));
            labels[i].setMinimumSize(new Dimension(120, labels[i].getPreferredSize().height));
            labels[i].setMaximumSize(new Dimension(120, labels[i].getPreferredSize().height));

            // Configure component
            components[i].setPreferredSize(new Dimension(400, components[i].getPreferredSize().height));
            components[i].setMaximumSize(new Dimension(400, components[i].getPreferredSize().height));

            // Add to field panel with spacing
            fieldPanel.add(labels[i]);
            fieldPanel.add(Box.createHorizontalStrut(20)); // 20px spacing between label and component
            fieldPanel.add(components[i]);
            fieldPanel.add(Box.createHorizontalGlue()); // Push everything to the left

            // Add field panel to form
            formPanel.add(fieldPanel);

            // Add vertical spacing between fields (except after last field)
            if (i < labels.length - 1) {
                formPanel.add(Box.createVerticalStrut(15));
            }
        }

        // Add form panel to main container
        add(formPanel);
        add(Box.createVerticalStrut(20)); // Gap before buttons

        // Create button panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Set consistent button styling
        changePasswordButton.setPreferredSize(new Dimension(120, 30));
        changePasswordButton.putClientProperty(FlatClientProperties.STYLE, "arc: 20");

        updateButton.setPreferredSize(new Dimension(120, 30));
        updateButton.putClientProperty(FlatClientProperties.STYLE, "arc: 20");

        // Center buttons horizontally
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(changePasswordButton);
        buttonsPanel.add(Box.createHorizontalStrut(10)); // 10px spacing between buttons
        buttonsPanel.add(updateButton);
        buttonsPanel.add(Box.createHorizontalGlue());

        // Add button panel to main layout
        add(buttonsPanel);
    }

    private void initComponent() {
        showTitle();
        addComponent();
    }

    private void changePassword(){
    }
    private void checkEmailInvalid(){
        emailTextField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent event){
                new CheckUserInformationInvalid(mainPanel).checkingEmailInvalid();
            }
        });
    }
    private void checkDateInvalid(){
        dobTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                new CheckUserInformationInvalid(mainPanel).checkingDateInvalid();
            }
        });
    }
    private void getInformation(){
        updateButton.addActionListener(e->{
           new PatientManagerController(this);
        });
    }
}
