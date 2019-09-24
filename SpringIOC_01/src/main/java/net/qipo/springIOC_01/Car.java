package net.qipo.springIOC_01;

public class Car {
    private String carName;
    private Integer price;
    private String color;

    public Car() {
        System.out.println("car被创建了");
    }

    @Override
    public String toString() {
        return "Car{" +
                "carName='" + carName + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
