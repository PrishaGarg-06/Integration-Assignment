package com.clickfile.ingestiontool.controller;

import com.clickfile.ingestiontool.model.ConnectionRequest;
import com.clickfile.ingestiontool.model.IngestionRequest;
import com.clickfile.ingestiontool.service.ClickHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingestion")
@CrossOrigin(origins = "*")
public class IngestionController {

    @Autowired
    private ClickHouseService clickHouseService;

    // Endpoint to test connection to ClickHouse
    @PostMapping("/connect-clickhouse")
    public ResponseEntity<String> connectClickHouse(@RequestBody ConnectionRequest request) {
        boolean success = clickHouseService.testConnection(request);
        if (success) {
            return ResponseEntity.ok("Connected to ClickHouse!");
        } else {
            return ResponseEntity.badRequest().body("Failed to connect to ClickHouse. Please check your credentials.");
        }
    }

    // Endpoint to retrieve list of tables from ClickHouse
    @PostMapping("/clickhouse-tables")
    public ResponseEntity<List<String>> getClickHouseTables(@RequestBody ConnectionRequest request) {
        List<String> tables = clickHouseService.getTables(request);
        return ResponseEntity.ok(tables);
    }

    // Endpoint to ingest data from flat file to ClickHouse
    @PostMapping("/flatfile-to-clickhouse")
    public ResponseEntity<String> ingestFlatFileToClickHouse(@RequestBody IngestionRequest request) {
        boolean success = clickHouseService.ingestDataFromFlatFile(
            request.getConnectionRequest(), request.getTableName(), request.getFilePath()
        );
        if (success) {
            return ResponseEntity.ok("Data ingestion from flat file to ClickHouse started.");
        } else {
            return ResponseEntity.badRequest().body("Failed to start ingestion. Please check the flat file and table.");
        }
    }


    // Endpoint to export data from ClickHouse to flat file
    @PostMapping("/clickhouse-to-flatfile")
    public ResponseEntity<String> exportClickHouseToFlatFile(@RequestBody IngestionRequest request) {
        boolean success = clickHouseService.exportDataToFlatFile(
            request.getConnectionRequest(), request.getTableName(), request.getFilePath()
        );
        if (success) {
            return ResponseEntity.ok("Data export from ClickHouse to flat file completed.");
        } else {
            return ResponseEntity.badRequest().body("Failed to export data. Please check the table name and path.");
        }
    }
}
