package com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.*;

@Component
public class CorpCodeService {


    public void downloadCorpCode(ResponseEntity<Resource> exchange) {
        Resource body = exchange.getBody();
        if (body != null) {
            File file = new File("./src/main/resources/corpCode.zip");
            try (InputStream inputStream = body.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(file)) {
                StreamUtils.copy(inputStream, outputStream);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
