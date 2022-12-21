package bsu.rfe.java.group9.lab2.Basatskaya.var1;
// Импортируются классы, используемые в приложении
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MainFrame extends JFrame
        // Главный класс приложения, он же класс фрейма
{
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 450;
    private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    // Текстовое поле для отображения результата
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    // Текстовое поле для отображения суммы с накоплением результата
    private JTextField textFieldSum;


    private ButtonGroup radioButtons = new ButtonGroup();// Группа радио-кнопок для обеспечения
    // уникальности выделения в группе

    private Box hboxFormulaType = Box.createHorizontalBox();// Контейнер для отображения радио-кнопок
    private int formulaId = 1;

    Formula formula = new Formula();

    public Double calculate1(Double x, Double y,Double z)// Формула №1 для рассчѐта
    {
        double a = Math.cos(Math.pow(Math.E, x))+Math.log(Math.pow(1+y, 2));
        double b = Math.sqrt(Math.pow(Math.E,Math.cos(x)+Math.pow(Math.sin(Math.PI*z), 2)));
        double c = Math.sqrt(1/x)+Math.cos(Math.pow(y, 2));
        return Math.pow(a+b+c,Math.sin(z));

    }

    public Double calculate2(Double x, Double y,Double z)// Формула №2 для рассчѐта
    {
        double a= Math.pow(1+Math.pow(x,2),1.0/y);
        double b= Math.pow(Math.E, Math.sin(z)+x);
        return a/b;
    }
    private void addRadioButton(String buttonName, final int formulaId)// Вспомогательный метод
    // для добавления кнопок на панель
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener()
                                 {
                                     public void actionPerformed(ActionEvent ev)
                                     {
                                         MainFrame.this.formulaId = formulaId;
                                         //imagePane.updateUI();
                                     }
                                 }
        );
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }


    public MainFrame() {// Конструктор класса
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));
        // Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
        // Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        JLabel sum = new JLabel ("Сумма :");
        //labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        textFieldSum = new JTextField("0",10);
        textFieldSum.setMaximumSize(
                textFieldSum.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(sum);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldSum);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        // Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y,z);
                    else
                        result = calculate2(x, y,z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        JButton MC = new JButton("MC");
        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                formula.resert();
                textFieldSum.setText("0");
            }

        });
        JButton Mplus = new JButton("M+");
        Mplus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                formula.setSum(Double.parseDouble(textFieldResult.getText()));
                textFieldSum.setText(Double.toString(formula.getSum()));

            }

        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(MC);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(Mplus);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));
        // Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}