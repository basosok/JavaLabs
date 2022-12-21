package bsu.rfe.java.group9.lab2.Basatskaya.var1;

public class Formula {
    private double sum;

    public Formula()
    {
        sum =0;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = this.sum+sum;
    }
    public void resert() {
        this.sum = 0;

    }

}
