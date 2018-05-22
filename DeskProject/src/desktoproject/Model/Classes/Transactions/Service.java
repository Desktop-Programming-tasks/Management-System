/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.Classes.Transactions;

import java.time.LocalDate;

/**
 *
 * @author gabriel
 */
public class Service extends Transaction{
    private LocalDate startDate;
    private LocalDate estimatedDate;
    private LocalDate finishDate;
    private String status;
    private String assignedEmployee;
    private ServiceType serviceType;
    
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
    
    @Override
    public String toString() {
        return super.toString()+"Service{" + "startDate=" + startDate + ", estimatedDate=" + estimatedDate + ", finishDate=" + finishDate + ", status=" + status + ", codServ=" + serviceType.getId()+ ", assignedEmployee=" + assignedEmployee + ", name=" + serviceType.getName() + '}';
    }
    
    
}
