package com.example.smsfrontend.view.product;

import com.example.smsfrontend.enums.NdsPositions;
import com.example.smsfrontend.proxy.product.Product;
import com.example.smsfrontend.proxy.segment.Segment;
import com.example.smsfrontend.proxy.segment.SegmentAdapter;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;

@SpringComponent
@UIScope
public class ProductEditor extends FormLayout implements KeyNotifier {

    private final ComboBox<Segment> segment = new ComboBox<>("Сегмент");
    private final ComboBox<NdsPositions> nds = new ComboBox<>("Ставка НДС");
    private final TextField name = new TextField("Наименование");

    private Button saveButton = new Button("Сохранить");
    private Button cancelButton = new Button("Отмена");
    ;

    private Binder<Product> binder = new BeanValidationBinder<>(Product.class);

    private Product product;

    private final SegmentAdapter segmentAdapter;

    public ProductEditor(SegmentAdapter segmentAdapter) {
        this.segmentAdapter = segmentAdapter;
        configureForm();
    }

    public void editProduct(Product product) {
        this.product = product;
        binder.setBean(product);
        setVisible(true);
    }

    private void configureForm() {
        segment.setItems(segmentAdapter.findAll());
        segment.setItemLabelGenerator(Segment::getName);

        nds.setItems(NdsPositions.values());
        nds.setItemLabelGenerator(ndsPositions -> String.valueOf(ndsPositions.getValue()));

        setVisible(false);

        configureButtons();

        binder.bindInstanceFields(this);
        add(name, segment, nds, getButtonPanel());

        setVisible(false);
    }

    private Component getButtonPanel() {
        return new HorizontalLayout(saveButton, cancelButton);
    }


    private void configureButtons() {
        saveButton.addClickShortcut(Key.ENTER);
        cancelButton.addClickShortcut(Key.ESCAPE);
    }

    private void save() {

    }


    @Getter
    public static abstract class ProductEditorEvent extends ComponentEvent<ProductEditor> {

        private final Product product;

        protected ProductEditorEvent(ProductEditor source, Product product) {
            super(source, false);
            this.product = product;
        }
    }

    public static class SaveEvent extends ProductEditorEvent {
        SaveEvent(ProductEditor source, Product product) {
            super(source, product);
        }
    }

    public static class CloseEvent extends ProductEditorEvent {
        CloseEvent(ProductEditor source, Product product) {
            super(source, product);
        }
    }

}
