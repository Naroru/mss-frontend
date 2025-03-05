package com.example.smsfrontend.view.product;

import com.example.smsfrontend.proxy.product.Product;
import com.example.smsfrontend.proxy.product.ProductAdapter;
import com.example.smsfrontend.proxy.segment.Segment;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.LumoIcon;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.vaadin.flow.data.provider.SortDirection.ASCENDING;

@Route("/products")
@UIScope
@PageTitle("Товары")
public class ProductList extends VerticalLayout implements KeyNotifier {

    private final Grid<Product> grid = new Grid<>(Product.class, false);

    private final TextField idFilter = new TextField("Код");
    private final TextField nameFilter = new TextField("Наименование");
    private final TextField segmentFilter = new TextField("Сегмент");

    private final Text header = new Text("Товары");

    private final ProductAdapter adapter;
    private final ProductEditor editor;

    @Autowired
    public ProductList(ProductAdapter adapter, ProductEditor editor) {
        this.adapter = adapter;
        this.editor = editor;

        configureGrid();
        add(grid);
    }


    private void configureGrid() {
        grid.addColumn(createDeleteComponentRenderer()).setWidth("3em").setFlexGrow(0);
        grid.addColumn(Product::getId).setHeader("Код").setKey("code");
        grid.addColumn(Product::getName).setHeader("Наименование");
        grid.addColumn(Product::getNds).setHeader("НДС");
        grid.addColumn(product -> product.getSegment().getName()).setHeader("Сегмент");

        grid.sort(List.of(new GridSortOrder<>(grid.getColumnByKey("code"), ASCENDING)));

    }

    private ComponentRenderer<Div, Product> createDeleteComponentRenderer() {
        return new ComponentRenderer<>(Div::new, (div, product) -> {
            if (product.isDeleted()) {
                div.add(new Icon("lumo", "cross"));
            } else {
                div.add(LumoIcon.CHECKMARK.create());
            }
        });
    }
}
