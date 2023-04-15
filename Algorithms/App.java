package Algorithms;
import java.util.Calendar;

record Invoice(String id, float amount) {
    static String prefix = String.valueOf(Calendar.getInstance().get(Calendar.YEAR))
            +String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
    public Invoice{
        id=prefix+id.trim();
        if(amount<0)
            throw new IllegalArgumentException("-ve values not allowed");
        amount=amount;
    }
    public Invoice(String id){
        this(id,0.0f);
    }

}
public class App
{
    public static void main( String[] args )
    {
        float[] amt = {400.00f,595.00f,300.00f,700.00f,595.00f};
        Invoice[] invoice = new Invoice[5];
        for(int i=0;i<invoice.length;i++)
            invoice[i] = new Invoice(String.valueOf(i+1), amt[i]);
        for(int i=0;i<invoice.length;i++)
            System.out.println(invoice[i].toString());
    }
}