/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Classes.Transactions;

import deskprojectserver.Enums.ServiceStatus;
import java.time.LocalDate;

/**
 *
 * @author gabriel
 */
public class Service extends Transaction{
    private LocalDate startDate;
    private LocalDate estimatedDate;
    private LocalDate finishDate;
    private ServiceStatus status;
    private String assignedEmployee;
    private ServiceType serviceType;

    public Service(LocalDate startDate, LocalDate estimatedDate, ServiceStatus status, String assignedEmployee, ServiceType serviceType) {
        super(serviceType.getPrice(), serviceType.getName());
        this.startDate = startDate;
        this.estimatedDate = estimatedDate;
        this.status = status;
        this.assignedEmployee = assignedEmployee;
        this.serviceType = serviceType;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(LocalDate estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(String assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}