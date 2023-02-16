package ua.cargo.entities;

import java.util.ArrayList;
import java.util.Date;

public class Report {
    private static final long serialVersionUID = 1L;
    private long id;
    private ArrayList <Route> routes;
    private Date dateFrom;
    private Date dateTo;
    private String filePath;

    public Report(long id, ArrayList<Route> routes, Date dateFrom, Date dateTo, String filePath) {
        this.id = id;
        this.routes = routes;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.filePath = filePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
