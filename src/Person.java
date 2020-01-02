import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Person {

    Scanner sc = new Scanner(System.in);

    //存钱
    public void save(BankCard card) {
        System.out.println("请输入您的存入金额：");
        double money = sc.nextDouble();
        if (money > 0) {
            card.setMoney(card.getMoney() + money);
            System.out.println("存入成功");
            card.balance();
        } else {
            System.out.println("请输入正确金额");
        }
    }

    //取钱
    public void withdraw(BankCard card) {
        System.out.println("请输入您要取出的金额：");
        double money = sc.nextDouble();
        if (money > 0 && money <= card.getMoney()) {
            card.setMoney(card.getMoney() - money);
            System.out.println("取款成功！");
            System.out.println("已取：" + money + "元");
            card.balance();
        } else if (money > card.getMoney()) {
            System.out.println("您的存款不足！");
        } else {
            System.out.println("请输入正确金额");
        }
    }

    //转账
    public void transfer(BankCard card) {
        int count = 3;
        while (count > 0) {
            System.out.println("请输入对方的账户id：");
            int destID = sc.nextInt();//目的账号
            Map<Integer, BankCard> bankCards = Bank.bankCards;
            if (bankCards.containsKey(destID)) {
                //查找目的账户存在，再进行转账操作
                BankCard dest = bankCards.get(destID);//目的账户
                System.out.println("请输入您要转账的金额：");
                double money = sc.nextDouble();
                if (money <= card.getMoney()) {
                    //有足够的金额才能转账
                    card.setMoney(card.getMoney() - money);
                    dest.setMoney(dest.getMoney() + money);
                    System.out.println("转账成功！");
                    card.balance();
                    return;
                } else {
                    System.out.println("余额不足");
                }
            }
            System.out.println("您还有" + (count - 1) + "次机会，请重新输入");
            count--;
        }
    }


}