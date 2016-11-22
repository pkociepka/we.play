package pl.edu.agh.weplay.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * Created by P on 21.11.2016.
 */
@Configuration
public class WebConfigurer implements ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Web application configuration");
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);

        initUrlRewriteProductionFilter(servletContext, disps);

        log.info("Web application fully configured");
    }

    private void initUrlRewriteProductionFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
        log.debug("Registering tuckey UurlRewriteFilter");

        FilterRegistration.Dynamic urlRewriteFilter = servletContext.addFilter("urlRewriteFilter", new UrlRewriteFilter());
        urlRewriteFilter.setInitParameter("confPath", "urlrewrite.xml");
//        urlRewriteFilter.setInitParameter("modRewriteConf", "true");

        urlRewriteFilter.addMappingForUrlPatterns(disps, true, "/*");
    }
}
