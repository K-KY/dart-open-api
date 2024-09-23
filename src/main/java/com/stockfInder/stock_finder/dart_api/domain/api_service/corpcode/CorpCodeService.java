package com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
            extractCorpCode(file.getPath(), file.getParent());
        }

    }

    private void extractCorpCode(String filePath, String parentPath) {
        try {
            unzip(filePath, parentPath);
            System.out.println("압축 해제 완료!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void unzip(String zipFilePath, String parentPath) throws IOException {

        // ZIP 파일을 읽기 위한 스트림 생성
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry zipEntry = zis.getNextEntry();

        // ZIP 파일의 각 엔트리(파일/디렉토리)를 순회하며 압축 해제
        while (zipEntry != null) {
            String filePath = parentPath + File.separator + zipEntry.getName();
            extractFile(zis, filePath);
            zis.closeEntry();
            zipEntry = zis.getNextEntry();
        }
        zis.close();
    }

    // 파일을 압축 해제하는 메서드
    private static void extractFile(ZipInputStream zis, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zis.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

}
