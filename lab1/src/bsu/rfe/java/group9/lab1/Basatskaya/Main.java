package bsu.rfe.java.group9.lab1.Basatskaya;//задание пакета, которому принадлежит класс 
public class Main {//объявление главного класса приложения
    public static void main(String[] args) throws Exception{//main()-главный метод, автоматически вызывается
        //обязательно должен быть определен как public static void,
        //из него вызываются все классы и объекты приложения
        //параметры командной строки находятся в массиве строк args
        //после выполнения метода main приложение завершает свою работу
        Food[] breakfast=new Food[20];//Анализ аргументов командной строки и создание для них 
        //экземпляров соответствующих классов для завтрака
        int cheese=0, apple=0, tea=0;
        int itemsSoFar=0;
        for (String arg: args) {
            String[] parts = arg.split("/");
            if (parts[0].equals("Cheese")){
                breakfast[itemsSoFar]=new Cheese();
                cheese++;
            }
            else
                if (parts[0].equals("Apple")) {
                    breakfast[itemsSoFar]=new Apple(parts[1]);
                    apple++;
                }
                else
                    if (parts[0].equals("Tea"))
                    {
                        breakfast[itemsSoFar]=new Tea(parts[1]);
                        tea++;
                    }
                    for (Food item: breakfast)
                        if (item!=null)//если элемент не null, употребить продукт
                            item.consume();
                    else//если дошли до элемента null - значит достигли конца списка продуктов, ведь 20
            //элементов в массиве было выделено с запасом, все могут быть не использованы
            break;
                    System.out.println("кол-во сыров"+cheese);
            System.out.println("кол-во яблок"+apple);
            System.out.println("кол-во чая"+tea);
            System.out.println("Всего хорошего!");
                            
        }
    }
}