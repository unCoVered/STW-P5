package persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.entities.TemperaturaIntervalosEntity;

@Generated(value="EclipseLink-2.6.0.v20150309-rNA", date="2015-04-04T13:38:53")
@StaticMetamodel(TemperaturaEntity.class)
public class TemperaturaEntity_ { 

    public static volatile SingularAttribute<TemperaturaEntity, Integer> minima;
    public static volatile SingularAttribute<TemperaturaEntity, Date> fecha;
    public static volatile SingularAttribute<TemperaturaEntity, Integer> codigo;
    public static volatile SingularAttribute<TemperaturaEntity, Integer> maxima;
    public static volatile ListAttribute<TemperaturaEntity, TemperaturaIntervalosEntity> temperaturaIntervalos;

}