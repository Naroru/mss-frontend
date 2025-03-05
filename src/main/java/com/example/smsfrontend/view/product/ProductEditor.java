package com.example.smsfrontend.view.product;

import com.example.smsfrontend.enums.NdsPositions;
import com.example.smsfrontend.proxy.segment.Segment;
import com.example.smsfrontend.proxy.segment.SegmentAdapter;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;


import java.util.List;

@SpringComponent
@UIScope
public class ProductEditor extends FormLayout implements KeyNotifier {

  private final ComboBox<Segment> segmentComboBox = new ComboBox<>();
  private final ComboBox<NdsPositions> ndsComboBox = new ComboBox<>();

  private final TextField name = new TextField("Наименование");

  private Button saveButton = new Button("Сохранить");
  private  Button cancelButton = new Button("Отмена");;

  private final SegmentAdapter segmentAdapter;

  public ProductEditor(SegmentAdapter segmentAdapter) {
    this.segmentAdapter = segmentAdapter;

    segmentComboBox.setItems(segmentAdapter.findAll());
    ndsComboBox.setItems(NdsPositions.values());
  }

  private void configureButtons() {

    saveButton.addClickShortcut(Key.ENTER);
    cancelButton.addClickShortcut(Key.ESCAPE);

  }

  private void save() {

  }

}
