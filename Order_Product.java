import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Order_Product {

public static void main(String args[]){


List<Product> prods1 = new ArrayList<>();
Product p1 = new Product("soap",true);
Product p2 = new Product("notebook",true);
Product p3 = new Product("lemon",false);

prods1.add(p1);
prods1.add(p2);
prods1.add(p3);

List<Product> prods2 = new ArrayList<>();

prods2.add(p1);
prods2.add(p2);

List<Product> prods3 = new ArrayList<>();
prods3.add(p2);
prods3.add(p3);

List<Order> orders = new ArrayList<>();
orders.add(new Order(prods1,100,1));
orders.add(new Order(prods2,200,2));
orders.add(new Order(prods3,700,3));

List<Order> newOrders = orders.stream()
.peek(o -> {
    List<Product> pdts =  o.getProducts().stream()
	.filter(Product::isInStock)
    .collect(Collectors.toList());
o.setProducts(pdts);
}).filter(o-> o.getTotalValue() > 500).sorted(Comparator.comparingInt(Order::getOrderNum)
.reversed())
.collect(Collectors.toList());

System.out.println("The order list size is :-" +  newOrders.size());
newOrders.forEach(o -> {
o.getProducts().forEach(p -> System.out.println(o.getOrderNum()+ "---" + p.getName()));
});

}
}

class Order{
List<Product> products;
private int totalValue;
private int orderNum;

public Order(List<Product> products, int totalValue,int orderNum){
this.products = products;
this.totalValue = totalValue;
this.orderNum = orderNum;
}
public List<Product> getProducts(){
return this.products;
}

public void setProducts(List<Product> pdts){
this.products = pdts;
}

public int getTotalValue(){
return this.totalValue;
}

public int getOrderNum(){
return this.orderNum;
}
}

class Product{

private String name;
private boolean inStock;

public Product(String name,boolean inStock){
this.name = name;
this.inStock = inStock;
}

public String getName(){
return this.name;
}
public boolean isInStock(){
return this.inStock;
}

}