package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author David
 */
@Entity
@Table(name = "DEPART")
@NamedQuery(name="Departamento.busquedaPorNombre", query="SELECT d FROM Departamento d WHERE d.dnombre=:dnombre")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id                         // Indica que es clave principal
    @Basic(optional = false)    // Indica que el campo no puede ser nulo  
    @Column(name = "dept_no")   // Nombre en la BD del campo
    private Short deptNo;
    
    @Column(name = "dnombre")
    private String dnombre;
    
    @Column(name = "loc")    
    private String loc;
    
    @OneToMany(mappedBy = "empNo", cascade=CascadeType.ALL)    // Atributo que relaciona en la clase Emple
 //   @JoinColumn(name="emp_no")                                 // Nombre de la columna en la tabla Emple
    private Collection<Empleado> empleCollection;

    public Departamento() {
    }

    public Departamento(Short deptNo) {
        this.deptNo = deptNo;
    }

    public Short getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Short deptNo) {
        this.deptNo = deptNo;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Collection<Empleado> getEmpleCollection() {
        return empleCollection;
    }

    public void setEmpleCollection(Collection<Empleado> empleCollection) {
        this.empleCollection = empleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptNo != null ? deptNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.deptNo == null && other.deptNo != null) || (this.deptNo != null && !this.deptNo.equals(other.deptNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       return this.deptNo + "\t" + this.dnombre + "\t\t" + this.loc;
    }

    

}
