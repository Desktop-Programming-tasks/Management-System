/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.objects.transations;

import java.util.Date;

/**
 *
 * @author gabriel
 */
public class Service extends Transation{
    private Date startDate;
    private Date estimatedDate;
    private Date finishDate;
    private String status;
    private int codServ;
    private String assignedEmployee;
    private String name;

    public Service(String name, int id) {
        super(id);
        this.name = name;
    }
    
    public Service(Date startDate, Date estimatedDate, Date finishDate, String status, int codServ, String assignedEmployee, String name, int id) {
        super(id);
        this.startDate = startDate;
        this.estimatedDate = estimatedDate;
        this.finishDate = finishDate;
        this.status = status;
        this.codServ = codServ;
        this.assignedEmployee = assignedEmployee;
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCodServ() {
        return codServ;
    }

    public void setCodServ(int codServ) {
        this.codServ = codServ;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(String assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
