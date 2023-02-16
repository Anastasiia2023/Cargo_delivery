package ua.cargo.filters.domain;

import ua.cargo.entities.enums.Role;

import java.util.Set;

public class Domain {
    private final String servletPath;
    private final String action;
    private Set<String> domainPages;
    private Set<String> domainActions;

    private Domain(String servletPath, String action) {
        this.servletPath = servletPath;
        this.action = action;
        setDomains();
    }

    private Domain(String servletPath, String action, Integer role) {
        this.servletPath = servletPath;
        this.action = action;
        setDomains(role);
    }

    public static Domain getDomain(String servletPath, String action) {
        return new Domain(servletPath, action);
    }

    public static Domain getDomain(String servletPath, String action, Integer role) {
        return new Domain(servletPath, action, role);
    }

    private void setDomains() {
        domainPages = DomainPagesSets.getAnonymousUserPages();
        domainActions = DomainActionsSets.getAnonymousUserActions();
    }

    private void setDomains(Integer role) {
        Role roleValue = Role.getRole(role);
        switch (roleValue) {
            case SUPERADMIN:
                domainPages = DomainPagesSets.getSuperadminPages();
                domainActions = DomainActionsSets.getSuperadminActions();
                break;
            case CUSTOMER:
                domainPages = DomainPagesSets.getCustomerPages();
                domainActions = DomainActionsSets.getCustomerActions();
                break;
            case MANAGER:
                domainPages = DomainPagesSets.getManagerPages();
                domainActions = DomainActionsSets.getManagerActions();
                break;
            default:
                domainPages = DomainPagesSets.getAnonymousUserPages();
                domainActions = DomainActionsSets.getAnonymousUserActions();
        }
    }

    public boolean checkAccess() {
        return !checkPages() || !checkActions();
    }

    private boolean checkPages() {
        if (servletPath != null) {
            return domainPages.contains(servletPath.substring(1));
        }
        return true;
    }

    private boolean checkActions() {
        if (action != null) {
            return domainActions.contains(action);
        }
        return true;
    }
}