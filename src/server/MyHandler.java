
package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;

public class MyHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        String method = httpExchange.getRequestMethod();
        
        if (method.equals("GET")) {
            response = handleGet(httpExchange);
        } else {
            response = "Only GET requests are allowed!";
        }
        
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
    }

    private String handleGet(HttpExchange httpExchange) {
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        String name = query != null ? query.substring(query.indexOf("=") + 1) : "Guest";
        
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder
            .append("<html>")
            .append("<body>")
            .append("<h1>")
            .append("Hello ")
            .append(name)
            .append("</h1>")
            .append("</body>")
            .append("</html>");

        return htmlBuilder.toString();
    }
}
