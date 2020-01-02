import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BankCard {
    private int id;//银行卡号
    private int password;//银行卡密码
    private double money;//银行卡余额

    //根据卡号和密码来新建一张银行卡 (构造方法)
    public BankCard(int id, int password) {
        this.id = id;
        this.password = password;
    }

    //查询余额
    public void balance() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("您的余额为： " + df.format(money));
    }

    //根据setter()方法和getter()方法来获取或者修改银行卡信息
    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setMoney(double money) {

        this.money = money;
    }

    public int getId() {
        return id;
    }

    public int getPassword() {
        return password;
    }

    public double getMoney() {
        return money;
    }


}
