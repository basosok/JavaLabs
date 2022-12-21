package bsu.rfe.java.group9.lab1.Basatskaya;

public abstract class Food implements Consumable{

    String name = null;
    int size;
    public Food(String name) {
        this.name = name;
    }

    public boolean equals(Object arg0) {//
        if (!(arg0 instanceof Food)) return false;//шаг 1 позволяет с помощью оператора instanceof определить потомков
        //(т.к. аргументом метода equals() является ссылка на самый общий тип Object)
        //этим самым проверяется совместимость классов объектов.
        if (name==null || ((Food)arg0).name==null) return false;//проверкой равенства внутреннего поля константе null
        //позволяет определить, полностью ли сконструированы наш объект (равенсивр которого проверяется) и объект-аргумент
        //(равенство с которым проверяется)
        //при этом,  так как для объекта класса Object внутреннее поле name не определено, а на шаге 1 мы убедились , что arg0
        //есть экземпляр класса Food
        //или какого-либо из его потомков, то необходимо привести его к типу Food.
        return name.equals(((Food)arg0).name);//проверкой равенства поля name у обоих объектов определяет
        //результат всей операции (равенство или неравенство объектов)
    }
    public String toString() {
        return name;
    }//преобразует состояние объекта в строковое представление
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
