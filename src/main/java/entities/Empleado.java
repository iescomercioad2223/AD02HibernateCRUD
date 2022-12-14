/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author David
 */

@Entity                 // Los objetos de esta clase queremos que sean persistentes
@Table(name = "EMPLE")  // Nombre de la Tabla en la BD
@NamedQueries ({        // Nos definimos dos consultas preparadas
    @NamedQuery(name="Empleado.busquedaPorNombre", query="SELECT e FROM Empleado e WHERE e.apellido=:apellido"), 
    @NamedQuery(name="Empleado.buscarTodos", query="SELECT e FROM Empleado e") 
})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "emp_no")
    private Short empNo;
    
    @Column(name = "apellido")
    private String apellido;
    
    @Column(name = "comision")
    private Float comision;
    
    @Column(name = "dir")
    private Short dir;
    
    @Column(name = "fecha_alt")
    @Temporal(TemporalType.DATE) // Debe indicarse Date y Calendar
    private Date fechaAlt;
    
    @Column(name = "oficio")
    private String oficio;
    
    @Column(name = "salario")
    private Float salario;
    
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no") // referencedColumnName campo en la tabla que es la foreign key en EMPLE
    @ManyToOne // Muchos empleados a 1 departamento
    private Departamento deptNo;

    public Empleado() {
    }

    public Empleado(Short empNo) {
        this.empNo = empNo;
    }

    public Short getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Short empNo) {
        this.empNo = empNo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

    public Short getDir() {
        return dir;
    }

    public void setDir(Short dir) {
        this.dir = dir;
    }

    public Date getFechaAlt() {
        return fechaAlt;
    }

    public void setFechaAlt(Date fechaAlt) {
        this.fechaAlt = fechaAlt;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public Departamento getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Departamento deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empNo != null ? empNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.empNo == null && other.empNo != null) || (this.empNo != null && !this.empNo.equals(other.empNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return empNo + "\t" + apellido + "\t" + comision + "\t" + dir + "\t" + fechaAlt + "\t" + oficio + "\t" + salario + "\t" + deptNo + '\t';
    }

  
    
}
