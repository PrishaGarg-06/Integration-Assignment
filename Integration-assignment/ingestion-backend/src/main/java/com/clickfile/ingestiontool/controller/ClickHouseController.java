import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/clickhouse")
public class ClickHouseController {

    private final ClickHouseService clickHouseService;

    public ClickHouseController(ClickHouseService clickHouseService) {
        this.clickHouseService = clickHouseService;
    }

    @GetMapping("/tables")
    public List<String> listTables() throws Exception {
        return clickHouseService.getTables();
    }

    @PostMapping("/ingest")
    public Map<String, Object> ingestData(@RequestBody Map<String, Object> request) throws Exception {
        String table = (String) request.get("table");
        List<String> columns = (List<String>) request.get("columns");
        List<List<String>> rows = (List<List<String>>) request.get("rows");

        List<String[]> data = new ArrayList<>();
        for (List<String> row : rows) {
            data.add(row.toArray(new String[0]));
        }

        int inserted = clickHouseService.ingestData(table, data, columns);
        return Map.of("inserted", inserted, "status", "success");
    }

    @GetMapping("/preview")
    public List<String[]> preview(@RequestParam String table, @RequestParam(defaultValue = "5") int limit) throws Exception {
        return clickHouseService.previewData(table, limit);
    }
}
