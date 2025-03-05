package com.example.smsfrontend.proxy.product;

import com.example.smsfrontend.enums.NdsPositions;
import com.example.smsfrontend.proxy.segment.Segment;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
  private long id;
  private String name;
  private Segment segment;
  private NdsPositions nds;
  private boolean isDeleted;
}
