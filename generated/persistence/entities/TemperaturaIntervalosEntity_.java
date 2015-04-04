package persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.entities.TemperaturaEntity;

@Generated(value="EclipseLink-2.6.0.v20150309-rNA", date="2015-04-04T13:38:53")
@StaticMetamodel(TemperaturaIntervalosEntity.class)
public class TemperaturaIntervalosEntity_ { 

    public static volatile SingularAttribute<TemperaturaIntervalosEntity, Date> fecha;
    public static volatile SingularAttribute<TemperaturaIntervalosEntity, Integer> codigo;
    public static volatile SingularAttribute<TemperaturaIntervalosEntity, String> hora;
    public static volatile SingularAttribute<TemperaturaIntervalosEntity, Integer> valorHora;
    public static volatile SingularAttribute<TemperaturaIntervalosEntity, TemperaturaEntity> temperatura;

}