import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        new gui();
    }
}
class gui implements ActionListener {

    JFrame frame;

    //declares decimal format to round up to three decimal points
    DecimalFormat threepoints = new DecimalFormat("#.###");

    //declare gui elements
    JTextField n1textfield;
    JLabel n1label;

    JTextField n2textfield;
    JLabel n2label;

    JLabel resultlabel;
    String result;

    JTextPane resultnumber;

    JButton calculate;

    //declare variables for calculation
    double n1parsed;
    double n2parsed;

    //declare font
    Font defaultfont = Font.getFont(Font.MONOSPACED);

    // stupid metadata maybe ill change it to something else later
    String version = "0";

    public gui() {
        //set background colors
        Color bg = new Color(35,35,35);
        Color bg2 = new Color(32,32,32);
        Color border = new Color(40,40,40);

        frame = new JFrame();

        n1textfield = new JTextField();
        n1textfield.setBounds(100, 10,150, 20);

        n1textfield.setBackground(bg2);
        n1textfield.setForeground(Color.WHITE);
        n1textfield.setCaretColor(Color.WHITE);
        n1textfield.setBorder(BorderFactory.createLineBorder(border));
        n1textfield.setFont(defaultfont);

        n1label = new JLabel("Number 1:");
        n1label.setBounds(20, 10, 80, 20);
        n1label.setForeground(Color.white);
        n1label.setFont(defaultfont);

        n2textfield = new JTextField();
        n2textfield.setBounds(100, 40,150, 20);
        n2textfield.setFont(defaultfont);

        n2textfield.setBackground(bg2);
        n2textfield.setForeground(Color.WHITE);
        n2textfield.setCaretColor(Color.WHITE);
        n2textfield.setBorder(BorderFactory.createLineBorder(border));

        n2label = new JLabel("Number 2:");
        n2label.setBounds(20, 40, 80, 20);
        n2label.setForeground(Color.WHITE);
        n2label.setFont(defaultfont);

        calculate = new JButton("Calculate");
        calculate.setBounds(20, 70, 230, 20);
        calculate.addActionListener(this);// adds so that something happens when clicked (see bottom)
        calculate.setFont(defaultfont);

        calculate.setBackground(bg);
        calculate.setForeground(Color.WHITE);
        calculate.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        calculate.setFocusPainted(false);


        resultlabel = new JLabel("Result:");//adds result label and centers it
        resultlabel.setBounds(20, 100, 80, 20);
        resultlabel.setForeground(Color.WHITE);//sets color
        resultlabel.setFont(defaultfont);

        resultnumber = new JTextPane();
        resultnumber.setBounds(100, 100, 150, 20);//size, position
        resultnumber.setBackground(bg2);
        resultnumber.setBorder(BorderFactory.createLineBorder(border));
        resultnumber.setForeground(Color.WHITE);
        resultnumber.setFont(defaultfont);

        //adds panel and stuff
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        // just makes the panel layout to be more customizable
        panel.setLayout(null);

        //sets background color
        panel.setBackground(bg);

        //adds all elements
        panel.add(n1label);
        panel.add(n1textfield);
        panel.add(n2label);
        panel.add(n2textfield);
        panel.add(resultlabel);
        panel.add(calculate);
        panel.add(resultlabel);
        panel.add(resultnumber);

        //misc stuff that i need
        frame.add(panel, BorderLayout.CENTER); //no freaking idea what this does but wont work without it
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // yeah it exits on close
        frame.setTitle("Fast Pythagoras v" + version); //title
        frame.setSize(270, 166); // i don't know why the height is so awkward but whatever floats this boat
        frame.setResizable(false); // makes user unable to resize; no need to
        frame.setVisible(true); // makes the window visible ig

    }

    //what happens when button is clicked
    @Override
    public void actionPerformed(ActionEvent ev) {

        //try will prevent crashes
        try {
            //checks if text box is empty
            if (!n1textfield.getText().isEmpty()) {
                n1parsed = Double.parseDouble(n1textfield.getText().replace(",", ".")); //if text box is full, number is double version of it. also fixes decimal numbers with commas not working
            } else {
                n1parsed = 0; // if text box is empty, number is 0
            }

            //same thing with number 2
            if (!n2textfield.getText().isEmpty()) {
                n2parsed = Double.parseDouble(n2textfield.getText().replace(",", "."));
            } else {
                n2parsed = 0;
            }

            //sets the result that is rounded up to 3 decimal points
            result = threepoints.format(Math.sqrt(Math.pow(n1parsed, 2) + Math.pow(n2parsed, 2)));

            //self explanatory i think
            resultnumber.setText(result);
        } catch (NumberFormatException | NullPointerException ex) {
            //if something goes wrong set result label to this instead of crashing
            resultnumber.setText("Invalid input");
        }
    }
}
