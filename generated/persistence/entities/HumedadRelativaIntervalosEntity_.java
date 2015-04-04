package persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.entities.HumedadRelativaEntity;

@Generated(value="EclipseLink-2.6.0.v20150309-rNA", date="2015-04-04T13:38:53")
@StaticMetamodel(HumedadRelativaIntervalosEntity.class)
public class HumedadRelativaIntervalosEntity_ { 

    public static volatile SingularAttribute<HumedadRelativaIntervalosEntity, Date> fecha;
    public static volatile SingularAttribute<HumedadRelativaIntervalosEntity, Integer> codigo;
    public static volatile SingularAttribute<HumedadRelativaIntervalosEntity, String> hora;
    public static volatile SingularAttribute<HumedadRelativaIntervalosEntity, HumedadRelativaEntity> humedadRelativa;
    public static volatile SingularAttribute<HumedadRelativaIntervalosEntity, Integer> valorHora;

}