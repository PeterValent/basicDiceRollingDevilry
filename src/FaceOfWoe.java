import javax.swing.*;
import java.awt.*;

public class FaceOfWoe {
    JTextField inField;
    JLabel outField1, outField2, outField3;
    JButton normalButton, advButton, disButton, d20Button, d4Button, d8Button, d10Button, d12Button, d100Button;
    RollingDice theRoll;

    FaceOfWoe() {
        JFrame frame = new JFrame();
        //region diceButtons
        d4Button = new JButton("d4");
        d4Button.setBounds(40, 40, 30, 20);
        d4Button.setMargin(new Insets(0,0,0,0));
        d4Button.addActionListener(e -> {
            inField.setText("d4");
        });
        d8Button = new JButton("d8");
        d8Button.setBounds(70, 40, 30, 20);
        d8Button.setMargin(new Insets(0,0,0,0));
        d8Button.addActionListener(e -> {
            inField.setText("d8");
        });
        d10Button = new JButton("d10");
        d10Button.setBounds(100, 40, 30, 20);
        d10Button.setMargin(new Insets(0,0,0,0));
        d10Button.addActionListener(e -> {
            inField.setText("d10");
        });
        d12Button = new JButton("d12");
        d12Button.setBounds(130, 40, 30, 20);
        d12Button.setMargin(new Insets(0,0,0,0));
        d12Button.addActionListener(e -> {
            inField.setText("d12");
        });
        d20Button = new JButton("d20");
        d20Button.setBounds(160, 40, 30, 20);
        d20Button.setMargin(new Insets(0,0,0,0));
        d20Button.addActionListener(e -> {
            inField.setText("d20");
        });
        d100Button = new JButton("d100");
        d100Button.setBounds(190, 40, 35, 20);
        d100Button.setMargin(new Insets(0,0,0,0));
        d100Button.addActionListener(e -> {
            inField.setText("d100");
        });
        //endregion
        inField = new JTextField();
        inField.setBounds(40, 60, 185, 20);
        normalButton = new JButton("Normal Roll");
        normalButton.setBounds(40, 85, 185, 20);
        normalButton.addActionListener(e -> {
            outField1.setText("");
            outField2.setText("");
            outField3.setText("");
            outField1.setText(new RollingDice(inField.getText(), 0).output.get(0));
        });
        advButton = new JButton("Advantage");
        advButton.setBounds(40, 110, 185, 20);
        advButton.addActionListener(e -> {
            outField1.setText("");
            outField2.setText("");
            outField3.setText("");
            outField2.setForeground(Color.BLACK);
            outField3.setForeground(Color.BLACK);
            theRoll = new RollingDice(inField.getText(), 1);
            outField1.setText(theRoll.output.get(0));
            outField2.setText(theRoll.output.get(1));
            outField3.setText(theRoll.output.get(2));
            if (outField2.getText().length() > outField3.getText().length()) {
                outField2.setForeground(Color.GREEN);
            } else {
                outField3.setForeground(Color.GREEN);
            }
        });
        disButton = new JButton("Disadvantage");
        disButton.setBounds(40, 135, 185, 20);
        disButton.addActionListener(e -> {
            outField1.setText("");
            outField2.setText("");
            outField3.setText("");
            outField2.setForeground(Color.BLACK);
            outField3.setForeground(Color.BLACK);
            theRoll = new RollingDice(inField.getText(), 2);
            outField1.setText(theRoll.output.get(0));
            outField2.setText(theRoll.output.get(1));
            outField3.setText(theRoll.output.get(2));
            if (outField2.getText().length() > outField3.getText().length()) {
                outField2.setForeground(Color.RED);
            } else {
                outField3.setForeground(Color.RED);
            }
        });
        outField1 = new JLabel();
        outField1.setBounds(40, 160, 185, 20);
        outField2 = new JLabel();
        outField2.setBounds(40, 180, 185, 20);
        outField3 = new JLabel();
        outField3.setBounds(40, 200, 185, 20);
        frame.add(inField);
        frame.add(outField1);
        frame.add(outField2);
        frame.add(outField3);
        frame.add(normalButton);
        frame.add(advButton);
        frame.add(disButton);
        frame.add(d20Button);
        frame.add(d4Button);
        frame.add(d8Button);
        frame.add(d10Button);
        frame.add(d12Button);
        frame.add(d100Button);
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
