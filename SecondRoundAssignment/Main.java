import java.util.Scanner;
import java.time.*;
import java.util.ArrayList;

/**
 * @author Yishan.Ji
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("欢迎来到宠物店");
        MyAnimalShop shop=new MyAnimalShop();
        // open until not in business
        shop.list();
        shop.customerList();
        while(shop.getBusiness()) {
            shop.custermer();
            shop.trade();
            shop.inBusiness();
        }
        //not in business,settle account
        shop.custermer();
    }
}
abstract class Animal{
    protected String animalName;
    protected int age;
    protected String sex;
    protected double price;
    public String getAge(int age){
        return  String.valueOf(age);
    }
    public String getAnimalName(){
        return animalName;
    }

    public double getPrice(){
        return price;
    }
    public String getSex(){
        return sex;
    }

    abstract public String toString();
}
class Dogs extends Animal{
    @Override
    public String getSex(){
        sex="female";
        return sex+"/";
    }
    @Override
    public String getAge(int age){
        this.age=age;
        age=1;
        return age +"/";
    }
    @Override
    public String getAnimalName(){
        return "Dog"+"/";
    }
    private boolean isVaccineInjected(){
        boolean vac=true;
        return  vac;
    };
    @Override
    public double getPrice(){
        price=100;
        return price;
    }
    @Override
    public String toString(){
        return  getAnimalName()+isVaccineInjected()+"/"+getAge(0)+getSex()+" "+getPrice();
    }
}
class Cats extends Animal{
    @Override
    public String getSex(){
        sex="female"+"/";
        return sex;
    }
    @Override
    public String getAge(int age){
        this.age=age;
        age=1;
        return age +"/";
    }
    @Override
    public String getAnimalName(){
        return "Cat"+"/";
    }
    @Override
   public double getPrice(){
        price=200;
        return price;
    }
    @Override
    public String toString(){
        return getAnimalName()+getAge(0)+getSex()+" "+getPrice();
    }
}
class Snake extends Animal{
    @Override
    public String getSex(){
        sex="female";
        return sex+"/";
    }
    @Override
    public String getAge(int age){
        this.age=age;
        age=1;
        return age +"/";
    }
    @Override
    public String getAnimalName(){
        return "Snake"+"/";
    }
    @Override
    public double getPrice(){
        price=300;
        return price;
    }
    @Override
    public String toString(){
        return getAnimalName()+getAge(0)+getSex()+" "+getPrice();
    }
}
class Customer{
    protected String cusName;
    protected int visitNum;
    protected LocalDateTime newestArriveTime=LocalDateTime.now();
    public int getVnum(){
        if(visitNum==0){
            visitNum=1;
        }
        else{
            visitNum++;
        }
        return visitNum;
    }
    public void cus(){// take in information
        System.out.println("欢迎光临!\n请问您叫什么");
        Scanner customer=new Scanner(System.in);
        cusName=customer.nextLine();
    }
    @Override
    public String toString(){
        //visitNum is not completed
        return ""+cusName+"   "+String.valueOf(getVnum())+"  "+newestArriveTime;

    }
    public String toCus(){
        visitNum--;
        return ""+cusName+"   "+String.valueOf(getVnum());
    }

}
interface AnimalShop{
    void  trade();
    void custermer();
    void inBusiness();

}
class MyAnimalShop extends Customer implements AnimalShop {
    ArrayList<String> customering;
    ArrayList<String> animals;
    ArrayList<String> customerList;
    protected double remaining = 100;
    double benefit;
    boolean business = true;
    Cats cat = new Cats();
    Dogs dog = new Dogs();
    Snake snake = new Snake();
    public void list() {
        ArrayList<String> animals = new ArrayList<>();
        this.animals = animals;
        animals.add(cat.toString());
        animals.add(dog.toString());
        animals.add(snake.toString());
    }
    @Override
    public void trade()  {// list of animals
        Scanner buy = new Scanner(System.in);
        System.out.println("购买还是售卖动物");
        String decide = buy.nextLine();
        if ("售卖".equals(decide)) {
            int selling = 0;
            while (selling == 0) {
                System.out.println("输入售卖动物名称（英文单数）");
                String buyA = buy.nextLine() + "/";
                buyA = buyA.toLowerCase();
                System.out.println("动物年龄");
                String buyAge = buy.nextLine() + "/";
                System.out.println("动物性别（male/female）");
                String buySex = buy.nextLine() + "/";
                System.out.println("请问您想卖多少钱");
                String buyPrice = buy.nextLine();
                String buyToString = buyA + buyAge + buySex + " " + buyPrice;
                animals.add(buyToString);
                remaining = remaining - Double.parseDouble(buyPrice);
                if(remaining<0){
                    throw new AnimalNotFountException();
                }
                System.out.println("是否继续售卖");
                String isSelling = buy.nextLine();
                if ("否".equals(isSelling)) {
                    selling = 1;
                }
            }
        }
        if ("购买".equals(decide)) {
            int buying = 0;
            System.out.println(animals);
            while (buying == 0) {
                System.out.println("请输入购买动物名称（英文小写单数）+/+动物年龄/+动物性别/+(一个空格)动物价钱");
                String toBuy = buy.nextLine();
                if(animals==null){
                    throw new InsufficientBalanceException();
                }
                int si = animals.size() - 1;
                int c=0;
                if(si==0){
                    animals=null;
                }
                for (int i = 0; i < si; i++) {
                    if (toBuy.equals(animals.get(i))) {
                        int b = 0;
                        c=1;
                        int length = animals.get(i).length()-1;
                        for (int a = 0; a < length; a++) {
                            if (animals.get(i).charAt(a) == ' ') {
                                b = a;
                            }
                        }
                        String price = animals.get(i).substring(b);
                        remaining = remaining + Double.parseDouble(price);
                            animals.remove(i);
                    }
                    if(c==0){
                        System.out.println("请按照格式输入/店内没有您想要的动物");
                    }
                }
                System.out.println("是否继续购买");
                String isBuying = buy.nextLine();
                if (isBuying.equals("否")) {
                    buying = 1;
                }
                else{
                    System.out.println(animals);
                }
            }
        }
        System.out.println("目前店内动物");
        System.out.println(animals);
        System.out.println("店内余额为：" + remaining);
    }

    @Override
    public void inBusiness() {
        System.out.println("是否继续营业(输入是否)");
        Scanner iB = new Scanner(System.in);
        String wIB = iB.nextLine();
        if ("否".equals(wIB)) {
            business = false;
            benefit = remaining - 100;
            System.out.println("今日利润为：" + benefit);
        }
    }

    public boolean getBusiness() {
        return business;
    }

    public void customerList() {
        ArrayList<String> customerList = new ArrayList();
        this.customerList = customerList;
        ArrayList<String> customering = new ArrayList();
        this.customering =customering;
    }

    @Override
    public void custermer() {//have a funtion that haven't apply
        Customer custo = new Customer();
        if (business == true) {
            custo.cus();
            String l = custo.toString();
            String l2= custo.toCus();
            customerList.add(l);
            customering.add(l2);
            int d=customering.size()-1;
            int g = -1;
            for(int f=0;f<d;f++){
                if(String.valueOf(String.valueOf(customering.get(f))).equals(l2)){
                    g=f;
                }
            }
            String newL;
            if(g!=-1){

                newL=custo.toString();
                customerList.set(g,newL);
                customerList.remove(customerList.size()-1);
            }

        }
        if (business == false) {
            System.out.println(customerList);
        }
    }
}
class aEception extends RuntimeException{

    public aEception() {
        super();
    }

    public aEception(String message, Throwable cause) {
        super(message, cause);
    }

    public aEception(String message) {
        super(message);
    }

    public aEception(Throwable cause) {
        super(cause);
    }
}
class AnimalNotFountException extends aEception{
    public AnimalNotFountException(){
        super();
        System.out.println("店内余额不足");
    }
}
class InsufficientBalanceException extends aEception{
    public InsufficientBalanceException(){
        super();
        System.out.println("宠物店内无动物");
    }

}

