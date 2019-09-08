package com.ksr.summerproject.server.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private java.sql.Timestamp reservationTime;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "bicycle_id", referencedColumnName = "id")
  private Bicycle bicycle;

    public Reservation(Timestamp reservationTime, Client client_id, Bicycle bicycle_id) {
        this.reservationTime = reservationTime;
        this.client = client_id;
        this.bicycle = bicycle_id;
    }

    public Reservation() {
    }
}
