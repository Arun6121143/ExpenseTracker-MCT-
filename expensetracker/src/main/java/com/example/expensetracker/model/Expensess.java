package com.example.expensetracker.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expensess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   
    private String description;
    private String title;
    private int price;

    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date  must be in the format DD-MM-YYYY.")
    private String date;

    @NotBlank(message = "provide the  username ")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "provide the  password")
   
    private String password;
    private LocalTime time;
}
