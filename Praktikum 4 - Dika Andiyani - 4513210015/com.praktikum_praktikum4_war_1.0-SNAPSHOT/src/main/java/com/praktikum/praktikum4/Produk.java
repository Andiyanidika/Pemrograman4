/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praktikum.praktikum4;
import java.math.BigDecimal;
import java.sql.*;

public class Produk {
    private Integer id;
    private String kode;
    private String nama;
    private BigDecimal harga;
    private Date terakhir_update;
    
  public Integer getId(){
      return id;
  }
  
  public String getKode(){
      return kode;
  }
  
  public String getNama(){
      return nama;
  }
  
  public BigDecimal getHarga(){
      return harga;
  }
  
  public Date getTerakhirUpdate(){
      return terakhir_update;
  }
  
}
