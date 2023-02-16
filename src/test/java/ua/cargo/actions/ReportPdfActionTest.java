package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.OrderDTO;
import ua.cargo.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cargo.services.OrderService;
import ua.cargo.services.impl.PdfService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.utils.FilterUtil.getOrderFilterQueryString;

public class ReportPdfActionTest {
}
