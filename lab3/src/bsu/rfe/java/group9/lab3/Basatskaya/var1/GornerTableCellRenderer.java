package bsu.rfe.java.group9.lab3.Basatskaya.var1;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private String needle = null;// Ищем ячейки, строковое представление которых совпадает с needle
    // (иголкой). Применяется аналогия поиска иголки в стоге сена, в роли
     // стога сена - таблица
    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();

    public GornerTableCellRenderer() {

        formatter.setMaximumFractionDigits(5);// Показывать только 5 знаков после запятой
        // Не использовать группировку (т.е. не отделять тысячи
// ни запятыми, ни пробелами), т.е. показывать число как "1000",
// а не "1 000" или "1,000"
        formatter.setGroupingUsed(false);
        // Установить в качестве разделителя дробной части точку, а не
// запятую. По умолчанию, в региональных настройках
// Россия/Беларусь дробная часть отделяется запятой
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        panel.add(label);// Разместить надпись внутри панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));// Установить выравнивание надписи по левому краю панели
    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(col == 2){

            label.setText(String.valueOf(value));

            //if(String.valueOf(value) == "true"){
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            //}

            panel.setBackground(Color.white);
            return panel;
        }
// Преобразовать double в строку с помощью форматировщика
        String formattedDouble = formatter.format(value);
        label.setText(formattedDouble);// Установить текст надписи равным строковому представлению числа



        if (col == 1 && needle != null && needle.equals(formattedDouble)) {
            // Номер столбца = 1 (т.е. второй столбец) + иголка не null
// (значит что-то ищем) +
// значение иголки совпадает со значением ячейки таблицы -
// окрасить задний фон панели в красный цвет
            panel.setBackground(Color.RED);
        } else {
            panel.setBackground(Color.white);// Иначе - в обычный белый



            double num = Double.valueOf(label.getText());
            int i = 0;
            String str = String.valueOf(num);
            while(i < str.length() && str.charAt(i++) != '.'){}
            for(; i < str.length(); i++){
                if(str.charAt(i) == '1' || str.charAt(i) == '3' || str.charAt(i) == '5'){
                    panel.setBackground(Color.orange);//ячейки, в дробной части которых
                   // присутствуют только числа 1, 3, 5, окрашиваются в оранжевый цвет
                } else {
                    panel.setBackground(Color.white);
                    break;
                }
            }
        }

        return panel;
    }
    public void setNeedle(String needle) {
        this.needle = needle;
    }
}