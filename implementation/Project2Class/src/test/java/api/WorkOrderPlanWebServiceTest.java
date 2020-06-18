/*package api;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class WorkOrderPlanWebServiceTest {




 /*   @Test
    public void readAll() {
    }

    @Test
    public void find() throws ClientProtocolException, IOException {
       /* String id = RandomStringUtils.randomNumeric(1);
        HttpUriRequest request = new HttpGet( "http://localhost:8080/webapi/myresource/workorderplan/1" + id );
        HttpResponse httpResponse = (HttpResponse) HttpClientBuilder.create().build().execute( request );
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NOT_FOUND));

        var actual = httpResponse.getStatusLine().getStatusCode();
        var exp = HttpStatus.SC_NOT_FOUND;
        assertEquals(actual, exp);

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        //assertThat(response.getStatusLine().getStatusCode(), equals(HttpStatus.SC_NOT_FOUND));

        var actual = response.getStatusLine().getStatusCode();
        var exp = HttpStatus.SC_NOT_FOUND;
        assertEquals(actual, exp);

        //assertThat(response.getStatusLine().getStatusCode(), equals(HttpStatus.SC_NOT_FOUND));*/
   // }

   /* @Test
    public void save() {
    }
}*/