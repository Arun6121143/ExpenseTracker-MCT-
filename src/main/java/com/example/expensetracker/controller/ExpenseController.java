package com.example.expensetracker.controller;


import java.time.LocalTime;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.model.Expensess;
import com.example.expensetracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseService service;

    @PostMapping("/postexpenes")
    public ResponseEntity<String> addexpense(@Valid @RequestBody Expensess exp) {

        exp.setTime(LocalTime.now());
        Expensess e = service.saveexpenses(exp);
        return new ResponseEntity<>(
                "saved Suceessfully with username:-" + e.getUsername() + " and password with : = " + e.getPassword(),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    private String loginuser(@RequestBody Expensess exp) {
        String username = exp.getUsername();
        String password = exp.getPassword();
        String str = service.loginuserby(username, password);
        return str;
    }

    @GetMapping("/getallexpenses")
    private List<Expensess> getexpenses() {
        return service.getallexp();
    }

    @GetMapping("/getexpensesbyid/{id}")
    private ResponseEntity<String> getbyidexp(@PathVariable int id) {
        JSONObject json =  service.getexpbyid(id);
        return new ResponseEntity<>(json.toString(),HttpStatus.CREATED);
    }

    @PutMapping("/updateexpenses/{id}")
    private String updateexpenses(@PathVariable int id, @RequestBody Expensess newe) {

        service.updateexpensesbyid(id, newe);
        return "Update sucessfully with id =" + id;

    }

    @DeleteMapping("/deletebyid/{id}")
    private String deletebyid(@PathVariable int id) {
        service.deletebyid(id);
        return "deleted Sucessfully id with" + id;
    }

    @GetMapping("/getbydate/{date}")
    private List<Expensess> getByDate(@PathVariable String date){
       return service.getexpensebydate(date);
    }

    @GetMapping("/getallpricebydate/{date}")
    private String getallprice(@PathVariable String date){
       int Total = service.sumofpricebydate(date);
       return "Total expenditure for the date "+ date +" "+ "is : "+Total;
    }


}
