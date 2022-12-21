package bsu.rfe.java.group9.lab1.Basatskaya;

public class Apple extends Food{private String size;

    public Apple(String size)
    {
        super("Яблоко");
        this.size = size;//инициализировать размер яблока
    }



    public void consume() {
        System.out.println(this + " съедено");
    }//инициализировать размер яблока

    public String getSize() {
        return size;
    }//селектор для доступа к полю данных РАЗМЕР

    public void setSize(String size) {
        this.size = size;
    }//модификатор для изменения поля данных РАЗМЕР

    public boolean equals(Object arg0) {//Используется для проверки равенства двух объектов. Сущность
        //метода различна для разных классов. обычно он возвращает true, если имеются две ссылки на один объект.
        //класс String возвращает true и в том случае, если символы двух строк совпадают

        if (super.equals(arg0)) {
            if (!(arg0 instanceof Apple)) return false;
            return size.equals(((Apple)arg0).size);
        } else
            return false;
    }
    // Переопределѐнная версия метода toString(), возвращающая не только
// название продукта, но и его размер

    public String toString() {
        return super.toString() + " размера '" + size.toUpperCase() + "'";
    }
}