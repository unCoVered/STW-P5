package persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20150309-rNA", date="2015-04-04T13:38:53")
@StaticMetamodel(VientoEntity.class)
public class VientoEntity_ { 

    public static volatile SingularAttribute<VientoEntity, Date> fecha;
    public static volatile SingularAttribute<VientoEntity, Integer> codigo;
    public static volatile SingularAttribute<VientoEntity, String> periodo;
    public static volatile SingularAttribute<VientoEntity, String> direccion;
    public static volatile SingularAttribute<VientoEntity, Integer> velocidad;

}