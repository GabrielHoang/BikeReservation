package com.ksr.summerproject.server.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String surname;
  private String email;
  private java.math.BigDecimal money;

  @OneToOne(mappedBy = "client_id")
  private Reservation reservation;

}
