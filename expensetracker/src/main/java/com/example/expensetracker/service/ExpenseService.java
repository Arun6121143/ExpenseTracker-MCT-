package com.example.expensetracker.service;


import java.time.LocalTime;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.dao.ExpensesRepository;
import com.example.expensetracker.model.Expensess;

@Service
public class ExpenseService {
    @Autowired
    ExpensesRepository repo;

    public Expensess saveexpenses(Expensess newexpense) {
        return repo.save(newexpense);
    }

    public List<Expensess> getallexp() {
        return repo.findAll();
    }

    public JSONObject getexpbyid(int id) {
        JSONObject obj = new JSONObject();
        Expensess exp = repo.findById(id).get();
        obj.put("description",exp.getDescription());
        obj.put("title",exp.getTitle());
        obj.put("price",exp.getPrice());
        obj.put("date", exp.getDate());
        return obj;
    }

    public void updateexpensesbyid(int id, Expensess newe) {

        Expensess newexp = repo.findById(id).get();
        newexp.setPrice(newe.getPrice());
        newexp.setTime(LocalTime.now());
        newexp.setDate(newe.getDate());
        newexp.setDescription(newe.getDescription());
        newexp.setPassword(newe.getPassword());
        newexp.setUsername(newe.getUsername());
        newexp.setTitle(newe.getTitle());
        repo.save(newexp);
    }

    public void deletebyid(int id) {

        repo.deleteById(id);
    }

    public String loginuserby(String username, String password) {
        List<Expensess> exp = repo.Login(username, password);
        if (exp.get(0).getUsername().equals(username) && exp.get(0).getPassword().equals(password)) {
            return "Login sucessfully";
        } else if (!exp.get(0).getUsername().equals(username)) {
            return "Invalid username";
        }
        return "Invalid  password";

    }

    public List<Expensess>  getexpensebydate(String date) {
      return  repo.getbydates(date);
    
    }

    public int sumofpricebydate(String date) {
        List<Expensess> e = repo.getbydates(date);
        int sum=0;
        for(int i = 0 ; i<e.size();i++){
           int price = e.get(i).getPrice();
           sum=sum+price;
        }
        return sum;
    }

}
