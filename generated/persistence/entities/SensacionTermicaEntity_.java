package persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.entities.SensacionTermicaIntervalosEntity;

@Generated(value="EclipseLink-2.6.0.v20150309-rNA", date="2015-04-04T13:38:53")
@StaticMetamodel(SensacionTermicaEntity.class)
public class SensacionTermicaEntity_ { 

    public static volatile SingularAttribute<SensacionTermicaEntity, Integer> minima;
    public static volatile SingularAttribute<SensacionTermicaEntity, Date> fecha;
    public static volatile ListAttribute<SensacionTermicaEntity, SensacionTermicaIntervalosEntity> sensacionIntervalos;
    public static volatile SingularAttribute<SensacionTermicaEntity, Integer> codigo;
    public static volatile SingularAttribute<SensacionTermicaEntity, Integer> maxima;

}