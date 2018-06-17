/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Transactions;

import Classes.Enums.ServiceStatus;
import Classes.Persons.Person;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gabriel
 */
public class Service extends Transaction implements Serializable {

    private Date startDate;
    private Date estimatedDate;
    private Date finishDate;
    private ServiceStatus status;
    private Person assignedEmployee;
    private ServiceType serviceType;

    public Service(Date startDate, Date estimatedDate, ServiceStatus status, Person assignedEmployee, ServiceType serviceType) {
        super(serviceType.getPrice(), serviceType.getName());
        super.setQuantity(1);
        this.startDate = startDate;
        this.estimatedDate = estimatedDate;
        this.status = status;
        this.assignedEmployee = assignedEmployee;
        this.serviceType = serviceType;

    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(Date estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public Person getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Person assignedEmployee) {
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
        return "Service{" + "startDate=" + startDate + ", estimatedDate=" + estimatedDate + ", finishDate=" + finishDate + ", status=" + status + ", assignedEmployee=" + assignedEmployee + ", serviceType=" + serviceType + '}';
    }
    
}
