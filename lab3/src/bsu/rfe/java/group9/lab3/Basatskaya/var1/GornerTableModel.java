package bsu.rfe.java.group9.lab3.Basatskaya.var1;

import javax.swing.table.AbstractTableModel;
import java.math.BigInteger;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
        // В данной модели три столбца
        return 3;
    }
    public int getRowCount() {
        // Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return Double.valueOf(Math.ceil((to-from)/step)).intValue()+1;
    }

    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;
        Double result = 0.0;
        switch (col) {
            case 0:
                // Если запрашивается значение 1-го столбца, то это X
                return x;
            case 1: {
                // Если запрашивается значение 2-го столбца, то это значение
                // многочлена
                for (int i = coefficients.length - 1; i >= 0; i--) {
                    result += coefficients[i] * Math.pow(x, coefficients.length - 1 - i);
                }
                return result;
            }
            case 2: {
                for (int i = coefficients.length - 1; i >= 0; i--) {
                    result += coefficients[i] * Math.pow(x, coefficients.length - 1 - i);
                }
                String str = String.valueOf(result);
                String final_string = "";
                int i = 0;
                while(str.charAt(i) != '.'){
                    final_string += str.charAt(i);
                    i++;
                }
                BigInteger big = BigInteger.valueOf(Integer.valueOf(final_string));
                boolean prime = big.isProbablePrime((int) Math.log(Integer.valueOf(final_string)));
                return Boolean.valueOf(prime);
            }
        }

        return result;
    }
    public String getColumnName(int col) {
        switch (col) {
            // Название 1-го столбца
            case 0: return "Value of X";
            // Название 2-го столбца
            case 1: return "Forward value";
            // Название 3-го столбца
            case 2: return "Is [num] prime?";
        }
        return "";
    }
    public Class<?> getColumnClass(int col) {
        return Double.class;
    }
}
