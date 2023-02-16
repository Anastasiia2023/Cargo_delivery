package ua.cargo.services.impl;

import com.itextpdf.kernel.colors.*;
import com.itextpdf.kernel.geom.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.borders.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;

import ua.cargo.dto.InvoiceDTO;
import ua.cargo.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.ServiceException;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import java.util.List;

public class PdfService {
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

    public PdfService() {
    }

    public ByteArrayOutputStream createOrdersPdf(ServletContext servletContext, List<OrderDTO> orders, String locale) {
        ResourceBundle resourceBundle = getBundle(locale);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Document document = getDocument(output, servletContext);
        document.add(getTableTitle(resourceBundle.getString(ORDER_TITLE).toUpperCase()));
        document.add(LINE_SEPARATOR);
        document.add(getOrderTable(orders, resourceBundle));
        document.close();
        return output;
    }

    public void createInvoicePdf(OrderDTO order, UserDTO user, InvoiceDTO invoice, String locale) throws ServiceException {
        String filename = invoice.getOrderId() + "_" + invoice.getDate().toString() + ".pdf";
        invoice.setPath("invoice/" + filename);
        ResourceBundle resourceBundle = getBundle(locale);
        try {
            Document document = getDocument(invoice.getPath());
            document.add(getTableTitle(resourceBundle.getString(INVOICE_TITLE).toUpperCase()));
            document.add(LINE_SEPARATOR);
            document.add(getInvoiceTable(user, invoice, resourceBundle));
            document.add(getTableTitle(resourceBundle.getString(ORDER_TITLE).toUpperCase()));
            document.add(LINE_SEPARATOR);
            document.add(getOrderTable(Collections.singletonList(order), resourceBundle));
            document.close();
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    public ByteArrayOutputStream readInvoicePdf(String filePath) throws ServiceException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            output.writeBytes(fis.readAllBytes());
            fis.close();
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return output;
    }

    private Document getDocument(ByteArrayOutputStream output, ServletContext servletContext) {
        PdfWriter writer = new PdfWriter(output);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());
//        PdfFont font = getPdfFont(servletContext);
//        if (font != null) {
//            document.setFont(font);
//        }
        return document;
    }

    private Document getDocument(String path) throws IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        file.createNewFile();
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdf = new PdfDocument(writer);
        return new Document(pdf, PageSize.A4.rotate());
    }

//    private Document readDocument(ByteArrayOutputStream output) throws IOException {
//        PdfReader reader = new PdfReader("");
//        PdfWriter writer = new PdfWriter(output);
//        PdfDocument documentPdf = new PdfDocument(reader, writer);
//        return new Document(documentPdf, PageSize.A4.rotate());
//        output.writeBytes(reader.);
//    }

    private static Paragraph getTableTitle(String tableTitle) {
        return new Paragraph(new Text(tableTitle))
                .setFontSize(TITLE_SIZE)
                .setTextAlignment(TextAlignment.CENTER);
    }

    private Table getOrderTable(List<OrderDTO> orders, ResourceBundle resourceBundle) {
        Table table = new Table(new float[]{8, 8, 8, 8, 8, 8});
        table.setWidth(UnitValue.createPercentValue(100));
        addTableHeader(table, ORDER_CELLS, resourceBundle);
        addOrderTableRows(table, orders);
        return table;
    }

    private Table getInvoiceTable(UserDTO user, InvoiceDTO invoice, ResourceBundle resourceBundle) {
        Table table = new Table(new float[]{30, 30, 30});
        table.setWidth(UnitValue.createPercentValue(100));
        addTableHeader(table, INVOICE_CELLS, resourceBundle);
        addInvoiceTableRows(table, user, invoice);
        return table;
    }

    private void addTableHeader(Table table, String[] cells, ResourceBundle resourceBundle) {
        Stream.of(cells)
                .forEach(columnTitle -> {
                    Cell header = new Cell();
                    header.setBackgroundColor(LIGHT_BLUE);
                    header.setBorder(new SolidBorder(1));
                    header.add(new Paragraph(resourceBundle.getString(columnTitle)));
                    table.addCell(header);
                });
    }

    private void addOrderTableRows(Table table, List<OrderDTO> orders) {
        orders.forEach(order ->
                {
                    table.addCell(String.valueOf(order.getId()));
                    table.addCell(order.getCityFrom() + "-" + order.getCityTo());
                    table.addCell(order.getCreationDate().toString());
                    table.addCell(order.getEstimatedDeliveryDate().toString());
                    table.addCell(order.getStatus());
                    table.addCell(String.valueOf(order.getPrice()));
                }
        );
    }

    private void addInvoiceTableRows(Table table, UserDTO userDTO, InvoiceDTO invoiceDTO) {
        table.addCell(String.format(USER_INFO_INVOICE, userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPhone()));
        table.addCell(String.format(BANK_INFO_INVOICE));
        table.addCell(String.format(INVOICE_INFO, invoiceDTO.getId(), invoiceDTO.getDate().toString()));
    }

//    private PdfFont getPdfFont(ServletContext servletContext) {
//        try {
//            URL resource = servletContext.getResource(FONT);
//            String fontUrl = resource.getFile();
//            return PdfFontFactory.createFont(fontUrl);
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//            return null;
//        }
//    }

    private ResourceBundle getBundle(String locale) {
        String resources = "resources";
        if (locale.contains("_")) {
            String[] splitLocale = locale.split("_");
            return ResourceBundle.getBundle(resources, new Locale(splitLocale[0], splitLocale[1]));
        } else {
            return ResourceBundle.getBundle(resources, new Locale(locale));
        }
    }
}