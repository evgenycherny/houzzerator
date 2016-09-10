package com.e3.houzzerator.datascrapper;

import com.e3.houzzerator.datascrapper.download.datamodel.Header;
import com.e3.houzzerator.datascrapper.download.datamodel.Range;
import com.e3.houzzerator.datascrapper.download.RestScanner;
import com.e3.houzzerator.datascrapper.download.datamodel.ScanParameter;
import com.e3.houzzerator.datascrapper.download.datamodel.ScanTemplate;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  etshiorny
 * Date:    6/25/16.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private RestScanner restScanner;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        logArgs(args);
        CommandLine cli = parseArguments(args);


        runScan();
        //String inputFile = cli.getOptionValue("i");
        //IScannerBuilder builder = new XmlBasedScannerBuilder(new File(inputFile));
        //RestScanner scanner = builder.build();
        //scanner.scan();
    }

    private void logArgs(String[] args) {
        logger.debug("List of arguments");
        for (String arg: args) logger.debug(arg);
    }

    private CommandLine parseArguments(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("i",true, "input");
        return parser.parse(options, args);
    }

    private void runScan() {
        ScanTemplate template = new ScanTemplate();
        template.setMethod("GET");
        template.setUrl("http://localhost:8080/api/v1/accounts/SKKNT7LdLspV66dN7A%5FV");
        template.getHeaders().add(buildHeader("Authorization","Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJhcGkuamNwZW5uZXkuY29tIiwiaWF0IjoxNDY3MDQxNTA4MjQ1LCJhdWQiOiJqY3Blbm5leS5jb20iLCJhdXRob3JpdGllcyI6WyJhY2NvdW50X3VzZXJfYWNjZXNzIl0sImV4cCI6MjkzNDA4MzAxNn0.l1VkBZM5bycdZA-agzJSnpZn8GbrvBs-VOWzsqwQEU6KDa8BBPg8PHWoQUnUVpFigMI19H2YslfMdXWRe4zN0WPJRBCfRIVAQrwisB79dsvPZQfj-MssRaMIUsI7d9cMxkva9qXg17eDFfmN1NbZgNbX7uDQpjgFYeappykA9XCv42QFUQA7PkECAX_XDx2s26cpYPWYWt8wZpGAyztXSLOOUoTsKF3vw6EkBtB25qzTrz_UD0jm0XZL38da7nmIR8aAJ7qIbyeD_4dDRZo2pFzXcZZHUZnt2ix7pFN7LutzyU0_vTE7ZJSMZokReu4W9H6mBnumdo6ucdRUkwsZwg"));
        template.getHeaders().add(buildHeader("Content-Type","application/json"));

        restScanner.setScanTemplate(template);
        restScanner.scan();
    }

    private Header buildHeader(String key, String value) {
        Header header = new Header();
        header.setKey(key);
        header.setValue(value);
        return header;
    }

    private void runScan2() {
        ScanTemplate template = new ScanTemplate();
        template.setUrl("http://example.com/api/get/${id}");
        List<ScanParameter> params = new ArrayList<>();
        params.add(new ScanParameter("id", new Range(1,2)));
        template.setParameters(params);

        restScanner.setScanTemplate(template);
        restScanner.scan();
    }
}
