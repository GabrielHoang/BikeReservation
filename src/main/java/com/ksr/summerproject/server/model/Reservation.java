package com.ksr.summerproject.server.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private java.sql.Timestamp reservationTime;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client_id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "bicycle_id", referencedColumnName = "id")
  private Bicycle bicycle_id;


}
