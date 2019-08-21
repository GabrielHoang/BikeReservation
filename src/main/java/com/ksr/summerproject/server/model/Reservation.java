package com.ksr.summerproject.server.model;


public class Reservation {

  private long id;
  private java.sql.Timestamp reservationTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Timestamp getReservationTime() {
    return reservationTime;
  }

  public void setReservationTime(java.sql.Timestamp reservationTime) {
    this.reservationTime = reservationTime;
  }

}
