package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.eltex.accountsystem.model.Table;
import ru.eltex.accountsystem.service.TableService;

@Service
public class TableController {
    @Autowired
    private TableService tableService;

    @PostMapping("/teacher/groupId")
    public void saveTable(@PathVariable String groupId, @RequestBody Table table) {
        tableService.saveTable(table, groupId);
    }
}
