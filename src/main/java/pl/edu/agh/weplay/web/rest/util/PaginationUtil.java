package pl.edu.agh.weplay.web.rest.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by P on 18.10.2016.
 */
public class PaginationUtil {

    public static HttpHeaders generatePaginationHttpHeaders(Page<?> page, String baseUrl)
            throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + page.getTotalElements());
        String link = "";
        if ((page.getNumber() + 1) < page.getTotalPages()) {
            link = "<" + (new URI(baseUrl + "?page=" + (page.getNumber() + 1) + "&size=" + page.getSize())).toString() + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getNumber()) > 0) {
            link += "<" + (new URI(baseUrl + "?page=" + (page.getNumber() - 1) + "&size=" + page.getSize())).toString() + ">; rel=\"prev\",";
        }
        // last and first link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += "<" + (new URI(baseUrl + "?page=" + lastPage + "&size=" + page.getSize())).toString() + ">; rel=\"last\",";
        link += "<" + (new URI(baseUrl + "?page=" + 0 + "&size=" + page.getSize())).toString() + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }
}
