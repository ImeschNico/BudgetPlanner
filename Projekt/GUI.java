import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



//Erstellen des GUI
public class GUI {

  public double totalIncomeLabel = 0.0;
  public JLabel totalIncomeLabelTextView = new JLabel("Totale Einnahmen: 0.0 CHF");
  private double totalExpenseLabel = 0.0;
  public JLabel totalExpenseLabelTextView = new JLabel("Totale Ausgaben: 0.0 CHF");
  public double totalBalance = 0.0;
  public JLabel totalBalanceLabelTextView = new JLabel("Kommuliertes Total: 0.0");
  public JPanel totalPanel = new RoundedPanel(15);
  private DefaultListModel<String> incomeListModel;
  private DefaultListModel<String> expenseListModel;
  
   public GUI(){

    incomeListModel = new DefaultListModel<>();
    expenseListModel = new DefaultListModel<>();

    // Erstellen des Frames
  JFrame frame = new JFrame("Budget Planner");
  frame.setSize(500, 700); 
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setLocationRelativeTo(null);
  frame.setResizable(false);
  frame.setLayout(null);
  ImageIcon icon = new ImageIcon("BudgetPlanner.png"); 
  frame.setIconImage(icon.getImage());


   // Erstellen des Titel Labels
   JLabel label = new JLabel("Budget Planner");
   label.setFont(new Font("Arial", Font.BOLD, 24));
   label.setHorizontalAlignment(SwingConstants.CENTER);
   label.setBounds(0, 40, 500,40);
   //erstellen des User Labels
   JLabel userLabel = new JLabel("User");
   userLabel.setBounds(20, 200, 80, 25);
   //ERstellen des Passwot Labels
   JLabel passwordLabel = new JLabel("Passwort");
   passwordLabel.setBounds(20, 250, 80, 25);
   //Erstellen der Result Label
   JLabel resultLabel = new JLabel();
   resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
   resultLabel.setBounds(100, 500, 300, 30);
   

   //Textfelder hinzufügen
   JTextField userTextField = new JTextField();
   userTextField.setBounds(150, 200, 200, 25);
   JPasswordField passwordTextField = new JPasswordField();
   passwordTextField.setBounds(150, 250, 200, 25);

   //Button erstellen
   JButton button = new JButton("Anmelden");
   button.setHorizontalAlignment(SwingConstants.CENTER);
   button.setBorderPainted(true);
   button.setFocusPainted(false);
   button.setBackground(Color.white);
   button.setBounds(100, 400, 300, 40);

   //Action Lisntener für Button
   button.addActionListener(new ActionListener() {

    //Vordefinertes User und Passwort
    final String validUsername = "admin";
    final String validPassword = "1234";

    @Override   //Sicherstellen, dass die Methode korrekt überschrieben wird

    public void actionPerformed(ActionEvent e){   //Methode, welche ausgeführt wird wenn der Button geklickt wird
     
      //Anmelde Logik kommt hier
      String username = userTextField.getText().trim(); //Eingabe USername
      String password = new String(passwordTextField.getPassword()).trim(); //Eingabe Passwort und Umwandeln in einen String

      //Überprüfen der Angaben
      if (username.equals(validUsername) && password.equals(validPassword)){
        resultLabel.setText("Anmeldung erfolgreich");
        resultLabel.setForeground(Color.green);

        //Button zu weiter Button machen und main Application öffnen
        button.setText("Weiter");
        button.removeActionListener(this);
        button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e){
            openMainApplication();
            frame.dispose();
            
          }
        });
      }else {
        resultLabel.setText("Anmeldung fehlgeschlagen");
        resultLabel.setForeground(Color.red);
        }
        
        //Label in frame hinzufügen
        frame.add(resultLabel);
        frame.repaint();
    }
   });

  //Hinzufügen des Label in das Frame
  frame.add(label);
  frame.add(userLabel);
  frame.add(passwordLabel);
  frame.add(userTextField);
  frame.add(passwordTextField);
  frame.add(button);

  //Sichtbarkeit des Frame
  frame.setVisible(true);
   }

   

   //Main Appliakation
   private void openMainApplication(){
    //Frame erstellen
    JFrame mainFrame = new JFrame("Budget Planner");
    mainFrame.getContentPane().setBackground(new Color(239, 235, 220));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    ImageIcon icon = new ImageIcon("BudgetPlanner.png"); 
    mainFrame.setIconImage(icon.getImage());
    mainFrame.setLayout(null);

    //Label erstellen
    Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);
    JLabel imageLabel = new JLabel(scaledIcon);
    imageLabel.setBounds(50, 40, 100, 100); 
    mainFrame.add(imageLabel);

    //Titel label erstellen
    JLabel titelLabel = new JLabel("Willkommen ");
    titelLabel.setBounds(250, 40, 500, 100);
    titelLabel.setFont(new Font("Arial", Font.BOLD, 24));;
    mainFrame.add(titelLabel);

    //Panel für Übersicht erstellen
    JPanel summaryPanel = new JPanel();
    summaryPanel.setBackground(new Color(239, 235, 220));
    summaryPanel.setLayout(new GridLayout(1, 3, 20, 0));
    summaryPanel.setBounds(50, 200, 1100,400);
    
    
    //Einkommen Panel
    RoundedPanel incomePanel = new RoundedPanel(15);
    incomePanel.setBackground(new Color(45, 203, 143));
    incomePanel.setLayout(new FlowLayout());
    JLabel incomeLabel = new JLabel("Einnahmen");
    incomeLabel.setFont(new Font("Arial", Font.BOLD, 25));
    incomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    incomeLabel.setVerticalAlignment(SwingConstants.TOP);
    incomePanel.add(incomeLabel, BorderLayout.CENTER);
    incomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  //Padding

    //Einkommen Liste erstellen
    JList<String> incomeList = new JList<>(incomeListModel);  // Liste mit dem Modell verbinden
    incomeList.setBackground(new Color(45, 203, 143));
    JScrollPane scrollPane = new JScrollPane(incomeList);    // Scroll Möglcihkeiten hinzufügen
    scrollPane.setPreferredSize (new Dimension(300, 300));
    scrollPane.getViewport().setBackground(new Color(45, 203, 143));
    incomePanel.add(scrollPane, BorderLayout.CENTER);

    //Button zum einfügen von Buchungen
    JButton incomeButton = new JButton("Hinzufügen");
    incomeButton.setHorizontalAlignment(SwingConstants.CENTER);
    incomeButton.setVerticalAlignment(SwingConstants.BOTTOM);
    incomeButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Padding
    incomeButton.setPreferredSize(new Dimension(100, 30));
    incomeButton.setFocusPainted(false);
    incomePanel.add(incomeButton, FlowLayout.CENTER);

    //Action Listener für Hinzufügen Button
    incomeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        createAddIncomePopUp();
      }
    });
     
    
    //Ausgaben Panel
    JPanel expensePanel = new RoundedPanel(15);
    expensePanel.setLayout(new FlowLayout());
    expensePanel.setBackground(new Color(204, 86, 85));
    JLabel expenseLabel = new JLabel("Ausgaben");
    expenseLabel.setFont(new Font("Arial", Font.BOLD, 25));
    expenseLabel.setHorizontalAlignment(SwingConstants.CENTER);
    expenseLabel.setVerticalAlignment(SwingConstants.TOP);
    expenseLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  //Padding
    expensePanel.add(expenseLabel, BorderLayout.CENTER);

    //Einkommen Liste erstellen
    JList<String> expenseList = new JList<>(expenseListModel);  // Liste mit dem Modell verbinden
    expenseList.setBackground(new Color(204, 86, 85));
    JScrollPane expensescrollPane = new JScrollPane(expenseList);    // Scroll Möglcihkeiten hinzufügen
    expensescrollPane.setPreferredSize (new Dimension(300, 300));
    expensescrollPane.getViewport().setBackground(new Color(45, 203, 143));
    expensePanel.add(expensescrollPane, BorderLayout.CENTER);

    //Ausgaben Button
    JButton expenseButton = new JButton("Hinzufügen");
    expenseButton.setHorizontalAlignment(SwingConstants.CENTER);
    expenseButton.setVerticalAlignment(SwingConstants.BOTTOM);
    expenseButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Padding
    expenseButton.setPreferredSize(new Dimension(100, 30));
    expenseButton.setFocusPainted(false);
    expensePanel.add(expenseButton, FlowLayout.CENTER);

    //Action Listener für Hinzufügen Button
    expenseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        createAddExpensePopUp();
      }
    });
     

    //Total Panel
    //JPanel totalPanel = new RoundedPanel(15);
    totalPanel.setBackground(Color.LIGHT_GRAY);
    totalPanel.setLayout(new GridLayout(0, 1, 0, 5));
    JLabel totalLabel = new JLabel("Total");
    totalLabel.setFont(new Font("Arial", Font.BOLD, 25));
    totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
    totalLabel.setVerticalAlignment(SwingConstants.TOP);
    totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    totalPanel.add(totalLabel);

    //Total Einnahmen übersicht erstellen
    totalIncomeLabelTextView.setHorizontalAlignment(SwingConstants.CENTER);
    totalIncomeLabelTextView.setFont(new Font("Arial", Font.BOLD, 17));
    totalPanel.add(totalIncomeLabelTextView);
    
    //Totale Ausgaben übersicht erstellen
    totalExpenseLabelTextView.setHorizontalAlignment(SwingConstants.CENTER);
    totalExpenseLabelTextView.setFont(new Font("Arial", Font.BOLD, 17));
    totalPanel.add(totalExpenseLabelTextView);
    
    //Total
    totalBalanceLabelTextView.setHorizontalAlignment(SwingConstants.CENTER);
    totalBalanceLabelTextView.setFont(new Font("Arial", Font.BOLD, 24));
    totalPanel.add(totalBalanceLabelTextView);

    //Panels zum Hauptpanel zufüfen
    summaryPanel.add(incomePanel);
    summaryPanel.add(expensePanel);
    summaryPanel.add(totalPanel);

    //Gesamtbehälter einfügen
    mainFrame.add(summaryPanel);

    mainFrame.setVisible(true);
   }

    //Methode für PopUp Fenster
   private void createAddIncomePopUp(){

    JDialog incomePopUp = new JDialog();
    incomePopUp.setTitle("Neue Einnahme");
    incomePopUp.setSize(400, 200);
    incomePopUp.setLocationRelativeTo(null);
    incomePopUp.setLayout(new BorderLayout());
    

    //Erstellen abgerundete Panels
    RoundedPanel roundedPanel = new RoundedPanel(15);
    roundedPanel.setLayout(new GridLayout(3, 2));
    

    //Erstellen ComboBox für Kategorien
    JLabel categoryLabel = new JLabel("Kategorie");
    String[] categories = {"Lohn", "Geschenk", "Sonstiges"};
    JComboBox<String> categoryComboBox = new JComboBox<>(categories);

    //Erstellen des Textfeld für Betrag
    JLabel amountLabel = new JLabel("Betrag");
    JTextField amountField = new JTextField();

    //erstellen des Speicher Button
    JButton incomeSaveButton =new JButton("Speichern");

    //Action Listener für save Button
    incomeSaveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        String category = (String) categoryComboBox.getSelectedItem();  //asugewählte Kategorie
        String amount = amountField.getText().trim();    // Betrag aus dem TextFeld holen

        
        if (!amount.isEmpty()){

          incomeListModel.addElement("Kategorie: "+ category + " |Betrag |"+ amount + " CHF");
          incomePopUp.dispose();

         try{     //Versuchen aus dem Betrag ein double zu machen
            double amountValue = Double.parseDouble(amount);
            totalIncomeLabel += amountValue;
            updateTotalBalance();
            repaintTotalPanel(totalPanel);
            String.valueOf(totalIncomeLabel);
            
            //Total Label aktualisieren
            totalIncomeLabelTextView.setText("Totale Einnahmen: " + totalIncomeLabel + "CHF");
  
            //Schleisse PopUp
            incomePopUp.dispose();
  
            }catch (NumberFormatException ex){    //Fals nicht möglich Fehlermeldung ausgeben
          JOptionPane.showMessageDialog(incomePopUp, "Ungültiger Betrag", "Fehler",JOptionPane.ERROR_MESSAGE);
          //einnahme zur Liste hinzufügen
          incomeListModel.addElement("Kategorie: "+ category + " |Betrag |"+ amount + " CHF");
          

          }
        }else{      //Felhermeldung wenn kein Betrag angegeben
          JOptionPane.showMessageDialog(incomePopUp, "Bitte Betrag eingeben", "Fehler", JOptionPane.ERROR_MESSAGE);

        }
      }
    });

    //alle KLomponenten in das ouded Panel hinzufügen
    roundedPanel.add(categoryLabel);
    roundedPanel.add(categoryComboBox);
    roundedPanel.add(amountLabel);
    roundedPanel.add(amountField);
    roundedPanel.add(new JLabel());
    roundedPanel.add(incomeSaveButton);

    

    //Panel ins Pop Up einfügen
    incomePopUp.add(roundedPanel);

    //Pop Up anzeigen
    incomePopUp.setVisible(true);

    
   }

   //Methode für Ausgaben
   private void createAddExpensePopUp(){
    JDialog expensePopUP = new JDialog();
    expensePopUP.setTitle("Neue Ausgabe");
    expensePopUP.setSize(400, 200);
    expensePopUP.setLocationRelativeTo(null);
    expensePopUP.setLayout(new BorderLayout());
    

    //Erstellen abgerundete Panels
    RoundedPanel roundedPanel = new RoundedPanel(15);
    roundedPanel.setLayout(new GridLayout(3, 2));
    

    //Erstellen ComboBox für Kategorien
    JLabel categoryLabel = new JLabel("Kategorie");
    String[] categories = {"Miete", "Essen", "Sonstiges"};
    JComboBox<String> categoryComboBox = new JComboBox<>(categories);

    //Erstellen des Textfeld für Betrag
    JLabel amaountLabel = new JLabel("Betrag");
    JTextField amaountField = new JTextField();

    //erstellen des Speicher Button
    JButton expenseSaveButton =new JButton("Speichern");

    //Action Listener für save Button
    expenseSaveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        String category = (String) categoryComboBox.getSelectedItem();  //asugewählte Kategorie
        String amount = amaountField.getText().trim();    // Betrag aus dem TextFeld holen

        if (!amount.isEmpty()){
          //ausgabe zur Liste hinzufügen
          expenseListModel.addElement("Kategorie: "+ category + " |Betrag |"+ amount + " CHF");
          expensePopUP.dispose();

          try{    //Versuchen den Eintrag in ein double zu verwandeln
            double expensAmountValue = Double.parseDouble(amount);
            totalExpenseLabel += expensAmountValue;
            updateTotalBalance();
            repaintTotalPanel(totalPanel);
            String.valueOf(totalExpenseLabel);
            
            //Total Label aktualisieren
            totalExpenseLabelTextView.setText("Totale Einnahmen: " + totalExpenseLabel + "CHF");
  
            //Schleisse PopUp
            expensePopUP.dispose();
  
            }catch (NumberFormatException ex){    //Fals nicht möglich Fehelermeldung
          JOptionPane.showMessageDialog(expensePopUP, "Ungültiger Betrag", "Fehler",JOptionPane.ERROR_MESSAGE);
          //Ausgabe zur Liste hinzufügen
          expenseListModel.addElement("Kategorie: "+ category + " |Betrag |"+ amount + " CHF");
          
          }

        }else{  //Fehelermeldung wenn kein Betrag eingegben wurde
          JOptionPane.showMessageDialog(expensePopUP, "Bitte Betrag eingeben", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
      
      }
    });
       //alle KLomponenten in das ouded Panel hinzufügen
       roundedPanel.add(categoryLabel);
       roundedPanel.add(categoryComboBox);
       roundedPanel.add(amaountLabel);
       roundedPanel.add(amaountField);
       roundedPanel.add(new JLabel());
       roundedPanel.add(expenseSaveButton);
   
       
   
       //Panel ins Pop Up einfügen
       expensePopUP.add(roundedPanel);
   
       //Pop Up anzeigen
       expensePopUP.setVisible(true);
   }

   public void updateTotalBalance(){
    String.valueOf(totalIncomeLabelTextView);
    String.valueOf(totalExpenseLabelTextView);
    totalBalance = totalIncomeLabel - totalExpenseLabel;
    repaintTotalPanel(totalPanel);
    
    totalBalanceLabelTextView.setText("Kommuliertes Total: "+ totalBalance + "CHF");
   }

   public void repaintTotalPanel(JPanel totalPanel){
    
    //Hintergrund anahand des Werts änder
    if (totalBalance >= 0){
      totalPanel.setBackground(new Color(45, 203, 143));
    }else{
      totalPanel.setBackground(new Color(204, 86, 85));
    }
    totalPanel.revalidate();
    totalPanel.repaint();
   }

    //Main Funktion
    public static void main(String[] args) {
      new GUI();
    }
}
