/**
 * Created by Jakab on 2015.03.10..
 */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UzletiLogika extends Dao {

    UzletiLogika(String host, String username, String password) {
        super(host, username, password);
    }

    private static Boolean logged_in = false;

    public static Boolean getLogged_in() {
        return logged_in;
    }

    // ------------------------ Registration method ------------------------
    public List<String> register(String rusername, String rpassword, String rpassword2,String remail) {
        List<String> errors = new ArrayList<String>();

        if(!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(remail).matches()){ errors.add("Ez az email-cim nem valodi."); }
        if(checkUserByEmail(remail)){ errors.add("Mar van felhasznalo ilyen e-mail cimmel."); }
        if(checkUserByName(rusername)){ errors.add("Mar van felhasznalo ilyen nevvel."); }
        if(!rpassword.equals(rpassword2)){ errors.add("A ket beirt jelszo nem egyezik meg."); }
        if(rusername.length() < 2){ errors.add("A felhasznalonev tol rovid."); }
        if(rusername.length() > 32){ errors.add("A felhasznalonev tol hosszu."); }
        if(rpassword.length() < 3){ errors.add("A jelszo tul rovid."); }
        if(rpassword.length() > 32){ errors.add("A jelszo tul hosszu."); }
        if(errors.isEmpty()){
            insertUser(rusername, Utility.SHA1(rusername, rpassword), remail);
            errors.add("Sikeres regisztracio!");
            return errors;
        } else {
            return errors;
        }
    }

    // ------------------------ Login method ------------------------
    public List<String> login (String lusername, String lpassword){
        List<String> errors = new ArrayList<String>();

        if(!checkUserByName(lusername) || !checkUserByPassword(Utility.SHA1(lusername, lpassword))){ errors.add("Hibas felhasznalonev vagy jelszo."); }
        if(errors.isEmpty()){
            logged_in = true;
            errors.add("Sikeres bejelentkezes!");
        }

        return errors;
    }

}
