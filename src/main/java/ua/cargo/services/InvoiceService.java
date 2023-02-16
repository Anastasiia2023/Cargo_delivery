package ua.cargo.services;
;
import ua.cargo.dto.InvoiceDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.ServiceException;

import java.io.ByteArrayOutputStream;


public interface InvoiceService extends CargoService<InvoiceDTO>{
    void add(InvoiceDTO invoiceDTO, String locale) throws ServiceException;

    ByteArrayOutputStream getPDFInvoice(InvoiceDTO invoiceDTO) throws ServiceException;

    ByteArrayOutputStream getPDFInvoice(InvoiceDTO invoiceDTO, UserDTO userDTO) throws ServiceException;
}
