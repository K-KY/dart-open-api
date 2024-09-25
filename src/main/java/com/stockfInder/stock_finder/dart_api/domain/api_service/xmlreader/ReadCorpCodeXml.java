package com.stockfInder.stock_finder.dart_api.domain.api_service.xmlreader;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity.CorpCode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReadCorpCodeXml {


    private static final String CORP_CODE = "corp_code";
    private static final String CORP_NAME = "corp_name";
    private static final String STOCK_CODE = "stock_code";
    private static final String MODIFY_DATE = "modify_date";
    private static final String DATE_FORMAT = "yyyyMMdd";

    public static List<CorpCode> readCorpCode()  {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse("./src/main/resources/CORPCODE.xml");
            Element element = document.getDocumentElement();
            return parseCorpCode(element);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<CorpCode> parseCorpCode(Element element) {
        NodeList corpCodeList = element.getElementsByTagName(CORP_CODE);
        NodeList corpNameList = element.getElementsByTagName(CORP_NAME);
        NodeList stockCodeList = element.getElementsByTagName(STOCK_CODE);
        NodeList modifyDateList = element.getElementsByTagName(MODIFY_DATE);
        List<CorpCode> corpCodeArr = new ArrayList<>();
        for (int i = 0; i < corpCodeList.getLength(); i++) {
            CorpCode corpCode = new CorpCode(
                    getTag(corpCodeList, i),
                    getTag(corpNameList, i),
                    getTag(stockCodeList, i),
                    parseDate(getTag(modifyDateList, i))
            );
            corpCodeArr.add(corpCode);
        }
        return filterNotListed(corpCodeArr);
    }

    private static String getTag(NodeList nodeList, int index) {
        if (nodeList != null && nodeList.getLength() > index) {
            Node node = nodeList.item(index);
            return node.getTextContent();
        }
        return null;
    }

    private static LocalDate parseDate(String date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, dateFormat);
    }

    private static List<CorpCode> filterNotListed(List<CorpCode> corpCodeArr) {
        return corpCodeArr.stream().filter(corpCode -> corpCode.getStockCode() == null).toList();
    }
}
