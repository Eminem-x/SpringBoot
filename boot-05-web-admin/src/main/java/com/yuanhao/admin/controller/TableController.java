package com.yuanhao.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yuanhao
 */
@Controller
public class TableController {

    @GetMapping("/basicTable")
    public String basicTable() {
        return "table/basic_table";
    }

    @GetMapping("/advancedTable")
    public String dynamicTable() {
        return "table/dynamic_table";
    }

    @GetMapping("/responsiveTable")
    public String responsiveTable() {
        return "table/responsive_table";
    }

    @GetMapping("/editTable")
    public String editableTable() {
        return "table/editable_table";
    }
}
