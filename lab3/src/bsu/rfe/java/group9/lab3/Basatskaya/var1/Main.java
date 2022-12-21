package bsu.rfe.java.group9.lab3.Basatskaya.var1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
class MainFrame extends JFrame {
    // Константы с исходным размером окна приложения
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    // Массив коэффициентов многочлена
    private Double[] coefficients;
    private JFileChooser fileChooser = null;// Объект диалогового окна для выбора файлов
    // Компонент не создаѐтся изначально, т.к. может и не понадобиться
// пользователю если тот не собирается сохранять данные в файл
    private JMenuItem saveToTextMenuItem; // Элементы меню вынесены в поля данных класса, так как ими необходимо
    // манипулировать из разных мест
    private JMenuItem saveToGraphicsMenuItem;
    private JMenuItem searchValueMenuItem;
    private JMenuItem getInfoAboutAuther;
    // Поля ввода для считывания значений переменных
    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JTextField textFieldStep;
    private Box hBoxResult;
    private GornerTableCellRenderer renderer = new GornerTableCellRenderer();// Визуализатор ячеек таблицы
    private GornerTableModel data;// Модель данных с результатами вычислений

    public MainFrame(Double[] coefficients) {// Обязательный вызов конструктора предка
        super("Gornor count polinomial");
        this.coefficients = coefficients;// Запомнить во внутреннем поле переданные коэффициенты
        setSize(WIDTH, HEIGHT);// Установить размеры окна
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,// Отцентрировать окно приложения на экране
                (kit.getScreenSize().height - HEIGHT) / 2);
        JMenuBar menuBar = new JMenuBar();// Создать меню
        setJMenuBar(menuBar);// Установить меню в качестве главного меню приложения
        JMenu fileMenu = new JMenu("File");// Добавить в меню пункт меню "Файл"
        menuBar.add(fileMenu);// Добавить его в главное меню
        JMenu tableMenu = new JMenu("Table");// Создать пункт меню "Таблица"
        menuBar.add(tableMenu);// Добавить его в главное меню

