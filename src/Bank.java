import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    //用哈希表来存储银行卡，根据卡号来找到对应的银行卡
    //这样查找的更快
    public static Map<Integer, BankCard> bankCards = new HashMap<>();

    //注册银行卡方法
    public void register(int id, int password) {
        //先查询要注册的银行卡号是否存在
        if (bankCards.containsKey(id)) {
            System.out.println("这个账户已经注册过了，请重新选择您的业务");
            return;
        }
        bankCards.put(id, new BankCard(id, password));
        System.out.println("注册成功，已将您的银行卡信息成功存入本行");
    }

    //登录,根据id和password取银行卡
    public BankCard login(int id, int password) {
        if (bankCards.size() == 0) {
            System.out.println("本行目前不存在任何银行卡信息");
            return null;
        }
        if (bankCards.get(id).getPassword() == password) {
            //如果密码正确，则登录成功，返回银行卡
            return bankCards.get(id);
        } else {
            //否则就返回null
            return null;
        }
    }

    //进入银行界面
    public void operate(Person person) {
        int count = 3;
        while (count > 0) {
            System.out.println("欢迎来到本银行，请选择您的业务：");
            System.out.println("====1.登录账户===2.注册账户===3.退出");
            Scanner scanner = new Scanner(System.in);
            int key = scanner.nextInt();
            if (key == 3) {
                System.out.println("您已成功退出银行系统");
                return;
            }
            System.out.println("请输入您的银行卡账号：");
            int id = scanner.nextInt();
            System.out.println("请输入您的银行卡密码：");
            int password = scanner.nextInt();
            switch (key) {
                case 1:
                    BankCard card = login(id, password);
                    if (card != null) {
                        System.out.printf("=== 账户%d登陆成功 ===", id);
                        while (true) {
                            System.out.println("===请输入您需要的业务===");
                            System.out.println("===1.存钱===2.取钱===3.转账===4.查询余额===5.退卡");
                            int operationNum = scanner.nextInt();
                            if (operationNum == 5) break;
                            operateLoginSuccess(person, operationNum, card);
                        }
                    } else {
                        System.out.println("登陆失败！");
                        count--;
                    }
                    break;
                case 2:
                    register(id, password);
                    break;
            } //end switch
        }//end while
    }

    private void operateLoginSuccess(Person person, int operationNum, BankCard card) {
        switch (operationNum) {
            case 1:
                person.save(card);
                break;
            case 2:
                person.withdraw(card);
                break;
            case 3:
                person.transfer(card);
                break;
            case 4:
                card.balance();
                break;
            default:
                break;
        }
    }

}
