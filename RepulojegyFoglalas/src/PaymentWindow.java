import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


public class PaymentWindow extends CustomComponent {

    @AutoGenerated
    private AbsoluteLayout mainLayout;
    @AutoGenerated
    private VerticalLayout verticalLayout_1;
    @AutoGenerated
    private HorizontalLayout horizontalLayout_1;
    @AutoGenerated
    private Button button_payCancel;
    @AutoGenerated
    private Button button_payOK;
    @AutoGenerated
    private Label label_Payment;

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    /**
     * The constructor should first build the main layout, set the
     * composition root and then do any custom initialization.
     *
     * The constructor will not be automatically regenerated by the
     * visual editor.
     */
    public PaymentWindow() {
        buildMainLayout();
        setCompositionRoot(mainLayout);

        // TODO add user code here
    }

    @AutoGenerated
    private AbsoluteLayout buildMainLayout() {
        // common part: create layout
        mainLayout = new AbsoluteLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("400px");
        mainLayout.setHeight("150px");
        mainLayout.setMargin(false);
        
        // top-level component properties
        setWidth("400px");
        setHeight("150px");
        
        // verticalLayout_1
        verticalLayout_1 = buildVerticalLayout_1();
        mainLayout.addComponent(verticalLayout_1, "left:0.0px;");
        
        return mainLayout;
    }

    @AutoGenerated
    private VerticalLayout buildVerticalLayout_1() {
        // common part: create layout
        verticalLayout_1 = new VerticalLayout();
        verticalLayout_1.setImmediate(false);
        verticalLayout_1.setWidth("100.0%");
        verticalLayout_1.setHeight("100.0%");
        verticalLayout_1.setMargin(true);
        
        // label_Payment
        label_Payment = new Label();
        label_Payment.setImmediate(false);
        label_Payment.setWidth("100.0%");
        label_Payment.setHeight("100.0%");
        verticalLayout_1.addComponent(label_Payment);
        verticalLayout_1.setExpandRatio(label_Payment, 1.0f);
        
        // horizontalLayout_1
        horizontalLayout_1 = buildHorizontalLayout_1();
        verticalLayout_1.addComponent(horizontalLayout_1);
        verticalLayout_1.setExpandRatio(horizontalLayout_1, 1.0f);
        
        return verticalLayout_1;
    }

    @AutoGenerated
    private HorizontalLayout buildHorizontalLayout_1() {
        // common part: create layout
        horizontalLayout_1 = new HorizontalLayout();
        horizontalLayout_1.setImmediate(false);
        horizontalLayout_1.setWidth("100.0%");
        horizontalLayout_1.setHeight("100.0%");
        horizontalLayout_1.setMargin(false);
        horizontalLayout_1.setSpacing(true);
        
        // button_payOK
        button_payOK = new Button();
        button_payOK.setCaption("Button");
        button_payOK.setImmediate(false);
        button_payOK.setWidth("-1px");
        button_payOK.setHeight("-1px");
        horizontalLayout_1.addComponent(button_payOK);
        horizontalLayout_1.setComponentAlignment(button_payOK,
                new Alignment(34));
        
        // button_payCancel
        button_payCancel = new Button();
        button_payCancel.setCaption("Button");
        button_payCancel.setImmediate(false);
        button_payCancel.setWidth("-1px");
        button_payCancel.setHeight("-1px");
        horizontalLayout_1.addComponent(button_payCancel);
        horizontalLayout_1.setComponentAlignment(button_payCancel,
                new Alignment(33));
        
        return horizontalLayout_1;
    }

}
