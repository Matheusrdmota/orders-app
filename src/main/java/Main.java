import classes.Moto;
import classes.Order;
import classes.Store;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Order> ordersAvailable = new ArrayList<>();
    static List<Moto> motosArray = new ArrayList<>();
    static List<Moto> availableMotosArray = new ArrayList<>();
    static List<Moto> motosRemainingWithoutOrder = new ArrayList<>();

    public static void main(String[] args) {

        initVariables();

        boolean cancel = false;

        while(!cancel){
            motosRemainingWithoutOrder = motosArray.stream()
                    .filter(x -> x.getOrders().size() == 0)
                    .collect(Collectors.toList());

            System.out.println("---------------Menu---------------\n" +
                    "Digite a opção desejada: \n" +
                    "1. Adicionar um pedido a um motoboy\n" +
                    "2. Consultar informações de pedidos de um motoboy\n" +
                    "3. Encerrar aplicação");

            Integer entry = scanner.nextInt();

            if(entry == 1){
                menuOption1();
            }
            else if(entry == 2){
                menuOption2();
            }
            else if(entry == 3){
                cancel = true;
                System.out.println("Aplicação Finalizada!!");
            }
            else{
                System.out.println("Digite uma opção válida!!");
            }
        }
    }

    public static void initVariables(){
        //Inicialização de variáveis

        Moto moto1 = new Moto("moto1", 2.0, Arrays.asList(1,2,3));
        Moto moto2 = new Moto("moto2", 2.0, Arrays.asList(1,2,3));
        Moto moto3 = new Moto("moto3", 2.0, Arrays.asList(1,2,3));
        Moto moto4 = new Moto("moto4", 2.0, Arrays.asList(1));
        Moto moto5 = new Moto("moto5", 3.0, Arrays.asList(1,2,3));

        Store store1 = new Store(1, 0.05);
        Store store2 = new Store(2, 0.05);
        Store store3 = new Store(3, 0.15);

        Order order1 = new Order(1,50.0, store1);
        Order order2 = new Order(2, 50.0, store1);
        Order order3 = new Order(3,50.0, store1);
        Order order4 = new Order(4, 50.0, store2);
        Order order5 = new Order(5,50.0, store2);
        Order order6 = new Order(6,50.0, store2);
        Order order7 = new Order(7, 50.0, store2);
        Order order8 = new Order(8, 50.0, store3);
        Order order9 = new Order(9, 50.0, store3);
        Order order10 = new Order(10, 100.0, store3);

        ordersAvailable.add(order1);
        ordersAvailable.add(order2);
        ordersAvailable.add(order3);
        ordersAvailable.add(order4);
        ordersAvailable.add(order5);
        ordersAvailable.add(order6);
        ordersAvailable.add(order7);
        ordersAvailable.add(order8);
        ordersAvailable.add(order9);
        ordersAvailable.add(order10);

        motosArray.add(moto1);
        motosArray.add(moto2);
        motosArray.add(moto3);
        motosArray.add(moto4);
        motosArray.add(moto5);
    }

    public static void menuOption1(){
        availableMotosArray = motosArray;
        System.out.println("Pedidos disponíveis\n" +
                "Nº - Loja - Valor");

        for(int i = 0; i < ordersAvailable.size(); i++){
            System.out.println(ordersAvailable.get(i).getOrderId() + " - " + ordersAvailable.get(i).getStore().getStoreName() + "  -  R$ " +
                    ordersAvailable.get(i).getOrderValue());
        }

        System.out.println("\n0. Voltar ao menu principal");

        System.out.println("Pedido escolhido: ");

        Integer orderChoice = scanner.nextInt();

        if(ordersAvailable.stream().filter(x -> x.getOrderId() == orderChoice)
                .collect(Collectors.toList()).size() == 0 || orderChoice == 0){
            if(orderChoice == 0){
                return;
            }
            System.out.println("Escolha uma opção válida!!");
            return;
        }

        Order chosenOrder = ordersAvailable.stream()
                .filter(x -> x.getOrderId() == orderChoice)
                .findFirst().orElseThrow();

        attAvailableMotos(chosenOrder);

        System.out.println("Motoboys\n" +
                "Motoboy - Comissão - Lojas Atendidas");

        for(int i = 0; i < availableMotosArray.size(); i++){
            System.out.println((i + 1) + " - " + availableMotosArray.get(i).getMotoName() + "  -  R$ " +
                    availableMotosArray.get(i).getComission() + " - " +
                    availableMotosArray.get(i).getStoresAttended());
        }

        System.out.println("\n0. Voltar ao menu principal");

        Integer chosenMoto = scanner.nextInt();

        if(!(chosenMoto >= 1 && chosenMoto <= availableMotosArray.size()) || chosenMoto == 0){
            if(chosenMoto == 0){
                return;
            }
            System.out.println("Escolha uma opção válida!!");
            return;
        }

        motosArray.get(motosArray.indexOf(availableMotosArray.get(chosenMoto - 1))).addOrder(chosenOrder);
        ordersAvailable.remove(chosenOrder);
        System.out.println("Sucesso!!");
    }

    public static void menuOption2(){
        System.out.println("Escolha um motoboy");
        for(int i = 0; i < motosArray.size(); i++){
            System.out.println((i + 1) + " - " + motosArray.get(i).getMotoName());
        }

        System.out.println("\n0. Voltar ao menu principal");

        Integer chosenMoto = scanner.nextInt();

        if(chosenMoto == 0){
            return;
        }

        System.out.println(motosArray.get(chosenMoto - 1));
    }

    public static void attAvailableMotos(Order order){
        availableMotosArray = availableMotosArray.stream()
                .filter(x -> x.getStoresAttended().contains(order.getStore().getStoreName()))
                .collect(Collectors.toList());

        if(order.getStore().getStoreName() == 1
                && ordersAvailable.stream()
                .filter(x -> x.getStore().getStoreName() == 1)
                .collect(Collectors.toList()).size() == 1
                && motosRemainingWithoutOrder.stream()
                .filter(x -> x.getMotoName().equals("moto4"))
                 .collect(Collectors.toList()).size() == 1){

            availableMotosArray = motosRemainingWithoutOrder.stream()
                                  .filter(x -> x.getMotoName().equals("moto4"))
                                  .collect(Collectors.toList());
            System.out.println("ok");
        }
        else if(ordersAvailable.size() == motosRemainingWithoutOrder.size()){
            List<Moto> ordersMoto4 = motosRemainingWithoutOrder.stream()
                    .filter(x -> x.getMotoName().equals("moto4"))
                    .collect(Collectors.toList());

            if(order.getStore().getStoreName() == 1
                    && ordersMoto4.size() == 0){
                availableMotosArray = ordersMoto4;
            }else{
                availableMotosArray = motosRemainingWithoutOrder;
            }
        }else{

        }
    }
    }

