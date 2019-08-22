package com.ksr.summerproject.server.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private java.sql.Timestamp reservationTime;


}
