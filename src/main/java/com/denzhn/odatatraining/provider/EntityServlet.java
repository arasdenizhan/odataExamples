package com.denzhn.odatatraining.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.commons.api.edmx.EdmxReference;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Slf4j
@Component
public class EntityServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
        try {
            HttpSession session = req.getSession(true);
            DataProvider dataProvider = (DataProvider) session.getAttribute(DataProvider.class.getName());
            if (dataProvider == null) {
                dataProvider = new DataProvider();
                session.setAttribute(DataProvider.class.getName(), dataProvider);
                log.info("Created new data provider.");
            }
            OData odata = OData.newInstance();
            ServiceMetadata edm = odata.createServiceMetadata(new EntitiesEdmProvider(), new ArrayList<EdmxReference>());
            ODataHttpHandler handler = odata.createHandler(edm);
            handler.register(new EntitiesProcessor(dataProvider));
            handler.process(req, resp);
        } catch (RuntimeException e) {
            log.info("server error: " + e);
            throw new ServletException(e);
        }
    }
}