        JMenu AboutMenu = new JMenu("About program");
        menuBar.add(AboutMenu);
        // Создать новое "действие" по сохранению в текстовый файл
        Action saveToTextAction = new AbstractAction("Save txt file") {
            public void actionPerformed(ActionEvent event) {
                if (fileChooser == null) {// Если экземпляр диалогового окна "Открыть
                   // файл" ещѐ не создан,то создать его
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));// и инициализировать текущей директорией

                }
                // Показать диалоговое окно
                if (fileChooser.showSaveDialog(MainFrame.this) ==
                        JFileChooser.APPROVE_OPTION)
                    saveToTextFile(fileChooser.getSelectedFile());// Если результат его показа успешный,
// сохранить данные в текстовый файл
            }
        };
        // Добавить соответствующий пункт подменю в меню "Файл"
        saveToTextMenuItem = fileMenu.add(saveToTextAction);
        // По умолчанию пункт меню является недоступным (данных ещѐ нет)
        saveToTextMenuItem.setEnabled(false);
        Action saveToGraphicsAction = new AbstractAction("Save data to create graphics") {
            public void actionPerformed(ActionEvent event) {
                if (fileChooser == null) {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if (fileChooser.showSaveDialog(MainFrame.this) ==
                        JFileChooser.APPROVE_OPTION);
                saveToGraphicsFile(fileChooser.getSelectedFile());
            }
        };

        saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);
        saveToGraphicsMenuItem.setEnabled(false);
        // Создать новое действие по поиску значений многочлена
        Action searchValueAction = new AbstractAction("Find polynomial value") {
            public void actionPerformed(ActionEvent event) {
                // Запросить пользователя ввести искомую строку
                String value =
                        JOptionPane.showInputDialog(MainFrame.this, "Enter fiend value",
                                "Find value", JOptionPane.QUESTION_MESSAGE);
                renderer.setNeedle(value);// Установить введенное значение в качестве иголки
                getContentPane().repaint();// Обновить таблицу
            }
        };


        searchValueMenuItem = tableMenu.add(searchValueAction);// Добавить действие в меню "Таблица"
        searchValueMenuItem.setEnabled(false);// По умолчанию пункт меню является недоступным (данных ещѐ нет)

        Action GetInfo = new AbstractAction("About author") {
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(null,"Anastasiya Basatskaya, group 9", "Author",
                        JOptionPane.QUESTION_MESSAGE);
            }
        };

        getInfoAboutAuther = AboutMenu.add(GetInfo);
        getInfoAboutAuther.setEnabled(true);
        // Создать область с полями ввода для границ отрезка и шага
        // Создать подпись для ввода левой границы отрезка
        JLabel labelForFrom = new JLabel("X changes from:");
        // Создать текстовое поле для ввода значения длиной в 10 символов
        // со значением по умолчанию 0.0
        textFieldFrom = new JTextField("0.0", 10);
        // Установить максимальный размер равный предпочтительному, чтобы
        // предотвратить увеличение размера поля ввода
        textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());
        JLabel labelForTo = new JLabel("to:");// Создать подпись для ввода левой границы отрезка
        textFieldTo = new JTextField("1.0", 10);// Создать текстовое поле для ввода значения длиной в 10 символов
        // со значением по умолчанию 1.0

        textFieldTo.setMaximumSize(textFieldTo.getPreferredSize());// Установить максимальный размер равный предпочтительному, чтобы
       // предотвратить увеличение размера поля ввода

        JLabel labelForStep = new JLabel("by step:");// Создать подпись для ввода шага табулирования
        textFieldStep = new JTextField("0.1", 10);// Создать текстовое поле для ввода
        // значения длиной в 10 символов со значением по умолчанию 0.1
        textFieldStep.setMaximumSize(textFieldStep.getPreferredSize());// Установить максимальный размер равный
        // предпочтительному, чтобы предотвратить увеличение размера поля ввода
        Box hboxRange = Box.createHorizontalBox();// Создать контейнер 1 типа "коробка с горизонтальной укладкой"
        hboxRange.setBorder(BorderFactory.createBevelBorder(1));// Задать для контейнера тип рамки "объѐмная"
        //добавляем "клеи" и "распорки"
        hboxRange.add(Box.createHorizontalGlue());
        hboxRange.add(labelForFrom);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldFrom);
        hboxRange.add(Box.createHorizontalGlue());
        hboxRange.add(labelForTo);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldTo);
        hboxRange.add(Box.createHorizontalGlue());
        hboxRange.add(labelForStep);// Добавить подпись "с шагом"
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(textFieldStep);// Добавить поле для ввода шага табулирования
        hboxRange.add(Box.createHorizontalGlue());

        // Установить предпочтительный размер области равным удвоенному
        // минимальному, чтобы при компоновке область совсем не сдавили
        hboxRange.setPreferredSize(new Dimension(
                Double.valueOf(hboxRange.getMaximumSize().getWidth()).intValue(),
                Double.valueOf(hboxRange.getMinimumSize().getHeight()).intValue() * 2));
        getContentPane().add(hboxRange, BorderLayout.NORTH);// Установить область в верхнюю (северную) часть компоновки
        JButton buttonCalc = new JButton("Count");// Создать кнопку "Вычислить"
        buttonCalc.addActionListener(new ActionListener() {// Задать действие на нажатие "Вычислить" и привязать к кнопке
            public void actionPerformed(ActionEvent ev) {
                try {
                    // Считать значения начала и конца отрезка, шага
                    Double from = Double.parseDouble(textFieldFrom.getText());
                    Double to = Double.parseDouble(textFieldTo.getText());
                    Double step = Double.parseDouble(textFieldStep.getText());
                    // На основе считанных данных создать новый экземпляр модели таблицы
                    data = new GornerTableModel(from, to, step, MainFrame.this.coefficients);
                    JTable table = new JTable(data);// Создать новый экземпляр таблицы
                    table.setDefaultRenderer(Double.class, renderer);// Установить в качестве визуализатора ячеек для
                    //класса Double разработанный визуализатор
                    table.setRowHeight(30);// Установить размер строки таблицы в 30 пикселей
                    hBoxResult.removeAll();// Удалить все вложенные элементы из контейнера hBoxResult
                    hBoxResult.add(new JScrollPane(table));// Добавить в hBoxResult таблицу, "обѐрнутую" в
                    //панель с полосами прокрутки
                    getContentPane().validate();// Обновить область содержания главного окна
                    // Пометить ряд элементов меню как доступных
                    saveToTextMenuItem.setEnabled(true);
                    saveToGraphicsMenuItem.setEnabled(true);
                    searchValueMenuItem.setEnabled(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Error folatin num entering", "Error number format",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Clear");// Создать кнопку "Очистить поля"
        buttonReset.addActionListener(new ActionListener() {// Задать действие на нажатие "Очистить поля"
            // и привязать к кнопке
            public void actionPerformed(ActionEvent ev) {
                textFieldFrom.setText("0.0");
                textFieldTo.setText("1.0");
                textFieldStep.setText("0.1");
                hBoxResult.removeAll();
                hBoxResult.add(new JPanel());
                saveToTextMenuItem.setEnabled(false);
                saveToGraphicsMenuItem.setEnabled(false);
                searchValueMenuItem.setEnabled(false);
                getContentPane().validate();
            }
        });
        // Поместить созданные кнопки в контейнер
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.setBorder(BorderFactory.createBevelBorder(1));
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setPreferredSize(new Dimension(Double.valueOf(hboxButtons.getMaximumSize().getWidth()).intValue(),
                Double.valueOf(hboxButtons.getMinimumSize().getHeight()).intValue() * 2));
        getContentPane().add(hboxButtons, BorderLayout.SOUTH);
        hBoxResult = Box.createHorizontalBox();
        hBoxResult.add(new JPanel());
        getContentPane().add(hBoxResult, BorderLayout.CENTER);
    }

    protected void saveToGraphicsFile(File selectedFile) {
        try {// Создать новый байтовый поток вывода, направленный в указанный файл

            DataOutputStream out = new DataOutputStream(new FileOutputStream(selectedFile));
            // Записать в поток вывода попарно значение X в точке, значение многочлена в точке
            for (int i = 0; i < data.getRowCount(); i++) {
                out.writeDouble((Double) data.getValueAt(i, 0));
                out.writeDouble((Double) data.getValueAt(i, 1));
                out.writeDouble((Double) data.getValueAt(i, 2));
            }
            out.close(); // Закрыть поток вывода
        } catch (Exception e) {// Исключительную ситуацию "ФайлНеНайден" в данном случае
        //можно не обрабатывать,
// так как мы файл создаѐм, а не открываем для чтения
        }
    }


    protected void saveToTextFile(File selectedFile) {
        try {// Создать новый символьный поток вывода, направленный в указанный файл

            PrintStream out = new PrintStream(selectedFile);
// Записать в поток вывода заголовочные сведения
            out.println("Result of polinomial by Gornar");
            out.print("Polinomial: ");
            for (int i = 0; i < coefficients.length; i++) {
                out.print(coefficients[i] + "*X^" +
                        (coefficients.length - i - 1));

                if (i != coefficients.length - 1)
                    out.print(" + ");
            }
            out.println("");
            out.println("Interval from " + data.getFrom() + " to " +
                    data.getTo() + " bt step " + data.getStep());
            out.println("====================================================");
            // Записать в поток вывода значения в точках
            for (int i = 0; i < data.getRowCount(); i++) {
                out.println("Val in the spot " + data.getValueAt(i, 0)
                        + " equal " + data.getValueAt(i, 1) +
                        " is primal: " + data.getValueAt(i, 2));
            }

            out.close();// Закрыть поток
        } catch (FileNotFoundException e) {// Исключительную ситуацию "ФайлНеНайден" можно не
// обрабатывать, так как мы файл создаѐм, а не открываем
        }
    }

    public static void main(String[] args) {
        // Если не задано ни одного аргумента командной строки -
// Продолжать вычисления невозможно, коэффиценты неизвестны
        if (args.length == 0) {
            System.out.println("Try to add some coefficients in the args[]!");
            System.exit(-1);
        }
        // Зарезервировать места в массиве коэффициентов столько, сколько
        //аргументов командной строки

        Double[] coefficients = new Double[args.length];
        int i = 0;
        try {
            // Перебрать аргументы, пытаясь преобразовать их в Double
            for (String arg : args) {
                coefficients[i++] = Double.parseDouble(arg);
            }
        } catch (NumberFormatException ex) {
            // Если преобразование невозможно - сообщить об ошибке и завершиться

            System.out.println("Error in string converting '" +
                    args[i] + "' in Double");
            System.exit(-2);
        }
// Создать экземпляр главного окна, передав ему коэффициенты
        MainFrame frame = new MainFrame(coefficients);
        // Задать действие, выполняемое при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
