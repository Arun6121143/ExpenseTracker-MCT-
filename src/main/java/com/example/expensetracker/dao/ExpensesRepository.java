package com.example.expensetracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.expensetracker.model.Expensess;

public interface ExpensesRepository extends JpaRepository<Expensess,Integer> {
    @Query(value="Select * from expensess where username = :username or password = :password",nativeQuery=true)
    List<Expensess> Login(String username , String password);
    
    @Query(value="Select * from expensess where date = :date",nativeQuery=true)  
    List<Expensess> getbydates(String date);
}
