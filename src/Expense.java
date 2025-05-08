import java.time.LocalDate;

public class Expense {
    double amount;
    String category;
    LocalDate date;

    public Expense(double amount, String category, LocalDate date){
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
    
    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }
    
    @Override
    public String toString(){
        return "$" + String.format("%.2f", amount) + " | " + category + " | " + date.toString();
    }
}

