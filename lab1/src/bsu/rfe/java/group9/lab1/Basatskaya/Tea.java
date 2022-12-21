package bsu.rfe.java.group9.lab1.Basatskaya;

public class Tea extends Food {//модификатор public описвает открытый класс, который может быть
    //переопределен или расширен м помощью любого другого элемента из
    //того же пакета или импортируемого в класс из другого пакета
    private String color;
    public Tea(String color)
    {
        super("Чай");
        this.color = color;
    }

    public void consume() {
        System.out.println(this + " выпили");
    }

    public String getSize() {
        return color;
    }

    public void setSize(String color) {
        this.color = color;
    }

    public boolean equals(Object arg0) {
        if (super.equals(arg0)) {
            if (!(arg0 instanceof Tea)) return false;
            return color.equals(((Tea)arg0).color);
        } else
            return false;
    }

    public String toString() {
        return super.toString() + " цвет '" + color.toUpperCase() + "'";
    }
}

