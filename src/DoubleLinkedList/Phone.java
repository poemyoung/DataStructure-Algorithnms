package DoubleLinkedList;

/**
 * JavaBean,用来测试
 */
public class Phone {
    String name;
    String period;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                ", period='" + period + '\'' +
                '}';
    }
}
