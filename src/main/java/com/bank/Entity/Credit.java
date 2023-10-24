package com.bank.Entity;

import com.bank.Enum.CreditStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Credit {
    public static final double TAUX = 0.12;
    @NonNull
    @Id
    @GeneratedValue
    private int id;
    @NonNull
    private int value;
    @NonNull
    @Enumerated(EnumType.STRING)
    private CreditStatus status;
    @NonNull
    private int duration;
    @NonNull
    private String remark;
    @NonNull
    private double loanTax;
    @OneToOne
    private Client client;
    @NonNull
    @ManyToOne
    private Agency agency;
    @NonNull
    @ManyToOne
    private Employee employee;
    @Column(name = "modification_date")
    private LocalDate modification_date;

    @Column(name = "modification_time")
    private LocalTime modification_time;

    @PrePersist
    protected void onPersist() {
        modification_date = LocalDate.now();
        modification_time = LocalTime.now();
    }
}