package ua.cargo.entities;

import java.util.ArrayList;
import java.util.List;

import static ua.cargo.actions.constants.ParameterValues.ASCENDING_ORDER;

public class Pagination {
    private final List<String> filters = new ArrayList<>();
    private String sortField;
    private String order = ASCENDING_ORDER;
    private int offset = 0;
    private int records = 5;

    public List<String> getFilters() {
        return filters;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }
}
