package com.ksr.summerproject.server.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Bicycle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String status;

  private int location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;
}
