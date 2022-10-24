package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author David
 */
@Entity
@Table(name = "depart")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)    
    @Column(name = "dept_no")
    private Short deptNo;
    
    @Column(name = "dnombre")
    private String dnombre;
    
    @Column(name = "loc")    
    private String loc;
    
    @OneToMany(mappedBy = "deptNo")
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
        return "Departamento{" + "deptNo=" + deptNo + ", dnombre=" + dnombre + ", loc=" + loc + ", empleCollection=" + empleCollection + '}';
    }

    

}
