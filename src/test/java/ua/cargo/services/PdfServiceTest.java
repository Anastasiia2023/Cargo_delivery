package ua.cargo.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.itextpdf.kernel.colors.*;
import com.itextpdf.kernel.geom.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.borders.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;

import ua.cargo.dao.OrderDAO;
import ua.cargo.dto.InvoiceDTO;
import ua.cargo.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.impl.OrderServiceImpl;
import ua.cargo.services.impl.PdfService;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class PdfServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(PdfService.class);
    private static final String FONT = "fonts/arial.ttf";
    private static final Color LIGHT_BLUE = new DeviceRgb(227, 242, 253);
    private static final int TITLE_SIZE = 20;
    private static final Paragraph LINE_SEPARATOR = new Paragraph(new Text("\n"));
    private static final String ORDER_TITLE = "orders";
    private static final String[] ORDER_CELLS = new String[]{"id", "route", "creation.date", "estimate.delivery.terms", "status", "delivery.cost"};

    private static final String INVOICE_TITLE = "invoice";

    private static final String[] INVOICE_CELLS = new String[]{"bill.to", "ship.to", "invoice"};

    private static final String USER_INFO_INVOICE = "%s %s\n %s\n %s";
    private static final String BANK_INFO_INVOICE = "CARGO BANK INTERNATIONAL\n IBAN 123400000321412441";
    private static final String INVOICE_INFO = "INVOICE ID: %s\n INVOICE DATE: %s";
    private PdfService pdfService;

    @Before
    public void init() {

        pdfService = new PdfService();
    }
    @Test
    public void createOrdersPdfTest(){
        long id = 1L;
        OrderDTO order = new OrderDTO(20,40,id, 500, "Cargo", id);
        List<OrderDTO> orderDTO = List.of(order);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


    }

}
