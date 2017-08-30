package employeeapp.pooja.com.hrapp;

/**
 * Created by Puja on 7/9/2017.
 */

public class HRclass {
    public String name;
    public String feedback;
    public Integer rating ;
    public String email;

    HRclass() {
    }
    public HRclass(String name, String email){
        this.name = name;
        this.email = email;
    }

    public  HRclass(String name){
        this.name = name;
    }

    public String getName(){ return name;}
    public String getEmail(){ return email;}
    public String getfeedback(){ return feedback; }
    public Integer getRating(){ return rating;}
    public void setName(){ this.name = name;}
    public void setEmail(){ this.email = email;}
    public void setfeedback(String feedback){ this.feedback = feedback; }
    public void setRating(int rating){ this.rating = rating; }
}
