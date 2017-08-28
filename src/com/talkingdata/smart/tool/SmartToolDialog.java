package com.talkingdata.smart.tool;

import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

public class SmartToolDialog extends JDialog {
    private final static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
    private JPanel contentPane;
    private JTextField uuidField;
    private JButton newButton;
    private JTextField encodeSourceFiled;
    private JTextField encodeValueField;
    private JButton encodeButton;
    private JTextField decodeSourceField;
    private JTextField decodeValueField;
    private JButton decodeButton;
    private JTextField unicodeToField;
    private JTextField unicodeToValueField;
    private JButton toButton;
    private JTextField unicodeFromField;
    private JTextField unicodeFromValueField;
    private JButton fromButton;
    private JTextField passwordField;
    private JSpinner spinner1;
    private JButton copyButton;
    private JButton capButton;
    private JButton lowButton;
    private JButton copyButton1;
    private JButton copyButton2;
    private JButton copyButton3;
    private JButton copyButton4;
    private JButton copyButton5;
    private JButton newButton1;

    public SmartToolDialog() {
        setContentPane(contentPane);
        setModal(true);

        spinner1.setValue(6);
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uuid = UUID.randomUUID().toString();
                uuidField.setText(uuid);
            }
        });
        capButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(uuidField.getText())) {
                    uuidField.setText(uuidField.getText().toUpperCase());
                }
            }
        });

        lowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(uuidField.getText())) {
                    uuidField.setText(uuidField.getText().toLowerCase());
                }
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(uuidField.getText())) {
                    writeTextToClipboard(uuidField.getText());
                }
            }
        });
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(encodeSourceFiled.getText())) {
                    if (StringUtils.isNotBlank(encodeSourceFiled.getText())) {
                        String url = convertQueryParameter(encodeSourceFiled.getText(), (value) -> URLEncoder.encode(value, "UTF-8"));
                        if (StringUtils.isNotBlank(url)) {
                            encodeValueField.setText(url);
                        }
                    }
                }
            }
        });
        copyButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(encodeValueField.getText())) {
                    writeTextToClipboard(encodeValueField.getText());
                }
            }
        });
        copyButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(decodeValueField.getText())) {
                    writeTextToClipboard(decodeValueField.getText());
                }
            }
        });
        copyButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(unicodeToValueField.getText())) {
                    writeTextToClipboard(unicodeToValueField.getText());
                }
            }
        });
        copyButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(unicodeFromValueField.getText())) {
                    writeTextToClipboard(unicodeFromValueField.getText());
                }
            }
        });
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(decodeSourceField.getText())) {
                    String url = convertQueryParameter(decodeSourceField.getText(), (value) -> URLDecoder.decode(value, "UTF-8"));
                    if (StringUtils.isNotBlank(url)) {
                        decodeValueField.setText(url);
                    }
                }
            }
        });
        toButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(unicodeToField.getText())) {
                    try {
                        unicodeToValueField.setText(encodeStr(unicodeToField.getText()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        fromButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(unicodeFromField.getText())) {
                    try {
                        unicodeFromValueField.setText(uencodeStr(unicodeFromField.getText()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        newButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer value = (Integer) spinner1.getValue();
                if (value != null && value.intValue() > 0) {
                    passwordField.setText(RandomStringUtils.random(value, characters));
                }
            }
        });
        copyButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isNotBlank(passwordField.getText())) {
                    writeTextToClipboard(passwordField.getText());
                }
            }
        });
    }

    private static void writeTextToClipboard(String value) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(value), null);
    }

    private static String unicodeEscaped(char ch) {
        String returnStr;
        final String charEsc = "\\u";

        if (ch < 0x10) {
            returnStr = "000" + Integer.toHexString(ch);
        } else if (ch < 0x100) {
            returnStr = "00" + Integer.toHexString(ch);
        } else if (ch < 0x1000) {
            returnStr = "0" + Integer.toHexString(ch);
        } else
            returnStr = "" + Integer.toHexString(ch);

        return charEsc + returnStr;
    }

    private String encodeStr(String nationalString) throws UnsupportedEncodingException {
        String convertedString = "";

        for (int i = 0; i < nationalString.length(); i++) {
            Character chs = nationalString.charAt(i);
            convertedString += unicodeEscaped(chs);
        }
        return convertedString;
    }

    private String uencodeStr(String escapedString) throws UnsupportedEncodingException {
        String convertedString = "";

        String[] arrStr = escapedString.split("\\\\u");
        String str, istr;
        for (int i = 1; i < arrStr.length; i++) {
            str = arrStr[i];
            if (!str.isEmpty()) {
                Integer iI = Integer.parseInt(str, 16);
                char[] chaCha = Character.toChars(iI);
                convertedString += String.valueOf(chaCha);
            }
        }
        return convertedString;
    }

    private String convertQueryParameter(String uri, QueryParameterConverter converter) {
        try {
            URL url = new URL(uri);

            StringBuilder sb = new StringBuilder(url.getProtocol())
                    .append("://")
                    .append(url.getAuthority())
                    .append(url.getPath());
            if (StringUtils.isNotBlank(url.getQuery())) {
                StringBuilder query = new StringBuilder();
                String[] array = url.getQuery().split("&");
                for (String value : array) {
                    int index = value.indexOf("=");
                    if (index > 0) {
                        query.append("&")
                                .append(value.substring(0, index))
                                .append("=")
                                .append(converter.convert(value.substring(index + 1)));
                    }
                }
                if (query.length() > 0) {
                    sb.append(query.replace(0, 1, "?"));
                }
            }

            if (StringUtils.isNotBlank(url.getRef())) {
                sb.append("#").append(url.getRef());
            }
            return sb.toString();
        } catch (Exception ex) {
            Messages.showErrorDialog("Error", ex.getMessage());
        }
        return null;
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.google.com/search?q=java+parse+url+example&oq=java+url+parse+ex&gs_l=psy-ab.3.0.0i8i30k1l3.5079.5511.0.7531.3.3.0.0.0.0.785.938.0j1j6-1.2.0....0...1.1.64.psy-ab..1.2.938...0j0i30k1.44-fIbAo4p8#t7");
    }

    interface QueryParameterConverter {
        String convert(String value) throws Exception;
    }
}
