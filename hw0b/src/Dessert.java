public class Dessert {
    private int flavor;
    private int price;
    static int num = 0;
    public Dessert(int f, int p){this.flavor = f; this.price = p; num ++;}
    public static int numDesserts(){
        return num;
    }
    public void printDessert(){
        System.out.printf("%d %d %d\n", this.flavor, this.price, num);
    }
    public static void main(String[] args){System.out.println("I love dessert!");}
}
